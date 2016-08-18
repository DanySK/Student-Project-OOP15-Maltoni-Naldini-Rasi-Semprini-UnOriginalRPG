package it.unibo.unori.controller.action;

import java.awt.event.ActionEvent;

import it.unibo.unori.controller.exceptions.NotValidStateException;
import it.unibo.unori.controller.state.DialogState.ErrorSeverity;

/**
 * This action should be linked to the button dedicated to menu opening (like ESC). It closes an opened InGameMenu.
 */
public class CloseMenuAction extends AbstractUnoriAction {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -6379981391618059109L;

    @Override
    public void actionPerformed(final ActionEvent arg0) {
        try {
            this.getController().closeMenu();
        } catch (NotValidStateException e) {
            this.getController().showError(e.getMessage(), ErrorSeverity.SERIUOS);
        }
    }

}
