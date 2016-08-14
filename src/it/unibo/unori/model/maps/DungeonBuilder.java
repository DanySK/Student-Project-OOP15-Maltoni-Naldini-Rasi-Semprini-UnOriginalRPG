package it.unibo.unori.model.maps;

import java.util.ArrayList;
import java.util.List;

import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.ArmorFactory;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.ItemImpl;
import it.unibo.unori.model.items.PotionFactory;
import it.unibo.unori.model.items.WeaponFactory;
import it.unibo.unori.model.maps.cell.ChestCellImpl;
import it.unibo.unori.model.maps.cell.MapCellImpl;
import it.unibo.unori.model.maps.cell.ObjectCellImpl;

/**
 * Class to build the dungeon.
 *
 */
public class DungeonBuilder {

    private static final GameMapFactory FACT = new GameMapFactory();
    private static final ArmorFactory AACT = new ArmorFactory();
    private static final PotionFactory PACT = new PotionFactory();
    private List<GameMap> rList = new ArrayList<>();
    private List<GameMap> sList = new ArrayList<>();
    private List<GameMap> tList = new ArrayList<>();
    private List<GameMap> fList = new ArrayList<>();
    private static final Position POS1 = new Position(7, 3);
    private static final Position POS2 = new Position(2, 11);

    /**
     * Method to link two dungeon room vertically.
     * @param lowerMap
     *          the lower map
     * @param upperMap
     *          the upper map
     */
    private void northLink(final GameMap lowerMap, final GameMap upperMap) {
       MapCellImpl c1 = new MapCellImpl(upperMap, new Position(8, 6));
       MapCellImpl c2 = new MapCellImpl(upperMap, new Position(8, 7));
       lowerMap.setCell(new Position(0, 6), c1);
       lowerMap.setCell(new Position(0, 7), c2);
       c1 = new MapCellImpl(lowerMap, new Position(1, 6));
       c2 = new MapCellImpl(lowerMap, new Position(1, 7));
       upperMap.setCell(new Position(9, 6), c1);
       upperMap.setCell(new Position(9, 7), c2);
    }

    /**
     * Method to link two maps horizontally.
     * @param westMap
     *          the west map
     * @param estMap
     *          the east map
     */
    private void westLink(final GameMap westMap, final GameMap estMap) {
        MapCellImpl c1 = new MapCellImpl(estMap, new Position(4, 1));
        MapCellImpl c2 = new MapCellImpl(estMap, new Position(5, 1));
        westMap.setCell(new Position(4, 13), c1);
        westMap.setCell(new Position(5, 13), c2);
        c1 = new MapCellImpl(westMap, new Position(4, 12));
        c2 = new MapCellImpl(westMap, new Position(5, 12));
        estMap.setCell(new Position(4, 0), c1);
        estMap.setCell(new Position(5, 0), c2);
    }

    /**
     * Method to link multi-level maps.
     * @param map1
     *          map to link
     * @param map2
     *          map to link
     */
    public void linkMap(final GameMap map1, final GameMap map2) {
        final MapCellImpl c1 = new MapCellImpl(map1, new Position(6, 5));
        final MapCellImpl c2 = new MapCellImpl(map2, new Position(6, 5));
        map2.setCell(new Position(5, 5), c1);
        map1.setCell(new Position(5, 5), c2);
    }

 

    private void storeItem(final Position pos, final GameMap map, final Item i) {
        map.setCell(pos, new ObjectCellImpl("", i));
    }

    private void storeChest(final Position pos, final GameMap map, final Item i) {
        map.setCell(pos, new ChestCellImpl("", i));
    }

    /**
     * Build final floor.
     */
    private void finalFloorBuilder() {
        if (fList.isEmpty()) {
            for (int i = 0; i < 2; i++) {
                fList.add(FACT.getSizeableMap(8, 12));
            }
        }
        this.storeItem(POS1, fList.get(0), PACT.getGigaPozione());
        this.storeItem(POS2, fList.get(0), PACT.getTrapiantoMana());
        this.northLink(fList.get(0), fList.get(1));
    }

    /**
     * Build third floor
     */
    private void thirdFloorBuilder() {
        if (tList.isEmpty()) {
            for (int i = 0; i < 9; i++) {
                tList.add(FACT.getSizeableMap(8, 12));
            }
        }
        this.northLink(tList.get(0), tList.get(2));
        this.northLink(tList.get(5), tList.get(0));
        this.northLink(tList.get(6), tList.get(5));
        this.northLink(tList.get(6), tList.get(7));
        this.westLink(tList.get(8), tList.get(1));
        this.westLink(tList.get(1), tList.get(0));
        this.westLink(tList.get(0), tList.get(3));
        this.westLink(tList.get(3), tList.get(4));
        this.storeChest(POS1, tList.get(0), AACT.getGoldEquip().get(ArmorPieces.SHIELD));
        this.storeChest(POS1, tList.get(4), ItemImpl.KEY);
        this.storeChest(POS2, tList.get(7), WeaponFactory.getSpadaMistica());
    }

