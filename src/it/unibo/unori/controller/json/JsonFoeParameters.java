package it.unibo.unori.controller.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.items.Weapon;

public class JsonFoeParameters {
    private final Status immunity;
    private final Map<Statistics, Integer> stats;
    private final Weapon weapon;
    private final List<MagicAttackInterface> magics;

    /**
     * Default constructor.
     * 
     * @param statistics
     *            the default statistics at starting IA
     * @param status
     *            the status the foe is immune to
     * @param weapon
     *            the default weapon at starting IA
     * @param magics
     *            a list of magic attacks the foe can use
     */
    public JsonFoeParameters(final Map<Statistics, Integer> statistics, final Status status, final Weapon weapon,
            final List<MagicAttackInterface> magics) {
        this.stats = new HashMap<Statistics, Integer>(statistics);
        this.immunity = status;
        this.magics = new ArrayList<MagicAttackInterface>(magics);
        this.weapon = weapon;
    }
    
    public JsonFoeParameters(final Map<Statistics, Integer> statistics, final Status status, final Weapon weapon,
            final MagicAttackInterface magic) {
        this(statistics, status, weapon, new ArrayList<MagicAttackInterface>());
        this.magics.add(magic);
    }

    public Status getImmunity() {
        return immunity;
    }

    public Map<Statistics, Integer> getStats() {
        return stats;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public List<MagicAttackInterface> getMagics() {
        return magics;
    }

}
