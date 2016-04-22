package it.unibo.unori.model.items;

import java.io.Serializable;

import it.unibo.unori.model.character.Hero;

/**
 * This Interface models the Item Bag.
 * It contains the main methods to call when using the Item Bag.
 */
public interface Bag extends Serializable {
    
    /**
     * This method adds an Item to the Bag.
     * @param toAdd the Item to be added.
     */
    void addItem(Item toAdd);
    
    /**
     * This method allows to remove an Item from the Bag.
     */
    void removeItem();
    
    /**
     * This method allows to use a Potion on a specified Hero.
     * @param my the Hero on which use the Potion.
     * @param p the Potion to use.
     */
    void usePotion(Hero my, Potion p);
    
    /**
     * This method allows to equip a Hero with a specified Armor.
     * @param my the Hero to equip.
     * @param arm the Armor to equip the Hero with.
     */
    void equip(Hero my, Armor arm);
    
    /**
     * This method allows to equip a Hero with a specified Weapon.
     * @param my the Hero to equip.
     * @param w the Weapon to equip the Hero with.
     */
    void arm(Hero my, Weapon w);
    
}
