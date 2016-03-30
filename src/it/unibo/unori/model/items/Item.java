package it.unibo.unori.model.items;

import java.io.Serializable;

/**
 * An Interface modeling a generic Item.
 */

public interface Item extends Serializable {
    
    /**
     * Gives the name of the Item.
     * @return the name of the Item.
     */
    String getName();
    
    /**
     * Gives the available quantity of the Item.
     * @return the quantity of the Item.
     */
    int getQuantity();
    
    /**
     * Gives the description of the Item.
     * @return the description of the Item.
     */
    String getDescription();
}
