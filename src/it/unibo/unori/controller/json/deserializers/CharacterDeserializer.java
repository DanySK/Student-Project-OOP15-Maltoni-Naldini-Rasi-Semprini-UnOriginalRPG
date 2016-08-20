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
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.items.Weapon;

public class CharacterDeserializer implements JsonDeserializer<Character> {
    private static final String NAME = "name";
    private static final String BATTLE_FRAME = "battleFrame";
    private static final String CURRENT_HP = "currentHP";
    private static final String CURRENT_MP = "currentMP";
    private static final String LEVEL = "level";
    private static final String WEAPON = "weapon";
    private static final String STATUS = "status";
    private static final String STATISTIC = "statistic";
    private static final String SPELL_LIST = "spellList";

    @Override
    public Character deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        Character returnChar;

        if (typeOfT.getClass().isAssignableFrom(Hero.class)) { // TODO check
            returnChar = context.deserialize(json, Hero.class);
        } else if (typeOfT.getClass().isAssignableFrom(Foe.class)) { // TODO check
            returnChar = context.deserialize(json, Foe.class);
        } else {
            final JsonObject jObj = (JsonObject) json;

            final String name = jObj.get(NAME).getAsString();
            final String battleFrame = jObj.get(BATTLE_FRAME).getAsString();
            final Map<Statistics, Integer> map = context.deserialize(jObj.get(STATISTIC),
                    new TypeToken<Map<Statistics, Integer>>() {
                    }.getType());
            final int level = jObj.get(LEVEL).getAsInt();
            final List<MagicAttackInterface> spellList = context.deserialize(jObj.get(SPELL_LIST),
                    new TypeToken<List<MagicAttackInterface>>() {
                    }.getType());
            final Weapon weapon = context.deserialize(jObj.get(WEAPON), Weapon.class);
            returnChar = new CharacterImpl(name, battleFrame, map, level, spellList, weapon);
            final int currentHP = jObj.get(CURRENT_HP).getAsInt();
            if (returnChar.getRemainingHP() > currentHP) {
                returnChar.takeDamage(returnChar.getRemainingHP() - currentHP);
            } else if (returnChar.getRemainingHP() < currentHP) {
                returnChar.restoreHP(currentHP - returnChar.getRemainingHP());
            }
            final int currentMP = jObj.get(CURRENT_MP).getAsInt();
            if (returnChar.getCurrentMP() > currentMP) {
                returnChar.consumeMP(returnChar.getCurrentMP() - currentMP);
            } else if (returnChar.getCurrentMP() < currentMP) {
                returnChar.restoreMP(currentMP - returnChar.getCurrentMP());
            }
            final Status status = context.deserialize(jObj.get(STATUS), Status.class);
            returnChar.setStatus(status);
        }

        return returnChar;
    }

}
