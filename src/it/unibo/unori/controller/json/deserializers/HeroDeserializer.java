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
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroImpl;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.Armor.ArmorPieces;

public class HeroDeserializer implements JsonDeserializer<Hero> {
    // CharacterImpl
    private static final String NAME = "name";
    private static final String CURRENT_HP = "currentHP";
    private static final String CURRENT_MP = "currentMP";
    private static final String LEVEL = "level";
    private static final String STATUS = "status";
    private static final String STATISTIC = "statistic";
    private static final String SPELL_LIST = "spellList";
    // HeroImpl
    private static final String ARMOR = "armor";
    private static final String WEAPON = "weapon";
    private static final String JOB = "heroJob";
    private static final String TOT_EXP = "totExp";
    private static final String CURRENT_EXP = "currentExp";
    private static final String CURRENT_BAR = "currentBar";
    private static final String DEFENDED = "defended";

    @Override
    public Hero deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jObj = (JsonObject) json;

        // Deserializing fields for constructor
        final String name = jObj.get(NAME).getAsString();
        final Jobs job = context.deserialize(jObj.get(JOB), Jobs.class);
        final Map<Statistics, Integer> params = context.deserialize(jObj.get(STATISTIC),
                new TypeToken<Map<Statistics, Integer>>() {
                }.getType());
        final Map<ArmorPieces, Armor> armor = context.deserialize(jObj.get(ARMOR),
                new TypeToken<Map<ArmorPieces, Armor>>() {
                }.getType());
        final Weapon weapon = context.deserialize(jObj.get(WEAPON), Weapon.class);
        // Instantiation
        final Hero returnHero = new HeroImpl(name, job, params, armor, weapon);
        // Other fields
        final List<MagicAttackInterface> spellList = context.deserialize(jObj.get(SPELL_LIST),
                new TypeToken<List<MagicAttackInterface>>() {
                }.getType());
        spellList.forEach(returnHero::addSpell); // TODO check
        final Status status = context.deserialize(jObj.get(STATUS), Status.class);
        returnHero.setStatus(status);
        final int level = jObj.get(LEVEL).getAsInt();
        returnHero.setLevel(level);
        final int currentBar = jObj.get(CURRENT_BAR).getAsInt();
        returnHero.setCurrentBar(currentBar); // TODO check
        final int totExp = jObj.get(TOT_EXP).getAsInt();
        returnHero.setTotExp(totExp); // TODO check
        final int currentExp = jObj.get(CURRENT_EXP).getAsInt();
        returnHero.addExp(currentExp);
        final boolean defended = jObj.get(DEFENDED).getAsBoolean();
        if (defended) {
            returnHero.setDefended();
        } else {
            returnHero.setUndefended();
        }
        final int currentHP = jObj.get(CURRENT_HP).getAsInt();
        if (returnHero.getRemainingHP() > currentHP) {
            returnHero.takeDamage(returnHero.getRemainingHP() - currentHP);
        } else if (returnHero.getRemainingHP() < currentHP) {
            returnHero.restoreHP(currentHP - returnHero.getRemainingHP());
        }
        final int currentMP = jObj.get(CURRENT_MP).getAsInt();
        if (returnHero.getCurrentMP() > currentMP) {
            returnHero.consumeMP(returnHero.getCurrentMP() - currentMP);
        } else if (returnHero.getCurrentMP() < currentMP) {
            returnHero.restoreMP(currentMP - returnHero.getCurrentMP());
        }

        return returnHero;
    }

}
