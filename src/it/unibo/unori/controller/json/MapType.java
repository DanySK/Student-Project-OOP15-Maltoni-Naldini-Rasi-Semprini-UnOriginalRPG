package it.unibo.unori.controller.json;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.WorldBuilder.MAPS;

public class MapType {
    private final MAPS mapType;
    private int floor;
    private int room;

    /**
     * Default constructor.
     * 
     * @param map
     *            the map
     * @param loader
     *            the WorldLoader containing all the maps
     */
    public MapType(final GameMap map, final WorldLoader loader) {
        if (loader.isOutsideDungeonMap(map)) {
            mapType = loader.getMapName(map);
            this.floor = -1;
            this.room = -1;
        } else if (loader.isDungeonMap(map)) {
            boolean check = false;
            mapType = MAPS.DUNGEON;
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
     * Returns the map type.
     * 
     * @return the map type
     */
    public MAPS getMapType() {
        return mapType;
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
