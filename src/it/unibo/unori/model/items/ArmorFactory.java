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
    
    private Map<Statistics, Integer> generateStatsMap(final Statistics physDef, final int valueFirst,
            final Statistics fireDef, final int valueSec, final Statistics iceDef,
            final int valueThir, final Statistics thdDef, final int valueFour) {
        Map<Statistics, Integer> map = new HashMap<>();
        map.put(physDef, valueFirst);
        map.put(fireDef, valueSec);
        map.put(iceDef, valueThir);
        map.put(thdDef, valueFour);
        return map;
    }
    
    /**
     * Create a standard equip for a character.
     * @return A list containing the equip
     */
    public Map<ArmorPieces, Armor> getStdEquip() {
        final Map<ArmorPieces, Armor> equip = new HashMap<>();
        final Map<Statistics, Integer> statsmap = this.generateStatsMap(
                Statistics.PHYSICDEF, 100,
                Statistics.FIREDEF, 0,
                Statistics.ICEDEF, 0,
                Statistics.THUNDERDEF, 0);
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
