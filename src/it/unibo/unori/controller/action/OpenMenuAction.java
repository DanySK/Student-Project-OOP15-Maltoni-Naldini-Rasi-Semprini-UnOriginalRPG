package it.unibo.unori.controller.action;

import java.awt.event.ActionEvent;

import it.unibo.unori.controller.exceptions.UnexpectedStateException;

/**
 * Action that should be linked to menu dedicated buttons as for example ESC.
 * This will open the menu if possible.
 */
public class OpenMenuAction extends AbstractUnoriAction {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 8382900980886929571L;

    @Override
    public void actionPerformed(final ActionEvent event) {
        try {
            this.getController().openMenu();
        } catch (UnexpectedStateException e) {
            this.getController().showError(e.getMessage());
        }
    }

}
