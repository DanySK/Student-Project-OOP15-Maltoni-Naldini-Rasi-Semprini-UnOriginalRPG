package it.unibo.unori.model.items;

import it.unibo.unori.model.character.Statistics;

/**
 * Factory to generate some Potions.
 *
 */
public class PotionFactory {
    
    /**
     * Method that generates a simple and standard Potion.
     * @return a simple Potion.
     */
    public Potion getStdPotion() {
        return new PotionImpl(1, Statistics.PHYSICATK, "Acqua", 
                "Un rimedio tanto comune quanto inutile", false);
    }
}
