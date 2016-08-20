package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

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

public class CellDeserializer implements JsonDeserializer<Cell> {
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
        } else if (jObj.has(INITIAL_POS)) {
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