package it.unibo.unori.model.maps;

import java.util.stream.IntStream;

import it.unibo.unori.model.character.Npc;
import it.unibo.unori.model.character.NpcImpl;
import it.unibo.unori.model.items.PotionFactory;
import it.unibo.unori.model.items.WeaponFactory;
import it.unibo.unori.model.maps.cell.Cell;
import it.unibo.unori.model.maps.cell.CellFactory;
import it.unibo.unori.model.maps.cell.MapCellImpl;
import it.unibo.unori.model.maps.cell.NPCCellImpl;
import it.unibo.unori.model.maps.cell.ObjectCellImpl;
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
    private static final PotionFactory PACT = new PotionFactory();
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
            final Cell cell = new MapCellImpl(LINKINGMAP,
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
        final GameMap map = this.getSizeableMap(4, 10);
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
        final GameMap map = this.getSizeableMap(20, 18);
        for (int i = 2; i < 6; i++) {
            map.setCell(new Position(4, i), FACT.getBlockedCell());
            map.setCell(new Position(5, i), FACT.getBlockedCell());
            map.setCell(new Position(14, i), FACT.getBlockedCell());
            map.setCell(new Position(15, i), FACT.getBlockedCell());
        }
        for (int i = 12; i < 15; i++) {
            for (int j = 4; j < 8; j++) {
                map.setCell(new Position(j, i), FACT.getBlockedCell());
            }
        }
        final Npc snm = new NpcImpl("Sto cercando la Lore, ma non la trovo!");
        map.setCell(new Position(2, 18), new NPCCellImpl("", snm));
        final Npc lego = new NpcImpl("Stanno portando gli hobbit a Isengard!");
        map.setCell(new Position(11, 4), new NPCCellImpl("", lego));
        return map;
    }

   /**
    * Create the Aisle to connect the village to the dungeon.
    * @return the aisle.
    */
   public GameMap createAisle() {
       final GameMap map = this.getSizeableMap(8, 22);
       final Npc link = new NpcImpl("Eyhaaaaaaa!");
       final Npc gigio = new NpcImpl("Quello lì è Link che si è perso e cerca di tornare a casa");
       final Npc sora = new NpcImpl("Siccome Kingdom Hearts 3 non esce mai, ho cambiato contratto e sono venuto qua!");
       map.setCell(new Position(3, 6), new NPCCellImpl("", link));
       map.setCell(new Position(7, 16), new NPCCellImpl("", sora));
       map.setCell(new Position(6, 6), new NPCCellImpl("", gigio));
       map.setCell(new Position(7, 3), new ObjectCellImpl(PACT.getPasticcheMagiche()));
       map.setCell(new Position(7, 21), new ObjectCellImpl(PACT.getRimedioDellaNonna()));
       return map;
   }

   /**
    * Create the entrance for the dungeon.
    * @return
    *       Map containing entrance for the dungeon
    */
   public GameMap createDungeonEntrance() {
       final GameMap map = this.getSizeableMap(10, 10);
       for (int i = 4; i < 7; i++) {
           for (int j = 4; j < 7; j++) {
              map.setCell(new Position(i, j), FACT.getBlockedCell()); 
           }
       }
       map.setCell(new Position(6, 5), FACT.getFreeCell());
       final Npc crest = new NpcImpl("Questo dungeon mi fa paura, vado a lavorare a Train Simulator");
       map.setCell(new Position(3, 6), new NPCCellImpl("", crest));
       map.setCell(new Position(8, 9), new ObjectCellImpl(PACT.getPozioneVita()));
       map.setCell(new Position(2, 9), new ObjectCellImpl(WeaponFactory.getLanciafiamme()));
       return map;
   }

}
