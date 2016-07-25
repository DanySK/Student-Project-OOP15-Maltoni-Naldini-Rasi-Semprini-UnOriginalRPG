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
    
    private Map<Statistics, Integer> generateMap(final Statistics phys, final int first,
            final Statistics fire, final int secnd, final Statistics ice,
            final int third, final Statistics thun, final int fourth) {
        final Map<Statistics, Integer> stats = new HashMap<Statistics, Integer>();
        stats.put(phys, first);
        stats.put(fire, secnd);
        stats.put(ice, third);
        stats.put(thun, fourth);
        
        return new HashMap<>(stats);
    }
    
    /**
     * Create a standard sword.
     * @return Standard Sword object
     */
    public Weapon getStdSword() {
        return new WeaponImpl("Spada Semplice",
                "Adatta per tagliare il burro più che i nemici",
                this.generateMap(
                    Statistics.PHYSICATK, 100,
                    Statistics.FIREATK, 0,
                    Statistics.ICEATK, 0,
                    Statistics.THUNDERATK, 0
                    ),
                Status.NONE);
    }
    
    public Weapon getPugnale() {
        return new WeaponImpl("Pugnale",
                "Un'arma adatta per gli attacchi fisici. Non ha nulla di magico",
                this.generateMap(
                    Statistics.PHYSICATK, 500,
                    Statistics.FIREATK, 0,
                    Statistics.ICEATK, 0,
                    Statistics.THUNDERATK, 0
                    ),
                Status.BLEEDING);
    }
    
    public Weapon getClava() {
        return new WeaponImpl("Clava Pesante",
                "Una Clava può ferire nell'orgoglio e nel corpo",
                this.generateMap(
                    Statistics.PHYSICATK, 350,
                    Statistics.FIREATK, 0,
                    Statistics.ICEATK, 10,
                    Statistics.THUNDERATK, 0
                    ),
                Status.NONE);
    }
    
    public Weapon getBalestra() {
        return new WeaponImpl("Balestra Antica",
                "Arma appartenuta a gloriosi guerrieri del passato",
                this.generateMap(
                    Statistics.PHYSICATK, 450,
                    Statistics.FIREATK, 12,
                    Statistics.ICEATK, 0,
                    Statistics.THUNDERATK, 20
                    ),
                Status.NONE);
    }
    
    public Weapon getMaledizione() {
        return new WeaponImpl("Maledizione Verbale",
                "Anche se è un'arma verbale, una maledizione non è da sottovalutare",
                this.generateMap(
                    Statistics.PHYSICATK, 150,
                    Statistics.FIREATK, 9,
                    Statistics.ICEATK, 20,
                    Statistics.THUNDERATK, 15
                    ),
                Status.CURSED);
    }
    
    public Weapon getChiodo() {
        return new WeaponImpl("Chiodo Arrugginito",
                "Enorme chiodo appuntito in grado di avvelenare",
                this.generateMap(
                    Statistics.PHYSICATK, 220,
                    Statistics.FIREATK, 8,
                    Statistics.ICEATK, 10,
                    Statistics.THUNDERATK, 15
                    ),
                Status.POISONED);
    }
    //TODO
}
