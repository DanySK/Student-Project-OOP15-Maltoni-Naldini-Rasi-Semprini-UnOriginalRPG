package it.unibo.unori.model.menu;

import it.unibo.unori.model.battle.exceptions.CantEscapeException;

/**
 * An interface modeling the in-Battle Menu.
 */
public interface BattleMenuInterface {
    
    /**
     * Method to handle the run away choice.
     * @throws CantEscapeException if the Hero can not escape.
     */
    void runAway() throws CantEscapeException;
    
    /**
     * Method to handle the open bag choice.
     */
    void useBag();
    
    /**
     * Method to handle the fight choice.
     */
    void fight();
    
    /**
     * Method to handle the special attack bar implementation.
     * The bar has to be shown in the Battle Menu.
     * @return the amount of points in the Bar.
     */
    int currentSpecialBar();

}
