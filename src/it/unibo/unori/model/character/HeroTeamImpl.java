package it.unibo.unori.model.character;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.unori.model.character.exceptions.MaxHeroException;

/**
 * Implementation of HeroTeam interface.
 *
 */
public class HeroTeamImpl implements HeroTeam {

    /**
     * 
     */
    private static final long serialVersionUID = 7340817911963589484L;
    
    /**
     * Max number of Heroes allowed in a Team.
     */
    public static final int MAXHERO = 4;
    private final List<Hero> heroList;

    /**
     * Constructor with an existent list of heroes.
     * 
     * @param l
     *            the input list
     * @throws IllegalArgumentException
     *             if the size of the list is too big
     */
    public HeroTeamImpl(final List<Hero> l) throws IllegalArgumentException {
        if (l.size() > MAXHERO) {
            throw new IllegalArgumentException();
        }
        this.heroList = l;
    }

    /**
     * Create a list with a single hero.
     * @param h
     *          hero to add 
     */
    public HeroTeamImpl(final Hero h) {
        this.heroList = new ArrayList<>();
        this.heroList.add(h);
    }

    /**
     * Create a list with no heroes.
     */
    public HeroTeamImpl() {
        this.heroList = new ArrayList<>();
    }

    private void checkListSize() throws IllegalStateException {
        if (this.heroList.size() == 0) {
            throw new IllegalStateException();
        }
    }
    
    @Override
    public boolean isEmpty() {
        return this.heroList.isEmpty();
    }

    @Override
    public void addHero(final Hero h) throws MaxHeroException {
        if (this.heroList.size() == MAXHERO) {
            throw new MaxHeroException();
        }
        this.heroList.add(h);
    }

    @Override
    public void removeHero(final Hero h) throws IllegalArgumentException {
        if (!this.heroList.contains(h) || this.heroList.size() == 1) {
            throw new IllegalArgumentException();
        }
        this.heroList.remove(h);
    }

    @Override
    public List<Hero> getAllHeroes() throws IllegalStateException {
        this.checkListSize();
        return new ArrayList<>(this.heroList);
    }

    @Override
    public List<Hero> getAliveHeroes() throws IllegalStateException {
        this.checkListSize();
        return this.heroList.stream().filter(i -> !i.getStatus().equals(Status.DEAD))
                .collect(Collectors.toList());
    }
    
    @Override
    public Hero getFirstHeroOnTurn() throws IllegalStateException {
        this.checkListSize();
        return this.heroList.get(0);
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
        result = prime * result + ((heroList == null) ? 0 : heroList.hashCode());
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
        final HeroTeamImpl other = (HeroTeamImpl) obj;
        return this.heroList.equals(other.getAllHeroes());
    }

    @Override
    public boolean isDefeated(final Hero h) {
        return h.getStatus().equals(Status.DEAD);
    }

    @Override
    public String defeatHero(final Hero h) throws IllegalArgumentException {
        if (this.isDefeated(h)) {
            return h.getName() + " e' stato sconfitto!";
        } else {
            return h.getName() + " incassa il colpo!";
        }
        
    }

}
