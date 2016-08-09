package it.unibo.unori.model.items;

import java.util.Map;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;

/**
 * An Interface modeling a generic Weapon.
 */
public interface Weapon extends Item {


    /**
     * Getter for PhysicalAtk parameter.
     * @return the physical attack of the weapon;
     */
    int getPhysicalAtk();

    /**
     * get the Fire Attack of the weapon.
     * @return the fire attack of the weapon
     */
    int getFireAtk();

    /**
     * get the Thunder Attack of the weapon.
     * @return the thunder attack of the weapon
     */
    int getThunderAtk();

    /**
     * get the Ice Attack of the weapon.
     * @return the ice attack of the weapon.
     */
    int getIceAtk();

    /**
     * get the status infused on the weapon.
     * @return the status of the weapon
     */
    Status getWeaponStatus();

    /**
     * Method that returns the map of stats with relative values for this Weapon.
     * @return the map of Stats.
     */
    Map<Statistics, Integer> getStats();

}
