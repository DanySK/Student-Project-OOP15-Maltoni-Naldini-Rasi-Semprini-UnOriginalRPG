package it.unibo.unori.model.maps;

/**
 * Party is the central object of the all map-related classes.
 * A object which implements party must be free to move on the game map, and interact
 * with Object on the GameMap
 *
 */
public interface Party {
    /**
     * set the current position of the party on the map.
     * @param posX
     *              X coordinate of the cell the party is on
     * @param posY
     *              Y coordinate of the cell the party is on
     *@throws IllegalArgumentException if the cell specified is blocked or absent
     */
    public void setCurrentPosition(int posX, int posY) throws IllegalArgumentException;

    /**
     * set the current map for the party to walk on.
     * @param map
     *          a map to walk on
     */
    public void setCurrentMap(GameMap map);

}
