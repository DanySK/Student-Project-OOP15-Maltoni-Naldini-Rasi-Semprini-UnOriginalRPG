package it.unibo.unori.controller;

import java.io.File;
import java.io.IOException;

import it.unibo.unori.controller.json.JsonFileManager;
import it.unibo.unori.controller.state.GameState;
import it.unibo.unori.controller.state.MainMenuState;
import it.unibo.unori.controller.state.MapState;
import it.unibo.unori.model.maps.SingletonParty;

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
         * needs to be started. It is a final class because it has no need to be extendable.
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
            // TODO
            stack.push(new MainMenuState());
            stack.render();
        }

        @Override
        public void startTimer() {
            this.stats.startCountingTime();
        }

        @Override
        public void saveGame() throws IOException {
            this.fileManager.saveGame(SingletonParty.getParty());
            // TODO need to be tesed: maybe is better to get it from MapState instance
        }

        @Override
        public void loadGame() throws IOException {
            /*
             * If the game is already started, it shouldn't change the current GameStatistics; if not, if the file
             * exists from previous plays, it should be loaded.
             */
            if (!this.stack.isGameReallyStarted() && new File(JsonFileManager.STATS_FILE).exists()) {
                this.stats.restore(this.fileManager.loadStats());
            }

            SingletonParty.loadParty(this.fileManager.loadGame());
            final GameState loadedGame = new MapState(SingletonParty.getParty().getCurrentGameMap());

            // If the game is already started, it should be removed to reload it.
            while (this.stack.isGameReallyStarted()) {
                this.stack.pop(); // TODO this cycle needs to be tested
            }

            this.stack.push(loadedGame);
            this.stack.render();
        }

    }
}
