package it.unibo.unori.model.maps;

import java.io.Serializable;

/**
 * Party is the central object of the all map-related classes.
 * A object which implements party must be free to move on the game map, and interact
 * with Object on the GameMap.
 * Serializable interface is added in order to save the object on file.
 *
 */
public interface Party extends Serializable {
    /**
     * Set the current position of the party on the map.
     * @param posX
     *              X coordinate of the cell the party is on
     * @param posY
     *              Y coordinate of the cell the party is on
     *@throws IllegalArgumentException if the cell specified is blocked or absent
     */
    public void setCurrentPosition(int posX, int posY) throws IllegalArgumentException;

    /**
     * Set the current map for the party to walk on.
     * @param map
     *          a map to walk on
     */
    public void setCurrentMap(GameMap map);

    /**
     * Set the current frame of party.
     * @param frame
     *              the frame to set
     */
    public void setCurrentFrame(Object frame);

    /**
     * Enum to define the four cardinal points and their skidding 
     * from the current cell.
     *
     */
    public enum CardinalPoints {
        /**
         * Four values for the cardinal points.
         */
        NORTH(1, 0), SOUTH(-1, 0), EST(0, 1), WEST(0, -1);

        /**
         * Parameters to save the skidding for each of the cardinal points.
         */
        private final int posX;
        private final int posY;

        private CardinalPoints(final int posX, final int posY) {
           this.posX = posX;
           this.posY = posY;
        }

        /**
         * get the skidding of the X coordinate.
         * @return the value of the skidding
         */
        public int getXSkidding() {
            return this.posX;
        }

        /**
         * get the skidding of the Y coordinate.
         * @return the value of the skidding
         */
        public int getYSkidding() {
            return this.posY;
        }
    }

}
