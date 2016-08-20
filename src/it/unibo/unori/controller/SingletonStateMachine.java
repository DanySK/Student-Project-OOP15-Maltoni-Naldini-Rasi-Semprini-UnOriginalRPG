package it.unibo.unori.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unibo.unori.controller.exceptions.UnexpectedStateException;
import it.unibo.unori.controller.json.JsonFileManager;
import it.unibo.unori.controller.json.MapType;
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
import it.unibo.unori.model.maps.WorldBuilder.MAPS;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;
import it.unibo.unori.view.layers.CharacterSelectionLayer;

/**
 * This class manages an implementation of
 * {@link it.unibo.controller.Controller} interface that matches the Singleton
 * pattern. It is a final class in order to not be extended.
 */
public final class SingletonStateMachine {
    private static Controller singletonController;

    private SingletonStateMachine() {
        // Empty constructor to match Singleton pattern
    }

    /**
     * Method to get the controller instance inside the class. Multiple
     * allocations are prevented also in a multi-thread system.
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
     * This class implements the {@link it.unibo.controller.Controller}
     * interface. It's declared private for encapsulation purpose: the only way
     * to use an instance of this class is by the method
     * {@link SingletonStateMachine#getController()} of the
     * SingletonStateMachine class.
     */
    private static final class StateMachineImpl implements Controller {
        private static final String UNEXPECTED_ERROR = "Unexpected error";
        private final StateMachineStack stack;
        private final GameStatistics stats;
        private final JsonFileManager fileManager;
        private final WorldLoader loader;

        /**
         * This default constructor instantiates a new StateMachine controller
         * class, adding a new
         * {@link it.unibo.unori.Controller.StateMachineStack} with a new
         * {@link it.unibo.unori.controller.state.MainMenuState} pushed at the
         * top. It also counts time, but the timer needs to be started. It is a
         * final class because it has no need to be extendible.
         */
        StateMachineImpl() {
            this.stack = new StateMachineStackImpl();
            this.stats = new GameStatisticsImpl();
            this.fileManager = new JsonFileManager();
            this.loader = new WorldLoader();
        }

        /**
         * {@inheritDoc} This is done by pushing a new MainMenuState and
         * updating and rendering it.
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
            this.fileManager.saveParty(SingletonParty.getParty());
            this.fileManager.saveMapType(SingletonParty.getParty().getCurrentGameMap());
            this.loader.serializeMaps(false);
        }

        @Override
        public void loadGame() throws IOException {
            this.restoreStatsIfNeeded();

            this.loader.loadWorld();
            SingletonParty.loadParty(this.fileManager.loadParty());
            final MapType type = this.fileManager.loadMapType();
            if (type.getMapType().equals(MAPS.DUNGEON)) {
                SingletonParty.getParty().setCurrentMap(
                        this.loader.getBuilder().getDungeonBuilder().getFloor(type.getFloor()).get(type.getRoom()));
            } else {
                SingletonParty.getParty().setCurrentMap(this.loader.getBuilder().getGameMap(type.getMapType()));
            }
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
                        this.showDialog(e.getMessage(), ErrorSeverity.SERIUOS);
                    }
                });

                try {
                    SingletonParty.getParty().setCurrentMap(loader.loadWorld());
                    SingletonParty.getParty()
                            .setFrame(SingletonParty.getParty().getHeroTeam().getAllHeroes().get(0).getBattleFrame());
                    this.stack.pushAndRender(new MapState(SingletonParty.getParty().getCurrentGameMap()));
                    this.startTimer();
                } catch (IOException e) {
                    this.showDialog(e.getMessage(), ErrorSeverity.SERIUOS);
                }

            } else {
                this.showDialog(new UnexpectedStateException().getMessage(), ErrorSeverity.SERIUOS);
            }

        }

        /**
         * If the statistics file exists from previous plays, it should be
         * loaded. This method does that.
         * 
         * @throws IOException
         *             if an error occurs
         * @throws SecurityException
         *             if a security manager exists and it denies read access to
         *             the file or directory
         * @throws FileNotFoundException
         *             if the file does not exist, is a directory rather than a
         *             regular file, or for some other reason cannot be opened
         *             for reading.
         * @throws JsonIOException
         *             if there was a problem reading from the Reader
         * @throws JsonSyntaxException
         *             if the file does not contain a valid representation for
         *             an object of type
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
        public void openMenu() throws UnexpectedStateException {
            if (MapState.class.isInstance(this.stack.peek())) {
                this.stack.pushAndRender(new InGameMenuState());
            } else {
                throw new UnexpectedStateException();
            }
        }

        @Override
        public void closeMenu() throws UnexpectedStateException {
            if (!this.stack.isEmpty() && InGameMenuState.class.isInstance(this.stack.peek())) {
                this.stack.pop();
                // this.stack.render(); //TODO here I should NOT render
            } else {
                throw new UnexpectedStateException();
            }
        }

        @Override
        public void closeGame() {
            this.stack.closeTheView();
        }

        @Override
        public StateMachineStack getStack() {
            return this.stack;
        }

        @Override
        public GameStatistics getStatistics() {
            return this.stats;
        }

        @Override
        public void showError(final String error) {
            if (error == null) {
                this.showDialog(UNEXPECTED_ERROR, ErrorSeverity.SERIUOS);
            } else {
                this.showDialog(error, ErrorSeverity.SERIUOS);
            }

        }

        @Override
        public void showCommunication(final String communication) {
            if (communication == null) {
                this.showDialog(UNEXPECTED_ERROR, ErrorSeverity.SERIUOS);
            } else {
                this.showDialog(communication, ErrorSeverity.MINOR);
            }
        }

        private void showDialog(final String error, final ErrorSeverity severity) {
            this.stack.pushAndRender(new DialogState(error, severity));
        }

        @Override
        public void startBattle(final List<Foe> foes) {
            final Party partyObject = SingletonParty.getParty();
            try {
                this.stack.pushAndRender(
                        new BattleState(partyObject.getHeroTeam(), new FoeSquadImpl(foes), partyObject.getPartyBag()));
            } catch (SpriteNotFoundException | IllegalArgumentException e) {
                this.showError(e.getMessage());
            }
            this.stats.increaseMonstersMet(foes.size());
        }

        @Override
        public WorldLoader getLoader() {
            return this.loader;
        }

    }
}
