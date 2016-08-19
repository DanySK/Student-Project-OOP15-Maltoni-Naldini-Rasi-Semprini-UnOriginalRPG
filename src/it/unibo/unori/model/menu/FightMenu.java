package it.unibo.unori.model.menu;

import it.unibo.unori.model.battle.Battle;
import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.battle.exceptions.BarNotFullException;
import it.unibo.unori.model.battle.exceptions.CantEscapeException;
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
    
    private final Battle battle;
   /**
    * Standard constructor for a Fight Menu.
    * @param batt the Battle from which generate the FightMenu.
    */
   public FightMenu(final Battle batt) {
       this.battle = batt;
   }
   
    @Override
    public DialogueInterface attack(final boolean whoAttacks) throws NoWeaponException {
        return this.getBattle().attack(whoAttacks);
    }

    @Override
    public DialogueInterface magic(final MagicAttackInterface m,
            final Foe enemy, final boolean whosFirst) 
                    throws NotEnoughMPExcpetion, MagicNotFoundException {
        return this.getBattle().useMagicAttack(m, enemy, whosFirst);
    }

    @Override
    public DialogueInterface specialAtk() throws BarNotFullException {
        return this.getBattle().specialAttack();
    }

    @Override
    public DialogueInterface defend(final Hero toDefend) 
            throws NotDefendableException {
        return this.getBattle().defend(toDefend);
    }
    
    @Override
    public DialogueInterface runAway() {
        try {
            return this.battle.runAway();
        } catch (CantEscapeException e) {
            return new Dialogue(e.toString());
        }
    }
    
    @Override
    public int currentSpecialBar() {
        return this.battle.getHeroOnTurn().getCurrentBar();
    }
    
    @Override
    public Battle getBattle() {
        return this.battle;
    }

}
