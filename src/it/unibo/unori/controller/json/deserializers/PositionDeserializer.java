package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import it.unibo.unori.model.maps.Position;

public class PositionDeserializer implements JsonDeserializer<Position> {
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