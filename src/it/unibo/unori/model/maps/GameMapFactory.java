package it.unibo.unori.model.maps;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import it.unibo.unori.model.character.Npc;
import it.unibo.unori.model.character.NpcImpl;
import it.unibo.unori.model.maps.cell.Cell;
import it.unibo.unori.model.maps.cell.CellFactory;
import it.unibo.unori.model.maps.cell.MapCellImpl;
import it.unibo.unori.model.maps.cell.NPCCellImpl;
import it.unibo.unori.model.menu.Dialogue;
import it.unibo.unori.model.menu.DialogueInterface;

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
        final GameMap map = new GameMapImpl(width + 2, length + 2);
        final Cell cell = new CellFactory().getBlockedCell();
        map.setRow(0, cell);
        map.setRow(width, cell);
        map.setColumn(0, cell);
        map.setColumn(length, cell);
        return map;
        }
    /**
     * Create a room map with 4 npc.
     * @return
     *          a room map with 4 npc around a table.
     */
    public GameMap create4NPCRoomMap() {
        final GameMap map = this.getSizeableMap(4, 4);
        map.setCell(new Position(2, 2), FACT.getBlockedCell());
        final Npc player1 = new NpcImpl("Caccia l'Asso!");
        final Npc player2 = new NpcImpl(new Dialogue("Full vince su tris eheheheh"));
        final Npc player3 = new NpcImpl(new Dialogue("Tutti combattono mostri e io sto a giocare a marafone"));
        final Npc player4 = new NpcImpl(new Dialogue("Io gioco drago bianco occhi blu! Ah no aspetta..."));
        map.setCell(new Position(1, 2), new NPCCellImpl("", player1));
        map.setCell(new Position(2, 1), new NPCCellImpl("", player2));
        map.setCell(new Position(3, 2), new NPCCellImpl("", player3));
        map.setCell(new Position(2, 3), new NPCCellImpl("", player4));
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
        final GameMap h1 = this.create4NPCRoomMap();
        final MapCellImpl c1 = new MapCellImpl("", map, new Position(6, 4));
        h1.setCell(new Position(h1.getMapWidth() - 1, 4), c1);
        final MapCellImpl c2 = new MapCellImpl("", h1, new Position(4, 4));
        map.setCell(new Position(5, 4), c2);
        map.setInitialCellPosition(new Position(6, 4));
        return map;
    }

}
