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

import it.unibo.unori.model.battle.MagicAttack;
import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.Statistics;
/**
 * This class should be registered in a {@link com.google.gson.GsonBuilder} to
 * serialize and deserialize a {@link it.unibo.unori.model.battle.MagicAttackInterface}
 * compatible class.
 */
public class MagicAttackSerializer implements JsonSerializer<MagicAttackInterface>, JsonDeserializer<MagicAttackInterface> {
    private static final String NAME = "name";
    private static final String SHOWN_STRING = "shownString";
    private static final String DESCRIPTION = "description";
    private static final String STATS = "stats";
    private static final String ACCURACY = "accuracy";
    private static final String MP_REQUIRED = "mpRequired";

    @Override
    public JsonElement serialize(final MagicAttackInterface src, final Type typeOfSrc,
                    final JsonSerializationContext context) {
        final JsonObject jObj = new JsonObject();

        final String name = src.toString();
        jObj.addProperty(NAME, name);
        final String shownString = src.getStringToShow();
        jObj.addProperty(SHOWN_STRING, shownString);
        final String description = src.getDescription();
        jObj.addProperty(DESCRIPTION, description);
        final Map<Statistics, Integer> statsMap = new HashMap<>();
        statsMap.put(Statistics.FIREATK, src.getFireAtk());
        statsMap.put(Statistics.ICEATK, src.getIceAtk());
        statsMap.put(Statistics.THUNDERATK, src.getThunderAtk());
        statsMap.put(Statistics.PHYSICATK, src.getPhysicAtk());
        final JsonElement stats = context.serialize(statsMap, new TypeToken<Map<Statistics, Integer>>() {
        }.getType());
        jObj.add(STATS, stats);
        final int accuracy = src.getAccuracy();
        jObj.addProperty(ACCURACY, accuracy);
        final int mpRequired = src.getMPRequired();
        jObj.addProperty(MP_REQUIRED, mpRequired);

        return jObj;
    }

    @Override
    public MagicAttackInterface deserialize(final JsonElement json, final Type typeOfT,
            final JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jObj = (JsonObject) json;

        final String name = jObj.get(NAME).getAsString();
        final String shownString = jObj.get(SHOWN_STRING).getAsString();
        final String description = jObj.get(DESCRIPTION).getAsString();
        final Map<Statistics, Integer> stats = context.deserialize(jObj.get(STATS),
                new TypeToken<Map<Statistics, Integer>>() {
                }.getType());
        final int accuracy = jObj.get(ACCURACY).getAsInt();
        final int mpRequired = jObj.get(MP_REQUIRED).getAsInt();

        return new MagicAttack(name, shownString, description, stats.get(Statistics.FIREATK),
                stats.get(Statistics.THUNDERATK), stats.get(Statistics.ICEATK), stats.get(Statistics.PHYSICATK),
                accuracy, mpRequired);
    }
}
