package it.unibo.unori.controller.actionlistener;

import java.awt.event.ActionEvent;

import it.unibo.unori.controller.exceptions.UnexpectedStateException;
import it.unibo.unori.controller.state.BattleState;
import it.unibo.unori.model.character.Hero;

/**
 * This ActionListener should be linked to the button that make the hero another hero that turn during battle.
 */
public class DefendActionListener extends AbstractUnoriActionListener {
    private final Hero heroToDefend;

    /**
     * Default constructor.
     * 
     * @param heroToDefend
     *            the hero to defend
     */
    public DefendActionListener(final Hero heroToDefend) {
        super();
        this.heroToDefend = heroToDefend;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (BattleState.class.isInstance(this.getController().getCurrentState())) {
            final BattleState currentState = (BattleState) this.getController().getCurrentState();
            currentState.defend(this.heroToDefend);
        } else {
            this.getController().showError(new UnexpectedStateException().getMessage());
        }
    }

}
