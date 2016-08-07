package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponImpl;

public class WeaponDeserializer implements JsonDeserializer<Weapon> {
    private final static String NAME = "name";
    private final static String DESC = "desc";
    private final static String STATS = "stats";
    private final static String INFLICTED_STATUS = "inflictedStatus";
    
    
    @Override
    public Weapon deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jObj = (JsonObject) json;
        
        final String name = jObj.get(NAME).getAsString();
        final String desc = jObj.get(DESC).getAsString();
        final Map<Statistics, Integer> stats = new HashMap<Statistics, Integer>(context.deserialize(jObj.get(STATS),
                new TypeToken<Map<Statistics, Integer>>() {
                }.getType())); // TODO Probably unnecessary new map
        final Status inflictedStatus = context.deserialize(jObj.get(INFLICTED_STATUS), Status.class);
        
        return new WeaponImpl(name, desc, stats, inflictedStatus);
    }
}
