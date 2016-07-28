package it.unibo.unori.controller;

import java.io.File;

import it.unibo.unori.controller.state.MainMenuState;
import it.unibo.unori.controller.utility.Save;

/**
 *
 */
public class StateMachine implements Controller {
    private final StateMachineStack stack;
    private final GameStatistics stats;

    /**
     * This default constructor instantiates a new StateMachine controller class, adding a new
     * {@link it.unibo.unori.Controller.StateMachineStack} with a new
     * {@link it.unibo.unori.controller.state.MainMenuState} pushed at the top. It incorporates a TimeCounter, but needs
     * to be started.
     */
    public StateMachine() {
        this.stack = new StateMachineStackImpl();
        this.stats = new GameStatisticsImpl();
        final File file = new File(Save.STATS_FILE);

        if (file.exists() && file.isFile()) {
            try {
                this.stats.restore(Save.loadStats());
            } catch (Exception e) {
                e.printStackTrace(); // TODO
            }
        }
    }

    /**
     * {@inheritDoc} This is done by pushing a new MainMenuState and updating and rendering it.
     */
    @Override
    public void begin() {
        // TODO
        stack.push(new MainMenuState());
        stack.update(0);
        stack.render();
    }

    @Override
    public void startTimer() {
        this.stats.startCountingTime();
    }

}
