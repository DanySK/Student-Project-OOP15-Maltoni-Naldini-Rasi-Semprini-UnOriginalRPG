package it.unibo.unori.model.maps;

import java.util.stream.IntStream;

import it.unibo.unori.model.maps.cell.Cell;
import it.unibo.unori.model.maps.cell.CellFactory;
import it.unibo.unori.model.maps.cell.MapCellImpl;

/**
 * Factory to realize some standard Maps.
 *
 */
public class GameMapFactory {
    /**
     * Generic standard map.
     */
    public static final GameMap LINKINGMAP = new GameMapImpl();
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

    /**
     * Create a Map whose lower bound is linked to another Map.
     * @return the linked Map.
     */
    public GameMap getSouthLinkedMap() {
        final GameMap map = new GameMapImpl();
        map.setRow(MAXSIZE, FACT.getBlockedCell());
        map.setColumn(0, FACT.getBlockedCell());
        map.setColumn(MAXSIZE, FACT.getBlockedCell());
        IntStream.range(0, MAXSIZE).forEachOrdered(i -> {
            final Position pos = new Position(0, i);
            final Cell cell = new MapCellImpl(new Object(), LINKINGMAP,
                                    new Position(MAXSIZE - 1, i));
            map.setCell(pos, cell);
        });
        return map;
    }

}
