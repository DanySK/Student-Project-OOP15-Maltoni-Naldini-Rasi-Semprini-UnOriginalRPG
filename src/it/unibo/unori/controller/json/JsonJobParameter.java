package it.unibo.unori.controller.json;

import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.Weapon;

/**
 * This class models an object used to serialize the Jobs parameters.
 */
public class JsonJobParameter {
    private final Map<Statistics, Integer> defaultStats;
    private final Map<Statistics, Integer> defaultIncrement;
    private final Map<ArmorPieces, Armor> defaultArmor;
    private final Weapon defaultWeapon;

    /**
     * Default constructor.
     * 
     * @param statistics
     *            the default statistics at starting level
     * @param growth
     *            the default increment of statistics at level up
     * @param armor
     *            the default armor at the moment of creation
     * @param weapon
     *            the default weapon at the moment of creation
     */
    public JsonJobParameter(final Map<Statistics, Integer> statistics, final Map<Statistics, Integer> growth,
            final Map<ArmorPieces, Armor> armor, final Weapon weapon) {
        this.defaultStats = statistics;
        this.defaultIncrement = growth;
        this.defaultArmor = armor;
        this.defaultWeapon = weapon;
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
    public Weapon getDefaultWeapon() {
        return this.defaultWeapon;
    }
}