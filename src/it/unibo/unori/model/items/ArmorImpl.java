package it.unibo.unori.model.items;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    private static final String STDNAME = "Nudo";
    private static final String STDDESC = "La semplice pelle";
    private static final int PRIME = 31;


    /**
     * Basic equipment for a character without any piece of that part of armor.
     */
    public static final ArmorImpl NAKED = new ArmorImpl();

    //Generate the standard statistics for the void constructor of the class
    private Map<Statistics, Integer> generateStdStats() {
        final Map<Statistics, Integer> stats = new HashMap<>();
        stats.put(Statistics.PHYSICDEF, 0);
        stats.put(Statistics.FIREDEF, 0);
        stats.put(Statistics.ICEDEF, 0);
        stats.put(Statistics.THUNDERDEF, 0);
        return stats;
    }
    // check if the input parameters of constructor are for the Naked object implementation.
    private boolean isNakedConstruction(final String name, final ArmorPieces piece, final String desc,
                final Map<Statistics, Integer> stats, final Status immunity) {
        return STDNAME.equals(name) && piece.equals(ArmorPieces.NONE)
                && stats.isEmpty() && immunity.equals(Status.NONE)
                && STDDESC.equals(desc);
    }

    /* check if the input set contains a value for each kind of defense and
     * if the value of Enumeration armorPieces is different from None
     */
    private boolean hasLegitStats(final Set<Statistics> s, final ArmorPieces arm) {
        return s.containsAll(Arrays.asList(Statistics.FIREDEF, Statistics.ICEDEF, 
                Statistics.THUNDERDEF, Statistics.PHYSICDEF))
                && s.size() == 4 
                && !arm.equals(ArmorPieces.NONE);
    }

    // Private constructor, called only to generate the Naked instance.
    private ArmorImpl() {
        this(STDNAME, ArmorPieces.NONE, STDDESC, new HashMap<>(), 
                   Status.NONE);
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
     * @throws IllegalArgumentException if stats does not contains all the parameters
     *  required.
     */
    public ArmorImpl(final String name, final ArmorPieces piece, final String desc,
                      final Map<Statistics, Integer> stats, final Status immunity) 
                       throws IllegalArgumentException {
        this.name = name;
        this.desc = desc;
        this.piece = piece;
        this.immunity = immunity;
        if (this.isNakedConstruction(name, piece, desc, stats, immunity)) {
            this.stats = this.generateStdStats();
        } else {
            if (this.hasLegitStats(stats.keySet(), piece)) {
                this.stats = stats;
            } else {
                throw new IllegalArgumentException();
            }
        }
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
        return this.stats.get(Statistics.PHYSICDEF);
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

    @Override
    public Map<Statistics, Integer> getStats() {
        return new HashMap<>(this.stats);
    }

    /**
     * HashCode method implemented using auto generation.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int result = 1;
        result = PRIME * result + ((desc == null) ? 0 : desc.hashCode());
        result = PRIME * result + ((immunity == null) ? 0 : immunity.hashCode());
        result = PRIME * result + ((name == null) ? 0 : name.hashCode());
        result = PRIME * result + ((piece == null) ? 0 : piece.hashCode());
        result = PRIME * result + ((stats == null) ? 0 : stats.hashCode());
        return result;
    }

    /**
     * Equals method implemented for the serialization.
     * 
     * @see java.lang.Object#equals(Object obj).
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final ArmorImpl other = (ArmorImpl) obj;
        final Map<Statistics, Integer> map = this.stats;
        return this.desc.equals(other.getDescription()) 
                && this.immunity.equals(other.getImmunity()) 
                && this.name.equals(other.getName()) 
                && this.piece.equals(other.getArmorClass()) 
                && other.getFireDef() == map.get(Statistics.FIREDEF)
                && other.getIceDefense() == map.get(Statistics.ICEDEF)
                && other.getThunderDefense() == map.get(Statistics.THUNDERDEF)
                && other.getPhysicalRes() == map.get(Statistics.PHYSICDEF);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
