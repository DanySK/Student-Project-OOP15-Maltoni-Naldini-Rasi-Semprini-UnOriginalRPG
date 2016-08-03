package it.unibo.unori.model.character;

import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
import it.unibo.unori.model.items.Weapon;

/**
 * Interface modeling a generic Foe of the game.
 */
public interface Foe extends Character {

    /**
     * Get the IA of the foe.
     * @return the IA of the foe
     */
    int getIA();
    
    /**
     * Get the Weapon that the foe is holding.
     * @return the Weapon that the foe is holding.
     */
    Weapon getWeapon();
    
    /**
     * Returns weather the Foe is holding a Weapon or not.
     * @return true if the Foe has a Weapon, false otherwise.
     */
    boolean hasWeapon();
    
    /**
     * Setter method to set the Weapon of the Foe.
     * @param w the Weapon to set.
     * @throws WeaponAlreadyException  if the Foe has already a Weapon.
     */
    void setWeapon(Weapon w) throws WeaponAlreadyException;

}
