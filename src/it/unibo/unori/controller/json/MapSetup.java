package it.unibo.unori.controller.json;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

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

        String path;
        
        System.out.println("Village: " + village./*getMapLength*/getMapColumns() + "x" + village./*getMapWidth*/getMapRows());
        System.out.println("Church: " + church./*getMapLength*/getMapColumns() + "x" + church./*getMapWidth*/getMapRows());
        System.out.println("House: " + house./*getMapLength*/getMapColumns() + "x" + house./*getMapWidth*/getMapRows());
        System.out.println("Shop: " + shop./*getMapLength*/getMapColumns() + "x" + shop./*getMapWidth*/getMapRows());
        System.out.println("Passage: " + passage./*getMapLength*/getMapColumns() + "x" + passage./*getMapWidth*/getMapRows());
        System.out.println("Dungeon entrance: " + dungeonEntrance./*getMapLength*/getMapColumns() + "x" + dungeonEntrance./*getMapWidth*/getMapRows());

        for (int i = 0; i < village./*getMapLength*/getMapColumns(); i++) {
            for (int j = 0; j < village./*getMapWidth*/getMapRows(); j++) {
                // Horizontal borders
                if (i == 0) {
                    if (j == 0) {
                        path = "res/sprites/map/fence/upper-left.png";
                    } else if (j == village./*getMapWidth*/getMapRows() - 1) {
                        path = "res/sprites/map/fence/upper-right.png";
                    } else {
                        path = "res/sprites/map/fence/width.png";
                    }
                } else if (i == village./*getMapLength*/getMapColumns() - 1) {
                    if (j == 0) {
                        path = "res/sprites/map/fence/lower-left.png";
                    } else if (j == village./*getMapWidth*/getMapRows() - 1) {
                        path = "res/sprites/map/fence/lower-right.png";
                    } else {
                        path = "res/sprites/map/fence/width.png";
                    }
                    // Vertical borders
                } else if (j == 0) {
                    path = "res/sprites/map/fence/height.png";
                } else if (j == village./*getMapWidth*/getMapRows() - 1) {
                    if (i >= 9 && j <= 12) {
                        path = /* "res/sprites/map/black.png" */"res/sprites/map/grass.png";
                    } else {
                        path = "res/sprites/map/fence/height.png";
                    }
                    // Ceilings
                } else if (((i == 4 || i == 14) && (j >= 2 && j <= 5))
                        || ((i == 4 || i == 5) && (j >= 12 && j <= 14))) {
                    path = "res/sprites/map/ceiling.png";
                    // Doors
                } else if ((i == 5 && j == 4) || (i == 15 && j == 4) || (i == 7 && j == 13)) {
                    path = "res/sprites/map/door.png";
                } else if (i == 5 && j == 2) {
                    path = "res/sprites/map/house/left.png";
                } else if (i == 5 && j == 3) {
                    path = "res/sprites/map/house/center.png";
                } else if (i == 5 && j == 5) {
                    path = "res/sprites/map/house/right.png";
                } else if (i == 15 && j == 2) {
                    path = "res/sprites/map/shop/left.png";
                } else if (i == 15 && j == 3) {
                    path = "res/sprites/map/shop/center.png";
                } else if (i == 15 && j == 5) {
                    path = "res/sprites/map/shop/right.png";
                } else if ((i == 6 || i == 7) && j == 12) {
                    path = "res/sprites/map/church/left.png";
                } else if (i == 6 && j == 13) {
                    path = "res/sprites/map/church/center.png";
                } else if ((i == 6 || i == 7) && j == 13) {
                    path = "res/sprites/map/church/right.png";
                    // NPCs
                } else if (i == 18 && j == 16) {
                    path = "res/sprites/npcs/grass/front-1.png";
                } else if (i == 11 && j == 4) {
                    path = "res/sprites/npcs/grass/front-2.png";
                } else if (i == 2 && j == 18) {
                    path = "res/sprites/npcs/grass/front-5.png";
                } else {
                    // Floor
                    path = "res/sprites/map/grass.png";
                }

                try {
                    village.getCell(new Position(i, j)).setFrame(path);
                } catch (Exception e) {
                    System.out.println("\nVillage error: I = " + i + "\nJ = " + j + "\npath = " + path);
                    throw e;
                }
            }
        }
        fileManager.saveMap(village, VILLAGE);

        for (int i = 0; i < church./*getMapLength*/getMapColumns(); i++) {
            for (int j = 0; j < church.getMapColumns(); j++) {
                // Borders
                if (i == 0 || i == church./*getMapLength*/getMapColumns() - 1 || j == 0 || j == church./*getMapWidth*/getMapRows() - 1) {
                    if (i == 10 && j == 4) {
                        path = "res/sprites/map/floor.png";
                    } else {
                        path = "res/sprites/map/black.png";
                    }
                    // NPCs
                } else if (i == 2 && j == 4) {
                    path = "res/sprites/npcs/floor/front-7.png";
                } else if (i == 6 && j == 1) {
                    path = "res/sprites/npcs/floor/front-2.png";
                } else if (i == 10 && j == 1) {
                    path = "res/sprites/npcs/floor/front-3.png";
                } else if (i == 10 && j == 7) {
                    path = "res/sprites/npcs/floor/front-6.png";
                } else if ((j == 2 || j == 5) && (i == 3 || i == 5 || i == 7)) {
                    path = "res/sprites/map/bench/left.png";
                } else if ((j == 3 || j == 6) && (i == 3 || i == 5 || i == 7)) {
                    path = "res/sprites/map/bench/right.png";
                } else {
                    // Floor
                    path = "res/sprites/map/floor.png";
                }

                try {
                    church.getCell(new Position(i, j)).setFrame(path);
                } catch (Exception e) {
                    System.out.println("\nChurch error: I = " + i + "\nJ = " + j + "\npath = " + path);
                    throw e;
                }
            }
        }
        fileManager.saveMap(church, CHURCH);

        for (int i = 0; i < house./*getMapLength*/getMapColumns(); i++) {
            for (int j = 0; j < house.getMapColumns(); j++) {
                // Borders
                if (i == 0 || i == house./*getMapLength*/getMapColumns() - 1 || j == 0 || j == house./*getMapWidth*/getMapRows() - 1) {
                    if (i == 3 && j == 4) {
                        path = "res/sprites/map/floor.png";
                    } else {
                        path = "res/sprites/map/black.png";
                    }
                    // NPCs
                } else if (i == 2 && j == 2) {
                    path = "res/sprites/map/table.png";
                } else if (i == 2 && j == 1) {
                    path = "res/sprites/npcs/floor/right.png";
                } else if (i == 1 && j == 2) {
                    path = "res/sprites/npcs/floor/front-1.png";
                } else if (i == 3 && j == 2) {
                    path = "res/sprites/npcs/floor/bottom.png";
                } else if (i == 2 && j == 3) {
                    path = "res/sprites/npcs/floor/left.png";
                } else {
                    // Floor
                    path = "res/sprites/map/floor.png";
                }
                try {
                    house.getCell(new Position(i, j)).setFrame(path);
                } catch (Exception e) {
                    System.out.println("\nHouse error: I = " + i + "\nJ = " + j + "\npath = " + path);
                    throw e;
                }
            }
        }
        fileManager.saveMap(house, FOUR_NPC_ROOM);

        for (int i = 0; i < shop./*getMapLength*/getMapColumns(); i++) {
            for (int j = 0; j < shop.getMapColumns(); j++) {
                // Borders
                if (i == 0 || i == shop./*getMapLength*/getMapColumns() - 1 || j == 0 || j == shop./*getMapWidth*/getMapRows() - 1) {
                    if (i == 5 && j == 7) {
                        path = "res/sprites/map/floor.png";
                    } else {
                        path = "res/sprites/map/black.png";
                    }
                    // NPCs
                } else if (i == 3) {
                    if (j == 1) {
                        path = "res/sprites/map/tables/width-1.png";
                    } else if (j == 10) {
                        path = "res/sprites/map/tables/width-3.png";
                    } else {
                        path = "res/sprites/map/tables/width-2.png";
                    }
                } else if (i == 2 && j == 5) {
                    path = "res/sprites/npcs/floor/front-9.png";
                } else if (i == 4 && j == 1) {
                    path = "res/sprites/npcs/floor/front-4.png";
                } else if (i == 4 && j == 10) {
                    path = "res/sprites/npcs/floor/front-5.png";
                } else {
                    // Floor
                    path = "res/sprites/map/floor.png";
                }
                try {
                    shop.getCell(new Position(i, j)).setFrame(path);
                } catch (Exception e) {
                    System.out.println("\nShop error: I = " + i + "\nJ = " + j + "\npath = " + path);
                    throw e;
                }
            }
        }
        fileManager.saveMap(shop, SHOP);

        for (int i = 0; i < passage./*getMapLength*/getMapColumns(); i++) {
            for (int j = 0; j < passage./*getMapWidth*/getMapRows(); j++) {
                if (j == 0) {
                    if (i == 0) {
                        path = "res/sprites/map/fence/upper-left.png";
                    } else if (i >= 3 && i <= 6) {
                        path = "res/sprites/map/grass.png";
                    } else if (i == passage./*getMapLength*/getMapColumns() - 1) {
                        path = "res/sprites/map/fence/lower-left.png";
                    } else {
                        path = "res/sprites/map/fence/height.png";
                    }
                } else if (j == passage./*getMapWidth*/getMapRows() - 1) {
                    if (i == 0) {
                        path = "res/sprites/map/fence/upper-right.png";
                    } else if (i >= 4 && i <= 5) {
                        path = "res/sprites/map/earth.png";
                    } else if (i == passage./*getMapLength*/getMapColumns() - 1) {
                        path = "res/sprites/map/fence/lower-right.png";
                    } else {
                        path = "res/sprites/map/fence/height.png";
                    }
                } else if (i == 0 || i == passage./*getMapLength*/getMapColumns() - 1) {
                    path = "res/sprites/map/fence/width.png";
                } else if ((i == 7 && (j == 3 || j == 21)) || (i == 2 && j == 18)) {
                    path = "res/sprites/map/item.png";
                } else if (j == 6 && i == 3) {
                    path = "res/sprites/npcs/grass/front-3.jpg";
                } else if (j == 6 && i == 6) {
                    path = "res/sprites/npcs/grass/front-4.jpg";
                } else if (j == 16 && i == 7) {
                    path = "res/sprites/npcs/grass/front-5.jpg";
                } else {
                    path = /* "res/sprites/map/black.png" */"res/sprites/map/grass.png";
                }
                try {
                    passage.getCell(new Position(i, j)).setFrame(path);
                } catch (Exception e) {
                    System.out.println("\nPassage error: I = " + i + "\nJ = " + j + "\npath = " + path);
                    throw e;
                }
            }
        }
        fileManager.saveMap(passage, PASSAGE);

        for (int i = 0; i < dungeonEntrance./*getMapLength*/getMapColumns(); i++) {
            for (int j = 0; j < dungeonEntrance./*getMapWidth*/getMapRows(); j++) {
                // Borders
                if (j == 0) {
                    if (i == 0) {
                        path = "res/sprites/map/fence/upper-left.png";
                    } else if (i >= 5 && i <= 6) {
                        path = "res/sprites/map/earth.png";
                    } else if (i == passage./*getMapLength*/getMapColumns() - 1) {
                        path = "res/sprites/map/fence/lower-left.png";
                    } else {
                        path = "res/sprites/map/fence/height.png";
                    }
                } else if (j == passage./*getMapWidth*/getMapRows()) {
                    if (i == 0) {
                        path = "res/sprites/map/fence/upper-right.png";
                    } else if (i == passage./*getMapLength*/getMapColumns() - 1) {
                        path = "res/sprites/map/fence/lower-right.png";
                    } else {
                        path = "res/sprites/map/fence/height.png";
                    }
                } else if (i == 0 || i == passage./*getMapLength*/getMapColumns() - 1) {
                    path = "res/sprites/map/fence/width.png";
                } else if (j == 9 && (i == 2 || i == 8)) {
                    path = "res/sprites/map/item.png";
                } else if (i == 8 && j == 2) {
                    path = "res/sprites/npcs/earth/front-1.jpg";
                } else if (((j == 4 || j == 6) && (i <= 6 && i >= 4)) || ((i == 4 || i == 5) && j == 5)) {
                    path = "res/sprites/map/border-1.png";
                } else if (i == 6 && j == 5) {
                    path = "res/sprites/map/black.png";
                } else {
                    // Floor
                    path = "res/sprites/map/earth.png";
                }
                try {
                    dungeonEntrance.getCell(new Position(i, j)).setFrame(path);
                } catch (Exception e) {
                    System.out.println("\nDungeon entrance error: I = " + i + "\nJ = " + j + "\npath = " + path);
                    throw e;
                }
            }
        }
        fileManager.saveMap(dungeonEntrance, DUNGEON_ENTRANCE);

        // Inside the dungeon of dungeon

        // TODO
    }

    public GameMap loadFullyLinkedMap() throws IOException {
        // Out of dungeon
        final GameMap village = this.loadVillage();
        final GameMap church = this.loadChurch();
        final GameMap house = this.loadHouse();
        final GameMap shop = this.loadShop();
        final GameMap passage = this.loadPassage();
        final GameMap dungeonEntrance = this.loadDungeonEntrance();

        Position from;
        Position to;

        // Linking village to the shop
        from = new Position(15, 4);
        to = new Position(5, 7);
        village.setCell(from, new MapCellImpl(village.getCell(from).getFrame(), shop, to));
        shop.setCell(to, new MapCellImpl(shop.getCell(to).getFrame(), village, from));

        // Linking the village to the church
        from = new Position(7, 13);
        to = new Position(10, 4);
        village.setCell(from, new MapCellImpl(village.getCell(from).getFrame(), church, to));
        church.setCell(to, new MapCellImpl(church.getCell(to).getFrame(), village, from));

        // Linking the house to the village
        from = new Position(5, 4);
        to = new Position(5, 4);
        village.setCell(from, new MapCellImpl(village.getCell(from).getFrame(), house, to));
        house.setCell(to, new MapCellImpl(house.getCell(to).getFrame(), village, from));

        // Linking the village to the passage
        for (int i = 9; i <= 12; i++) {
            from = new Position(i, 19);
            to = new Position(i - 6, 0);
            village.setCell(from, new MapCellImpl(village.getCell(from).getFrame(), passage, to));
            passage.setCell(to, new MapCellImpl(passage.getCell(to).getFrame(), village, from));
        }

        // Linking the passage to the dungeon entrance
        for (int i = 4; i <= 5; i++) {
            from = new Position(i, 23);
            to = new Position(i + 1, 0);
            passage.setCell(from, new MapCellImpl(village.getCell(from).getFrame(), dungeonEntrance, to));
            dungeonEntrance.setCell(to, new MapCellImpl(dungeonEntrance.getCell(to).getFrame(), passage, from));
        }

        // Inside the dungeon of dungeon

        // TODO

        return village;
    }

    /**
     * Loads from file a room map with 4 NPCs, that is the inside of the house.
     * 
     * @return a room map with 4 NPCs around a table.
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a
     *             regular file, or for some other reason cannot be opened for
     *             reading
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an
     *             object of type
     */
    public GameMap loadHouse() throws IOException {
        return this.checkAndLoad(FOUR_NPC_ROOM);
    }

    /**
     * Loads from file the map of the blacksmith's shop.
     * 
     * @return the map of the blacksmith shop.
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a
     *             regular file, or for some other reason cannot be opened for
     *             reading
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an
     *             object of type
     */
    public GameMap loadShop() throws IOException {
        return this.checkAndLoad(SHOP);
    }

    /**
     * Loads from file the map of the church.
     * 
     * @return the map of the church
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a
     *             regular file, or for some other reason cannot be opened for
     *             reading
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an
     *             object of type
     */
    public GameMap loadChurch() throws IOException {
        return this.checkAndLoad(CHURCH);
    }

    /**
     * Loads from file the map of the village.
     * 
     * @return the map of the village
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a
     *             regular file, or for some other reason cannot be opened for
     *             reading
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an
     *             object of type
     */
    public GameMap loadVillage() throws IOException {
        return this.checkAndLoad(VILLAGE);
    }

    /**
     * Loads from file the aisle passage map that connects the village to the
     * dungeon.
     * 
     * @return the passage
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a
     *             regular file, or for some other reason cannot be opened for
     *             reading
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an
     *             object of type
     */
    public GameMap loadPassage() throws IOException {
        return this.checkAndLoad(PASSAGE);
    }

    public GameMap loadDungeonEntrance() throws IOException {
        return this.checkAndLoad(DUNGEON_ENTRANCE);
    }

    /**
     * This method deserializes a GameMap serialized with JSON on the specified
     * file and caches a copy if needed in the future.
     * 
     * @param path
     *            the path where to find the file
     * @return the map serialized on the specified file
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file does not exist, is a directory rather than a
     *             regular file, or for some other reason cannot be opened for
     *             reading
     * @throws JsonIOException
     *             if there was a problem reading from the Reader
     * @throws JsonSyntaxException
     *             if the file does not contain a valid representation for an
     *             object of type
     */
    private GameMap checkAndLoad(final String path) throws IOException {
        if (!this.maps.containsKey(path)) {
            this.maps.put(path, fileManager.loadMap(path));
        }
        return this.maps.get(path);
    }
}
