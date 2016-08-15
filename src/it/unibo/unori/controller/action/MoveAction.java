package it.unibo.unori.controller.action;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingConstants;

import it.unibo.unori.controller.Controller;
import it.unibo.unori.controller.SingletonStateMachine;
import it.unibo.unori.controller.exceptions.UnsupportedSwingConstantException;
import it.unibo.unori.controller.state.DialogState.ErrorSeverity;
import it.unibo.unori.controller.state.MapState;
import it.unibo.unori.model.maps.Party.CardinalPoints;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;
import it.unibo.unori.view.layers.MapLayer;

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

    private final CardinalPoints direction;
    private final Controller controller;

    /**
     * Default constructor.
     * 
     * @param direction
     *            the direction to move to
     */
    public MoveAction(final CardinalPoints direction) {
        super();
        this.direction = direction;
        this.controller = SingletonStateMachine.getController();
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (this.controller.getCurrentStateClass().isInstance(MapState.class)) {
            final MapState currentState = (MapState) this.controller.getCurrentState();

            if (currentState.moveParty(this.direction)) {
                MapLayer currentLayer = (MapLayer) currentState.getLayer();

                (currentLayer).move(MoveAction.convertCardinalPointsToSwingConstants(direction));

                if (currentState.checkMapChanges()) {
                    try {
                        currentLayer.changeMap(currentState.getMap().getFrames(),
                                new Point(currentState.getCurrentPosition().getPosX(),
                                        currentState.getCurrentPosition().getPosY()));
                    } catch (SpriteNotFoundException e) {
                        this.controller.showError(e.getMessage(), ErrorSeverity.SERIUOS);
                    }
                }
                currentState.randomEncounters();
            }
        }
    }

    public static Map<Integer, Action> getSupportedMovementsMap() {
        final Map<Integer, Action> returnMap = new HashMap<>();

        returnMap.put(SwingConstants.NORTH,
                new MoveAction(convertSwingConstantsToCardinalPoints(SwingConstants.NORTH)));
        returnMap.put(SwingConstants.SOUTH,
                new MoveAction(convertSwingConstantsToCardinalPoints(SwingConstants.SOUTH)));
        returnMap.put(SwingConstants.WEST, new MoveAction(convertSwingConstantsToCardinalPoints(SwingConstants.WEST)));
        returnMap.put(SwingConstants.EAST, new MoveAction(convertSwingConstantsToCardinalPoints(SwingConstants.EAST)));

        return returnMap;
    }

    public static CardinalPoints convertSwingConstantsToCardinalPoints(final int swingConstant)
            throws UnsupportedSwingConstantException {
        final Optional<CardinalPoints> cardinal;
        switch (swingConstant) {
            case SwingConstants.NORTH:
                cardinal = Optional.of(CardinalPoints.NORTH);
                break;
            case SwingConstants.SOUTH:
                cardinal = Optional.of(CardinalPoints.SOUTH);
                break;
            case SwingConstants.EAST:
                cardinal = Optional.of(CardinalPoints.EAST);
                break;
            case SwingConstants.WEST:
                cardinal = Optional.of(CardinalPoints.WEST);
                break;
            default:
                cardinal = Optional.empty();
                break;
        }

        return cardinal.orElseThrow(() -> new UnsupportedSwingConstantException());
    }

    public static int convertCardinalPointsToSwingConstants(final CardinalPoints direction)
            throws UnsupportedSwingConstantException {
        final Optional<Integer> swingConstant;
        if (direction.equals(CardinalPoints.NORTH)) {
            swingConstant = Optional.of(SwingConstants.NORTH);
        } else if (direction.equals(CardinalPoints.SOUTH)) {
            swingConstant = Optional.of(SwingConstants.SOUTH);
        } else if (direction.equals(CardinalPoints.WEST)) {
            swingConstant = Optional.of(SwingConstants.WEST);
        } else if (direction.equals(CardinalPoints.EAST)) {
            swingConstant = Optional.of(SwingConstants.EAST);
        } else {
            swingConstant = Optional.empty();
        }

        return swingConstant.orElseThrow(() -> new UnsupportedSwingConstantException());
    }
}