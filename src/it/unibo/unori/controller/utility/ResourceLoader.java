package it.unibo.unori.controller.utility;

import java.io.IOException;
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
     * For compatibility purpose, it corrects automatically paths specifying resource default folder
     * (starting with "res").
     * 
     * @param path
     *            the path to the file
     * @return A InputStream object or null if no resource with this name is
     *         found
     * @throws IOException 
     */
    public static InputStream load(final String path) {
        String correctPath = path;
        
        // This is for compatibility purpose
        if (correctPath.startsWith("res/")) {
            correctPath = correctPath.substring(3);
        }

        return ResourceLoader.class.getResourceAsStream(correctPath);
    }
}
