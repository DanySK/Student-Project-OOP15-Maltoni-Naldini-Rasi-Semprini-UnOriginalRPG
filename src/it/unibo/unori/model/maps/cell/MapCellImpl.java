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
    private static final int PRIME = 31;
    private final GameMap mapToLink;
    private final Position initialPos;

    /**
     * Constructor.
     * @param mapToLink
     *        the map to set in party 
     * @param initialPos
     *         initial position of the map to link
     */
    public MapCellImpl(final GameMap mapToLink, 
                       final Position initialPos) {
        super("res/sprites/map/black.png", CellState.BLOCKED);
        this.mapToLink = mapToLink;
        this.initialPos = initialPos;
    }

    @Override
    public GameMap getCellMap() {
        this.mapToLink.setInitialCellPosition(initialPos);
        return this.mapToLink;
    }

 
    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = PRIME * result + ((initialPos == null) ? 0 : initialPos.hashCode());
        result = PRIME * result + ((mapToLink == null) ? 0 : mapToLink.hashCode());
        return result;
    }


    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof MapCellImpl)) {
            return false;
        }
        final MapCellImpl other = (MapCellImpl) obj;
        if (initialPos == null) {
            if (other.initialPos != null) {
                return false;
            }
        } else if (!initialPos.equals(other.initialPos)) {
            return false;
        }
        if (mapToLink == null) {
            if (other.mapToLink != null) {
                return false;
            }
        } else if (!mapToLink.equals(other.mapToLink)) {
            return false;
        }
        return true;
    }

}
