package it.unibo.unori.controller;

/**
 * This is the interface for the main controller, started by the main object and that models the control of the basics
 * of the game.
 */
public interface Controller {

    /**
     * Starts the main controller, so the game begins.
     */
    void begin();

    /**
     * Starts counting the time played.
     */
    void startTimer();
}
