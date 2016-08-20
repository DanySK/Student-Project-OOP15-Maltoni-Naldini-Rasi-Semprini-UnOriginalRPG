package it.unibo.unori.controller.actionlistener;

import java.awt.event.ActionEvent;

import it.unibo.unori.controller.exceptions.UnexpectedStateException;
import it.unibo.unori.controller.state.BattleState;
import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.Foe;

/**
 * This should be linked to the button that make the hero launch a magic attack to a specified enemy that turn during
 * battle.
 */
public class MagicAttackActionListener extends AbstractUnoriActionListener {
    private final MagicAttackInterface magic;
    private final Foe enemy;

    /**
     * Default constructor.
     * 
     * @param magic
     *            the magic the player chose to use
     * @param enemy
     *            the target the player chose
     */
    public MagicAttackActionListener(final MagicAttackInterface magic, final Foe enemy) {
        super();
        this.magic = magic;
        this.enemy = enemy;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (BattleState.class.isInstance(this.getController().getCurrentState())) {
            final BattleState currentState = (BattleState) this.getController().getCurrentState();
            currentState.magicAttack(this.magic, this.enemy);
        } else {
            this.getController().showError(new UnexpectedStateException().getMessage());
        }
    }

}
