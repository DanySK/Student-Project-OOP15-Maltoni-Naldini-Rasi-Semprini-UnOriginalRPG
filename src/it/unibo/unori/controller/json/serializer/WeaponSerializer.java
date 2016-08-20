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
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponImpl;

public class WeaponSerializer implements JsonSerializer<Weapon>, JsonDeserializer<Weapon> {
    private static final String NAME = "name";
    private static final String DESC = "desc";
    private static final String STATS = "stats";
    private static final String INFLICTED_STATUS = "inflictedStatus";

    @Override
    public JsonElement serialize(final Weapon src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject jObj = new JsonObject();

        final String name = src.getName();
        jObj.addProperty(NAME, name);
        final String desc = src.getDescription();
        jObj.addProperty(DESC, desc);
        final Map<Statistics, Integer> statsMap = new HashMap<>();
        statsMap.put(Statistics.PHYSICATK, src.getPhysicalAtk());
        statsMap.put(Statistics.FIREATK, src.getFireAtk());
        statsMap.put(Statistics.ICEATK, src.getIceAtk());
        statsMap.put(Statistics.THUNDERATK, src.getThunderAtk());
        final JsonElement stats = context.serialize(statsMap, new TypeToken<Map<Statistics, Integer>>() {
        }.getType());
        jObj.add(STATS, stats);
        final JsonElement inflictedStatus = context.serialize(src.getWeaponStatus(), Status.class);
        jObj.add(INFLICTED_STATUS, inflictedStatus);

        return jObj;
    }

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
