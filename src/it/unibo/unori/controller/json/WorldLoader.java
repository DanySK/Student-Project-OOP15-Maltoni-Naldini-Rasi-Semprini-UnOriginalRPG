package it.unibo.unori.controller.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.WorldBuilder;
import it.unibo.unori.model.maps.WorldBuilder.MAPS;

/**
 * This class loads all the world from files.
 */
public class WorldLoader {
    private static final String MAPS_DIRECTORY = "res/maps";
    private static final String DUNGEON_DIRECTORY = MAPS_DIRECTORY + "/dungeon";
    public static final int FIRST_FLOOR_NUMBER = 1;
    public static final int LAST_FLOOR_NUMBER = 4;

    /**
     * This is the path to the file were is serialized the GameMap of the house
     * containing 4 NPCs around a table.
     */
    public static final String FOUR_NPC_ROOM = MAPS_DIRECTORY + "/House.json";

    /**
     * This is the path to the file were is serialized the GameMap of the shop.
     */
    public static final String SHOP = MAPS_DIRECTORY + "/Shop.json";

    /**
     * This is the path to the file were is serialized the GameMap of the
     * church.
     */
    public static final String CHURCH = MAPS_DIRECTORY + "/Church.json";

    /**
     * This is the path to the file were is serialized the GameMap of the
     * village.
     */
    public static final String VILLAGE = MAPS_DIRECTORY + "/Village.json";

    /**
     * This is the path to the file were is serialized the GameMap of the small
     * passage in the overworld, between the village and the dungeon entrance.
     */
    public static final String PASSAGE = MAPS_DIRECTORY + "/Passage.json";

    /**
     * This is the path to the file were is serialized the GameMap of the
     * dungeon entrance.
     */
    public static final String DUNGEON_ENTRANCE = MAPS_DIRECTORY + "/DungeonEntrance.json";

    private final Map<String, GameMap> maps;
    private final JsonFileManager fileManager;
    private final WorldBuilder builder;

    /**
     * Default constructor.
     */
    public WorldLoader() {
        this.maps = new HashMap<>();
        this.fileManager = new JsonFileManager();
        this.builder = new WorldBuilder();
    }

    /**
     * This method serializes the default maps generated by
     * {@link it.unibo.unori.model.maps.WorldLoader}.
     * 
     * @param rebuild
     *            true if should rebuild world, false otherwise
     * @throws IOException
     *             if an error occurs
     * @throws FileNotFoundException
     *             if the file exists but is a directory rather than a regular
     *             file, does not exist but cannot be created, or cannot be
     *             opened for any other reason
     * @throws SecurityException
     *             if a security manager exists and its checkWrite method denies
     *             write access to the file
     * @throws JsonIOException
     *             if there was a problem writing to the writer
     */
    public void serializeMaps(final boolean rebuild) throws IOException {
        final JsonFileManager fm = new JsonFileManager();
        if (rebuild) {
            builder.buildWorld();
        }
        fm.saveMap(builder.getGameMap(MAPS.CITY), VILLAGE);
        fm.saveMap(builder.getGameMap(MAPS.SHOP), SHOP);
        fm.saveMap(builder.getGameMap(MAPS.CHURCH), CHURCH);
        fm.saveMap(builder.getGameMap(MAPS.HOUSE), FOUR_NPC_ROOM);
        fm.saveMap(builder.getGameMap(MAPS.AISLE), PASSAGE);
        fm.saveMap(builder.getGameMap(MAPS.DENTRANCE), DUNGEON_ENTRANCE);

        for (int i = FIRST_FLOOR_NUMBER; i <= LAST_FLOOR_NUMBER; i++) {
            final List<GameMap> currentFloor = builder.getDungeonBuilder().getFloor(i);
            for (int j = 0; j < currentFloor.size(); j++) {
                fm.saveMap(currentFloor.get(j), getFloorRoomPath(i, j));
            }
        }
    }

    /**
     * This method returns the path of the file the corresponding GameMap
     * modeling a Dungeon room is serialized on.
     * 
     * @param floorNumber
     *            the number of the floor, from {@value #FIRST_FLOOR_NUMBER} to
     *            {@value #LAST_FLOOR_NUMBER}
     * @param roomNumber
     *            the number of the map in that floor (depends on the floor)
     * @return the path of the file the GameMap is serialized on
     */
    public static String getFloorRoomPath(final int floorNumber, final int roomNumber) {
        final StringBuilder directory = new StringBuilder(DUNGEON_DIRECTORY);
        switch (floorNumber) {
            case 1:
                directory.append("/firstFloor");
                break;
            case 2:
                directory.append("/secondFloor");
                break;
            case 3:
                directory.append("/thirdFloor");
                break;
            case 4:
                directory.append("/finalFloor");
                break;
            default:
                throw new IllegalArgumentException("Floor not present");
        }
        return directory.append("/Map").append(roomNumber).toString();
    }

