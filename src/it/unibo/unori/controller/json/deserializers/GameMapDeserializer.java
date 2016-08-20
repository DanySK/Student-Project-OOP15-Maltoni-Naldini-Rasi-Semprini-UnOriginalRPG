package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.GameMapImpl;
import it.unibo.unori.model.maps.Position;
import it.unibo.unori.model.maps.cell.Cell;

public class GameMapDeserializer implements JsonDeserializer<GameMap> {
    private static final String FLOOR_MAP = "floorMap";
    private static final String INITIAL_POSITION = "initialPosition";
    private static final String BATTLE_STATE = "battleState";

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
