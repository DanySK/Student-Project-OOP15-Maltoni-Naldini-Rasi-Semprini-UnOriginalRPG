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
    
    private static final int MAXEN = 4;
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
}
