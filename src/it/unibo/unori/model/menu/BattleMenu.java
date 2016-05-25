package it.unibo.unori.model.menu;

import it.unibo.unori.model.battle.Battle;
import it.unibo.unori.model.battle.BattleImpl;
import it.unibo.unori.model.battle.exceptions.CantEscapeException;

/**
 * First trial for an in-Battle model menu.
 */
public class BattleMenu implements BattleMenuInterface {
    
    private final Battle battle;
    
    /**
     * Standard constructor.
     * @param battle the current Battle.
     */
    public BattleMenu(final BattleImpl battle) {
        this.battle = battle;
    }
    
    @Override
    public void runAway() throws CantEscapeException {
        this.battle.runAway(this.battle.getFoeOnTurn(), this.battle.getHeroOnTurn());
    }
    
    @Override
    public void useBag() {
        //TODO
    }
    
    @Override
    public void fight() {
        //TODO
    }
    
    @Override
    public int currentSpecialBar() {
        return this.battle.getHeroOnTurn().getCurrentBar();
    }
}
