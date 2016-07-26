package it.unibo.unori.controller.utility;

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
 * Utility class which provides default parameters for the jobs loading them from file.
 */
public final class JobsSetup {
    /**
     * Path to the JSON file of the warrior's default statistics and armors/weapons.
     */
    public static final String WARRIOR = "res/Warrior.json";
    /**
     * Path to the JSON file of the paladin's default statistics and armors/weapons.
     */
    public static final String PALADIN = "res/Paladin.json";
    /**
     * Path to the JSON file of the mage's default statistics and armors/weapons.
     */
    public static final String MAGE = "res/Mage.json";
    /**
     * Path to the JSON file of the ranger's default statistics and armors/weapons.
     */
    public static final String RANGER = "res/Ranger.json";
    /**
     * Path to the JSON file of the cook's default statistics and armors/weapons.
     */
    public static final String COOK = "res/Cook.json";
    /**
     * Path to the JSON file of the clown's default statistics and armors/weapons.
     */
    public static final String CLOWN = "res/Clown.json";

    private JobsSetup() {
        // Empty private constructor, because this is an utility class
    }

    /**
     * Main method. It creates JSON file starting from Factories of the model.
     * 
     * @param args 
     */
    public static void main(final String[] args) {
        final StatisticsFactory sf = new StatisticsFactory();
        final GrowthFactory gf = new GrowthFactory();
        final ArmorFactory af = new ArmorFactory();
        final WeaponFactory wf = new WeaponFactory();
        Optional<String> jobPath = Optional.empty();
        JsonJobParameter jsonJob;

        for (final Jobs j : Jobs.values()) {
            try {
                jobPath = Optional.of(getPath(j));
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if (jobPath.isPresent()) {
                try {
                    jsonJob = new JsonJobParameter(sf.getJobStats(j), gf.getJobGrowth(j), af.getStdEquip(),
                                    wf.getStdSword());
                    Save.serializeJSON(jsonJob, jobPath.get());
                } catch (IOException e) {
                    // TODO
                    e.printStackTrace();
                }

            }

        }

    }

    /**
     * Loads the default statistics of the job from JSON file. Suggested to pass constants provided by this class.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values
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
    public static Map<Statistics, Integer> getDefaultStats(final String path) throws IOException {
        final JsonJobParameter objFromFile = Save.deserializeJSON(path);
        return new HashMap<>(objFromFile.getDefaultStats());
    }

    /**
     * Loads the default statistics of the job from JSON file. Suggested to pass constants provided by this class. This
     * method acts exactly the same as {@link #getDefaultStats(String)}, but instead of throwing exceptions, return an
     * empty map if something wrong happens.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values, or an empty map if something wrong happens
     */
    public static Map<Statistics, Integer> getDefaultStatsMap(final String path) {
        try {
            return getDefaultStats(path);
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

    /**
     * Loads the default statistics increments of the job from JSON file. Suggested to pass constants provided by this
     * class.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values
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
    public static Map<Statistics, Integer> getDefaultIncrements(final String path) throws IOException {
        final JsonJobParameter objFromFile = Save.deserializeJSON(path);
        return new HashMap<>(objFromFile.getDefaultIncrement());
    }

    /**
     * Loads the default statistics increments of the job from JSON file. Suggested to pass constants provided by this
     * class. This method acts exactly the same as {@link #getDefaultIncrements(String)}, but instead of throwing
     * exceptions, return an empty map if something wrong happens.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values, or an empty map if something wrong happens
     */
    public static Map<Statistics, Integer> getDefaultIncrementsMap(final String path) {
        try {
            return JobsSetup.getDefaultIncrements(path);
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

    /**
     * Loads the default armor of the job from JSON file. Suggested to pass constants provided by this class.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values
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
    public static Map<ArmorPieces, Armor> getDefaultArmor(final String path) throws IOException {
        final JsonJobParameter objFromFile = Save.deserializeJSON(path);
        return new HashMap<>(objFromFile.getDefaultArmor());
    }

    /**
     * Loads the default armor of the job from JSON file. Suggested to pass constants provided by this class. This
     * method acts exactly the same as {@link #getDefaultArmor(String)}, but instead of throwing exceptions, return an
     * empty map if something wrong happens.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values, or an empty map if something wrong happens
     */
    public static Map<ArmorPieces, Armor> getDefaultArmorMap(final String path) {
        try {
            return JobsSetup.getDefaultArmor(path);
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

    /**
     * Loads the default weapon of the job from JSON file. Suggested to pass constants provided by this class.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return the weapon
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
    public static Weapon getDefaultWeapon(final String path) throws IOException {
        final JsonJobParameter objFromFile = Save.deserializeJSON(path);
        return objFromFile.getDefaultWeapon();
    }

    /**
     * Loads the default weapon of the job from JSON file. Suggested to pass constants provided by this class. This
     * method acts exactly the same as {@link #getDefaultWeapon(String)}, but instead of throwing exceptions, returns
     * null if something wrong happens.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return the weapon, or null if something wrong happens
     */
    public static Weapon getDefaultWeaponNullable(final String path) {
        try {
            return JobsSetup.getDefaultWeapon(path);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Return the path of the JSON file that contains the default parameters of the Job.
     * 
     * @param job
     *            the Job
     * @return the path if the job is found
     * @throws FileNotFoundException
     *             if it can't find any default provided file path for the given Job
     */
    public static String getPath(final Jobs job) throws FileNotFoundException {
        Optional<String> jobPath = Optional.empty();

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
        }

        return jobPath.orElseThrow(
                        () -> new FileNotFoundException("It is not provided any default file path for this Job"));
    }

    /**
     * Private static class that models an object used to serialize the Jobs
     */
    private static class JsonJobParameter {
        private final Map<Statistics, Integer> defaultStats;
        private final Map<Statistics, Integer> defaultIncrement;
        private final Map<ArmorPieces, Armor> defaultArmor;
        private final Weapon defaultWeapon;

        JsonJobParameter(final Map<Statistics, Integer> defaultStats, final Map<Statistics, Integer> defaultIncrement,
                        final Map<ArmorPieces, Armor> defaultArmor, final Weapon defaultWeapon) {
            this.defaultStats = defaultStats;
            this.defaultIncrement = defaultIncrement;
            this.defaultArmor = defaultArmor;
            this.defaultWeapon = defaultWeapon;
        }

        /**
         * Get the initial equipments of a job.
         * 
         * @return a defensive copy of the equipments
         */
        public Map<ArmorPieces, Armor> getDefaultArmor() {
            return new HashMap<>(this.defaultArmor);
        }

        /**
         * Get the initial statistics of a job.
         * 
         * @return a defensive copy of the statistics
         */
        public Map<Statistics, Integer> getDefaultStats() {
            return new HashMap<>(this.defaultStats);
        }

        /**
         * Get the increment values of the job statistics .
         * 
         * @return a defensive copy of the statistics
         */
        public Map<Statistics, Integer> getDefaultIncrement() {
            return new HashMap<>(this.defaultIncrement);
        }

        /**
         * Get the starter weapon of the job.
         * 
         * @return the initial weapon of the job
         */
        public final Weapon getDefaultWeapon() {
            return this.defaultWeapon;
        }
    }
}
