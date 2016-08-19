package it.unibo.unori.controller.actionlistener;

import java.awt.event.ActionEvent;

import it.unibo.unori.controller.exceptions.UnexpectedStateException;
import it.unibo.unori.controller.state.BattleState;

/**
 * This ActionListener should be linked to the button that make the hero attack that turn during battle.
 */
public class AttackActionListener extends AbstractUnoriActionListener {

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (BattleState.class.isInstance(this.getController().getCurrentState())) {
            ((BattleState) this.getController().getCurrentState()).attack();
        } else {
            this.getController().showError(new UnexpectedStateException().getMessage());
        }
    }

}
