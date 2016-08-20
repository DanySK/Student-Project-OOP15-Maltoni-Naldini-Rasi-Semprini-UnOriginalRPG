package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.GameMapImpl;
import it.unibo.unori.model.maps.Position;
import it.unibo.unori.model.maps.cell.Cell;

/**
 * This class should be registered in a {@link com.google.gson.GsonBuilder} to
 * serialize and deserialize a {@link it.unibo.unori.model.maps.GameMap}
 * compatible class. {@link it.unibo.unori.model.maps.cell.MapCellImpl}
 * instances are serialized and deserialized without Linked maps, so the map
 * should be manually re-linked.
 */
public class GameMapSerializer implements JsonSerializer<GameMap>, JsonDeserializer<GameMap> {
    private static final String FLOOR_MAP = "floorMap";
    private static final String INITIAL_POSITION = "initialPosition";
    private static final String BATTLE_STATE = "battleState";

    @Override
    public JsonElement serialize(final GameMap src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject jObj = new JsonObject();
        final Cell[][] floorMapMatrix = new Cell[src.getMapRows()][src.getMapColumns()];
        for (int i = 0; i < src.getMapRows(); i++) {
            for (int j = 0; j < src.getMapColumns(); j++) {
                floorMapMatrix[i][j] = src.getCell(new Position(i, j));
            }
        }
        final JsonElement floorMap = context.serialize(floorMapMatrix, Cell[][].class);
        jObj.add(FLOOR_MAP, floorMap);

        final JsonElement initalPosition = context.serialize(src.getInitialCellPosition(), Position.class);
        jObj.add(INITIAL_POSITION, initalPosition);

        final boolean battleState = src.isBattleState();
        jObj.addProperty(BATTLE_STATE, battleState);

        return jObj;
    }

    @Override
    public GameMap deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
                    throws JsonParseException {
        final JsonObject jObj = (JsonObject) json;
        final Cell[][] floorMap = context.deserialize(jObj.get(FLOOR_MAP), Cell[][].class);
        // System.out.println(floorMap);
        final Position initialPosition = context.deserialize(jObj.get(INITIAL_POSITION), Position.class);
        // System.out.println(initialPosition);
        final boolean battleState = jObj.get(BATTLE_STATE).getAsBoolean();
        // System.out.println(battleState);
        final GameMap returnMap = new GameMapImpl(floorMap.length, floorMap[0].length, battleState);
        returnMap.setInitialCellPosition(initialPosition);

        for (int i = 0; i < floorMap.length; i++) {
            for (int j = 0; j < floorMap[i].length; j++) {
                returnMap.setCell(new Position(i, j), floorMap[i][j]);
            }
        }

        return returnMap;
    }

}
