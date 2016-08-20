package it.unibo.unori.controller.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import it.unibo.unori.controller.Controller;
import it.unibo.unori.controller.SingletonStateMachine;

/**
 * This abstract class models the common part of a specific Action for this game.
 */
public abstract class AbstractUnoriAction extends AbstractAction {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 2117446124544840608L;
    private final Controller controller;

    /**
     * Default constructor.
     */
    public AbstractUnoriAction() {
        this.controller = SingletonStateMachine.getController();
    }

    @Override
    public abstract void actionPerformed(final ActionEvent arg0);

    /**
     * @return the controller
     */
    public Controller getController() {
        return controller;
    }

}
