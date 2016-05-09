package it.unibo.unori.model.battle;
import java.util.HashMap;

import java.util.Map;
import it.unibo.unori.model.character.Statistics;

/**
 * List of possible magic attacks available.
 *
 */
public enum MagicAttacks {
    
    TUONO("Tuono", "Hai lanciato un Tuono!", "Descrizione", 0, 3, 0, 2, 7),
    TEMPESTA("Tempesta", "Hai generato una Tempesta!", "Descrizione", 0, 4, 1, 3, 7),
    COMETA("Cometa", "Hai lanciato una Cometa!", "Descrizione", 2, 7, 1, 4, 5),
    FIAMMA("Fiamma", "Hai lanciato una Fiamma!", "Descrizione", 3, 0, 0, 2, 7),
    FUOCO_INCROCIATO("Fuoco incrociato", "Hai lanciato una croce di Fuoco!",
            "Descrizione", 5, 1, 0, 3, 6),
    TERRA_BRUCIATA("Terra bruciata", "Hai generato un Incendio!",
            "Descrizione", 8, 1, 1, 5, 4),
    PUGNO_GELATO("Pugno Gelato", "Hai tirato un pugno congelato!",
            "Descrizione", 0, 0, 2, 2, 8),
    RAGGIO_GHIACCIATO("Raggio Ghiacciato", "Hai usato un raggio ghiacciato!",
            "Descrizione", 0, 1, 4, 3, 7),
    GELO("Gelo", "Hai creato una Tempesta Gelata!", "Descrizione", 1, 2, 7, 5, 6);
    
    private final String name;
    private final String shownString;
    private final String description;
    private final Map<Statistics, Integer> stats;
    private final int accuracy;
    
    
    private MagicAttacks(final String name, final String shownString, 
            final String description, final int fireAtk, final int thunderAtk,
            final int iceAtk, final int physicAtk, final int accuracy) {
        
        this.name = name;
        this.shownString = shownString;
        this.description = description;
        this.stats = new HashMap<>();
        this.stats.put(Statistics.FIREATK, fireAtk);
        this.stats.put(Statistics.THUNDERATK, thunderAtk);
        this.stats.put(Statistics.ICEATK, iceAtk);
        this.stats.put(Statistics.PHYSICATK, physicAtk);
        this.accuracy = accuracy;
        
    }
}
