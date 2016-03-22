package it.unibo.unori;

import it.unibo.unori.controller.Controller;
import it.unibo.unori.controller.StateMachine;

public class Main {

    public static void main(final String... args) {
        final Controller c = new StateMachine();

        c.begin();
    }

}
