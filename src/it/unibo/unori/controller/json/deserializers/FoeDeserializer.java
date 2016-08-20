package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeImpl;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.character.factory.FoesFindable;

public class FoeDeserializer implements JsonDeserializer<Foe> {
    // CharacterImpl
    private static final String NAME = "name";
    private static final String BATTLE_FRAME = "battleFrame";
    private static final String CURRENT_HP = "currentHP";
    private static final String CURRENT_MP = "currentMP";
    private static final String LEVEL = "level";
    private static final String STATUS = "status";
    // private static final String STATISTIC = "statistic";
    // private static final String WEAPON = "wep";
    private static final String SPELL_LIST = "spellList";
    // FoeImpl
    private static final String SMARTNESS = "ia";
    private static final String TYPE = "type";

    @Override
    public Foe deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jObj = (JsonObject) json;

        // Deserializing fields for constructor
        final String name = jObj.get(NAME).getAsString();
        /*final Map<Statistics, Integer> params = context.deserialize(jObj.get(STATISTIC),
                new TypeToken<Map<Statistics, Integer>>() {
                }.getType());
        final Weapon weapon = context.deserialize(jObj.get(WEAPON), Weapon.class);*/
        final FoesFindable type = context.deserialize(jObj.get(TYPE), FoesFindable.class);
        final int smartness = jObj.get(SMARTNESS).getAsInt();
        final String battleFrame = jObj.get(BATTLE_FRAME).getAsString();
        // Instantiation
        final Foe returnFoe = new FoeImpl(smartness, name, battleFrame, type);
        // Other fields
        final int currentHP = jObj.get(CURRENT_HP).getAsInt();
        if (returnFoe.getRemainingHP() > currentHP) {
            returnFoe.takeDamage(returnFoe.getRemainingHP() - currentHP);
        } else if (returnFoe.getRemainingHP() < currentHP) {
            returnFoe.restoreHP(currentHP - returnFoe.getRemainingHP());
        }
        final int currentMP = jObj.get(CURRENT_MP).getAsInt();
        if (returnFoe.getCurrentMP() > currentMP) {
            returnFoe.consumeMP(returnFoe.getCurrentMP() - currentMP);
        } else if (returnFoe.getCurrentMP() < currentMP) {
            returnFoe.restoreMP(currentMP - returnFoe.getCurrentMP());
        }
        final int level = jObj.get(LEVEL).getAsInt();
        returnFoe.setLevel(level);
        final Status status = context.deserialize(jObj.get(STATUS), Status.class);
        returnFoe.setStatus(status);
        final List<MagicAttackInterface> spellList = context.deserialize(jObj.get(SPELL_LIST),
                new TypeToken<List<MagicAttackInterface>>() {
                }.getType());
        spellList.forEach(returnFoe::addSpell); // TODO check

        return returnFoe;
    }

}
