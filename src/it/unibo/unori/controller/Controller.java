package it.unibo.unori.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unibo.unori.controller.exceptions.NotValidStateException;
import it.unibo.unori.controller.state.GameState;

/**
 * This is the interface for the main controller, started by the main object and
 * that models the control of the basics of the game.
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
     * 
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file exists but is a directory rather than a regular
     *             file, does not exist but cannot be created, or cannot be
     *             opened for any other reason
     * @throws SecurityException
     *             if a security manager exists and its checkWrite method denies
     *             write access to the file
     * @throws JsonIOException
     *             if there was a problem writing to the writer
     */
    void saveGame() throws IOException;

    /**
     * Loads a previously saved game; this includes the Party object and, if
     * present, the GameStatistics object. On the stack the method will push a
     * correctly set MapState.
     * 
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file exists but is a directory rather than a regular
     *             file, does not exist but cannot be created, or cannot be
     *             opened for any other reason
     * @throws SecurityException
     *             if a security manager exists and its checkWrite method denies
     *             write access to the file
     * @throws JsonIOException
     *             if there was a problem writing to the writer
     */
    void loadGame() throws IOException;

    /**
     * Starts a new game. On the stack the method will push a new
     * CharacterSelectionState.
     * 
     * @throws IOException
     *             if an error occurs
     * @throws SecurityException
     *             if a security manager exists and it denies read access to the
     *             file or directory
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a
     *             regular file, or for some other reason cannot be opened for
     *             reading.
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an
     *             object of type
     */
    void newGame() throws IOException;
    
    GameState getCurrentState();
    
    Class<?> getCurrentStateClass();

    void setParty();
    
    void openMenu() throws NotValidStateException;

    void closeMenu() throws NotValidStateException;

}
