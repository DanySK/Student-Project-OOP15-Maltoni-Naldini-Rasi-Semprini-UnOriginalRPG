package it.unibo.unori.controller.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.GameMapFactory;
import it.unibo.unori.model.maps.Position;
import it.unibo.unori.model.maps.cell.MapCellImpl;

public class MapSetup {
    public static final String FOUR_NPC_ROOM = "res/maps/House.json";
    public static final String SHOP = "res/maps/Shop.json";
    public static final String CHURCH = "res/maps/Church.json";
    public static final String VILLAGE = "res/maps/Village.json";
    public static final String PASSAGE = "res/maps/Passage.json";
    public static final String DUNGEON_ENTRANCE = "res/maps/DungeonEntrance.json";

    private final Map<String, GameMap> maps;
    private final JsonFileManager fileManager;

    public MapSetup() {
        this.maps = new HashMap<>();
        this.fileManager = new JsonFileManager();
    }

    public static void main(String[] args) throws IOException {
        final JsonFileManager fileManager = new JsonFileManager();
        final GameMapFactory gmf = new GameMapFactory();

        // Out of dungeon
        final GameMap village = gmf.getVillageMap();
        final GameMap church = gmf.createChurch();
        final GameMap house = gmf.create4NPCRoomMap();
        final GameMap shop = gmf.createShop();
        final GameMap passage = gmf.createAisle();
        final GameMap dungeonEntrance = gmf.createDungeonEntrance();

        fileManager.saveMap(village, VILLAGE);
        fileManager.saveMap(church, CHURCH);
        fileManager.saveMap(house, FOUR_NPC_ROOM);
        fileManager.saveMap(shop, SHOP);
        fileManager.saveMap(passage, PASSAGE);
        fileManager.saveMap(dungeonEntrance, DUNGEON_ENTRANCE);
        
        // Inside the dungeon of dungeon

        // TODO
    }

    public GameMap loadFullyLinkedMap() throws IOException {
        // Out of dungeon
        final GameMap village = this.loadVillage();
        final GameMap church = this.loadChurch();
        final GameMap house = this.load4NPCRoom();
        final GameMap shop = this.loadShop();
        final GameMap passage = this.loadPassage();
        final GameMap dungeonEntrance = this.loadDungeonEntrance();

        Position from;
        Position to;

        // Linking village to the shop
        from = new Position(15, 4);
        to = new Position(4, 5);
        village.setCell(from, new MapCellImpl("", shop, to));
        shop.setCell(to, new MapCellImpl("", village, from));

        // Linking the village to the church
        from = new Position(7, 13);
        to = new Position(10, 4);
        village.setCell(from, new MapCellImpl("", church, to));
        church.setCell(to, new MapCellImpl("", village, from));

        // Linking the house to the village
        from = new Position(5, 4);
        to = new Position(3, 4);
        village.setCell(from, new MapCellImpl("", house, to));
        house.setCell(to, new MapCellImpl("", village, from));

        // Linking the village to the passage
        for (int i = 8; i <= 12; i++) {
            from = new Position(i, 17);
            to = new Position(i - 6, 1);
            village.setCell(from, new MapCellImpl("", passage, to));
            passage.setCell(to, new MapCellImpl("", village, from));
        }

        // Linking the passage to the dungeon entrance
        for (int i = 4; i <= 5; i++) {
            from = new Position(i, 24);
            to = new Position(i + 2, 1);
            passage.setCell(from, new MapCellImpl("", dungeonEntrance, to));
            dungeonEntrance.setCell(to, new MapCellImpl("", passage, from));
        }

        // Inside the dungeon of dungeon

        // TODO

        return village;
    }

    public GameMap load4NPCRoom() throws IOException {
        return this.checkAndLoad(FOUR_NPC_ROOM);
    }

    public GameMap loadShop() throws IOException {
        return this.checkAndLoad(SHOP);
    }

    public GameMap loadChurch() throws IOException {
        return this.checkAndLoad(CHURCH);
    }

    public GameMap loadVillage() throws IOException {
        return this.checkAndLoad(VILLAGE);
    }

    public GameMap loadPassage() throws IOException {
        return this.checkAndLoad(PASSAGE);
    }

    public GameMap loadDungeonEntrance() throws IOException {
        return this.checkAndLoad(DUNGEON_ENTRANCE);
    }

    private GameMap checkAndLoad(final String path) throws IOException {
        if (!this.maps.containsKey(path)) {
            this.maps.put(path, fileManager.loadMap(path));
        }
        return this.maps.get(path);
    }
}
