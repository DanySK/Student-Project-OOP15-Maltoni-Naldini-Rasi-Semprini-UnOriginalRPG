package it.unibo.unori.model.items;

import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;

/**
 * Factory to create some common weapon.
 *
 */
public class WeaponFactory {
 
    /**
     * Create a standard sword.
     * @return Standard Sword object
     */
    public Weapon getStdSword() {
        final Map<Statistics, Integer> stats = new HashMap<Statistics, Integer>();
        stats.put(Statistics.PHYSICATK, 100);
        stats.put(Statistics.FIREATK, 0);
        stats.put(Statistics.ICEATK, 0);
        stats.put(Statistics.THUNDERATK, 0);
        return new WeaponImpl("Spada Semplice",
                "Adatta per tagliare il burro più che i nemici",
                stats, Status.NONE);
    }

}
