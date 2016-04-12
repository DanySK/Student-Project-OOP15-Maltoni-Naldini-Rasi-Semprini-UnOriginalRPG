package it.unibo.unori.model.maps;

import it.unibo.unori.model.maps.cell.CellFactory;

/**
 * Factory to realize some standard Maps.
 *
 */
public class GameMapFactory {

    private static final GameMap LINKINGMAP = new GameMapImpl();
    private static final CellFactory FACT = new CellFactory();
    private static final int MAXSIZE = 99;

    /**
     * Create a standard room, a map whose borders are blocked cells.
     * @return a Map which represent a room
     */
    public GameMap getStdRoom() {
        final GameMap map = new GameMapImpl();
        map.setRow(0, FACT.getBlockedCell());
        map.setRow(MAXSIZE, FACT.getBlockedCell());
        map.setColumn(0, FACT.getBlockedCell());
        map.setColumn(MAXSIZE, FACT.getBlockedCell());
        return map;
    }

}
