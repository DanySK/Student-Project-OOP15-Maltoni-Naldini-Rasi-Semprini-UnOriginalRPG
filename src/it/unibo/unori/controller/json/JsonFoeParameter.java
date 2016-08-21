package it.unibo.unori.controller.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.items.Weapon;

/**
 * This class models an object used to serialize the Jobs parameters. It can
 * support also more than one magic serialization and status serialization, but
 * model doesn't use these features.
 */
public class JsonFoeParameter {
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
    public JsonFoeParameter(final Map<Statistics, Integer> statistics, final Status status, final Weapon weapon,
            final List<MagicAttackInterface> magics) {
        this.stats = new HashMap<Statistics, Integer>(statistics);
        this.immunity = status;
        this.magics = new ArrayList<MagicAttackInterface>(magics);
        this.weapon = weapon;
    }

    /**
     * Default constructor. Serializes only one magic.
     * 
     * @param statistics
     *            the default statistics at starting IA
     * @param status
     *            the status the foe is immune to
     * @param weapon
     *            the default weapon at starting IA
     * @param magic
     *            a list of magic attacks the foe can use
     */
    public JsonFoeParameter(final Map<Statistics, Integer> statistics, final Status status, final Weapon weapon,
            final MagicAttackInterface magic) {
        this(statistics, status, weapon, new ArrayList<MagicAttackInterface>());
        this.magics.add(magic);
    }

    /**
     * Returns the immunity of the foe.
     * 
     * @return the status the foe is immune to
     */
    public Status getImmunity() {
        return immunity;
    }

    /**
     * Returns the statistics of the foe.
     * 
     * @return the statistics map
     */
    public Map<Statistics, Integer> getStats() {
        return stats;
    }

    /**
     * Returns the weapon of the foe.
     * 
     * @return the weapon
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Returns the list of the available magic attacks of the foe.
     * 
     * @return the magic attacks
     */
    public List<MagicAttackInterface> getMagics() {
        return magics;
    }

}
