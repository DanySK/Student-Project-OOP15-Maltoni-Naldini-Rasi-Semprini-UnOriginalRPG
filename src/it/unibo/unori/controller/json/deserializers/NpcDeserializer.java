package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import it.unibo.unori.model.character.Npc;
import it.unibo.unori.model.character.NpcImpl;
import it.unibo.unori.model.menu.Dialogue;

public class NpcDeserializer implements JsonDeserializer<Npc> {
    private static final String SENTENCE = "sentence";

    @Override
    public Npc deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final Dialogue sentence = context.deserialize(((JsonObject) json).get(SENTENCE),
                Dialogue.class);
        return new NpcImpl(sentence);
    }

}