    /**
     * Build second floor.
     */
    private void secondFloorBuilder() {
        if (sList.isEmpty()) {
            for (int i = 0; i < 18; i++) {
                sList.add(FACT.getSizeableMap(8, 12));
            }
        }
        this.northLink(sList.get(1), sList.get(0));
        this.northLink(sList.get(2), sList.get(1));
        this.westLink(sList.get(4), sList.get(1));
        this.westLink(sList.get(3), sList.get(2));
        this.storeItem(POS1, sList.get(4), PACT.getGranPozione());
        this.westLink(sList.get(6), sList.get(3));
        this.northLink(sList.get(7), sList.get(6));
        this.northLink(sList.get(8), sList.get(7));
        this.westLink(sList.get(8), sList.get(9));
        this.storeChest(POS2, sList.get(9), WeaponFactory.getSpadaMistica());
        this.northLink(sList.get(6), sList.get(5));
        this.northLink(sList.get(5), sList.get(10));
        this.storeItem(POS1, sList.get(10), AACT.getSilverEquip().get(ArmorPieces.SHIELD));
        this.northLink(sList.get(10), sList.get(11));
        this.westLink(sList.get(12), sList.get(11));
        this.storeItem(POS1, sList.get(12), ItemImpl.KEY);
        this.westLink(sList.get(13), sList.get(5));
        this.westLink(sList.get(15), sList.get(13));
        this.storeItem(POS1, sList.get(15), PACT.getGranPozione());
        this.northLink(sList.get(14), sList.get(13));
        this.westLink(sList.get(16), sList.get(14));
        this.northLink(sList.get(17), sList.get(16));
    }

    /**
     * Build first floor.
     */
    private void firstFloorBuilder() {
        if (rList.isEmpty()) {
            for (int i = 0; i < 17; i++) {
                rList.add(FACT.getSizeableMap(8, 12));
            }
        }
        this.westLink(rList.get(0), rList.get(2));
        this.storeItem(POS1, rList.get(1), PACT.getAspirinaMagica());
        this.storeItem(POS2, rList.get(2), PACT.getIntruglio());
        this.westLink(rList.get(1), rList.get(0));
        this.northLink(rList.get(0), rList.get(3));
        this.northLink(rList.get(3), rList.get(4));
        this.storeItem(POS2, rList.get(4), WeaponFactory.getChiodo());
        this.westLink(rList.get(4), rList.get(13));
        this.storeItem(POS1, rList.get(13), AACT.getBronzeEquip().get(ArmorPieces.SHIELD));
        this.westLink(rList.get(5), rList.get(4));
        this.northLink(rList.get(5), rList.get(6));
        this.westLink(rList.get(7), rList.get(6));
        this.storeItem(POS1, rList.get(7), WeaponFactory.getCannone());
        this.westLink(rList.get(6), rList.get(8));
        this.northLink(rList.get(8), rList.get(9));
        this.storeItem(POS1, rList.get(9), PACT.getRimedioDellaNonna());
        this.northLink(rList.get(9), rList.get(10));
        this.westLink(rList.get(11), rList.get(10));
        this.storeItem(POS2, rList.get(11), AACT.getSilverEquip().get(ArmorPieces.GLOVES));
        this.westLink(rList.get(8), rList.get(12));
        this.westLink(rList.get(12), rList.get(14));
        this.storeItem(POS1, rList.get(14), PACT.getTrapiantoMana());
        this.northLink(rList.get(14), rList.get(15));
        this.westLink(rList.get(15), rList.get(16));
    }

    /**
     * Connect the floor and return the first room of first floor.
     * @return
     *         first room of first floor.
     */
    public GameMap dungeonBuild() {
       this.finalFloorBuilder();
       this.thirdFloorBuilder();
       this.secondFloorBuilder();
       this.firstFloorBuilder();
       this.linkMap(fList.get(0), tList.get(tList.size() - 1));
       this.linkMap(tList.get(0), sList.get(sList.size() - 1));
       this.linkMap(sList.get(0), rList.get(rList.size() - 1));
       return rList.get(0);
    }

    /**
     * Set a list of map as a floor of the dungeon.
     * @param floorNum
     *          number of the floor to set
     * @param maps
     *      list of the maps of the floor
     * @throws IllegalArgumentException
     *          if the number of the floor is not between 1 and 4
     */
    public void setFloor(final int floorNum, final List<GameMap> maps) 
                            throws IllegalArgumentException {
        switch (floorNum) {
        case 1: this.rList = new ArrayList<>(maps); break;
        case 2: this.sList = new ArrayList<>(maps); break;
        case 3: this.tList = new ArrayList<>(maps); break;
        case 4: this.fList = new ArrayList<>(maps); break;
        default: throw new IllegalArgumentException(); 
        }
    }

}
