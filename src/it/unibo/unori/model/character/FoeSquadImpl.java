package it.unibo.unori.model.character;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.unori.model.character.exceptions.MaxFoesException;

/**
 * Implementation of a FoeSquad, a team of Foes to be presented in Battle Mode.
 *
 */
public class FoeSquadImpl implements FoeSquad {
    
    
    /**
     * 
     */
    private static final long serialVersionUID = 4383761859347253973L;
    
    /**
     * Max number of Foes allowed in a Team.
     */
    public static final int MAXEN = 4;
    private List<Foe> enemies;
    
    /**
     * Constructor for a FoeSquad having already a List of Foes as a parameter.
     * @param list the List of Foes.
     * @throws IllegalArgumentException if the List is bigger than MAXEN.
     */
    public FoeSquadImpl(final List<Foe> list) throws IllegalArgumentException {
        if (list.size() > MAXEN) {
            throw new IllegalArgumentException();
        }
        this.enemies = list;
    }
    
    /**
     * Constructor to instantiate a FoeSquad having just a Foe as a parameter.
     * @param en the first Foe to populate the squad.
     */
    public FoeSquadImpl(final Foe en) {
        this.enemies = new ArrayList<>();
        this.enemies.add(en);
    }
    
    /**
     * Constructor with no parameters to first create an empty Squad.
     */
    public FoeSquadImpl() {
        this.enemies = new ArrayList<>();
    }
    
    //Method to check the size of the List.
    private void checkListSize() throws IllegalStateException {
        if (this.enemies.size() == 0) {
            throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean isEmpty() {
        return this.enemies.isEmpty();
    }
    
    @Override
    public boolean isDefeated(final Foe f) {
        return f.getStatus().equals(Status.DEAD);
    }
    
    @Override
    public void addFoe(final Foe toAdd) throws MaxFoesException {
        if (this.enemies.size() == MAXEN) {
            throw new MaxFoesException();
        }
        this.enemies.add(toAdd);
    }
    
    @Override
    public void removeFoe(final Foe toRemove) throws IllegalArgumentException {
        if (!this.enemies.contains(toRemove) || this.enemies.size() == 1) {
            throw new IllegalArgumentException();
        }
        this.enemies.remove(toRemove);

    }
    
    @Override
    public List<Foe> getAllFoes() throws IllegalStateException {
        this.checkListSize();
        return new ArrayList<>(this.enemies);
    }

    @Override
    public List<Foe> getAliveFoes() throws IllegalStateException {
        this.checkListSize();
        return this.enemies.stream().filter(i -> !i.getStatus().equals(Status.DEAD))
                .collect(Collectors.toList());
    }
    
    @Override
    public Foe getFirstFoeOnTurn() throws IllegalStateException {
        this.checkListSize();
        return this.enemies.get(0);
    }
    
    @Override
    public String defeatFoe(final Foe f) throws IllegalArgumentException {
        if (this.isDefeated(f)) {
            return f.getName() + " e' stato sconfitto!";
        } else {
            return f.getName() + " incassa il colpo!";
        }
    }
    
    @Override
    public String getNameOfSquad() {
        return this.enemies.size() + " nemici!";
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
        result = prime * result + ((enemies == null) ? 0 : enemies.hashCode());
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
        final FoeSquadImpl other = (FoeSquadImpl) obj;
        return this.enemies.equals(other.getAllFoes());
    }
}
