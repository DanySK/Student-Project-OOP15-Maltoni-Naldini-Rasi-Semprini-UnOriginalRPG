package it.unibo.unori.controller.state;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.GameMapImpl;

/**
 * This GameState models the state of exploring a map (world, town or dungeon
 * room).
 */
public class MapState implements GameState {
    private GameMap map;

    /**
     * Default constructor.
     */
    public MapState() {
        this.map = new GameMapImpl();

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double elapsedTime) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onEnter() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onExit() {
        // TODO Auto-generated method stub

    }

}
