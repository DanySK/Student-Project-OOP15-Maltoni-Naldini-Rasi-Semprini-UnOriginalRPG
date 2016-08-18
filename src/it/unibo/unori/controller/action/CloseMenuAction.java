package it.unibo.unori.controller.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import it.unibo.unori.controller.Controller;
import it.unibo.unori.controller.SingletonStateMachine;
import it.unibo.unori.controller.exceptions.NotValidStateException;
import it.unibo.unori.controller.state.DialogState.ErrorSeverity;

/**
 * This action should be linked to the button dedicated to menu opening (like ESC). It closes an opened InGameMenu.
 */
public class CloseMenuAction extends AbstractAction {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -6379981391618059109L;
    private final Controller controller;

    /**
     * Default constructor.
     */
    public CloseMenuAction() {
        super();
        this.controller = SingletonStateMachine.getController();
    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {
        try {
            this.controller.closeMenu();
        } catch (NotValidStateException e) {
            this.controller.showError(e.getMessage(), ErrorSeverity.SERIUOS);
            System.out.println("Error in close menu action:");
            e.printStackTrace();
        }
    }

}
