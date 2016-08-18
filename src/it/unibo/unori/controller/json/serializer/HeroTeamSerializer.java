package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.unibo.unori.model.character.HeroImpl;
import it.unibo.unori.model.character.HeroTeam;

public class HeroTeamSerializer implements JsonSerializer<HeroTeam> {
    // private static final String HERO_LIST = "heroList";

    @Override
    public JsonElement serialize(final HeroTeam src, final Type typeOfSrc, final JsonSerializationContext context) {
        // final JsonObject jObj = new JsonObject();
        final JsonArray array = new JsonArray();
        src.getAllHeroes().forEach(h -> array.add(context.serialize(h, HeroImpl.class)));
        /*final JsonElement heroList = context.serialize(src.getAllHeroes(), new TypeToken<List<Hero>>() {
        }.getType());*/
        // jObj.add(HERO_LIST, array);

        return /*jObj*/ array;
    }

}
