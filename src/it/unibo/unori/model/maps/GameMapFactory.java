package it.unibo.unori.model.maps;

import java.util.stream.IntStream;

import it.unibo.unori.model.character.Npc;
import it.unibo.unori.model.character.NpcImpl;
import it.unibo.unori.model.maps.cell.Cell;
import it.unibo.unori.model.maps.cell.CellFactory;
import it.unibo.unori.model.maps.cell.MapCellImpl;
import it.unibo.unori.model.maps.cell.NPCCellImpl;
import it.unibo.unori.model.menu.Dialogue;

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
        map.setRow(width + 1, cell);
        map.setColumn(0, cell);
        map.setColumn(length + 1, cell);
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
     * Create the blacksmith's shop.
     * @return
     *          map of the blacksmith shop.
     */
    public GameMap createShop() {
        final GameMap map = this.getSizeableMap(10, 4);
        map.setRow(3, FACT.getBlockedCell());
        final Npc blackSmith = new NpcImpl("Fuori dalla mia palude!");
        final Npc assistant1 = new NpcImpl("Il fabbro si è barricato dietro il bancone!"
                + " Nessuno portà andare a parlargli ora!");
        final Npc assistant2 = new NpcImpl("Il fabbro è arrabbiato perchè "
                + "i ladri gli hanno rubato tutti i suoi averi!");
        map.setCell(new Position(2, 5), new NPCCellImpl("", blackSmith));
        map.setCell(new Position(4, 1), new NPCCellImpl("", assistant1));
        map.setCell(new Position(4, 10), new NPCCellImpl("", assistant2));
        return map;
    }

    /**
     * create the map for the church.
     * @return
     *          the map of the church
     */
    public GameMap createChurch() {
        final GameMap map = this.getSizeableMap(9, 7);
        final Npc priest = new NpcImpl("Mi hanno sbattuto quaggiù perchè mi mangiavo tutte le ostie!");
        final Npc complot = new NpcImpl("Hai notato che questo posto ha entrate ma non uscite? Devono essere gli Illuminati!");
        final Npc solider = new NpcImpl("Sono una guardia inutile, ma non quanto lui dalla parte opposta!");
        for (int i = 3; i < 8; i += 2) {
            map.setCell(new Position(i, 2), FACT.getBlockedCell());
            map.setCell(new Position(i, 3), FACT.getBlockedCell());
            map.setCell(new Position(i, 5), FACT.getBlockedCell());
            map.setCell(new Position(i, 6), FACT.getBlockedCell());
        }
        map.setCell(new Position(2, 4), new NPCCellImpl("", priest));
        map.setCell(new Position(9, 1), new NPCCellImpl("", solider));
        map.setCell(new Position(9, 7), new NPCCellImpl("", solider));
        map.setCell(new Position(5, 1), new NPCCellImpl("", complot));
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
        MapCellImpl c1 = new MapCellImpl("", map, new Position(6, 4));
        h1.setCell(new Position(h1.getMapWidth() - 1, 4), c1);
        MapCellImpl c2 = new MapCellImpl("", h1, new Position(4, 4));
        map.setCell(new Position(5, 4), c2);
        map.setInitialCellPosition(new Position(6, 4));

        final GameMap ch = this.createChurch();
        c1 = new MapCellImpl("", ch, new Position(9, 4));
        map.setCell(new Position(7, 13), c1);
        c2 = new MapCellImpl("", map, new Position(8, 13));
        for (int i = 3; i < 6; i++) {
            ch.setCell(new Position(10, i), c2);
        }

        final GameMap sh = this.createShop();
        c1 = new MapCellImpl("", sh, new Position(4, 7)); 
        map.setCell(new Position(15, 4), c1);
        c2 = new MapCellImpl("", map, new Position(16, 4));
        sh.setCell(new Position(5, 7), c2);

        final Npc snm = new NpcImpl("Sto cercando la Lore, ma non la trovo!");
        map.setCell(new Position(2, 18), new NPCCellImpl("", snm));
        final Npc lego = new NpcImpl("Stanno portando gli hobbit a Isengard!");
        map.setCell(new Position(11, 4), new NPCCellImpl("", lego));
        return map;
    }

}
