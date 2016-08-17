package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import it.unibo.unori.model.character.Npc;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.GameMapImpl;
import it.unibo.unori.model.maps.Position;
import it.unibo.unori.model.maps.cell.Cell;
import it.unibo.unori.model.maps.cell.CellState;
import it.unibo.unori.model.maps.cell.ChestCellImpl;
import it.unibo.unori.model.maps.cell.MapCellImpl;
import it.unibo.unori.model.maps.cell.NPCCellImpl;
import it.unibo.unori.model.maps.cell.ObjectCellImpl;
import it.unibo.unori.model.maps.cell.SimpleCellImpl;

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

    public static class PositionDeserializer implements JsonDeserializer<Position> {
        private static final String POS_X = "posX";
        private static final String POS_Y = "posY";

        @Override
        public Position deserialize(final JsonElement json, final Type typeOfT,
                final JsonDeserializationContext context) throws JsonParseException {
            final JsonObject jObj = (JsonObject) json;

            final int posX = jObj.get(POS_X).getAsInt();
            final int posY = jObj.get(POS_Y).getAsInt();

            return new Position(posX, posY);
        }

    }

    public static class CellDeserializer implements JsonDeserializer<Cell> {
        // Common
        private static final String PATH = "path";
        // SimpleCellImpl
        private static final String STATE = "state";
        // ObjectCellImpl
        private static final String OBJ = "obj";
        // NPCCellImpl
        private static final String NPC = "npc";
        // MapCellImpl
        // private static final String MAP_TO_LINK = "mapToLink";
        private static final String INITIAL_POS = "initialPos";
        // ChestCellImpl
        private static final String ITEM = "o";

        @Override
        public Cell deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
                throws JsonParseException {
            final JsonObject jObj = (JsonObject) json;

            final String path = jObj.get(PATH).getAsString();

            Cell returnCell;
            final CellState state = context.deserialize(jObj.get(STATE), CellState.class);

            if (jObj.has(OBJ)) {
                final Item obj = context.deserialize(jObj.get(OBJ), Item.class);
                returnCell = new ObjectCellImpl(obj);
                returnCell.setFrame(path);
            } else if (jObj.has(NPC)) {
                final Npc npc = context.deserialize(jObj.get(NPC), Npc.class);
                returnCell = new NPCCellImpl(path, npc);
            } else if (jObj.has(INITIAL_POSITION)) {
                // final GameMap mapToLink = context.deserialize(jObj.get(MAP_TO_LINK), GameMap.class);
                final Position initialPos = context.deserialize(jObj.get(INITIAL_POS), Position.class);
                returnCell = new MapCellImpl(/*mapToLink*/null, initialPos);
                returnCell.setFrame(path);
            } else if (jObj.has(ITEM)) {
                final Item item = context.deserialize(jObj.get(ITEM), Item.class);
                returnCell = new ChestCellImpl(item);
                returnCell.setFrame(path);
            } else {
                returnCell = new SimpleCellImpl(path, state);
            }

            /*
             * The state is common, but automatically set by constructor;
             * this is necessary because it can be changed. 
             */
            if (!SimpleCellImpl.class.isInstance(returnCell)) { // TODO check, maybe unnecessary
                returnCell.setState(state);
            }

            return returnCell;
        }

    }

}
