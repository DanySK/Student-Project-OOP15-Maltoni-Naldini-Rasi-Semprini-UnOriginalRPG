package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.ArmorImpl;
import it.unibo.unori.model.items.Armor.ArmorPieces;

public class ArmorSerializer implements JsonSerializer<Armor>, JsonDeserializer<Armor> {
    private static final String NAME = "name";
    private static final String PIECE = "piece";
    private static final String DESC = "desc";
    private static final String STATS = "stats";
    private static final String IMMUNITY = "immunity";

    @Override
    public JsonElement serialize(final Armor src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject jObj = new JsonObject();

        final String name = src.getName();
        jObj.addProperty(NAME, name);
        final JsonElement piece = context.serialize(src.getArmorClass(), ArmorPieces.class);
        jObj.add(PIECE, piece);
        final String desc = src.getDescription();
        jObj.addProperty(DESC, desc);
        final JsonElement stats = context.serialize(src.getStats(), new TypeToken<Map<Statistics, Integer>>() {
        }.getType());
        jObj.add(STATS, stats);
        final JsonElement immunity = context.serialize(src.getImmunity(), Status.class);
        jObj.add(IMMUNITY, immunity);
        return jObj;
    }
    
    @Override
    public Armor deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jObj = (JsonObject) json;

        final String name = jObj.get(NAME).getAsString();
        final ArmorPieces piece = context.deserialize(jObj.get(PIECE), ArmorPieces.class);
        final String desc = jObj.get(DESC).getAsString();
        final Map<Statistics, Integer> stats = new HashMap<Statistics, Integer>(context.deserialize(jObj.get(STATS),
                new TypeToken<Map<Statistics, Integer>>() {
                }.getType())); // TODO Probably unnecessary new map
        final Status immunity = context.deserialize(jObj.get(IMMUNITY), Status.class);

        return new ArmorImpl(name, piece, desc, stats, immunity);
    }

}
