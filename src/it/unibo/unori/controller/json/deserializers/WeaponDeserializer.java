package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponImpl;

public class WeaponDeserializer implements JsonDeserializer<Weapon> {
    private static final String NAME = "name";
    private static final String DESC = "desc";
    private static final String STATS = "stats";
    private static final String INFLICTED_STATUS = "inflictedStatus";

    @Override
    public Weapon deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
                    throws JsonParseException {
        final JsonObject jObj = (JsonObject) json;

        final String name = jObj.get(NAME).getAsString();
        final String desc = jObj.get(DESC).getAsString();
        final Map<Statistics, Integer> stats = context.deserialize(jObj.get(STATS),
                        new TypeToken<Map<Statistics, Integer>>() {
                        }.getType());
        final Status inflictedStatus = context.deserialize(jObj.get(INFLICTED_STATUS), Status.class);

        return new WeaponImpl(name, desc, stats, inflictedStatus);
    }
}
