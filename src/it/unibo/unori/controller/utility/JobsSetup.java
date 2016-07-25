package it.unibo.unori.controller.utility;

import java.util.Map;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.Weapon;
/**
 *  Utility class which provides default parameters for the jobs loading them from file.
 */
public final class JobsSetup {
    /**
     * Path to the JSON file of the warrior's default statistics and armors/weapons.
     */
    public static final String WARRIOR = "Warrior.js";
    /**
     * Path to the JSON file of the paladin's default statistics and armors/weapons.
     */
    public static final String PALADIN = "Paladin.js";
    /**
     * Path to the JSON file of the mage's default statistics and armors/weapons.
     */
    public static final String MAGE = "Mage.js";
    /**
     * Path to the JSON file of the ranger's default statistics and armors/weapons.
     */
    public static final String RANGER = "Ranger.js";
    /**
     * Path to the JSON file of the cook's default statistics and armors/weapons.
     */
    public static final String COOK = "Cook.js";
    /**
     * Path to the JSON file of the clown's default statistics and armors/weapons.
     */
    public static final String CLOWN = "Clown.js";

    private JobsSetup() {
        // Empty constructor
    }

    /**
     * Loads the default statistics of the job from JSON file. Suggeted to pass constants provided by this class.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values
     */
    public static Map<Statistics, Integer> getDefaultStats(final String path) {
        // TODO
        return null;
    }

    /**
     * Loads the default statistics increments of the job from JSON file. Suggeted to pass constants provided by this
     * class.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values
     */
    public static Map<Statistics, Integer> getDefaultIncrements(final String path) {
        // TODO
        return null;
    }

    /**
     * Loads the default armor of the job from JSON file. Suggeted to pass constants provided by this class.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values
     */
    public static Map<ArmorPieces, Armor> getDefaultArmor(final String path) {
        // TODO
        return null;
    }

    /**
     * Loads the default weapon of the job from JSON file. Suggeted to pass constants provided by this class.
     * 
     * @param path
     *            the path to the file of the desired job.
     * @return a map containing the values
     */
    public static Weapon getDefaultWeapon(final String path) {
        // TODO
        return null;
    }
}
