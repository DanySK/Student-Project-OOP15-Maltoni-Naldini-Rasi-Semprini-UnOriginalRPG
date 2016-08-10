package it.unibo.unori.controller.actionlistener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import it.unibo.unori.controller.Controller;
import it.unibo.unori.controller.SingletonStateMachine;

/**
 * This abstract class models the common part of a specific ActionListener for this game.
 */
public abstract class AbstractUnoriActionListener implements ActionListener {
    private final Controller control;

    /**
     * Default constructor. It instantiates the internal controller.
     */
    public AbstractUnoriActionListener() {
        this.control = SingletonStateMachine.getController(); 
    }

    @Override
    public abstract void actionPerformed(final ActionEvent event);

    /**
     * Getter method for internal controller. Useful in subclasses of this class.
     * 
     * @return the controller
     */
    public Controller getController() {
        return this.control;
    }

}
