package it.unibo.unori.model.maps;

import java.io.Serializable;

/**
 * 
 * Interface to contains the basic methods for a game-map.
 * Serializable interface is added in order to save Map-Object on file
 *
 */
public interface GameMap extends Serializable {

    /**
     * Set the dimension of the current map. 
     * @param width
     *              the width of map in terms of number of cells
     * @param length
     *               the lenght of map in terms of number of cells
     */
    public void setDimension(int width, int length);

}
