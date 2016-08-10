package it.unibo.unori.model.menu;

import it.unibo.unori.model.battle.MagicAttack;
import it.unibo.unori.model.battle.exceptions.BarNotFullException;
import it.unibo.unori.model.battle.exceptions.NotDefendableException;
import it.unibo.unori.model.battle.exceptions.NotEnoughMPExcpetion;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.exceptions.MagicNotFoundException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;

/**
 * Interface to model a in-Battle Menu, fight session.
 *
 */
public interface FightInterface {
    
    /**
     * Method that has to call a special Attack from Battle.
     * @return a confirmation String
     * @throws BarNotFullException if the special Bar is not full yet.
     */
    String specialAtk() throws BarNotFullException;
    
    /**
     * Method that calls a defense from Battle.
     * @param toDefend the Hero to be defended.
     * @return a confirmation String. 
     * @throws NotDefendableException if the Hero set as a parameter is not defendable.
     */
    String defend(Hero toDefend) throws NotDefendableException;
    
    /**
     * Method that calls an attack from Battle.
     * @param whoAttacks true if the Hero attacks, false if the Foe attacks.
     * @return a confirmation String.
     * @throws NoWeaponException if the Hero (or the Foe, it depends on who attacks) is not holding
     * any Weapon.
     */
    String attack(boolean whoAttacks) throws NoWeaponException;
    
    /**
     * Method that calls a magic attack from Battle.
     * @param m the MagicAttack to use.
     * @param enemy the enemy to attack.
     * @param whosFirst true if the Hero is going to throw the attack, false if it's a Foe.
     * @return a confirmation String. 
     * @throws NotEnoughMPExcpetion if the Hero (or the Foe) has not got enough MPs.
     * @throws MagicNotFoundException if the Hero (or the Foe) has not got the Magic in his spell list.
     */
    String magic(MagicAttack m, Foe enemy, boolean whosFirst) 
            throws NotEnoughMPExcpetion, MagicNotFoundException;
}
