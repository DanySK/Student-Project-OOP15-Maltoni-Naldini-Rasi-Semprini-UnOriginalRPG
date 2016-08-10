package it.unibo.unori.controller.action;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingConstants;

import it.unibo.unori.controller.Controller;
import it.unibo.unori.controller.SingletonStateMachine;
import it.unibo.unori.controller.exceptions.UnsupportedSwingConstantException;
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
                // ((MapLayer)
                // currentState.getLayer()).moveParty(this.direction);
            }
        }
    }

    public static Map<Integer, Action> getSupportedMovementsMap() {
        final Map<Integer, Action> returnMap = new HashMap<>();

        returnMap.put(SwingConstants.NORTH, new MoveAction(convertSwingConstantsToCardinalPoints(SwingConstants.NORTH)));
        returnMap.put(SwingConstants.SOUTH, new MoveAction(convertSwingConstantsToCardinalPoints(SwingConstants.SOUTH)));
        returnMap.put(SwingConstants.WEST, new MoveAction(convertSwingConstantsToCardinalPoints(SwingConstants.WEST)));
        returnMap.put(SwingConstants.EAST, new MoveAction(convertSwingConstantsToCardinalPoints(SwingConstants.EAST)));

        return returnMap;
    }

    public static Party.CardinalPoints convertSwingConstantsToCardinalPoints(final int swingConstant)
            throws UnsupportedSwingConstantException {
        final Optional<Party.CardinalPoints> cardinal;
        switch (swingConstant) {
            case SwingConstants.NORTH:
                cardinal = Optional.of(Party.CardinalPoints.NORTH);
                break;
            case SwingConstants.SOUTH:
                cardinal = Optional.of(Party.CardinalPoints.SOUTH);
                break;
            case SwingConstants.EAST:
                cardinal = Optional.of(Party.CardinalPoints.EAST);
                break;
            case SwingConstants.WEST:
                cardinal = Optional.of(Party.CardinalPoints.WEST);
                break;
            default:
                cardinal = Optional.empty();
                break;
        }

        return cardinal.orElseThrow(() -> new UnsupportedSwingConstantException());
    }
}