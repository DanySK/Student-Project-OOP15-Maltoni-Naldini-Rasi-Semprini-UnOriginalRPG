package it.unibo.unori.controller.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.WorldBuilder;
import it.unibo.unori.model.maps.WorldBuilder.MAPS;

public class WorldLoader {
    public static final String FOUR_NPC_ROOM = "res/maps/House.json";
    public static final String SHOP = "res/maps/Shop.json";
    public static final String CHURCH = "res/maps/Church.json";
    public static final String VILLAGE = "res/maps/Village.json";
    public static final String PASSAGE = "res/maps/Passage.json";
    public static final String DUNGEON_ENTRANCE = "res/maps/DungeonEntrance.json";

    private final Map<String, GameMap> maps;
    private final JsonFileManager fileManager;

    public WorldLoader() {
        this.maps = new HashMap<>();
        this.fileManager = new JsonFileManager();
    }

    public static void main(String[] args) {
        final WorldBuilder wb = new WorldBuilder();
        final JsonFileManager fm = new JsonFileManager();
        wb.buildWorld();
        try {
            fm.saveMap(wb.getGameMap(MAPS.CITY), VILLAGE);
            fm.saveMap(wb.getGameMap(MAPS.SHOP), SHOP);
            fm.saveMap(wb.getGameMap(MAPS.CHURCH), CHURCH);
            fm.saveMap(wb.getGameMap(MAPS.HOUSE), FOUR_NPC_ROOM);
            fm.saveMap(wb.getGameMap(MAPS.AISLE), PASSAGE);
            fm.saveMap(wb.getGameMap(MAPS.DENTRANCE), DUNGEON_ENTRANCE);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public GameMap loadMap(final String path) throws IOException {
        if (!this.maps.containsKey(path)) {
            this.maps.put(path, this.fileManager.loadMap(path));
        }
        return this.maps.get(path);
    }

    public GameMap loadWorld() throws IOException {
        WorldBuilder builder = new WorldBuilder();
        builder.setGameMap(convertStandardPathToMapName(VILLAGE), this.fileManager.loadMap(VILLAGE));
        builder.setGameMap(convertStandardPathToMapName(SHOP), this.fileManager.loadMap(SHOP));
        builder.setGameMap(convertStandardPathToMapName(CHURCH), this.fileManager.loadMap(CHURCH));
        builder.setGameMap(convertStandardPathToMapName(FOUR_NPC_ROOM), this.fileManager.loadMap(FOUR_NPC_ROOM));
        builder.setGameMap(convertStandardPathToMapName(PASSAGE), this.fileManager.loadMap(PASSAGE));
        builder.setGameMap(convertStandardPathToMapName(DUNGEON_ENTRANCE), this.fileManager.loadMap(DUNGEON_ENTRANCE));

        return builder.buildWorld();
    }

    private MAPS convertStandardPathToMapName(final String path) {
        final Optional<MAPS> mapName;
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
}
