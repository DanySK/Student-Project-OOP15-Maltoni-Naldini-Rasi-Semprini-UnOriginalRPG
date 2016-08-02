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
        return new PotionImpl(10, Statistics.PHYSICATK, "Acqua", "Un rimedio molto comune", false);
    }
}
