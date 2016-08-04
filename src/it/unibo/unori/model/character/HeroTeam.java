package it.unibo.unori.model.character;

import java.io.Serializable;
import java.util.List;

import it.unibo.unori.model.character.exceptions.MaxHeroException;

/**
 * Interface to define methods to handle the characters 
 * as statistics and equipments.
 *
 */
public interface HeroTeam extends Serializable {

    /**
     * Add a hero to the party.
     * @param h
     *          hero to add
     * @throws MaxHeroException if the party is already full
     */
    void addHero(Hero h) throws MaxHeroException;

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
     * @throws IllegalStateException if the list is empty
     */
    List<Hero> getAllHeroes() throws IllegalStateException;

    /**
     * Return a list of the alive heroes. 
     * @return
     *          a list containing the alive heroes
     *@throws IllegalStateException if the list is empty
     */
    List<Hero> getAliveHeroes() throws IllegalStateException;
    
    /**
     * Returns the first Hero of the Team.
     * @return the Hero on the first place.
     * @throws IllegalStateException if the list is empty
     */
    Hero getFirstHeroOnTurn() throws IllegalStateException;
    
    /**
     * Method to check if a certain Hero is defeated.
     * @param h the Hero to check.
     * @return true if the Hero is dead, false otherwise.
     */
    boolean isDefeated(Hero h);
    
    /**
     * Method to be called in battle, it kills a Hero if his Status goes Dead.
     * @param h the Hero to defeat.
     * @return a confirmation String.
     * @throws IllegalArgumentException if the Hero is not present on the aliveHeroes list.
     */
    String defeatHero(Hero h) throws IllegalArgumentException;

}
