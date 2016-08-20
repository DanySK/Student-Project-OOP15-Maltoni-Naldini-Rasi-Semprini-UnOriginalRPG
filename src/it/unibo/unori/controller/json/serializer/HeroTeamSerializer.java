package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroImpl;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.HeroTeamImpl;

public class HeroTeamSerializer implements JsonSerializer<HeroTeam>, JsonDeserializer<HeroTeam> {

    @Override
    public JsonElement serialize(final HeroTeam src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonArray array = new JsonArray();
        src.getAllHeroes().forEach(h -> array.add(context.serialize(h, HeroImpl.class)));
        return array;
    }

    @Override
    public HeroTeam deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
                    throws JsonParseException {
        // final JsonArray array = ((JsonObject) json).get(HERO_LIST).getAsJsonArray();
        final List<Hero> heroList = new ArrayList<>();
        /* array */json.getAsJsonArray().forEach(je -> heroList.add(context.deserialize(je, Hero.class)));
        return new HeroTeamImpl(heroList);

        /*
         * final List<Hero> heroList = context.deserialize(((JsonObject) json).get(HERO_LIST), new
         * TypeToken<List<Hero>>(){}.getType());
         */
    }

}
