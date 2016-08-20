package it.unibo.unori.controller.actionlistener;

import java.awt.event.ActionEvent;

import it.unibo.unori.controller.exceptions.UnexpectedStateException;
import it.unibo.unori.controller.state.BattleState;

/**
 * This should be linked to the button that make the hero try to run away from the battle.
 */
public class EscapeActionListener extends AbstractUnoriActionListener {

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (BattleState.class.isInstance(this.getController().getCurrentState())) {
            final BattleState currentState = (BattleState) this.getController().getCurrentState();
            currentState.runAway();
        } else {
            this.getController().showError(new UnexpectedStateException().getMessage());
        }
    }

}
