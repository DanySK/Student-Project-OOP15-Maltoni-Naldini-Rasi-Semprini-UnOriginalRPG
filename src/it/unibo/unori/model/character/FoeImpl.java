package it.unibo.unori.model.character;

import java.util.Map;

import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
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
    private Weapon wep;

    /**
     * Standard constructor for a Foe.
     * @param intelligence the IA of the Foe.
     * @param name the name of the Foe.
     * @param battleFrame path of frame to set for the battle interface
     * @param map the Statistics of the Foe.
     * @param weapon the Weapon that the Foe is holding.
     */
    public FoeImpl(final int intelligence, final String name,
            final String battleFrame, final Map<Statistics, Integer> map, final Weapon weapon) {
        super(name, battleFrame, map);
        this.ia = intelligence;
        this.wep = weapon;
    }
    
    private boolean isNotPresentWeapon() {
        return this.wep.equals(WeaponImpl.FISTS);
    }

    @Override
    public int getIA() {
        return this.ia;
    }
    
    @Override
    public Weapon getWeapon() {
        return this.wep;
    }

    @Override
    public boolean hasWeapon() {
        return !this.isNotPresentWeapon();
    }

    @Override
    public void setWeapon(final Weapon w) throws WeaponAlreadyException {
        if (this.isNotPresentWeapon()) {
            this.wep = w;
        } else {
            throw new WeaponAlreadyException();
        }
    }

}
