package it.unibo.unori.controller.actionlistener;

import java.awt.event.ActionEvent;

import it.unibo.unori.controller.state.BattleState;
import it.unibo.unori.controller.state.DialogState.ErrorSeverity;
import it.unibo.unori.model.character.exceptions.NoWeaponException;

/**
 * This should be linked to button that make the hero attack that turn.
 */
public class AttackActionListener extends AbstractUnoriActionListener {

    public AttackActionListener() {
        super();
        // TODO
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (BattleState.class.isInstance(this.getController().getCurrentState())) {
            final BattleState currentState = (BattleState) this.getController().getCurrentState();
            try {
                currentState.getModel().attack(true);
            } catch (NoWeaponException e) {
                this.getController().showError(e.getMessage(), ErrorSeverity.MINOR);
            }
        }
     // TODO not valid state exception
    }

}
