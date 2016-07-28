package it.unibo.unori.controller.utility;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.ArmorImpl;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponImpl;

/**
 * This class models an object used to serialize the Jobs.
 */
public class JsonJobParameter {
    private final Map<Statistics, Integer> defaultStats;
    private final Map<Statistics, Integer> defaultIncrement;
    private final Map<ArmorPieces, Armor> defaultArmor;
    private final Weapon defaultWeapon;

    /**
     * 
     * @param defaultStats
     * @param defaultIncrement
     * @param defaultArmor
     * @param defaultWeapon
     */
    public JsonJobParameter(final Map<Statistics, Integer> defaultStats,
                    final Map<Statistics, Integer> defaultIncrement, final Map<ArmorPieces, Armor> defaultArmor,
                    final Weapon defaultWeapon) {
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
    public Weapon getDefaultWeapon() {
        return this.defaultWeapon;
    }

    /**
     * It instantiates a new Gson object with {@link com.google.gson.InstanceCreator<T>} for
     * {@link com.google.gson.InstanceCreator<Armor>} and {@link com.google.gson.InstanceCreator<Armor>} already
     * registered, so it should deserialize a {@link it.unibo.unori.controller.utility.JsonJobParameter} without problems.
     * 
     * @return a new Gson object
     */
    public static Gson createGson() {
        return new GsonBuilder().registerTypeAdapter(Armor.class, new InstanceCreator<Armor>() {
            @Override
            public Armor createInstance(final Type type) {
                return ArmorImpl.NAKED;
            }
        }).registerTypeAdapter(Weapon.class, new InstanceCreator<Weapon>() {
            @Override
            public Weapon createInstance(final Type type) {
                return WeaponImpl.FISTS;
            }
        }).create();
    }
}