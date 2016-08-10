package it.unibo.unori.model.maps;

import java.util.ArrayList;
import java.util.List;

import it.unibo.unori.model.items.ArmorFactory;
import it.unibo.unori.model.maps.cell.MapCellImpl;

/**
 * Class to build the dungeon.
 *
 */
public class DungeonBuilder {

    private static final GameMapFactory FACT = new GameMapFactory();
    private static final ArmorFactory AACT = new ArmorFactory();

    private void northLink(final GameMap lowerMap, final GameMap upperMap) {
       MapCellImpl c1 = new MapCellImpl("", upperMap, new Position(8, 6));
       MapCellImpl c2 = new MapCellImpl("", upperMap, new Position(8, 7));
       lowerMap.setCell(new Position(0, 6), c1);
       lowerMap.setCell(new Position(0, 7), c2);
       c1 = new MapCellImpl("", lowerMap, new Position(1, 6));
       c2 = new MapCellImpl("", lowerMap, new Position(1, 7));
       upperMap.setCell(new Position(9, 6), c1);
       upperMap.setCell(new Position(9, 7), c2);
    }

    private void westLink(final GameMap westMap, final GameMap estMap) {
        MapCellImpl c1 = new MapCellImpl("", estMap, new Position(4, 1));
        MapCellImpl c2 = new MapCellImpl("", estMap, new Position(5, 1));
        westMap.setCell(new Position(4, 13), c1);
        westMap.setCell(new Position(5, 13), c2);
        c1 = new MapCellImpl("", westMap, new Position(4, 12));
        c2 = new MapCellImpl("", westMap, new Position(5, 12));
        estMap.setCell(new Position(4, 0), c1);
        estMap.setCell(new Position(5, 0), c2);
    }

    private GameMap firstFloorBuilder() {
        final GameMap mainRoom = FACT.getSizeableMap(8, 12);
        final List<GameMap> roomList = new ArrayList<>();
        roomList.add(mainRoom);
        for (int i = 0; i < 16; i++) {
            roomList.add(FACT.getSizeableMap(8, 12));
        }
        return mainRoom;
    }

}
