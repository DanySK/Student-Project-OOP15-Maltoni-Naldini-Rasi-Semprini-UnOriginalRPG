package it.unibo.unori.model.battle;

import java.util.HashMap;
import java.util.Map;
import it.unibo.unori.model.character.Statistics;

/**
 * This Class allows to create a specific Magic Attack.
 */
public class MagicAttack implements MagicAttackInterface {
    
    /**
     * 
     */
    private static final long serialVersionUID = -160010845074781246L;
    
    private final String name;
    private final String shownString;
    private final String description;
    private final Map<Statistics, Integer> stats;
    private final int accuracy;
    private final int mpRequired;
    
    public MagicAttack(final String name, final String shownString, 
            final String description, final int fireAtk, final int thunderAtk,
            final int iceAtk, final int physicAtk, final int accuracy, final int MP) {
        
        this.name = name;
        this.shownString = shownString;
        this.description = description;
        this.stats = new HashMap<>();
        this.stats.put(Statistics.FIREATK, fireAtk);
        this.stats.put(Statistics.THUNDERATK, thunderAtk);
        this.stats.put(Statistics.ICEATK, iceAtk);
        this.stats.put(Statistics.PHYSICATK, physicAtk);
        this.accuracy = accuracy;
        this.mpRequired = MP;
        
    }
    
    @Override
    public String getDescription() {
        return this.description;
    }
    
    @Override
    public String getStringToShow() {
        return this.shownString;
    }
    
    @Override
    public int getFireAtk() {
        return this.stats.get(Statistics.FIREATK);
    }
    
    @Override
    public int getThunderAtk() {
        return this.stats.get(Statistics.THUNDERATK);
    }
    
    @Override
    public int getIceAtk() {
        return this.stats.get(Statistics.ICEATK);
    }
    
    @Override
    public int getPhysicAtk() {
        return this.stats.get(Statistics.PHYSICATK);
    }
    
    @Override
    public int getAccuracy() {
        return this.accuracy;
    }
    
    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int getMPRequired() {
        return this.mpRequired;
    }
    
}
