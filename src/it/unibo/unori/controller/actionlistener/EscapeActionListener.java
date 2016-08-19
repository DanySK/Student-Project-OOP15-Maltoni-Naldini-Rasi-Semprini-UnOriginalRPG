package it.unibo.unori.controller.actionlistener;

import java.awt.event.ActionEvent;

import it.unibo.unori.controller.exceptions.UnexpectedStateException;
import it.unibo.unori.controller.state.BattleState;
import it.unibo.unori.controller.state.DialogState.ErrorSeverity;
import it.unibo.unori.model.battle.exceptions.CantEscapeException;
/**
 * This should be linked to the button that make the hero try to run away from the battle.
 */
public class EscapeActionListener extends AbstractUnoriActionListener {

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (BattleState.class.isInstance(this.getController().getCurrentState())) {
            final BattleState currentState = (BattleState) this.getController().getCurrentState();
            try {
                currentState.getModel().runAway();
            } catch (CantEscapeException e) {
                this.getController().showCommunication(new UnexpectedStateException().getMessage());
            }
        } else {
            this.getController().showError(new UnexpectedStateException().getMessage());
        }
    }

}
