package it.unibo.unori.model.character;

import it.unibo.unori.model.battle.utility.BattleLogics;
import it.unibo.unori.model.character.factory.FoesFactory;
import it.unibo.unori.model.character.factory.FoesFindable;

/**
 * Implementation of Interface Foe.
 */
public class FoeImpl extends CharacterImpl implements Foe {

    /**
     * 
     */
    private static final long serialVersionUID = -1168567801329410379L;
    private final int ia;
    private final FoesFindable type;

    /**
     * Standard constructor for a Foe.
     * @param intelligence the IA of the Foe.
     * @param name the name of the Foe.
     * @param battleFrame path of frame to set for the battle interface
     * @param type the type of the Foe.
     */
    public FoeImpl(final int intelligence, final String name, 
            final String battleFrame, final FoesFindable type) {
        super(name, battleFrame, FoesFactory.getGrowingStats(intelligence),
                intelligence, FoesFactory.getGrownMagics(intelligence),
                FoesFactory.getWeaponGrown(intelligence));
        this.ia = intelligence;
        this.type = type;
        FoesFactory.getGrownMagics(intelligence).stream().forEach(m -> this.addSpell(m));
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
    public FoesFindable getType() {
        return this.type;
    }
    
    @Override
    public Status getImmunity() {
        return this.type.getImmunity();
    }

}
