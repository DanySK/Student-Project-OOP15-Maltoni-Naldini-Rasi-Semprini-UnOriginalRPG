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
    
    /**
     * A constructor for a Magic Attack.
     * @param name the name of the attack.
     * @param shownString the String to show in Battle.
     * @param description the description of the attack.
     * @param fireAtk the fire power of the attack.
     * @param thunderAtk the thunder power of the attack.
     * @param iceAtk the ice power of the attack.
     * @param physicAtk the physic power of the attack.
     * @param accuracy the accuracy of the attack (it goes from 1 to 10).
     * @param mp the MP required to throw the attack.
     */
    public MagicAttack(final String name, final String shownString, 
            final String description, final int fireAtk, final int thunderAtk,
            final int iceAtk, final int physicAtk, final int accuracy, final int mp) {
        
        this.name = name;
        this.shownString = shownString;
        this.description = description;
        this.stats = new HashMap<>();
        this.stats.put(Statistics.FIREATK, fireAtk);
        this.stats.put(Statistics.THUNDERATK, thunderAtk);
        this.stats.put(Statistics.ICEATK, iceAtk);
        this.stats.put(Statistics.PHYSICATK, physicAtk);
        this.accuracy = accuracy;
        this.mpRequired = mp;
        
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
    
    @Override
    public Map<Statistics, Integer> getMap() {
        Map<Statistics, Integer> mapToCheckMagic = new HashMap<>();
        mapToCheckMagic.put(Statistics.FIREATK, this.getFireAtk());
        mapToCheckMagic.put(Statistics.ICEATK, this.getIceAtk());
        mapToCheckMagic.put(Statistics.THUNDERATK, this.getThunderAtk());
        return mapToCheckMagic;
    }
    
    /**
     * HashCode method implemented using auto generation.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + accuracy;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + mpRequired;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((shownString == null) ? 0 : shownString.hashCode());
        result = prime * result + ((stats == null) ? 0 : stats.hashCode());
        return result;
    }
    
    /**
     * Equals method implemented for the serialization.
     * 
     * @see java.lang.Object#equals(Object obj).
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true; 
        }
        if (obj == null) {
            return false; 
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final MagicAttack other = (MagicAttack) obj;
        final Map<Statistics, Integer> map = this.stats;
        return this.accuracy == other.getAccuracy() && this.description == other.getDescription()
                && (this.name).equals(other.toString()) && this.mpRequired == other.getMPRequired()
                && this.shownString == other.getStringToShow()
                && other.getFireAtk() == map.get(Statistics.FIREATK)
                && other.getIceAtk() == map.get(Statistics.ICEATK)
                && other.getPhysicAtk() == map.get(Statistics.PHYSICATK)
                && other.getThunderAtk() == map.get(Statistics.THUNDERATK);
    }
    
}
