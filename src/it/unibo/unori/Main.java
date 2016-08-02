package it.unibo.unori;

import it.unibo.unori.controller.Controller;
import it.unibo.unori.controller.SingletonStateMachine;

/**
 * This is the main class, the one that is launched at start, and the one that instantiates and starts the controller.
 */
public final class Main {
    private Main() {
        // Empty constructor
    }

    /**
     * First method to start.
     * 
     * @param args
     *            parameters not used
     */
    public static void main(final String... args) {
        final Controller c = SingletonStateMachine.getController();

        c.begin();
    }

}
