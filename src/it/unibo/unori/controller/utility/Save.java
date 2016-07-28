package it.unibo.unori.controller.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.controller.GameStatistics;
import it.unibo.unori.controller.TimeCounter;
import it.unibo.unori.controller.TimeCounterImpl;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.ArmorImpl;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponImpl;
import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.SingletonParty;

/**
 * Utility class that provides static methods for load/save data from/to file.
 */
public final class Save {
    /**
     * Static parameter for standard JSON object serialization save file.
     */
    public static final String SAVE_FILE = "res/Save.json";

    /**
     * Static parameter for standard JSON object serialization statistics file.
     */
    public static final String STATS_FILE = "res/Stats.json";

    private Save() {
        // Empty private constructor, because this is an utility class
    }

    /**
     * The method restores a previously saved game from a given-path file.
     * 
     * @param path
     *            the path of the file
     * @return the Party object, which is the de facto savegame
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
        return deserializeJSON(Party.class, path);
    }

    /**
     * The method restores a previously saved game from the default save file.
     * 
     * @return the Party object, which is the de facto savegame
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
     * The method restores a previously saved game statistics object from a given-path file.
     * 
     * @param path
     *            the path of the file
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
    public static GameStatistics loadStatsFromPath(final String path) throws IOException {
        return deserializeJSON(GameStatistics.class, path);
    }

    /**
     * The method restores a previously saved game statistics object from default path.
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
        return Save.loadStatsFromPath(STATS_FILE);
    }

    /**
     * This method saves the statistics of the game. It saves in default path.
     * 
     * @param stats
     *            the object which contains the game statistics
     * @param path
     *            the path of the file
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
    public static void saveStatsToPath(final GameStatistics stats, final String path) throws IOException {
        serializeJSON(stats, path);
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
        Save.saveStatsToPath(stats, STATS_FILE);
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
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final Writer writer = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
        gson.toJson(objectToSerialize, writer);
        writer.close();
    }
    
    public static <T> T deserializeJSON(final String path) throws IOException {
        
        Type type = new TypeToken<T>() { }.getType();
        
        Optional<T> returnObject = Optional.empty();
        final Gson gson = new GsonBuilder().registerTypeAdapter(Armor.class, new InstanceCreator<Armor>() {
            @Override
            public Armor createInstance(final Type type) {
                return ArmorImpl.NAKED;
            }
        }).registerTypeAdapter(Party.class, new InstanceCreator<Party>() {
            @Override
            public Party createInstance(final Type type) {
                return SingletonParty.getParty();
            }
        }).registerTypeAdapter(Weapon.class, new InstanceCreator<Weapon>() {
            @Override
            public Weapon createInstance(final Type type) {
                return WeaponImpl.FISTS;
            }
        }).registerTypeAdapter(TimeCounter.class, new InstanceCreator<TimeCounter>() {
            @Override
            public TimeCounter createInstance(final Type type) {
                return new TimeCounterImpl();
            }
        }).create();

        final Reader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
        returnObject = Optional.ofNullable(gson.fromJson(reader, type));
        reader.close();

        return returnObject.orElseThrow(() -> new JsonIOException("The file provided is corrupted or non valid"));
    }

    /**
     * This method loads a file from a given path and returns a single object from what reads.
     * 
     * @param <T>
     *            the type of the object serialized on the file
     * @param c
     *            the type of the object serialized on the file; it needs to be specified because sometimes it can't
     *            parse the correct type automatically
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
    public static <T> T deserializeJSON(final Class<T> c, final String path) throws IOException {
        /*
         * Type type = new TypeToken<T>() { }.getType();
         */
        Optional<T> returnObject = Optional.empty();
        final Gson gson = new GsonBuilder().registerTypeAdapter(Armor.class, new InstanceCreator<Armor>() {
            @Override
            public Armor createInstance(final Type type) {
                return ArmorImpl.NAKED;
            }
        }).registerTypeAdapter(Party.class, new InstanceCreator<Party>() {
            @Override
            public Party createInstance(final Type type) {
                return SingletonParty.getParty();
            }
        }).registerTypeAdapter(Weapon.class, new InstanceCreator<Weapon>() {
            @Override
            public Weapon createInstance(final Type type) {
                return WeaponImpl.FISTS;
            }
        }).registerTypeAdapter(TimeCounter.class, new InstanceCreator<TimeCounter>() {
            @Override
            public TimeCounter createInstance(final Type type) {
                return new TimeCounterImpl();
            }
        }).create();

        final Reader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
        returnObject = Optional.ofNullable(gson.fromJson(reader, /* type */c));
        reader.close();

        return returnObject.orElseThrow(() -> new JsonIOException("The file provided is corrupted or non valid"));
    }

    // Maybe not useless
    public static <T> T deserializeJSON(final Class<T> c, final Gson gson, final String path) throws IOException {
        /*
         * Type type = new TypeToken<T>() { }.getType();
         */

        Optional<T> returnObject = Optional.empty();

        final Reader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
        returnObject = Optional.ofNullable(gson.fromJson(reader, c));
        reader.close();

        return returnObject.orElseThrow(() -> new JsonIOException("The file provided is corrupted or non valid"));
    }
}
