package it.unibo.unori.controller.json;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.jobs.GrowthFactory;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.character.jobs.StatisticsFactory;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.ArmorFactory;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponFactory;

/**
 * Utility class which provides default parameters for the jobs loading them
 * from file.
 */
public final class JobsSetup {
    /**
     * Path to the JSON file of the warrior's default statistics and
     * armors/weapons.
     */
    public static final String WARRIOR = "res/Warrior.json";
    /**
     * Path to the JSON file of the paladin's default statistics and
     * armors/weapons.
     */
    public static final String PALADIN = "res/Paladin.json";
    /**
     * Path to the JSON file of the mage's default statistics and
     * armors/weapons.
     */
    public static final String MAGE = "res/Mage.json";
    /**
     * Path to the JSON file of the ranger's default statistics and
     * armors/weapons.
     */
    public static final String RANGER = "res/Ranger.json";
    /**
     * Path to the JSON file of the cook's default statistics and
     * armors/weapons.
     */
    public static final String COOK = "res/Cook.json";
    /**
     * Path to the JSON file of the clown's default statistics and
     * armors/weapons.
     */
    public static final String CLOWN = "res/Clown.json";

    private final Map<String, Map<Statistics, Integer>> statsMap;
    private final Map<String, Map<Statistics, Integer>> growthMap;
    private final Map<String, Map<ArmorPieces, Armor>> armorMap;
    private final Map<String, Weapon> weaponMap;

    private final JsonFileManager fileManager;

    /**
     * Default constructor.
     */
    public JobsSetup() {
        fileManager = new JsonFileManager();
        statsMap = new HashMap<>();
        growthMap = new HashMap<>();
        armorMap = new HashMap<>();
        weaponMap = new HashMap<>();
    }

    /**
     * Main method. It creates JSON file starting from Factories of the model.
     * 
     * @param args
     *            standard main parameter
     * @throws IOException if something goes wrong
     */
    public static void main(final String[] args) throws IOException {
        final StatisticsFactory sf = new StatisticsFactory();
        final GrowthFactory gf = new GrowthFactory();
        final ArmorFactory af = new ArmorFactory();
        final WeaponFactory wf = new WeaponFactory();
        final JsonFileManager jfm = new JsonFileManager();
        Optional<String> jobPath = Optional.empty();
        JsonJobParameter jsonJob;

        for (final Jobs j : Jobs.values()) {
            if (j.equals(Jobs.DUMP)) {
                jobPath = Optional.empty();
            } else {
                jobPath = Optional.of(getPath(j));
            }
            if (jobPath.isPresent()) {
                jsonJob = new JsonJobParameter(sf.getJobStats(j), gf.getJobGrowth(j), af.getStdEquip(),
                        wf.getStdSword());
                jfm.saveJob(jsonJob, jobPath.get());

            }

        }

    }

