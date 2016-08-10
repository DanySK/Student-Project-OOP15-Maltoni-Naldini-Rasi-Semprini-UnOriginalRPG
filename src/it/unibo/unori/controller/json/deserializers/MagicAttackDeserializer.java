package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.model.battle.MagicAttack;
import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.Statistics;

public class MagicAttackDeserializer implements JsonDeserializer<MagicAttackInterface> {
    private static final String NAME = "name";
    private static final String SHOWN_STRING = "shownString";
    private static final String DESCRIPTION = "description";
    private static final String STATS = "stats";
    private static final String ACCURACY = "accuracy";
    private static final String MP_REQUIRED = "mpRequired";

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
