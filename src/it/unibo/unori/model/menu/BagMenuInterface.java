package it.unibo.unori.model.menu;

import java.util.List;
import java.util.Map;

import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.menu.utility.Pair;

/**
 * Interface to model an in-Bag Menu.
 *
 */
public interface BagMenuInterface {
    
    /**
     * Method to scroll the Bag Items up by one.
     * @return 
     */
    void scrollUp();
    
    
    /**
     * Method to scroll the Bag Items down by one.
     * @return 
     */
    void scrollDown();
    
    
    /**
     * Getter method that returns the entire bag content.
     * @return all the Items in the Bag.
     */
    Map<Item, Integer> getAllItems();
    
    /**
     * Getter method that returns all the Armors in the bag.
     * @return all the Armors in the Bag.
     */
    Map<Armor, Integer> getArmors();
    
    /**
     * Getter method that returns all the Weapons in the bag.
     * @return all the Weapons in the Bag.
     */
    Map<Weapon, Integer> getWeapons();
    
    /**
     * Getter method that returns all the Potions in the bag.
     * @return all the Potions in the Bag.
     */
    Map<Potion, Integer> getPotions();
    
    /**
     * Getter method the whole Bag itself.
     * @return the Bag.
     */
    Bag getBag();

    /**
     * Method to update the Menu with another Bag. 
     * For Test purposes.
     * @param b the bag to update with.
     */
    void update(Bag b);

    /**
     * Method that returns the list of Items stored in BagMenu.
     * For Test purposes.
     * @return the list of Items stored.
     */
    List<Pair<Item, Integer>> getList();

    /**
     * Getter method that returns the currently selected Item, with its relative quantity.
     * @return the currently selected Item and its quantity.
     */
    Pair<Item, Integer> getSelected();
}
