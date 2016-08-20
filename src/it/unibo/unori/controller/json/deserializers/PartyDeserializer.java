package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.SingletonParty;

/**
 * This class should be registered in a {@link com.google.gson.GsonBuilder} to
 * deserialize a {@link it.unibo.unori.model.maps.Party} compatible class.
 * {@link it.unibo.unori.model.maps.cell.MapCellImpl} instances are serialized
 * and deserialized without Linked maps, so the map inside the Party should be
 * manually re-linked.
 */
public class PartyDeserializer implements JsonDeserializer<Party> {

    @Override
    public Party deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        return context.deserialize(json, SingletonParty.getParty().getClass());
    }

}
