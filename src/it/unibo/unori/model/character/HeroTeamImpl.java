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
    private static final int MAXHERO = 4;
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
    public List<Hero> getAllHeroes() {
        return new ArrayList<>(this.heroList);
    }

    @Override
    public List<Hero> getAliveHeroes() {
        return this.heroList.stream().filter(i -> !i.getStatus().equals(Status.DEAD))
                .collect(Collectors.toList());
    }

}
