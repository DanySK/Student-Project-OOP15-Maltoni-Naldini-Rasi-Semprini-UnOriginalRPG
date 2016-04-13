package it.unibo.unori.model.maps;

import java.io.Serializable;

import it.unibo.unori.model.maps.exceptions.BlockedPathException;
import it.unibo.unori.model.menu.DummyMenu;

/**
 * Party is the central object of the all map-related classes.
 * A object which implements party must be free to move on the game map, and interact
 * with Object on the GameMap.
 * Serializable interface is added in order to save the object on file.
 *
 */
public interface Party extends Serializable {


    /**
     * Set the current map for the party to walk on.
     * @param map
     *          a map to walk on
     */
    public void setCurrentMap(GameMap map);

    /**
     * Get method for the current map.
     * @return
     *          the current map
     */
    public GameMap getCurrentGameMap();

    /**
     * Set the current frame of party.
     * @param frame
     *              the frame to set
     */
    public void setCurrentFrame(Object frame);

    /**
     * Get method for the frame Object.
     * @return
     *          the frame Object of the party
     */
    public Object getCurrentFrame();

    /**
     * Move the party in the specified direction, if possible.
     * @param direction
     *                  the direction for the party to move
     * @throws BlockedPathException if the party can't move to that direction.
     */

    public void moveParty(CardinalPoints direction) throws BlockedPathException;

    /**
     * Method to interact with the looked Cell.
     * @return a dialogue, related to the kind of Cell
     */
    public DummyMenu interact();

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
