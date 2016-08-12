package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.unibo.unori.model.character.Npc;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.Position;
import it.unibo.unori.model.maps.cell.Cell;
import it.unibo.unori.model.maps.cell.CellState;
import it.unibo.unori.model.maps.cell.ChestCellImpl;
import it.unibo.unori.model.maps.cell.MapCellImpl;
import it.unibo.unori.model.maps.cell.NPCCellImpl;
import it.unibo.unori.model.maps.cell.ObjectCellImpl;
import it.unibo.unori.model.maps.exceptions.NoObjectFoundException;

public class GameMapSerializer implements JsonSerializer<GameMap> {
    private static final String FLOOR_MAP = "floorMap";
    private static final String INITIAL_POSITION = "initialPosition";

    @Override
    public JsonElement serialize(final GameMap src, final Type typeOfSrc, final JsonSerializationContext context) {
        JsonObject jObj = new JsonObject();
        final Cell[][] floorMapMatrix = new Cell[src.getMapLength()][src.getMapWidth()];
        for (int i = 0; i < src.getMapLength(); i++) {
            for (int j = 0; j < src.getMapWidth(); j++) {
                floorMapMatrix[i][j] = src.getCell(new Position(i, j));
            }
        }
        final JsonElement floorMap = context.serialize(floorMapMatrix, Cell[][].class);
        jObj.add(FLOOR_MAP, floorMap);

        final JsonElement initalPosition = context.serialize(src.getInitialCellPosition(), Position.class);
        jObj.add(INITIAL_POSITION, initalPosition);

        return jObj;
    }

    public static class PositionSerializer implements JsonSerializer<Position> {
        private static final String POS_X = "posX";
        private static final String POS_Y = "posY";

        @Override
        public JsonElement serialize(final Position src, final Type typeOfSrc, final JsonSerializationContext context) {
            final JsonObject jObj = new JsonObject();

            final int posX = src.getPosX();
            jObj.addProperty(POS_X, posX);
            final int posY = src.getPosY();
            jObj.addProperty(POS_Y, posY);

            return jObj;
        }

    }

    public static class CellSerializer implements JsonSerializer<Cell> {
        // Common
        private static final String PATH = "path";
        // SimpleCellImpl
        private static final String STATE = "state";
        // ObjectCellImpl
        private static final String OBJ = "obj";
        // NPCCellImpl
        private static final String NPC = "npc";
        // MapCellImpl
        private static final String MAP_TO_LINK = "mapToLink";
        private static final String INITIAL_POS = "initialPos";
        // ChestCellImpl
        private static final String ITEM = "o";

        @Override
        public JsonElement serialize(final Cell src, final Type typeOfSrc, final JsonSerializationContext context) {
            final JsonObject jObj = new JsonObject();

            final String path = src.getFrame();
            jObj.addProperty(PATH, path);
            final JsonElement state = context.serialize(src.getState(), CellState.class);
            jObj.add(STATE, state);

            if (typeOfSrc.getClass().isAssignableFrom(ObjectCellImpl.class)) {
                JsonElement obj;
                try {
                    obj = context.serialize(((ObjectCellImpl) src).getObject(), Item.class);
                } catch (NoObjectFoundException e) {
                    obj = null;
                }
                jObj.add(OBJ, obj);
            } else if (typeOfSrc.getClass().isAssignableFrom(NPCCellImpl.class)) {
                final JsonElement npc = context.serialize(((NPCCellImpl) src).getNpc(), Npc.class);
                jObj.add(NPC, npc);
            } else if (typeOfSrc.getClass().isAssignableFrom(MapCellImpl.class)) {
                final JsonElement mapToLink = context.serialize(((MapCellImpl) src).getCellMap(), GameMap.class);
                jObj.add(MAP_TO_LINK, mapToLink);
                final JsonElement initialPos = context
                        .serialize(((MapCellImpl) src).getCellMap().getInitialCellPosition(), Position.class);
                jObj.add(INITIAL_POS, initialPos);
            } else if (typeOfSrc.getClass().isAssignableFrom(ChestCellImpl.class)) {
                JsonElement item;
                try {
                    item = context.serialize(((ChestCellImpl) src).getObject(), Item.class);
                } catch (NoObjectFoundException e) {
                    item = null;
                }
                jObj.add(ITEM, item);
            }

            return jObj;
        }

    }

}
