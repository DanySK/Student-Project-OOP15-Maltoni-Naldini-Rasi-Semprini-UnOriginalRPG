package it.unibo.unori;

import it.unibo.unori.controller.Controller;
import it.unibo.unori.controller.StateMachine;

/**
 * This is the main class, the one that is launched at start, and the one that
 * instantiates and starts the controller.
 */
public class Main {
    /**
     * First method to start.
     * 
     * @param args
     *            parameters not used
     */
    public static void main(final String... args) {
        final Controller c = new StateMachine();

        c.begin();
    }

}
