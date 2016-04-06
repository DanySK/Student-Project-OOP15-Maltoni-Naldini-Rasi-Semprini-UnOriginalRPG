package it.unibo.unori.model.items;

/**
 * An Interface modeling a generic Weapon.
 */
public interface Weapon extends Item {
    
    /**
     * This method is a getter, and gives me the amount of
     * damage that the Weapon can inflict.
     * @return the damage.
     */
    int getDamage();
    
}
