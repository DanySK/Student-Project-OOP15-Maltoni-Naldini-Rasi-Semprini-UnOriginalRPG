package it.unibo.unori.model.character;

import java.util.List;

/**
 * Interface to define methods to handle the characters 
 * as statistics and equipments.
 *
 */
public interface HeroTeam {

    /**
     * Add a hero to the party.
     * @param h
     *          hero to add
     * @throws IndexOutOfBoundsException if the party is already full
     */
    void addHero(Hero h) throws IndexOutOfBoundsException;

    /**
     * Remove hero selected.
     * @param h
     *          hero to remove
     * @throws IllegalArgumentException if the hero is not present in the party
     */
    void removeHero(Hero h) throws IllegalArgumentException;

    /**
     * Return the list of heroes.
     * @return a list containing all the heroes of the party
     */
    List<Hero> getAllHeroes();

    /**
     * Return a list of the alive heroes. 
     * @return
     *          a list containing the alive heroes
     */
    List<Hero> getAliveHeroes();

}
