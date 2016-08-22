package it.unibo.unori;

import java.io.InputStream;

/**
 * Utility class.
 *
 */
public class ResourceLoader {
    /**
     * Loader from path to InputStream.
     * @param path
     *          path to load
     * 
     * @return
     *      InputStream.
     */
    public static InputStream load(final String path) {
        InputStream input = ResourceLoader.class.getResourceAsStream(path);
        if (path.startsWith("res/")) {
            input = ResourceLoader.class.getResourceAsStream(path.substring(3));
        }
        if (input == null) {
            input = ResourceLoader.class.getResourceAsStream("/" + path);
        }
        return input;
    }
}
