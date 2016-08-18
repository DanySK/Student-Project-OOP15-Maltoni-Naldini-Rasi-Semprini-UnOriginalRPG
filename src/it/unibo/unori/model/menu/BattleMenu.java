package it.unibo.unori.model.menu;

import it.unibo.unori.model.battle.Battle;
import it.unibo.unori.model.battle.exceptions.CantEscapeException;
import it.unibo.unori.model.items.Bag;

/**
 * First trial for an in-Battle model menu.
 */
public class BattleMenu implements BattleMenuInterface {
    
    private final Battle battle;
    private final Bag bag;
    
    /**
     * Standard constructor.
     * @param batt the current Battle.
     */
    public BattleMenu(final Battle batt) {
        this.battle = batt;
        this.bag = this.battle.getItemBag();
    }
    
    @Override
    public void runAway() throws CantEscapeException {
        this.battle.runAway();
    }
    
    @Override
    public BagMenuInterface useBag() {
        return new BagMenu(this.battle);
    }
    
    @Override
    public FightInterface fight() {
        return new FightMenu(this.battle);
    }
    
    @Override
    public int currentSpecialBar() {
        return this.battle.getHeroOnTurn().getCurrentBar();
    }
    
    public Battle getBattle() {
        return this.battle;
    }
    
    public Bag getBag() {
        return this.bag;
    }
}
