package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.unibo.unori.model.maps.Position;

public class PositionSerializer implements JsonSerializer<Position>, JsonDeserializer<Position> {
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

    @Override
    public Position deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
                    throws JsonParseException {
        final JsonObject jObj = (JsonObject) json;

        final int posX = jObj.get(POS_X).getAsInt();
        final int posY = jObj.get(POS_Y).getAsInt();

        return new Position(posX, posY);
    }

}