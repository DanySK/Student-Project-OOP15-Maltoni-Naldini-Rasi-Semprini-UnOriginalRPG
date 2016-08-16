package it.unibo.unori.controller.state;

import it.unibo.unori.view.layers.Layer;

/**
 * This interface models a game state (eg. local map, world map, main menu,
 * battle) in the state machine controller
 */
public interface GameState {

    /**
     * Return the graphic part of the GameState as a layer.
     * 
     * @return the graphic part of the state as a layer
     */
    Layer getLayer();
}
