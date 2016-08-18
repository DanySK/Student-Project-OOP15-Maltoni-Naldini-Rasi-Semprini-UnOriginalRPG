package it.unibo.unori.model.menu;

import it.unibo.unori.model.battle.Battle;
import it.unibo.unori.model.battle.exceptions.CantEscapeException;
import it.unibo.unori.model.items.Bag;

/**
 * An interface modeling the in-Battle Menu.
 */
public interface BattleMenuInterface {
    
    /**
     * Method to handle the run away choice.
     * @return a Dialogue.
     * @throws CantEscapeException if the Hero can not escape.
     */
    DialogueInterface runAway() throws CantEscapeException;
    
    /**
     * Method to handle the open bag choice.
     * @return the BagMenu.
     */
    BagMenuInterface useBag();
    
    /**
     * Method to handle the fight choice.
     * @return the FightMenu.
     */
    FightInterface fight();
    
    /**
     * Method to handle the special attack bar implementation.
     * The bar has to be shown in the Battle Menu.
     * @return the amount of points in the Bar.
     */
    int currentSpecialBar();
    
    /**
     * Getter method that returns the Battle.
     * @return the Battle that this Menu has.
     */
    Battle getBattle();
    
    /**
     * Getter Method that returns the Bag of the Battle.
     * @return the Bag of the Battle.
     */
    Bag getBag();

}
