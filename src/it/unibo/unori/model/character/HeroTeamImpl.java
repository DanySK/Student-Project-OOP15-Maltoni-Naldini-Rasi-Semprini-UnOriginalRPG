package it.unibo.unori.model.character;

import java.util.List;

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

    @Override
    public void addHero(Hero h) throws IndexOutOfBoundsException {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeHero(Hero h) throws IllegalArgumentException {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Hero> getAllHeroes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Hero> getAliveHeroes() {
        // TODO Auto-generated method stub
        return null;
    }

}
