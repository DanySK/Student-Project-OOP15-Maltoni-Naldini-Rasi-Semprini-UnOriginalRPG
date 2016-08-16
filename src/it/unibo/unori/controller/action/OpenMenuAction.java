package it.unibo.unori.controller.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import it.unibo.unori.controller.Controller;
import it.unibo.unori.controller.SingletonStateMachine;
import it.unibo.unori.controller.exceptions.NotValidStateException;
import it.unibo.unori.controller.state.MapState;

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
        if (MapState.class.isInstance(this.controller.getCurrentState())) {
            try {
                this.controller.openMenu();
            } catch (NotValidStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            // TODO
        }
    }

}
