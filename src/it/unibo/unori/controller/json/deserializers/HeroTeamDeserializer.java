package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.HeroTeamImpl;

public class HeroTeamDeserializer implements JsonDeserializer<HeroTeam> {
    private static final String HERO_LIST = "heroList";
    
    @Override
    public HeroTeam deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final List<Hero> heroList = context.deserialize(((JsonObject) json).get(HERO_LIST), new TypeToken<List<Hero>>(){}.getType());
        
        return new HeroTeamImpl(heroList);
    }

}
