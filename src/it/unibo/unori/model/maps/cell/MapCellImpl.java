package it.unibo.unori.model.maps.cell;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.Position;

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
    private final Position initialPos;

    /**
     * Constructor.
     * @param path
     *        the path of frame object to set
     * @param mapToLink
     *        the map to set in party 
     * @param initialPos
     *          position of the initial cell
     *@throws IllegalArgumentException if the cell with the specified coordinates
     *                                  does not belong to the map
     */
    public MapCellImpl(final String path, final GameMap mapToLink, 
                       final Position initialPos) 
                               throws IllegalArgumentException {
        super(path, CellState.BLOCKED);
        this.mapToLink = mapToLink;
        this.initialPos = initialPos;
    }

    @Override
    public GameMap getCellMap() {
        this.mapToLink.setInitialCellPosition(initialPos);
        return this.mapToLink;
    }

}
