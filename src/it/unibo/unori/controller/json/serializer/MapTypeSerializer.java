package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.unibo.unori.controller.json.MapType;
import it.unibo.unori.model.maps.WorldBuilder.MAPS;

/**
 * This class should be registered in a {@link com.google.gson.GsonBuilder} to
 * serialize and deserialize a {@link it.unibo.unori.controller.json.MapType}
 * class.
 */
public class MapTypeSerializer implements JsonSerializer<MapType>, JsonDeserializer<MapType> {
    private static final String TYPE = "type";
    private static final String FLOOR = "floor";
    private static final String ROOM = "room";

    @Override
    public MapType deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jObj = json.getAsJsonObject();

        final MAPS type = context.deserialize(jObj.get(TYPE), MAPS.class);
        final int floor = jObj.get(FLOOR).getAsInt();
        final int room = jObj.get(ROOM).getAsInt();

        return new MapType(type, floor, room);
    }

    @Override
    public JsonElement serialize(final MapType src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject jObj = new JsonObject();
        final JsonElement type = context.serialize(src.getMapType(), MAPS.class);
        jObj.add(TYPE, type);
        final int floor = src.getFloor();
        jObj.addProperty(FLOOR, floor);
        final int room = src.getRoom();
        jObj.addProperty(ROOM, room);

        return jObj;
    }

}
