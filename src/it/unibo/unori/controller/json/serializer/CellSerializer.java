package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.unibo.unori.model.character.Npc;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.maps.Position;
import it.unibo.unori.model.maps.cell.Cell;
import it.unibo.unori.model.maps.cell.CellState;
import it.unibo.unori.model.maps.cell.ChestCellImpl;
import it.unibo.unori.model.maps.cell.MapCellImpl;
import it.unibo.unori.model.maps.cell.NPCCellImpl;
import it.unibo.unori.model.maps.cell.ObjectCellImpl;
import it.unibo.unori.model.maps.cell.SimpleCellImpl;
import it.unibo.unori.model.maps.exceptions.NoObjectFoundException;

/**
 * This class should be registered in a {@link com.google.gson.GsonBuilder} to
 * serialize and deserialize a {@link it.unibo.unori.model.maps.cell.Cell}
 * compatible class. {@link it.unibo.unori.model.maps.cell.MapCellImpl}
 * instances are serialized and deserialized without Linked maps, so the cell
 * should be manually re-linked.
 */
public class CellSerializer implements JsonSerializer<Cell>, JsonDeserializer<Cell> {
    // Common
    private static final String PATH = "path";
    // SimpleCellImpl
    private static final String STATE = "state";
    // ObjectCellImpl
    private static final String OBJ = "obj";
    // NPCCellImpl
    private static final String NPC = "npc";
    // MapCellImpl
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

        if (ObjectCellImpl.class.isInstance(src)) {
            JsonElement obj;
            try {
                obj = context.serialize(((ObjectCellImpl) src).getObject(), Item.class);
            } catch (NoObjectFoundException e) {
                obj = null;
            }
            jObj.add(OBJ, obj);
        } else if (NPCCellImpl.class.isInstance(src)) {
            final JsonElement npc = context.serialize(((NPCCellImpl) src).getNpc(), Npc.class);
            jObj.add(NPC, npc);
        } else if (MapCellImpl.class.isInstance(src)) {
            // Because of serialization problems, I decided not to serialize
            // linked maps
            final JsonElement initialPos = context.serialize(((MapCellImpl) src).getCellMap().getInitialCellPosition(),
                    Position.class);
            jObj.add(INITIAL_POS, initialPos);
        } else if (ChestCellImpl.class.isInstance(src)) {
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
        } else if (jObj.has(INITIAL_POS)) {
            // Because of serialization problems, I decided not to serialize
            // linked maps
            final Position initialPos = context.deserialize(jObj.get(INITIAL_POS), Position.class);
            returnCell = new MapCellImpl(null, initialPos);
            returnCell.setFrame(path);
        } else if (jObj.has(ITEM)) {
            final Item item = context.deserialize(jObj.get(ITEM), Item.class);
            returnCell = new ChestCellImpl(item);
            returnCell.setFrame(path);
        } else {
            returnCell = new SimpleCellImpl(path, state);
        }

        /*
         * The state is common, but automatically set by constructor; this is
         * necessary because it can be changed.
         */
        if (!SimpleCellImpl.class.isInstance(returnCell)) {
            returnCell.setState(state);
        }

        return returnCell;
    }

}