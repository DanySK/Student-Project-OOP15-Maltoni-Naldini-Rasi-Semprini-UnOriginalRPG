package it.unibo.unori.model.maps;

import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.model.maps.cell.MapCellImpl;

/**
 * Class to create and handle all the gameWorld.
 *
 */
public class WorldBuilder {

    private final Map<MAPS, GameMap> mapsList = new HashMap<>();
    private final DungeonBuilder b = new DungeonBuilder();
    private final GameMapFactory fact = new GameMapFactory();

    /**
     * Default constructor, generate all the GameMaps and put them in the map.
     */
    public WorldBuilder() {
        this.mapsList.put(MAPS.CITY, fact.getVillageMap());
        this.mapsList.put(MAPS.AISLE, fact.createAisle());
        this.mapsList.put(MAPS.CHURCH, fact.createChurch());
        this.mapsList.put(MAPS.DENTRANCE, fact.createDungeonEntrance());
        this.mapsList.put(MAPS.HOUSE, fact.create4NPCRoomMap());
        this.mapsList.put(MAPS.SHOP, fact.createShop());
    }

    /**
     * Method to connect all the map in the Overworld.
     * @return
     *         the first map of the game.
     */
    public GameMap buildWorld() {
        final GameMap map = this.mapsList.get(MAPS.CITY);
        final GameMap h1 = this.mapsList.get(MAPS.HOUSE);
        MapCellImpl c1 = new MapCellImpl(map, new Position(6, 4));
        h1.setCell(new Position(h1.getMapRows() - 1, 4), c1);
        MapCellImpl c2 = new MapCellImpl(h1, new Position(4, 4));
        map.setCell(new Position(5, 4), c2);
        map.setInitialCellPosition(new Position(6, 4));
        final GameMap ch = this.mapsList.get(MAPS.CHURCH);
        c1 = new MapCellImpl(ch, new Position(9, 4));
        map.setCell(new Position(7, 13), c1);
        c2 = new MapCellImpl(map, new Position(8, 13));
        for (int i = 3; i < 6; i++) {
            ch.setCell(new Position(10, i), c2);
        }
        final GameMap sh = this.mapsList.get(MAPS.SHOP);
        c1 = new MapCellImpl(sh, new Position(4, 7)); 
        map.setCell(new Position(15, 4), c1);
        c2 = new MapCellImpl(map, new Position(16, 4));
        sh.setCell(new Position(5, 7), c2);

        final GameMap ai = this.mapsList.get(MAPS.AISLE);
        for (int i = 3; i < 7; i++) {
            c1 = new MapCellImpl(ai, new Position(i, 1));
            map.setCell(new Position(i + 6, 19), c1);
            c2 = new MapCellImpl(map, new Position(i + 6, 18));
            ai.setCell(new Position(i, 0), c2);
            }
        final GameMap de = this.mapsList.get(MAPS.DENTRANCE);
        for (int i = 4; i < 6; i++) {
            c1 = new MapCellImpl(de, new Position(i + 1, 1));
            ai.setCell(new Position(i, 23), c1);
            c2 = new MapCellImpl(ai, new Position(i, 22));
            de.setCell(new Position(i + 1, 0), c2);
        }
        this.b.linkMap(b.dungeonBuild(), de);
        map.setInitialCellPosition(new Position(10, 10));
        return map;
    }

    /**
     * Get a specific map, in order to set some parameters.
     * @param areaName
     *          name of the game area, chosen from the enumeration.
     * @return
     *          the gameMap of the correspondent area.
     */ 
    public GameMap getGameMap(final MAPS areaName) {
        return this.mapsList.get(areaName);
    }

    /**
     * Set a map for a specific area.
     * @param mapName
     *          name of the area
     * @param newMap
     *          map to be set in that game area.
     */
    public void setGameMap(final MAPS mapName, final GameMap newMap) {
        this.mapsList.replace(mapName, newMap);
    }

    /**
     * Getter for the dungeonBuilder.
     * @return
     *      the dungeonBuilder object
     */
    public DungeonBuilder getDungeonBuilder() {
        return this.b;
    }

    /**
     * Enum for the GameMap.
     *
     */
    public enum MAPS {
        /**
         * Kind of maps ammitted in the enumeration.
         */
        CITY, DENTRANCE, SHOP, HOUSE, CHURCH, AISLE, DUNGEON;
    }
}
