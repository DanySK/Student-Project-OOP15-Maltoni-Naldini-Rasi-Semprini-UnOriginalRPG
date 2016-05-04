package it.unibo.unori.controller.utility;

/**
 * This exception is thrown if exist something (like a directory) that makes impossible to create an utility file (like
 * the SaveGame) because of the name and the path are the same.
 */
public class DefaultPathTakenException extends Exception {

    /**
     * Eclipse-generated serialVersionUID
     */
    private static final long serialVersionUID = -3434639821740137181L;

}
