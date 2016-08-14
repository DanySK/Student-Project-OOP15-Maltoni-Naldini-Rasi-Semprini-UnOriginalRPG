package it.unibo.unori.controller.actionlistener;

import java.awt.event.ActionEvent;

import it.unibo.unori.controller.state.BattleState;
import it.unibo.unori.controller.state.InGameMenuState;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.exceptions.HeroDeadException;
import it.unibo.unori.model.items.exceptions.HeroNotDeadException;
import it.unibo.unori.model.items.exceptions.ItemNotFoundException;

public class ObjectUseActionListener extends AbstractUnoriActionListener {
    private final Item itemUsed;
    private final Hero targetHero;

    public ObjectUseActionListener(final Item item, final Hero target) {
        super();
        this.itemUsed = item;
        this.targetHero = target;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        if(this.getController().getCurrentStateClass().isInstance(BattleState.class) && this.itemUsed.getClass().isInstance(Potion.class)) {
            final BattleState currentState = (BattleState) this.getController().getCurrentState();
            try {
                currentState.getModel().usePotion(targetHero, (Potion) itemUsed);
            } catch (ItemNotFoundException | HeroDeadException | HeroNotDeadException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        } else if(this.getController().getCurrentStateClass().isInstance(InGameMenuState.class)) {
            
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Item getItemUsed() {
        return itemUsed;
    }

    public Hero getTargetHero() {
        return targetHero;
    }
}
