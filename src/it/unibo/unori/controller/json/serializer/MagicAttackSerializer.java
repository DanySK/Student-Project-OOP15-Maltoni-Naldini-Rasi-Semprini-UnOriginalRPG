package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.Statistics;

public class MagicAttackSerializer implements JsonSerializer<MagicAttackInterface> {
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

}
