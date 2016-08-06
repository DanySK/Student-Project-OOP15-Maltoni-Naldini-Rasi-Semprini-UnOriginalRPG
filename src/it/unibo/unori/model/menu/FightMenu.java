package it.unibo.unori.model.menu;

import it.unibo.unori.model.battle.Battle;
import it.unibo.unori.model.battle.MagicAttack;
import it.unibo.unori.model.battle.exceptions.BarNotFullException;
import it.unibo.unori.model.battle.exceptions.NotDefendableException;
import it.unibo.unori.model.battle.exceptions.NotEnoughMPExcpetion;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.exceptions.MagicNotFoundException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;

/**
 * Class that implements FightInterface, to model a fight menu in Battle.
 *
 */
public class FightMenu implements FightInterface {
    
   private final Battle bat;
   
   public FightMenu(final Battle battle) {
       this.bat = battle;
   }
   
    @Override
    public String attack(final boolean whoAttacks) throws NoWeaponException {
        return this.bat.attack(whoAttacks);
    }

    @Override
    public String magic(final MagicAttack m, final Foe enemy, final boolean whosFirst) 
            throws NotEnoughMPExcpetion, MagicNotFoundException {
        return this.bat.useMagicAttack(m, enemy, whosFirst);
    }

    @Override
    public String specialAtk() throws BarNotFullException {
        return this.bat.specialAttack();
    }

    @Override
    public String defend(final Hero toDefend) throws NotDefendableException {
        return this.bat.defend(toDefend);
    }

}
