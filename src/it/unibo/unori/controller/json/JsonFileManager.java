package it.unibo.unori.controller.json;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unibo.unori.controller.GameStatisticsImpl;
import it.unibo.unori.controller.json.deserializers.CharacterDeserializer;
import it.unibo.unori.controller.json.deserializers.DialogueDeserializer;
import it.unibo.unori.controller.json.deserializers.FoeDeserializer;
import it.unibo.unori.controller.json.deserializers.FoeSquadDeserializer;
import it.unibo.unori.controller.json.deserializers.ItemDeserializer;
import it.unibo.unori.controller.json.deserializers.PartyDeserializer;
import it.unibo.unori.controller.json.serializer.ArmorSerializer;
import it.unibo.unori.controller.json.serializer.BagSerializer;
import it.unibo.unori.controller.json.serializer.CellSerializer;
import it.unibo.unori.controller.json.serializer.GameMapSerializer;
import it.unibo.unori.controller.json.serializer.HeroSerializer;
import it.unibo.unori.controller.json.serializer.HeroTeamSerializer;
import it.unibo.unori.controller.json.serializer.ItemSerializer;
import it.unibo.unori.controller.json.serializer.MagicAttackSerializer;
import it.unibo.unori.controller.json.serializer.NpcSerializer;
import it.unibo.unori.controller.json.serializer.PositionSerializer;
import it.unibo.unori.controller.json.serializer.PotionSerializer;
import it.unibo.unori.controller.json.serializer.WeaponSerializer;
import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.Npc;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.Position;
import it.unibo.unori.model.maps.cell.Cell;
import it.unibo.unori.model.menu.DialogueInterface;

/**
 * This class models a clean boundary between Google Gson library and the needs of this project, providing methods to
 * serialize and deserialize from JSON files instances of Party, GameStatistics and JsonJobsParamethers.
 */
public class JsonFileManager {
    /**
     * Static parameter for standard JSON object serialization save file.
     */
    public static final String SAVE_FILE = "res/Save.json";

    /**
     * Static parameter for standard JSON object serialization statistics file.
     */
    public static final String STATS_FILE = "res/Stats.json";

    private final Gson gson;

