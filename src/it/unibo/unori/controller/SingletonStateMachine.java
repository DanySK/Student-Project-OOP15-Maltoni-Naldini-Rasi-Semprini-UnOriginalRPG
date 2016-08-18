package it.unibo.unori.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unibo.unori.controller.exceptions.NotValidStateException;
import it.unibo.unori.controller.json.JsonFileManager;
import it.unibo.unori.controller.json.WorldLoader;
import it.unibo.unori.controller.state.BattleState;
import it.unibo.unori.controller.state.CharacterSelectionState;
import it.unibo.unori.controller.state.DialogState;
import it.unibo.unori.controller.state.DialogState.ErrorSeverity;
import it.unibo.unori.controller.state.GameState;
import it.unibo.unori.controller.state.InGameMenuState;
import it.unibo.unori.controller.state.MainMenuState;
import it.unibo.unori.controller.state.MapState;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeSquadImpl;
import it.unibo.unori.model.character.HeroImpl;
import it.unibo.unori.model.character.exceptions.MaxHeroException;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.SingletonParty;
import it.unibo.unori.view.layers.CharacterSelectionLayer;

/**
 * This class manages an implementation of {@link it.unibo.controller.Controller} interface that matches the Singleton
 * pattern. It is a final class in order to not be extended.
 */
public final class SingletonStateMachine {
    private static Controller singletonController;

    private SingletonStateMachine() {
        // Empty constructor to match Singleton pattern
    }

    /**
     * Method to get the controller instance inside the class. Multiple allocations are prevented also in a multi-thread
     * system.
     * 
     * @return the single instance of Controller created.
     */
    public static Controller getController() {
        synchronized (SingletonStateMachine.class) {
            if (singletonController == null) {
                singletonController = new StateMachineImpl();
            }
        }
        return singletonController;
    }

    /**
     * This class implements the {@link it.unibo.controller.Controller} interface. It's declared private for
     * encapsulation purpose: the only way to use an instance of this class is by the method
     * {@link SingletonStateMachine#getController()} of the SingletonStateMachine class.
     */
    private static final class StateMachineImpl implements Controller {
        private final StateMachineStack stack;
        private final GameStatistics stats;
        private final JsonFileManager fileManager;

        /**
         * This default constructor instantiates a new StateMachine controller class, adding a new
         * {@link it.unibo.unori.Controller.StateMachineStack} with a new
         * {@link it.unibo.unori.controller.state.MainMenuState} pushed at the top. It also counts time, but the timer
         * needs to be started. It is a final class because it has no need to be extendible.
         */
        StateMachineImpl() {
            this.stack = new StateMachineStackImpl();
            this.stats = new GameStatisticsImpl();
            this.fileManager = new JsonFileManager();
        }

        /**
         * {@inheritDoc} This is done by pushing a new MainMenuState and updating and rendering it.
         */
        @Override
        public void begin() {
            stack.pushAndRender(new MainMenuState());
        }

        @Override
        public void startTimer() {
            this.stats.startCountingTime();
        }

        @Override
        public void saveGame() throws IOException {
            this.fileManager.saveGame(SingletonParty.getParty());
            // TODO need to be tested: maybe is better to get the map from
            // MapState instance
        }

        @Override
        public void loadGame() throws IOException {
            this.restoreStatsIfNeeded();

            SingletonParty.loadParty(this.fileManager.loadGame());
            final GameState loadedGame = new MapState(SingletonParty.getParty().getCurrentGameMap());

            this.stack.pushAndRender(loadedGame);
        }

        @Override
        public void newGame() throws IOException {
            this.restoreStatsIfNeeded(); // TODO check
            this.stack.pushAndRender(new CharacterSelectionState());
        }

        @Override
        public void startGame() {
            if (CharacterSelectionLayer.class.isInstance(this.stack.peek().getLayer())) {
                final Map<String, Jobs> selected = ((CharacterSelectionLayer) this.stack.pop().getLayer()).getParty();
                selected.entrySet().forEach(entry -> {
                    try {
                        SingletonParty.getParty().getHeroTeam().addHero(new HeroImpl(entry.getKey(), entry.getValue()));
                    } catch (MaxHeroException | IllegalArgumentException e) {
                        this.showError(e.getMessage(), ErrorSeverity.SERIUOS);
                    }
                });

                try {
                    final WorldLoader loader = new WorldLoader();
                    // final WorldBuilder builder = new WorldBuilder();
                    SingletonParty.getParty().setCurrentMap(loader.loadWorld()/* builder.buildWorld() */);
                    /*final Map<CardinalPoints, String> framesMap = new HashMap<>();
                    for (final CardinalPoints cp : CardinalPoints.values()) {
                        framesMap.put(cp,
                                        SingletonParty.getParty().getHeroTeam().getAllHeroes().get(0).getBattleFrame());
                    }
                    SingletonParty.getParty().setFrames(framesMap);*/
                    this.stack.pushAndRender(new MapState(SingletonParty.getParty().getCurrentGameMap()));
                    this.startTimer();
                } catch (IOException e) {
                    this.showError(e.getMessage(), ErrorSeverity.SERIUOS);
                }

            } else {
                this.showError(new NotValidStateException().getMessage(), ErrorSeverity.SERIUOS);
            }

        }

        /**
         * If the statistics file exists from previous plays, it should be loaded. This method does that.
         * 
         * @throws IOException
         *             if an error occurs
         * @throws SecurityException
         *             if a security manager exists and it denies read access to the file or directory
         * @throws FileNotFoundException
         *             if the file does not exist, is a directory rather than a regular file, or for some other reason
         *             cannot be opened for reading.
         * @throws JsonIOException
         *             if there was a problem reading from the Reader
         * @throws JsonSyntaxException
         *             if the file does not contain a valid representation for an object of type
         */
        private void restoreStatsIfNeeded() throws IOException {
            if (new File(JsonFileManager.STATS_FILE).exists()) {
                this.stats.restore(this.fileManager.loadStats());
            }
        }

        @Override
        public GameState getCurrentState() {
            return this.stack.peek();
        }

        @Override
        public void openMenu() throws NotValidStateException {
            if (MapState.class.isInstance(this.stack.peek())) {
                this.stack.pushAndRender(new InGameMenuState());
            } else {
                throw new NotValidStateException();
            }
        }

        @Override
        public void closeMenu() throws NotValidStateException {
            if (InGameMenuState.class.isInstance(this.stack.peek())) {
                this.stack.pop();
                this.stack.render();
            } else {
                throw new NotValidStateException();
            }
        }

        @Override
        public void closeGame() {
            this.stack.closeTheView();
            // System.exit(0);
        }

        @Override
        public StateMachineStack getStack() {
            return this.stack;
        }

        @Override
        public void showError(final String error, final ErrorSeverity severity) {
            this.stack.pushAndRender(new DialogState(error, severity));
        }

        @Override
        public void startBattle(final List<Foe> foes) {
            final Party partyObject = SingletonParty.getParty();
            this.stack.pushAndRender(new BattleState(partyObject.getHeroTeam(), new FoeSquadImpl(foes),
                            partyObject.getPartyBag()));
            this.stats.increaseMonstersMet(foes.size());
        }

    }
}
