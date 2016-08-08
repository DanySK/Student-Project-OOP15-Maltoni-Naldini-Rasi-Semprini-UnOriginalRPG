package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.Character;
import it.unibo.unori.model.character.CharacterImpl;
import it.unibo.unori.model.character.Statistics;

public class CharacterDeserializer implements JsonDeserializer<Character> {
    private static final String NAME = "name";
    private static final String BATTLE_FRAME = "battleFrame";
    private static final String CURRENT_HP = "currentHP";
    private static final String CURRENT_MP = "currentMP";
    private static final String LEVEL = "level";
    private static final String STATUS = "status";
    private static final String STATISTIC = "statistic";
    private static final String SPELL_LIST = "spellList";

    @Override
    public Character deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jObj = (JsonObject) json;

        final String name = jObj.get(NAME).getAsString();
        final String battleFrame = jObj.get(BATTLE_FRAME).getAsString();
        final Map<Statistics, Integer> map = context.deserialize(jObj.get(STATISTIC),
                new TypeToken<Map<Statistics, Integer>>() {
                }.getType());
        final int level = jObj.get(LEVEL).getAsInt();
        final List<MagicAttackInterface> spellList = context.deserialize(jObj.get(SPELL_LIST),
                new TypeToken<List<MagicAttackInterface>>() {
                }.getType());
        final Character returnChar = new CharacterImpl(name, battleFrame, map, level, spellList);

        // TODO Miss some other fields

        // TODO Miss check for extensions classes

        return returnChar;
    }

}
