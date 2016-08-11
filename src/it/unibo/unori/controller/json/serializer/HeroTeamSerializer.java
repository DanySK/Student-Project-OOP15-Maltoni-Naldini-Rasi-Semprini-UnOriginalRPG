package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;

public class HeroTeamSerializer implements JsonSerializer<HeroTeam> {
    private static final String HERO_LIST = "heroList";

    @Override
    public JsonElement serialize(final HeroTeam src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject jObj = new JsonObject();
        final JsonElement heroList = context.serialize(src.getAllHeroes(), new TypeToken<List<Hero>>() {
        }.getType());
        jObj.add(HERO_LIST, heroList);

        return jObj;
    }

}
