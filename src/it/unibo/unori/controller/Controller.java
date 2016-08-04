package it.unibo.unori.controller;

import java.io.IOException;

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

    /**
     * Saves the game.
     * @throws IOException 
     */
    void saveGame() throws IOException;

    /**
     * Loads a previously saved game; this includes the Party object and, if present, the GameStatistics object.
     * @throws IOException 
     */
    void loadGame() throws IOException;
    
    void newGame() throws IOException;

    void setParty();
}