    /**
     * This method loads a GameMap serialized on a specified file. The map is
     * probably not linked to other maps.
     * 
     * @param path
     *            the path were to find the file
     * @return the GameMap deserialized
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
    public GameMap loadMap(final String path) throws IOException {
        if (!this.maps.containsKey(path)) {
            this.maps.put(path, this.fileManager.loadMap(path));
        }
        return this.maps.get(path);
    }

    /**
     * This method load the whole world from default files, linking all the maps
     * together and returning the map of the village (linked to the others).
     * 
     * @return the map of the village, linked to the other maps of the world
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
    public GameMap loadWorld() throws IOException {
        if (checkFiles()) {
            builder.setGameMap(convertStandardPathToMapName(VILLAGE), this.fileManager.loadMap(VILLAGE));
            builder.setGameMap(convertStandardPathToMapName(SHOP), this.fileManager.loadMap(SHOP));
            builder.setGameMap(convertStandardPathToMapName(CHURCH), this.fileManager.loadMap(CHURCH));
            builder.setGameMap(convertStandardPathToMapName(FOUR_NPC_ROOM), this.fileManager.loadMap(FOUR_NPC_ROOM));
            builder.setGameMap(convertStandardPathToMapName(PASSAGE), this.fileManager.loadMap(PASSAGE));
            builder.setGameMap(convertStandardPathToMapName(DUNGEON_ENTRANCE),
                    this.fileManager.loadMap(DUNGEON_ENTRANCE));

            builder.getDungeonBuilder().dungeonBuild(); // Necessary to generate
                                                        // maps to have floor
                                                        // sizes

            for (int i = FIRST_FLOOR_NUMBER; i <= LAST_FLOOR_NUMBER; i++) {
                final List<GameMap> currentFloor = new ArrayList<>();
                for (int j = 0; j < builder.getDungeonBuilder().getFloor(i).size(); j++) {
                    currentFloor.add(j, this.loadMap(WorldLoader.getFloorRoomPath(i, j)));
                }
                builder.getDungeonBuilder().setFloor(i, currentFloor);
            }
        } else {
            this.serializeMaps(true);
        }

        return builder.buildWorld();
    }

    /**
     * This method converts default-paths string to the enum used to identify
     * the types of maps.
     * 
     * @param path
     *            the default-paths string of the serialized map
     * @return the respective MAPS name
     */
    private MAPS convertStandardPathToMapName(final String path) {
        Optional<MAPS> mapName;
        switch (path) {
            case FOUR_NPC_ROOM:
                mapName = Optional.of(MAPS.HOUSE);
                break;
            case SHOP:
                mapName = Optional.of(MAPS.SHOP);
                break;
            case CHURCH:
                mapName = Optional.of(MAPS.CHURCH);
                break;
            case VILLAGE:
                mapName = Optional.of(MAPS.CITY);
                break;
            case PASSAGE:
                mapName = Optional.of(MAPS.AISLE);
                break;
            case DUNGEON_ENTRANCE:
                mapName = Optional.of(MAPS.DENTRANCE);
                break;
            default:
                mapName = Optional.empty();
                break;
        }
        return mapName.orElseThrow(() -> new IllegalArgumentException());
    }

    /**
     * This method returns the internal builder of the World.
     * 
     * @return the WorldBuilder
     */
    public WorldBuilder getBuilder() {
        return this.builder;
    }

    public boolean isDungeonMap(final GameMap map) {
        return IntStream.rangeClosed(FIRST_FLOOR_NUMBER, LAST_FLOOR_NUMBER)
                .anyMatch(i -> this.builder.getDungeonBuilder().getFloor(i).contains(map));
    }

    public boolean isOutsideDungeonMap(final GameMap map) {
        final List<MAPS> mapsList = new ArrayList<>();
        for (final MAPS m : MAPS.values()) {
            if (!m.equals(MAPS.DUNGEON)) {
                mapsList.add(m);
            }
        }

        return mapsList.stream().anyMatch(m -> this.builder.getGameMap(m).equals(map));
    }

    public MAPS getMapName(final GameMap map) {
        for (final MAPS m : MAPS.values()) {
            if (this.builder.getGameMap(m).equals(map)) {
                return m;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Checks if all files exist.
     * 
     * @return
     */
    private boolean checkFiles() {
        try {
            return new File(FOUR_NPC_ROOM).isFile() && new File(DUNGEON_ENTRANCE).isFile() && new File(CHURCH).isFile()
                    && new File(PASSAGE).isFile() && new File(SHOP).isFile() && new File(VILLAGE).isFile()
                    && new File(getFloorRoomPath(FIRST_FLOOR_NUMBER, 0)).getParentFile().getParentFile().isDirectory()
                    && new File(getFloorRoomPath(FIRST_FLOOR_NUMBER, 0)).getParentFile().getParentFile()
                            .list().length > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
