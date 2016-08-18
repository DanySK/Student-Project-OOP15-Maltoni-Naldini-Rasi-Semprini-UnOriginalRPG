package it.unibo.unori.controller.actionlistener;

import java.awt.event.ActionEvent;

import it.unibo.unori.controller.state.BattleState;
import it.unibo.unori.controller.state.DialogState.ErrorSeverity;
import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.battle.exceptions.NotEnoughMPExcpetion;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.exceptions.MagicNotFoundException;

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
            try {
                currentState.getModel().useMagicAttack(magic, enemy, true);
            } catch (NotEnoughMPExcpetion e) {
                this.getController().showError(e.getMessage(), ErrorSeverity.MINOR);
            } catch (MagicNotFoundException e) {
                this.getController().showError(e.getMessage(), ErrorSeverity.SERIUOS);
            }
        }
        // TODO not valid state exception
    }

}
