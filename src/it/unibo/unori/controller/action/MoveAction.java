package it.unibo.unori.controller.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import it.unibo.unori.controller.Controller;
import it.unibo.unori.controller.SingletonStateMachine;
import it.unibo.unori.controller.state.MapState;
import it.unibo.unori.model.maps.Party;

/**
 * Action that should be linked with movement dedicated buttons as WASD or UP,
 * DOWN, LEFT, RIGHT. This uses the controller to query the model if the
 * movement is possible: if yes, does the movement both on model and view, if
 * not, does nothing.
 */
public class MoveAction extends AbstractAction {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -7895803067785268380L;

    private final Party.CardinalPoints direction;
    private final Controller controller;

    /**
     * Default constructor.
     * 
     * @param direction
     *            the direction to move to
     */
    public MoveAction(final Party.CardinalPoints direction) {
        super();
        this.direction = direction;
        this.controller = SingletonStateMachine.getController();
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (this.controller.getCurrentStateClass().isInstance(MapState.class)) {
            final MapState currentState = (MapState) this.controller.getCurrentState();
            if (currentState.moveParty(this.direction)) {
             // ((MapLayer) currentState.getLayer()).moveParty(this.direction);
            }
        }
    }
}