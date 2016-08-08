package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.FoeSquadImpl;

public class FoeSquadDeserializer implements JsonDeserializer<FoeSquad> {
    private static final String ENEMIES = "enemies";
    
    @Override
    public FoeSquad deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final List<Foe> enemies = context.deserialize(((JsonObject) json).get(ENEMIES), new TypeToken<List<Foe>>(){}.getType());
        
        return new FoeSquadImpl(enemies);
    }

}
