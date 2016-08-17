package it.unibo.unori.controller.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import it.unibo.unori.controller.Controller;
import it.unibo.unori.controller.SingletonStateMachine;
import it.unibo.unori.controller.exceptions.NotValidStateException;
import it.unibo.unori.controller.state.DialogState.ErrorSeverity;

/**
 * Action that should be linked to menu dedicated buttons as for example ESC.
 * This will open the menu if possible.
 */
public class OpenMenuAction extends AbstractAction {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 8382900980886929571L;
    private final Controller controller;

    /**
     * Default constructor.
     */
    public OpenMenuAction() {
        super();
        this.controller = SingletonStateMachine.getController();
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        try {
            this.controller.openMenu();
        } catch (NotValidStateException e) {
            this.controller.showError(e.getMessage(), ErrorSeverity.SERIUOS);
            System.out.println("Error in open menu action:");
            e.printStackTrace();
        }
    }

}
