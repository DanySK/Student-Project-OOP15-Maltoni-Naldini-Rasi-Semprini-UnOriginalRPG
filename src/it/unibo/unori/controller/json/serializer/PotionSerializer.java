package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.items.Potion;

public class PotionSerializer implements JsonSerializer<Potion> {
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String POINTS = "points";
    private static final String STATS_TO_RESTORE = "statToRestore";
    private static final String STATUS_RESTORABLE = "statusRestorable";
    
    @Override
    public JsonElement serialize(final Potion src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject jObj = new JsonObject();
        
        final String name = src.getName();
        jObj.addProperty(NAME, name);
        final String description = src.getDescription();
        jObj.addProperty(DESCRIPTION, description);
        final int points = src.getRestore();
        jObj.addProperty(POINTS, points);
        final JsonElement statToRestore = context.serialize(src.getStatisticToRestore(), Statistics.class);
        jObj.add(STATS_TO_RESTORE, statToRestore);
        final boolean statusRestorable = src.isStatusChanging();
        jObj.addProperty(STATUS_RESTORABLE, statusRestorable);
        
        return jObj;
    }

}
