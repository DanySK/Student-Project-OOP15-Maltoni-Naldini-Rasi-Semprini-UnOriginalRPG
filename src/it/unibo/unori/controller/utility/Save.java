package it.unibo.unori.controller.utility;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.controller.GameStatistics;
import it.unibo.unori.controller.exceptions.CorruptedUtilityFileException;
import it.unibo.unori.model.maps.Party;

/**
 * Utility class that provides static methods for load/save data from/to file.
 */
public final class Save {
    /**
     * Static parameter for standard JSON object serialization save file.
     */
    private static final String SAVE_FILE = "res/Save.json";

    /**
     * Static parameter for standard JSON object serialization statistics file.
     */
    private static final String STATS_FILE = "res/Stats.json";

    private Save() {
        // Empty private constructor, because this is an utility class
    }

    /**
     * The method restores a previously saved game from a given-path file.
     * 
     * @param path
     *            the path where of the file
     * @return the Party object, which is the de facto savegame
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a regular file, or for some other reason
     *             cannot be opened for reading
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a regular file, or for some other reason
     *             cannot be opened for reading
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an object of type
     */
    public static Party loadGameFromPath(final String path) throws IOException {
        return deserializeJSON(path);
    }

    /**
     * The method restores a previously saved game from the default save file.
     * 
     * @return the Party object, which is the de facto savegame
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a regular file, or for some other reason
     *             cannot be opened for reading
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a regular file, or for some other reason
     *             cannot be opened for reading
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an object of type
     */
    public static Party loadGame() throws IOException {
        return loadGameFromPath(SAVE_FILE);
    }

    /**
     * This method saves the state of the game by serializing the Party object (containing position on map, statistics,
     * etc...) and the time played. It saves in a given-path file.
     * 
     * @param party
     *            the party object
     * @param path
     *            the path of the file
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file exists but is a directory rather than a regular file, does not exist but cannot be
     *             created, or cannot be opened for any other reason
     * @throws SecurityException
     *             if a security manager exists and its checkWrite method denies write access to the file.
     * @throws JsonIOException
     *             if there was a problem writing to the writer
     */
    public static void saveGameToPath(final Party party, final String path) throws IOException {
        serializeJSON(party, path);
    }

    /**
     * This method saves the state of the game by serializing the Party object (containing position on map, statistics,
     * etc...) and the time played. It saves in default path.
     * 
     * @param party
     *            the party object
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file exists but is a directory rather than a regular file, does not exist but cannot be
     *             created, or cannot be opened for any other reason
     * @throws SecurityException
     *             if a security manager exists and its checkWrite method denies write access to the file.
     * @throws JsonIOException
     *             if there was a problem writing to the writer
     */
    public static void saveGame(final Party party) throws IOException {
        saveGameToPath(party, SAVE_FILE);
    }

    /**
     * The method restores a previously saved game statistics object. If the file is not found, returns a new
     * {@link it.unibo.unori.controller.GameStatistics.java}, with all values set to 0.
     * 
     * @return the GameStatistics object
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a regular file, or for some other reason
     *             cannot be opened for reading.
     * @throws IOException
     *             if an error occurs
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an object of type
     */
    public static GameStatistics loadStats() throws IOException {
        GameStatistics returnObj;
        try {
            returnObj = deserializeJSON(STATS_FILE);
        } catch (FileNotFoundException e) {
            returnObj = new GameStatistics();
        }

        return returnObj;
    }

    /**
     * This method saves the statistics of the game. It saves in default path.
     * 
     * @param stats
     *            the object which contains the game statistics
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file exists but is a directory rather than a regular file, does not exist but cannot be
     *             created, or cannot be opened for any other reason
     * @throws SecurityException
     *             if a security manager exists and its checkWrite method denies write access to the file
     * @throws JsonIOException
     *             if there was a problem writing to the writer
     */
    public static void saveStats(final GameStatistics stats) throws IOException {
        serializeJSON(stats, STATS_FILE);
    }

    /**
     * This method serializes on a file in a given path a single object.
     * 
     * @param objectToSerialize
     *            the object you want to serialize on the given file
     * @param path
     *            the path where to find or create the JSON file
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file exists but is a directory rather than a regular file, does not exist but cannot be
     *             created, or cannot be opened for any other reason
     * @throws SecurityException
     *             if a security manager exists and its checkWrite method denies write access to the file
     * @throws JsonIOException
     *             if there was a problem writing to the writer
     */
    public static void serializeJSON(final Object objectToSerialize, final String path) throws IOException {
        Gson gson = new GsonBuilder().create();
        Writer writer = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
        gson.toJson(objectToSerialize, writer);
        writer.close();
    }

    /**
     * This method loads a file from a given path and returns a single object from what reads.
     * 
     * @param <T>
     *            the type of the object serialized on the file
     * @param path
     *            the path where to find the JSON file
     * @return the serialized object
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a regular file, or for some other reason
     *             cannot be opened for reading
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an object of type
     */
    public static <T> T deserializeJSON(final String path) throws IOException {
        Type type = new TypeToken<T>() {
        }.getType();
        Optional<T> returnObject = Optional.empty();
        Gson gson = new GsonBuilder().create();

        Reader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
        returnObject = Optional.ofNullable(gson.fromJson(reader, type));
        reader.close();

        return returnObject.orElseThrow(() -> new JsonIOException("The file provided is corrupted or non valid"));
    }
}
