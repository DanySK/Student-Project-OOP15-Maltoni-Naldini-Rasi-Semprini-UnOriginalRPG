package it.unibo.unori.controller.utility;

import java.io.InputStream;

/**
 * This utility class provides a working file loading everywhere in the project,
 * and both in source running and jar running.
 */
public final class ResourceLoader {
    private ResourceLoader() {
        // Empty private constructor
    }

    /**
     * Static method that wraps class.getResourceAsStream() method to return an
     * input stream form a specified path.
     * <p>
     * It corrects automatically paths specifying resource default folder
     * (starting with "res") or without "/" starting separator.
     * 
     * @param path
     *            the path to the file
     * @return A InputStream object or null if no resource with this name is found
     */
    public static InputStream load(final String path) {
        InputStream input = ResourceLoader.class.getResourceAsStream(path);
        String correctPath = path;
        if (correctPath.startsWith("res/")) {
            correctPath = path.substring(3);
            input = ResourceLoader.class.getResourceAsStream(correctPath);
        }

        if (input == null) {
            correctPath = new StringBuilder("/").append(correctPath).toString();
            input = ResourceLoader.class.getResourceAsStream(correctPath);
        }

        return input;
    }
}
