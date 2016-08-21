package it.unibo.unori;

import java.io.InputStream;

public class ResourceLoader {
    public static InputStream load(final String path) {
        InputStream input = ResourceLoader.class.getResourceAsStream(path);
        if (input == null) {
            input = ResourceLoader.class.getResourceAsStream("/" + path);
        }
        return input;
    }
}
