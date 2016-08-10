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
import it.unibo.unori.controller.json.deserializers.ArmorDeserializer;
import it.unibo.unori.controller.json.deserializers.BagDeserializer;
import it.unibo.unori.controller.json.deserializers.CharacterDeserializer;
import it.unibo.unori.controller.json.deserializers.DialogueDeserializer;
import it.unibo.unori.controller.json.deserializers.FoeDeserializer;
import it.unibo.unori.controller.json.deserializers.FoeSquadDeserializer;
import it.unibo.unori.controller.json.deserializers.GameMapDeserializer;
import it.unibo.unori.controller.json.deserializers.HeroDeserializer;
import it.unibo.unori.controller.json.deserializers.HeroTeamDeserializer;
import it.unibo.unori.controller.json.deserializers.ItemDeserializer;
import it.unibo.unori.controller.json.deserializers.MagicAttackDeserializer;
import it.unibo.unori.controller.json.deserializers.NpcDeserializer;
import it.unibo.unori.controller.json.deserializers.PartyDeserializer;
import it.unibo.unori.controller.json.deserializers.PotionDeserializer;
import it.unibo.unori.controller.json.deserializers.WeaponDeserializer;
import it.unibo.unori.controller.json.serializer.ArmorSerializer;
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
import it.unibo.unori.model.menu.Dialogue;
import it.unibo.unori.model.menu.DialogueInterface;

