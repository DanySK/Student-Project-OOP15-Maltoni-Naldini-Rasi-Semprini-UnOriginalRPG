package it.unibo.unori.controller.json;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.WorldBuilder.MAPS;

/**
 * This object models a map type for better repositioning of the party after
 * game loading.
 */
public class MapType {
    private final MAPS type;
    private int floor;
    private int room;

    /**
     * Default constructor. Extracts parameters from map and loader.
     * 
     * @param map
     *            the map
     * @param loader
     *            the WorldLoader containing all the maps
     */
    public MapType(final GameMap map, final WorldLoader loader) {
        if (loader.isOutsideDungeonMap(map)) {
            type = loader.getMapName(map);
            this.floor = -1;
            this.room = -1;
        } else if (loader.isDungeonMap(map)) {
            boolean check = false;
            type = MAPS.DUNGEON;
            for (int i = WorldLoader.FIRST_FLOOR_NUMBER; i <= WorldLoader.LAST_FLOOR_NUMBER && !check; i++) {
                for (int j = 0; j < loader.getBuilder().getDungeonBuilder().getFloor(i).size() && !check; j++) {
                    if (loader.getBuilder().getDungeonBuilder().getFloor(i).get(j).equals(map)) {
                        floor = i;
                        room = j;
                        check = true;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException();
        }

    }

    /**
     * Default constructor.
     * 
     * @param type
     *            the type of the map
     * @param floor
     *            the floor of the map
     * @param room
     *            the room of the map
     */
    public MapType(final MAPS type, final int floor, final int room) {
        this.type = type;
        this.floor = floor;
        this.room = room;
    }

    /**
     * Returns the map type.
     * 
     * @return the map type
     */
    public MAPS getMapType() {
        return type;
    }

    /**
     * Returns the floor number. If it's not a dungeon, it returns -1.
     * 
     * @return the floor number
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Returns the room number. If it's not a dungeon, it returns -1.
     * 
     * @return the room number
     */
    public int getRoom() {
        return room;
    }
}
