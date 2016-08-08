package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeImpl;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.items.Weapon;

public class FoeDeserializer implements JsonDeserializer<Foe> {
    // CharacterImpl
    private static final String NAME = "name";
    private static final String BATTLE_FRAME = "battleFrame";
    private static final String CURRENT_HP = "currentHP";
    private static final String CURRENT_MP = "currentMP";
    private static final String LEVEL = "level";
    private static final String STATUS = "status";
    private static final String STATISTIC = "statistic";
    private static final String SPELL_LIST = "spellList";
    // FoeImpl
    private static final String IA = "ia";
    private static final String WEAPON = "wep";

    @Override
    public Foe deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jObj = (JsonObject) json;

        // Deserializing fields for constructor
        final String name = jObj.get(NAME).getAsString();
        final Map<Statistics, Integer> params = context.deserialize(jObj.get(STATISTIC),
                new TypeToken<Map<Statistics, Integer>>() {
                }.getType());
        final Weapon weapon = context.deserialize(jObj.get(WEAPON), Weapon.class);
        final int ia = jObj.get(IA).getAsInt();
        final String battleFrame = jObj.get(BATTLE_FRAME).getAsString();
        // Instantiation
        final Foe returnFoe = new FoeImpl(ia, name, battleFrame, params, weapon);
        // Other fields

        // TODO

        return returnFoe; // TODO
    }

}
