package it.unibo.unori.model.maps;

import java.io.Serializable;

/**
 * Class to describe a position, composed by an X coordinate and a Y coordinate.
 *
 */
public class Position implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8911145488518450506L;
    private final int posX;
    private final int posY;

    /**
     * Standard constructor for Position.
     * @param posX
     *              X coordinate of the position
     * @param posY
     *              Y coordinate of the position
     */
    public Position(final int posX, final int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Method to get the X coordinate.
     * @return X coordinate
     */
    public int getPosX() {
        return this.posX;
    }

    /**
     * Method to get the Y coordinate.
     * @return Y coordinate
     */
    public int getPosY() { 
      return this.posY;
    }

}
