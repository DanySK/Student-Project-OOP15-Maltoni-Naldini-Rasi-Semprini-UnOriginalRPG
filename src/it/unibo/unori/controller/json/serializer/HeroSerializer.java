package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.Weapon;

public class HeroSerializer implements JsonSerializer<Hero> {
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
    public JsonElement serialize(final Hero src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject jObj = new JsonObject();

        final String name = src.getName();
        jObj.addProperty(NAME, name);
        final JsonElement job = context.serialize(src.getJob(), Jobs.class);
        jObj.add(JOB, job);
        final JsonElement params = context.serialize(src.getStatistics(), new TypeToken<Map<Statistics, Integer>>() {
        }.getType());
        jObj.add(STATISTIC, params);
        final JsonElement armor = context.serialize(src.getWholeArmor(), new TypeToken<Map<ArmorPieces, Armor>>() {
        }.getType());
        jObj.add(ARMOR, armor);
        JsonElement weapon;
        try {
            weapon = context.serialize(src.getWeapon(), Weapon.class);
        } catch (NoWeaponException e) {
            weapon = null;
        }
        jObj.add(WEAPON, weapon);
        final JsonElement spellList = context.serialize(src.getMagics(), new TypeToken<List<MagicAttackInterface>>() {
                }.getType());
        jObj.add(SPELL_LIST, spellList);
        final JsonElement status = context.serialize(src.getStatus(), Status.class);
        jObj.add(STATUS, status);
        final int level = src.getLevel();
        jObj.addProperty(LEVEL, level);
        final int currentBar = src.getCurrentBar();
        jObj.addProperty(CURRENT_BAR, currentBar);
        final int totExp = src.getExpTot();
        jObj.addProperty(TOT_EXP, totExp);
        final int currentExp = src.getRemainingExp();
        jObj.addProperty(CURRENT_EXP, currentExp);
        final boolean defended = src.isDefended();
        jObj.addProperty(DEFENDED, defended);
        final int currentHP = src.getRemainingHP();
        jObj.addProperty(CURRENT_HP, currentHP);
        final int currentMP = src.getCurrentMP();
        jObj.addProperty(CURRENT_MP, currentMP);

        return jObj;
    }

}