    /**
     * Default constructor.
     */
    public JsonFileManager() {
        gson = new GsonBuilder()/* .enableComplexMapKeySerialization() */.setPrettyPrinting()
                        .registerTypeAdapter(Item.class, new ItemSerializer())
                        // .registerTypeAdapter(Item.class, new ItemDeserializer())
                        .registerTypeAdapter(Armor.class, new ArmorSerializer())
                        // .registerTypeAdapter(Armor.class, new ArmorDeserializer())
                        .registerTypeAdapter(Weapon.class, new WeaponSerializer())
                        // .registerTypeAdapter(Weapon.class, new WeaponDeserializer())
                        .registerTypeAdapter(Potion.class, new PotionSerializer())
                        // .registerTypeAdapter(Potion.class, new PotionDeserializer())
                        .registerTypeAdapter(Bag.class, new BagSerializer())
                        // .registerTypeAdapter(Bag.class, new BagDeserializer())
                        .registerTypeAdapter(MagicAttackInterface.class, new MagicAttackSerializer())
                        // .registerTypeAdapter(MagicAttackInterface.class, new MagicAttackDeserializer())
                        .registerTypeAdapter(Character.class, new CharacterDeserializer())
                        .registerTypeAdapter(Foe.class, new FoeDeserializer())
                        .registerTypeAdapter(FoeSquad.class, new FoeSquadDeserializer())
                        .registerTypeAdapter(Hero.class, new HeroSerializer())
                        // .registerTypeAdapter(Hero.class, new HeroDeserializer())
                        .registerTypeAdapter(HeroTeam.class, new HeroTeamSerializer())
                        // .registerTypeAdapter(HeroTeam.class, new HeroTeamDeserializer())
                        .registerTypeAdapter(Npc.class, new NpcSerializer())
                        // .registerTypeAdapter(Npc.class, new NpcDeserializer())
                        .registerTypeAdapter(DialogueInterface.class, new DialogueDeserializer())
                        .registerTypeAdapter(Position.class, new PositionSerializer())
                        // .registerTypeAdapter(Position.class, new GameMapDeserializer.PositionDeserializer())
                        .registerTypeAdapter(Cell.class, new CellSerializer())
                        // .registerTypeAdapter(Cell.class, new GameMapDeserializer.CellDeserializer())
                        .registerTypeAdapter(GameMap.class, new GameMapSerializer())
                        // .registerTypeAdapter(GameMap.class, new GameMapDeserializer())
                        .registerTypeAdapter(Party.class, new PartyDeserializer()).create();
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
    public Party loadGameFromPath(final String path) throws IOException {
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
    public Party loadGame() throws IOException {
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
     *             if a security manager exists and its checkWrite method denies write access to the file
     * @throws JsonIOException
     *             if there was a problem writing to the writer
     */
    public void saveGameToPath(final Party party, final String path) throws IOException {
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
     *             if a security manager exists and its checkWrite method denies write access to the file
     * @throws JsonIOException
     *             if there was a problem writing to the writer
     */
    public void saveGame(final Party party) throws IOException {
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
    public GameStatisticsImpl loadStatsFromPath(final String path) throws IOException {
        return deserializeJSON(GameStatisticsImpl.class, path);
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
    public GameStatisticsImpl loadStats() throws IOException {
        return loadStatsFromPath(STATS_FILE);
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
    public void saveStatsToPath(final GameStatisticsImpl stats, final String path) throws IOException {
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
    public void saveStats(final GameStatisticsImpl stats) throws IOException {
        saveStatsToPath(stats, STATS_FILE);
    }

    /**
     * This method saves a JsonJobParameter object containing all the default parameters of a specific Job.
     * 
     * @param job
     *            the object to serialize
     * @param path
     *            the path where to find the JSON file
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
    public void saveJob(final JsonJobParameter job, final String path) throws IOException {
        // System.out.println(this.gson.toJson(job));

        this.serializeJSON(job, path);
    }

    /**
     * This method returns a JsonJobParameter object containing all the default parameters of a specific Job.
     * 
     * @param path
     *            the path where to find the JSON file
     * @return the deserialized JsonJobParameter object
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
    public JsonJobParameter loadJob(final String path) throws IOException {
        return this.deserializeJSON(JsonJobParameter.class, path);
    }

    public void saveFoe(final JsonFoeParameters foe, final String path) throws IOException {
        this.serializeJSON(foe, path);
    }

    public JsonFoeParameters loadFoe(final String path) throws IOException {
        return this.deserializeJSON(JsonFoeParameters.class, path);
    }

    public void saveMap(final GameMap map, final String path) throws IOException {
        this.serializeJSON(map, path);
    }

    /**
     * This method loads from a file in a specified path a JSON-serialized GameMap.
     * 
     * @param path
     *            the path where to find the file
     * @return the map serialized on the specified file
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
    public GameMap loadMap(final String path) throws IOException {
        return this.deserializeJSON(GameMap.class, path);
    }

    public void saveItem(final Item item, final String path) throws IOException {
        this.serializeJSON(item, path);
    }

    public Item loadItem(final String path) throws IOException {
        return this.deserializeJSON(Item.class, path);
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
    private void serializeJSON(final Object objectToSerialize, final String path) throws IOException {
        final Writer writer = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
        gson.toJson(objectToSerialize, writer);
        writer.close();
    }

    /**
     * This method loads a file from a given path and returns a single object from what reads. It is private because
     * tested only with the classes needed here. It does not work with Collections and Maps.
     * 
     * @param <T>
     *            the type of the object serialized on the file
     * @param clazz
     *            the type of the object serialized on the file; it needs to be specified because sometimes it can't
     *            parse the correct type automatically
     * @param path
     *            the path where to find the JSON file
     * @return the serialized object
     * @throws IllegalArgumentException
     *             if passed a Collection or a Map
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
    private <T> T deserializeJSON(final Class<T> clazz, final String path) throws IOException {
        Optional<T> returnObject = Optional.empty();

        if (Collection.class.isAssignableFrom(clazz) || Map.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("The " + clazz.toString() + " is not deserializable by this method");
        }

        final Reader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
        returnObject = Optional.ofNullable(gson.fromJson(reader, clazz));
        reader.close();

        return returnObject.orElseThrow(() -> new JsonIOException("The file provided is corrupted or non valid"));
    }
}
