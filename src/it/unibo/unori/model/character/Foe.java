package it.unibo.unori.model.character;

import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
import it.unibo.unori.model.items.Weapon;

/**
 * Interface modeling a generic Foe of the game.
 */
public interface Foe extends Character {

    /**
     * Get the IA of the foe.
     * The IA goes from 1 to 10.
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
    
    /**
     * This method allows to remove a Weapon from the equipment of the Foe.
     * @throws NoWeaponException if the Foe is not holding any Weapon.
     */
    void unsetWeapon() throws NoWeaponException;
    
    /**
     * This method is supposed to be used in Battle by a Foe.
     * It allows him to restore a certain Status, and it can be used as often as the
     * level of the Foe increases.
     * @param statToRestore the Statistic to be restored.
     * @return a String representing the Stat that has been restored.
     */
    String restoreInBattle(Statistics statToRestore);

}
