package it.unibo.unori.model.character;

import java.io.Serializable;
import java.util.List;

import it.unibo.unori.model.character.exceptions.MaxFoesException;

/**
 * An interface to model a team of Foes to be presented in Batlle Mode.
 *
 */
public interface FoeSquad extends Serializable {
    
    /**
     * Method to add a foe to the Squad.
     * @param toAdd the Foe to be added.
     * @throws MaxFoesException if the FoeSquad is full.
     */
    void addFoe(final Foe toAdd) throws MaxFoesException;
    
    /**
     * Method to remove a foe from the Squad.
     * @param toRemove the foe to be removed.
     * @throws IllegalArgumentException if the FoeSquad is empty or if the Foe to be
     * removed is not present in the Squad.
     */
    void removeFoe(final Foe toRemove) throws IllegalArgumentException;
    
    /**
     * A getter method that returns the whole Squad of Foes.
     * @return the whole Squad.
     * @throws IllegalStateException if the list of Foes is empty.
     */
    List<Foe> getAllFoes() throws IllegalStateException;
    
    /**
     * A getter method that returns the list of the Foes whose status is not DEAD.
     * @return the list of alive Foes
     * @throws IllegalStateException if the list of Foes is empty.
     */
    List<Foe> getAliveFoes() throws IllegalStateException;
    
    /**
     * A getter method that returns the first Foe who's allowed to move in Battle.
     * @return the first Foe of the Squad.
     * @throws IllegalStateException if the list of Foes is empty.
     */
    Foe getFirstFoeOnTurn() throws IllegalStateException;
    
    /**
     * Method to be Called when a Foe is defeated.
     * @param f the Foe interested
     * @return a confirmation String
     * @throws IllegalArgumentException if the foe is not present on the aliveFoes List.
     */
    String defeatFoe(Foe f) throws IllegalArgumentException;
    
    /**
     * Method to check if a Foe is defeated.
     * @param f the Foe to check.
     * @return true if the Foe is defeated, false otherwise.
     */
    boolean isDefeated(Foe f);
    
    /**
     * Method to check if the FoeSquad is empty.
     * @return true if the Squad is Empty, false otherwise.
     */
    boolean isEmpty();
    
    /**
     * This getter method returns a String representing the FoeSquad.
     * @return a String used as a name for the Squad.
     */
    String getNameOfSquad();

    /**
     * Method that returns the next Foe who can be set on turn in Battle.
     * @return the next Foe on turn.
     */
    Foe getNextFoe();
}
