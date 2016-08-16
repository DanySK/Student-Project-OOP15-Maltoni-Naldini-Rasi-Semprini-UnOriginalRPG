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
        final Map<Statistics, Integer> map = new HashMap<>();
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

    /**
     * Create the bronze set, which defend from sleep.
     * @return
     *          a list containing the bronze armor
     */
    public Map<ArmorPieces, Armor> getBronzeEquip() {
        final Map<ArmorPieces, Armor> equip = new HashMap<>();
        final Map<Statistics, Integer> statsmap = this.generateStatsMap(
                Statistics.PHYSICDEF, 300,
                Statistics.FIREDEF, 100,
                Statistics.ICEDEF, 100,
                Statistics.THUNDERDEF, 100);
        equip.put(ArmorPieces.HELMET, new ArmorImpl("Elmo di bronzo", ArmorPieces.HELMET, 
                "Elmo bronzeo, protegge dal sonno", statsmap, Status.ASLEEP));
        equip.put(ArmorPieces.ARMOR, new ArmorImpl("Cotta di bronzo", ArmorPieces.ARMOR,
                "Cotta bronzea, protegge dal sonno", statsmap, Status.ASLEEP));
        equip.put(ArmorPieces.GLOVES, new ArmorImpl("Guanti di bronzo", ArmorPieces.GLOVES,
                "Guanti bronzei, proteggono dal sonno", statsmap, Status.ASLEEP));
        equip.put(ArmorPieces.TROUSERS, new ArmorImpl("Schinieri di bronzo", ArmorPieces.TROUSERS,
                "Schinieri bronzei, proteggono dal sonno", statsmap, Status.ASLEEP));
        equip.put(ArmorPieces.SHIELD, new ArmorImpl("Scudo", ArmorPieces.SHIELD,
                "Scudo bronzeo, protegge dal sonno", statsmap, Status.ASLEEP));
        return equip;
    }

    /**
     * Create the silver set, which defend from sleep.
     * @return
     *          a list containing the silver armor
     */
    public Map<ArmorPieces, Armor> getSilverEquip() {
        final Map<ArmorPieces, Armor> equip = new HashMap<>();
        final Map<Statistics, Integer> statsmap = this.generateStatsMap(
                Statistics.PHYSICDEF, 500,
                Statistics.FIREDEF, 250,
                Statistics.ICEDEF, 250,
                Statistics.THUNDERDEF, 250);
        equip.put(ArmorPieces.HELMET, new ArmorImpl("Elmo d'argento", ArmorPieces.HELMET, 
                "Elmo argenteo, di grande qualità", statsmap, Status.POISONED));
        equip.put(ArmorPieces.ARMOR, new ArmorImpl("Cotta d'argento", ArmorPieces.ARMOR,
                "Cotta argentea, di grande qualità", statsmap, Status.POISONED));
        equip.put(ArmorPieces.GLOVES, new ArmorImpl("Guanti d'argento", ArmorPieces.GLOVES,
                "Guanti argentei, di grande qualità", statsmap, Status.POISONED));
        equip.put(ArmorPieces.TROUSERS, new ArmorImpl("Schinieri d'argento", ArmorPieces.TROUSERS,
                "Schinieri argentei, di grande qualità", statsmap, Status.POISONED));
        equip.put(ArmorPieces.SHIELD, new ArmorImpl("Scudo d'argento", ArmorPieces.SHIELD,
                "Scudo di Dario Argento", statsmap, Status.POISONED));
        return equip;
    }

    /**
     * Create the gold set, which defend from sleep.
     * @return
     *          a list containing the gold armor
     */
    public Map<ArmorPieces, Armor> getGoldEquip() {
        final Map<ArmorPieces, Armor> equip = new HashMap<>();
        final Map<Statistics, Integer> statsmap = this.generateStatsMap(
                Statistics.PHYSICDEF, 1000,
                Statistics.FIREDEF, 500,
                Statistics.ICEDEF, 500,
                Statistics.THUNDERDEF, 500);
        equip.put(ArmorPieces.HELMET, new ArmorImpl("Elmo d'oro", ArmorPieces.HELMET, 
                "Elmo d'oro, leggendario.", statsmap, Status.CURSED));
        equip.put(ArmorPieces.ARMOR, new ArmorImpl("Cotta d'oro", ArmorPieces.ARMOR,
                "Cotta d'oro, leggendaria.", statsmap, Status.CURSED));
        equip.put(ArmorPieces.GLOVES, new ArmorImpl("Guanti d'oro", ArmorPieces.GLOVES,
                "Guanti d'oro, leggendari.", statsmap, Status.CURSED));
        equip.put(ArmorPieces.TROUSERS, new ArmorImpl("Schinieri d'oro", ArmorPieces.TROUSERS,
                "Schinieri d'oro, leggendari", statsmap, Status.CURSED));
        equip.put(ArmorPieces.SHIELD, new ArmorImpl("Scudo d'oro", ArmorPieces.SHIELD,
                "Scudo d'oro, leggendario", statsmap, Status.CURSED));
        return equip;
    }

}
