package it.unibo.unori.controller.state;

import java.awt.Point;

import it.unibo.unori.controller.action.InteractAction;
import it.unibo.unori.controller.action.MoveAction;
import it.unibo.unori.controller.action.OpenMenuAction;
import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.SingletonParty;
import it.unibo.unori.model.maps.exceptions.BlockedPathException;
import it.unibo.unori.model.menu.DialogueInterface;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;
import it.unibo.unori.view.layers.MapLayer;

/**
 * This GameState models the state of exploring a map (world, town or dungeon
 * room).
 */
public class MapState extends AbstractGameState {
    private final Party party;

    /**
     * Default constructor.
     * 
     * @param map
     *            the map to start from
     * @throws SpriteNotFoundException
     *             if the sprite at the path provided does not exist
     */
    public MapState(final GameMap map) throws SpriteNotFoundException {
        super(new MapLayer(MoveAction.getSupportedMovementsMap(), 
              new InteractAction(), 
              new OpenMenuAction(),
              map.getFrames(),
              new Point(map.getInitialCellPosition().getPosX(), 
              map.getInitialCellPosition().getPosY()),
              SingletonParty.getParty().getCurrentFrame()));
        party = SingletonParty.getParty();
        party.setCurrentMap(map);
        // TODO
    }

    @Override
    public void update(final double elapsedTime) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onEnter() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onExit() {
        // TODO Auto-generated method stub

    }

    /**
     * Moves the party in the specified direction. Returns if it is possible.
     * 
     * @param direction
     *            the direction to move to
     * @return true if the movement was possible and was done
     */
    public boolean moveParty(final Party.CardinalPoints direction) {
        try {
            this.party.moveParty(direction);
        } catch (BlockedPathException e) {
            return false;
        }
        return true;
    }

    /**
     * Opens a dialogue from the model.
     * 
     * @return the dialogue from the model
     */
    public DialogueInterface interact() {
        return this.party.interact();
    }
}
