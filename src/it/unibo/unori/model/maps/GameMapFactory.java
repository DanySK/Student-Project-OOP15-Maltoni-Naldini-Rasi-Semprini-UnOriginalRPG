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
            final Cell cell = new MapCellImpl("", LINKINGMAP,
                                    new Position(MAXSIZE - 1, i));
            map.setCell(pos, cell);
        });
        return map;
    }

    /**
     * Create a map with the defined dimension.
     * @param width
     *          width of the map
     * @param length
     *          length of the map
     * @return
     *          a closed map sized as liked
     */
    public GameMap getSizeableMap(final int width, final int length) {
        final GameMap map = new GameMapImpl(width + 1, length + 1);
        final Cell cell = new CellFactory().getBlockedCell();
        map.setRow(0, cell);
        map.setRow(width, cell);
        map.setColumn(0, cell);
        map.setColumn(length, cell);
        return map;
        }
    /**
     * Create a room map.
     * @return
     *          a room map.
     */
    public GameMap createRoomMap() {
        final GameMap map = this.getSizeableMap(5, 6);
        map.setCell(new Position(2, 2), FACT.getBlockedCell());
        return map;
    }

    /**
     * Create the villageMap.
     * @return villageMap.
     */
   public GameMap getVillageMap() {
        final GameMap map = this.getSizeableMap(19, 17);
        for (int i = 2; i < 6; i++) {
            map.setCell(new Position(4, i), FACT.getBlockedCell());
            map.setCell(new Position(5, i), FACT.getBlockedCell());
            map.setCell(new Position(14, i), FACT.getBlockedCell());
            map.setCell(new Position(15, i), FACT.getBlockedCell());
        }
        return map;
    }

}
