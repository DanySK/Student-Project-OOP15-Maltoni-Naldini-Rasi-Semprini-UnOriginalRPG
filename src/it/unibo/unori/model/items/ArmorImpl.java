package it.unibo.unori.model.items;

import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;



/**
 * Describe an armor implementations.
 * It has simply some parameters and their getter.
 */
public class ArmorImpl implements Armor {

    /**
     * 
     */
    private static final long serialVersionUID = 6023683727427402144L;
    private final String name;
    private final ArmorPieces piece;
    private final String desc;
    private final Map<Statistics, Integer> stats;
    private final Status immunity;
    /**
     * Basic equipment for a character without any piece of that part of armor.
     */
    public static final ArmorImpl NAKED = new ArmorImpl();

    private ArmorImpl() {
        this("Naked", ArmorPieces.NONE, "La semplice pelle", new HashMap<>(), 
                   Status.NONE);
        this.stats.put(Statistics.PHYISICDEF, 0);
        this.stats.put(Statistics.FIREDEF, 0);
        this.stats.put(Statistics.ICEDEF, 0);
        this.stats.put(Statistics.THUNDERDEF, 0);
       }

    /**
     * Standard constructor.
     * @param name
     *             name of the armor.
     * @param piece
     *              part of the armor.
     * @param desc
     *              description of the armor.
     * @param stats
     *              statistics of the armor.
     * @param immunity
     *              immunity to status of the armor.
     */
    public ArmorImpl(final String name, final ArmorPieces piece, final String desc,
                      final Map<Statistics, Integer> stats, final Status immunity) {
        this.name = name;
        this.desc = desc;
        this.piece = piece;
        this.stats = stats;
        this.immunity = immunity;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.desc;
    }

    @Override
    public int getPhysicalRes() {
        return this.stats.get(Statistics.PHYISICDEF);
    }

    @Override
    public int getFireDef() {
        return this.stats.get(Statistics.FIREDEF);
    }

    @Override
    public int getThunderDefense() {
        return this.stats.get(Statistics.THUNDERDEF);
    }

    @Override
    public int getIceDefense() {
        return this.stats.get(Statistics.ICEDEF);
    }

    @Override
    public Status getImmunity() {
        return this.immunity;
    }

    @Override
    public ArmorPieces getArmorClass() {
        return this.piece;
    }

}
