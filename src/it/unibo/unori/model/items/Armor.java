package it.unibo.unori.model.items;

import java.util.Map;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;

/**
 * An Interface modeling a generic Armor.
 */
public interface Armor extends Item {

    /**
     * Get the physical defense of the armor.
     * @return physical defense of the armor
     */
    int getPhysicalRes();

    /**
     * Get the fire defense of the armor.
     * @return fire defense of the armor
     */
    int getFireDef();

    /**
     * Get the thunder defense of the armor.
     * @return the thunder defense of the armor
     */
    int getThunderDefense();

    /**
     * Get the ice defense of the armor.
     * @return the ice defense of the armor
     */
    int getIceDefense();

    /**
     * Get the immunity to a particular status, if present.
     * @return the status which the armor is immune to
     */
    Status getImmunity();

    /**
     * Get the armor class of the piece.
     * @return the kind of the piece of armor.
     */
    ArmorPieces getArmorClass();
    
    /**
     * Getter method that returns the map of stats of this Armor.
     * @return a Map of Stats (a defensive copy).
     */
    Map<Statistics, Integer> getStats();

    /**
     * Enumeration to define the different kind of armors.
     *
     */
    enum ArmorPieces {
        SHIELD, HELMET, ARMOR, GLOVES, TROUSERS, NONE 
     }
    
}


