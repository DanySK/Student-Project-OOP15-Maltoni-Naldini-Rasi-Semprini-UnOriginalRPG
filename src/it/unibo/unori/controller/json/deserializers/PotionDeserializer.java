package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.PotionImpl;

public class PotionDeserializer implements JsonDeserializer<Potion> {
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String POINTS = "points";
    private static final String STATS_TO_RESTORE = "statsToRestore";
    private static final String STATUS_RESTORABLE =  "statusRestorable";
    
    
    @Override
    public Potion deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jObj = (JsonObject) json;
        
        final String name = jObj.get(NAME).getAsString();
        final String description = jObj.get(DESCRIPTION).getAsString();
        final int points = jObj.get(POINTS).getAsInt();
        final Statistics statToRestore = context.deserialize(jObj.get(STATS_TO_RESTORE), Statistics.class);
        final boolean statusRestorable = jObj.get(STATUS_RESTORABLE).getAsBoolean();
        
        return new PotionImpl(points, statToRestore, name, description, statusRestorable);
    }

}
