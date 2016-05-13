package it.unibo.unori.controller.utility;

import java.util.Map;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.Weapon;

public class JobsSetup {
    public static final String WARRIOR = "Warrior.js";
    public static final String PALADIN = "Paladin.js";
    public static final String MAGE = "Mage.js";
    public static final String RANGER = "Ranger.js";
    public static final String COOK = "Cook.js";
    public static final String CLOWN = "Clown.js";

    private JobsSetup() {
        // Empty constructor
    }

    public static Map<Statistics, Integer> getDefaultStats(final String path) {
        // TODO
        return null;
    }
    
    public static Map<Statistics, Integer> getDefaultIncrements(final String path) {
        // TODO
        return null;
    }
    
    public static Map<ArmorPieces, Armor> getDefaultArmor(final String path) {
        // TODO
        return null;
    }
    
    public static Weapon getDefaultWeapon(final String path) {
        // TODO
        return null;
    }
}
