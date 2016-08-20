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

/**
 * This class should be registered in a {@link com.google.gson.GsonBuilder} to
 * serialize and deserialize a {@link it.unibo.unori.model.character.HeroTeam}
 * compatible class.
 */
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
        final List<Hero> heroList = new ArrayList<>();
        json.getAsJsonArray().forEach(je -> heroList.add(context.deserialize(je, Hero.class)));
        return new HeroTeamImpl(heroList);
    }

}
