package it.unibo.unori.model.character;

import java.util.Map;

import it.unibo.unori.model.battle.utility.BattleLogics;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponImpl;

/**
 * Implementation of Interface Foe.
 */
public class FoeImpl extends CharacterImpl implements Foe {

    /**
     * 
     */
    private static final long serialVersionUID = -1168567801329410379L;
    private final int ia;
    private final Status immunity;

    /**
     * Standard constructor for a Foe.
     * @param intelligence the IA of the Foe.
     * @param name the name of the Foe.
     * @param battleFrame path of frame to set for the battle interface
     * @param map the Statistics of the Foe.
     * @param weapon the Weapon that the Foe is holding.
     * @param immune a Status to which this Foe is immune.
     */
    public FoeImpl(final int intelligence, final String name, final String battleFrame,
            final Map<Statistics, Integer> map, final Weapon weapon, final Status immune) {
        super(name, battleFrame, map, weapon);
        this.ia = intelligence;
        this.immunity = immune;
    }
    
    /**
     * Constructor to create a Foe who has not initially any Weapon.
     * @param intelligence the IA of the Foe.
     * @param name the name of the Foe.
     * @param battleFrame path of frame to set for the battle interface
     * @param map the Statistics of the Foe.
     * @param immune a Status to which this Foe is immune.
     */
    public FoeImpl(final int intelligence, final String name,
            final String battleFrame, final Map<Statistics, Integer> map, final Status immune) {
        this(intelligence, name, battleFrame, map, WeaponImpl.FISTS, immune);
    }
    
    /**
     * Constructor to create a Foe that has initially no Weapon and no immunity.
     * @param intelligence the IA of the Foe.
     * @param name the name of the Foe.
     * @param battleFrame path of frame to set for the battle interface
     * @param map the Statistics of the Foe.
     */
    public FoeImpl(final int intelligence, final String name,
            final String battleFrame, final Map<Statistics, Integer> map) {
        this(intelligence, name, battleFrame, map, WeaponImpl.FISTS, Status.NONE);
    }
    
    /**
     * Constructor to create a Foe that has no immunity.
     * @param intelligence the IA of the Foe.
     * @param name the name of the Foe.
     * @param battleFrame path of frame to set for the battle interface
     * @param map the Statistics of the Foe.
     * @param weapon the Weapon that the Foe is holding.
     */
    public FoeImpl(final int intelligence, final String name, final String battleFrame,
            final Map<Statistics, Integer> map, final Weapon weapon) {
        this(intelligence, name, battleFrame, map, weapon, Status.NONE);
    }

    @Override
    public int getIA() {
        return this.ia;
    }

    @Override
    public String restoreInBattle(final Statistics statToRestore) {
        switch (statToRestore) {
        case TOTALHP: this.restoreHP(this.getTotalHP() - this.getRemainingHP());
            return "HP";
        case TOTALMP: this.restoreMP(BattleLogics.mPToRestoreForFoe(this));
            return "MP";
        default:
            break;
        }
        return null;
    }
    
    @Override
    public Status getImmunity() {
        return this.immunity;
    }

}
