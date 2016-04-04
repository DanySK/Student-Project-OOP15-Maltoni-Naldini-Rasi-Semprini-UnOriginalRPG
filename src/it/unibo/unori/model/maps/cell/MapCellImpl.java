package it.unibo.unori.model.maps.cell;

import it.unibo.unori.model.maps.GameMap;

/**
 * Cell implementation to handle a Map Switch.
 * Adds a GameMap object and a pair of coordinates to set the new map and position
 * of the party.
 *
 */
public class MapCellImpl extends SimpleCellImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 7553361363890344023L;
    private final GameMap mapToLink;

    /**
     * Constructor.
     * @param frame
     *        the frame object to set
     * @param mapToLink
     *        the map to set in party 
     * @param initialX
     *          X-Coordinate of the initial cell
     * @param initialY
     *          Y- coordinate of the initial cell
     *@throws IllegalArgumentException if the cell with the specified coordinates
     *                                  does not belong to the map
     */
    public MapCellImpl(final Object frame, final GameMap mapToLink, 
                       final int initialX, final int initialY) 
                               throws IllegalArgumentException {
        super(frame, CellState.FREE);
        this.mapToLink = mapToLink;
        this.mapToLink.setInitialCell(initialX, initialY);
    }

    @Override
    public GameMap getCellMap() {
        return this.mapToLink;
    }

}
