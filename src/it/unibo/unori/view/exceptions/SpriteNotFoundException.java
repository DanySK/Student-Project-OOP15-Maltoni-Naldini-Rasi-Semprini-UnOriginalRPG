package it.unibo.unori.view.exceptions;

import java.io.FileNotFoundException;

/**
 *
 * This class is an exception thrown if the sprite file is not found.
 *
 */
public class SpriteNotFoundException extends FileNotFoundException {
    private static final String DEFAULT_MESSAGE = "Sprite not found";

    /**
     * Creates this exception.
     */
    public SpriteNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructor that let the developer specify which action command was not
     * recognized.
     *
     * @param path
     *            the path of the sprite.
     */
    public SpriteNotFoundException(final String path) {
        super(DEFAULT_MESSAGE + " (" + path + ")");
    }
}
