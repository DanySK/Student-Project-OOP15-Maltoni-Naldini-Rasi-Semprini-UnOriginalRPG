package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.SingletonParty;

public class PartyDeserializer implements JsonDeserializer<Party> {
    // private final static String CURRENT_POSITION = "currentPosition";
    // private final static String CURRENT_MAP = "currentMap";
    // private final static String FRAMES = "frames";
    // private final static String ORIENTATION = "orientation";
    // private final static String PARTY_BAG = "partyBag";
    // private final static String HERO_TEAM = "heroteam";
    
    @Override
    public Party deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        // final JsonObject jObj = (JsonObject) json;
        
        // final Position currentPosition = context.deserialize(jObj.get(CURRENT_POSITION), Position.class);
        // final GameMap currentMap = context.deserialize(jObj.get(CURRENT_MAP), GameMap.class);
        // final Map<CardinalPoints, String> frames = new HashMap<CardinalPoints, String>(context.deserialize(jObj.get(FRAMES), new TypeToken<Map<CardinalPoints, String>>() {
        // }.getType())); // TODO Probably unnecessary new map
        // final CardinalPoints orientation = context.deserialize(jObj.get(ORIENTATION), CardinalPoints.class);
        // final Bag partyBag = context.deserialize(jObj.get(PARTY_BAG), Bag.class);
        // final HeroTeam heroteam = context.deserialize(jObj.get(HERO_TEAM), HeroTeam.class);
        
        return context.deserialize(json, SingletonParty.getParty().getClass()); // TODO I'm not sure this would work, but for now it's OK
    }

}