    /**
     * Loads the default statistics of the job from JSON file. Suggested to pass
     * constants provided by this class.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values
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
    public Map<Statistics, Integer> getDefaultStats(final String path) throws IOException {
        if (!this.statsMap.containsKey(path)) {
            this.statsMap.put(path, fileManager.loadJob(path).getDefaultStats());
        }
        return new HashMap<>(this.statsMap.get(path));
    }

    /**
     * Loads the default statistics of the job from JSON file. Suggested to pass
     * constants provided by this class. This method acts exactly the same as
     * {@link #getDefaultStats(String)}, but instead of throwing exceptions,
     * return an empty map if something wrong happens.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values, or an empty map if something wrong
     *         happens
     */
    public Map<Statistics, Integer> getDefaultStatsMap(final String path) {
        try {
            return getDefaultStats(path);
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

    /**
     * Loads the default statistics increments of the job from JSON file.
     * Suggested to pass constants provided by this class.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values
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
    public Map<Statistics, Integer> getDefaultIncrements(final String path) throws IOException {
        if (!this.growthMap.containsKey(path)) {
            this.growthMap.put(path, fileManager.loadJob(path).getDefaultIncrement());
        }
        return new HashMap<>(this.growthMap.get(path));
    }

    /**
     * Loads the default statistics increments of the job from JSON file.
     * Suggested to pass constants provided by this class. This method acts
     * exactly the same as {@link #getDefaultIncrements(String)}, but instead of
     * throwing exceptions, return an empty map if something wrong happens.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values, or an empty map if something wrong
     *         happens
     */
    public Map<Statistics, Integer> getDefaultIncrementsMap(final String path) {
        try {
            return getDefaultIncrements(path);
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

    /**
     * Loads the default armor of the job from JSON file. Suggested to pass
     * constants provided by this class.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values
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
    public Map<ArmorPieces, Armor> getDefaultArmor(final String path) throws IOException {
        if (!this.armorMap.containsKey(path)) {
            this.armorMap.put(path, fileManager.loadJob(path).getDefaultArmor());
        }
        return new HashMap<>(this.armorMap.get(path));
    }

    /**
     * Loads the default armor of the job from JSON file. Suggested to pass
     * constants provided by this class. This method acts exactly the same as
     * {@link #getDefaultArmor(String)}, but instead of throwing exceptions,
     * return an empty map if something wrong happens.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values, or an empty map if something wrong
     *         happens
     */
    public Map<ArmorPieces, Armor> getDefaultArmorMap(final String path) {
        try {
            return getDefaultArmor(path);
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

    /**
     * Loads the default weapon of the job from JSON file. Suggested to pass
     * constants provided by this class.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return the weapon
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
    public Weapon getDefaultWeapon(final String path) throws IOException {
        if (!this.weaponMap.containsKey(path)) {
            this.weaponMap.put(path, fileManager.loadJob(path).getDefaultWeapon());
        }
        return this.weaponMap.get(path);
    }

    /**
     * Loads the default weapon of the job from JSON file. Suggested to pass
     * constants provided by this class. This method acts exactly the same as
     * {@link #getDefaultWeapon(String)}, but instead of throwing exceptions,
     * returns null if something wrong happens.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return the weapon, or null if something wrong happens
     */
    public Weapon getDefaultWeaponNullable(final String path) {
        try {
            return getDefaultWeapon(path);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Return the path of the PNG file that contains the battle sprite of the
     * Job. As requested, it returns null if it can't find any default provided
     * file path for the given Job
     * 
     * @param job
     *            the Job (suggested to use this class' constant)
     * @return the path if the job is found, or null if it can't find any
     *         default provided file path for the given Job
     */
    public String getBattleSpritePath(final String job) {
        final String spritePath;

        switch (job) {
            case WARRIOR:
                spritePath = "res/sprites/warrior.png";
                break;
            case PALADIN:
                spritePath = "res/sprites/paladin.png";
                break;
            case MAGE:
                spritePath = "res/sprites/mage.png";
                break;
            case RANGER:
                spritePath = "res/sprites/ranger.png";
                break;
            case COOK:
                spritePath = "res/sprites/cook.png";
                break;
            case CLOWN:
                spritePath = "res/sprites/clown.png";
                break;
            default:
                spritePath = null;
                break;
        }

        return spritePath;
    }

    /**
     * Return the path of the JSON file that contains the default parameters of
     * the Job.
     * 
     * @param job
     *            the Job
     * @return the path if the job is found
     * @throws FileNotFoundException
     *             if it can't find any default provided file path for the given
     *             Job
     */
    public static String getPath(final Jobs job) throws FileNotFoundException {
        Optional<String> jobPath;

        if (job.equals(Jobs.WARRIOR)) {
            jobPath = Optional.of(WARRIOR);
        } else if (job.equals(Jobs.PALADIN)) {
            jobPath = Optional.of(PALADIN);
        } else if (job.equals(Jobs.MAGE)) {
            jobPath = Optional.of(MAGE);
        } else if (job.equals(Jobs.RANGER)) {
            jobPath = Optional.of(RANGER);
        } else if (job.equals(Jobs.COOK)) {
            jobPath = Optional.of(COOK);
        } else if (job.equals(Jobs.CLOWN)) {
            jobPath = Optional.of(CLOWN);
        } else {
            jobPath = Optional.empty();
        }

        return jobPath.orElseThrow(
                () -> new FileNotFoundException("It is not provided any default file path for the Job " + job));
    }
}
