package it.unibo.unori.model.maps;

import java.io.Serializable;
import java.util.Random;

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
    private final int prime;

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
        this.prime = new Random().nextInt(100) + 1;
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

    @Override
    public boolean equals(final Object position) {
        if (position == null) {
            return false;
        }
        if (this.getClass().equals(position.getClass())) {
            final Position p = (Position) position;
            if (this.posX == p.getPosX() && this.posY == p.getPosY()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = prime * result + this.getPosX() + this.getPosY();
        return result;
    }
}