/**
 * This class models a clean boundary between Google Gson library and the needs
 * of this project, providing methods to serialize and deserialize from JSON
 * files instances of Party, GameStatistics and JsonJobsParamethers.
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

    public final Gson gson;

    /**
     * Default constructor.
     */
    public JsonFileManager() {
        /*
         * final InstanceCreator<Weapon> weaponDeserialize = (type) -> new
         * WeaponImpl(); final InstanceCreator<Party> partyDeserialize = (type)
         * -> SingletonParty.getParty(); final InstanceCreator<Potion>
         * potionDeserialize = (type) -> new PotionFactory().getStdPotion();
         * final InstanceCreator<Bag> bagDeserialize = (type) -> new BagImpl();
         * final InstanceCreator<Item> itemDeserialize = (type) -> { if
         * (type.getClass().isAssignableFrom(Armor.class)) { return
         * ArmorImpl.NAKED; } else if
         * (type.getClass().isAssignableFrom(Weapon.class)) { return
         * WeaponImpl.FISTS; } else if
         * (type.getClass().isAssignableFrom(Potion.class)) { return new
         * PotionFactory().getStdPotion(); } else { return new ItemImpl("", "");
         * } }; final InstanceCreator<GameMap> mapDeserialize = (type) -> new
         * GameMapImpl();
         */

        gson = new GsonBuilder()/*.enableComplexMapKeySerialization()*/.setPrettyPrinting()
                .registerTypeAdapter(Item.class, new ItemDeserializer())
                .registerTypeAdapter(Armor.class, new ArmorSerializer())
                .registerTypeAdapter(Armor.class, new ArmorDeserializer())
                .registerTypeAdapter(Weapon.class, new WeaponSerializer())
                .registerTypeAdapter(Weapon.class, new WeaponDeserializer())
                .registerTypeAdapter(Potion.class, new PotionDeserializer())
                .registerTypeAdapter(Bag.class, new BagDeserializer())
                .registerTypeAdapter(MagicAttackInterface.class, new MagicAttackDeserializer())
                .registerTypeAdapter(Character.class, new CharacterDeserializer())
                .registerTypeAdapter(Foe.class, new FoeDeserializer())
                .registerTypeAdapter(FoeSquad.class, new FoeSquadDeserializer())
                .registerTypeAdapter(Hero.class, new HeroDeserializer())
                .registerTypeAdapter(HeroTeam.class, new HeroTeamDeserializer())
                .registerTypeAdapter(Npc.class, new NpcDeserializer())
                .registerTypeAdapter(DialogueInterface.class, new DialogueDeserializer()) // TODO
                                                                                          // not
                                                                                          // sure
                                                                                          // what
                                                                                          // to
                                                                                          // keep
                .registerTypeAdapter(Dialogue.class, new DialogueDeserializer()) // TODO
                                                                                 // not
                                                                                 // sure
                                                                                 // what
                                                                                 // to
                                                                                 // keep
                .registerTypeAdapter(Position.class, new GameMapDeserializer.PositionDeserializer())
                .registerTypeAdapter(Cell.class, new GameMapDeserializer.CellDeserializer())
                .registerTypeAdapter(GameMap.class, new GameMapDeserializer())
                .registerTypeAdapter(Party.class, new PartyDeserializer())
                .registerTypeAdapter(Position.class, new GameMapDeserializer.PositionDeserializer()).create();
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
     *             if the file does not exist, is a directory rather than a
     *             regular file, or for some other reason cannot be opened for
     *             reading
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an
     *             object of type
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
     *             if the file does not exist, is a directory rather than a
     *             regular file, or for some other reason cannot be opened for
     *             reading
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an
     *             object of type
     */
    public Party loadGame() throws IOException {
        return loadGameFromPath(SAVE_FILE);
    }

    /**
     * This method saves the state of the game by serializing the Party object
     * (containing position on map, statistics, etc...) and the time played. It
     * saves in a given-path file.
     * 
     * @param party
     *            the party object
     * @param path
     *            the path of the file
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
    public void saveGameToPath(final Party party, final String path) throws IOException {
        serializeJSON(party, path);
    }

    /**
     * This method saves the state of the game by serializing the Party object
     * (containing position on map, statistics, etc...) and the time played. It
     * saves in default path.
     * 
     * @param party
     *            the party object
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
    public void saveGame(final Party party) throws IOException {
        saveGameToPath(party, SAVE_FILE);
    }

    /**
     * The method restores a previously saved game statistics object from a
     * given-path file.
     * 
     * @param path
     *            the path of the file
     * @return the GameStatistics object
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a
     *             regular file, or for some other reason cannot be opened for
     *             reading.
     * @throws IOException
     *             if an error occurs
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an
     *             object of type
     */
    public GameStatisticsImpl loadStatsFromPath(final String path) throws IOException {
        return deserializeJSON(GameStatisticsImpl.class, path);
    }

    /**
     * The method restores a previously saved game statistics object from
     * default path.
     * 
     * @return the GameStatistics object
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a
     *             regular file, or for some other reason cannot be opened for
     *             reading.
     * @throws IOException
     *             if an error occurs
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an
     *             object of type
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
     *             if the file exists but is a directory rather than a regular
     *             file, does not exist but cannot be created, or cannot be
     *             opened for any other reason
     * @throws SecurityException
     *             if a security manager exists and its checkWrite method denies
     *             write access to the file
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
     *             if the file exists but is a directory rather than a regular
     *             file, does not exist but cannot be created, or cannot be
     *             opened for any other reason
     * @throws SecurityException
     *             if a security manager exists and its checkWrite method denies
     *             write access to the file
     * @throws JsonIOException
     *             if there was a problem writing to the writer
     */
    public void saveStats(final GameStatisticsImpl stats) throws IOException {
        saveStatsToPath(stats, STATS_FILE);
    }

    /**
     * This method saves a JsonJobParameter object containing all the default
     * parameters of a specific Job.
     * 
     * @param job
     *            the object to serialize
     * @param path
     *            the path where to find the JSON file
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
    public void saveJob(final JsonJobParameter job, final String path) throws IOException {
        System.out.println(this.gson.toJson(job));
        
        this.serializeJSON(job, path);
    }

    /**
     * This method returns a JsonJobParameter object containing all the default
     * parameters of a specific Job.
     * 
     * @param path
     *            the path where to find the JSON file
     * @return the deserialized JsonJobParameter object
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a
     *             regular file, or for some other reason cannot be opened for
     *             reading.
     * @throws IOException
     *             if an error occurs
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an
     *             object of type
     */
    public JsonJobParameter loadJob(final String path) throws IOException {
        return this.deserializeJSON(JsonJobParameter.class, path);
    }

    public <T> List<T> loadListAsArray(final Class<T[]> arrayClass, final String path) throws IOException {
        final T[] array = this.deserializeJSON(arrayClass, path);

        return Arrays.asList(array);
    }

    public <T> List<T> loadList(final Class<T> arrayClass, final String path) throws IOException {
        @SuppressWarnings("unchecked")
        final T[] array = (T[]) this.deserializeJSON(Array.newInstance(arrayClass, 0).getClass(), path);

        return Arrays.asList(array);
    }

    /*
     * public Item loadItem(final String path) throws IOException { final Item
     * item = this.deserializeJSON(Item.class, path);
     * 
     * return item; }
     */
    public GameMap loadMap(final String path) throws IOException {
        return this.deserializeJSON(GameMap.class, path);
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
     *             if the file exists but is a directory rather than a regular
     *             file, does not exist but cannot be created, or cannot be
     *             opened for any other reason
     * @throws SecurityException
     *             if a security manager exists and its checkWrite method denies
     *             write access to the file
     * @throws JsonIOException
     *             if there was a problem writing to the writer
     */
    public /* private */ void serializeJSON(final Object objectToSerialize, final String path) throws IOException {
        final Writer writer = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
        gson.toJson(objectToSerialize, writer);
        writer.close();
    }

    /**
     * This method loads a file from a given path and returns a single object
     * from what reads. It is private because tested only with the classes
     * needed here. It does not work with Collections and Maps.
     * 
     * @param <T>
     *            the type of the object serialized on the file
     * @param clazz
     *            the type of the object serialized on the file; it needs to be
     *            specified because sometimes it can't parse the correct type
     *            automatically
     * @param path
     *            the path where to find the JSON file
     * @return the serialized object
     * @throws IllegalArgumentException
     *             if passed a Collection or a Map
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a
     *             regular file, or for some other reason cannot be opened for
     *             reading
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an
     *             object of type
     */
    public /* private */ <T> T deserializeJSON(final Class<T> clazz, final String path) throws IOException {
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
