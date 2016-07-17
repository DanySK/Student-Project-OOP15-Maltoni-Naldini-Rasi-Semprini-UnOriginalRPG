package it.unibo.unori.model.items;

import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.items.Armor.ArmorPieces;

/**
 * Factory to build some armor.
 *
 */
public class ArmorFactory {

    /**
     * Create a standard equip for a character.
     * @return A list containing the equip
     */
    public Map<ArmorPieces, Armor> getStdEquip() {
        final Map<ArmorPieces, Armor> equip = new HashMap<>();
        final Map<Statistics, Integer> statsmap = new HashMap<>(); 
        statsmap.put(Statistics.PHYSICDEF, 100);
        statsmap.put(Statistics.FIREDEF, 0);
        statsmap.put(Statistics.ICEDEF, 0);
        statsmap.put(Statistics.THUNDERDEF, 0);
        equip.put(ArmorPieces.HELMET, new ArmorImpl("Elmo", ArmorPieces.HELMET, 
                "Elmo piuttosto brutto", statsmap, Status.NONE));
        equip.put(ArmorPieces.ARMOR, new ArmorImpl("Cotta", ArmorPieces.ARMOR,
                "Cotta piuttosto brutto", statsmap, Status.NONE));
        equip.put(ArmorPieces.GLOVES, new ArmorImpl("Guanti", ArmorPieces.GLOVES,
                "Guanti piuttosto brutti", statsmap, Status.NONE));
        equip.put(ArmorPieces.TROUSERS, new ArmorImpl("Schinieri", ArmorPieces.TROUSERS,
                "Schinieri piuttosto brutti", statsmap, Status.NONE));
        equip.put(ArmorPieces.SHIELD, new ArmorImpl("Scudo", ArmorPieces.SHIELD,
                "Scudo piuttosto brutto", statsmap, Status.NONE));
        return equip;
    }

}
