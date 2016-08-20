package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.unibo.unori.model.character.Npc;
import it.unibo.unori.model.character.NpcImpl;
import it.unibo.unori.model.menu.Dialogue;

/**
 * This class should be registered in a {@link com.google.gson.GsonBuilder} to
 * serialize and deserialize a {@link it.unibo.unori.model.character.Npc}
 * compatible class.
 */
public class NpcSerializer implements JsonSerializer<Npc>, JsonDeserializer<Npc> {
    private static final String SENTENCE = "sentence";

    @Override
    public JsonElement serialize(final Npc src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject jObj = new JsonObject();

        final JsonElement sentence = context.serialize(src.getDialogue(), Dialogue.class);
        jObj.add(SENTENCE, sentence);

        return jObj;
    }

    @Override
    public Npc deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final Dialogue sentence = context.deserialize(((JsonObject) json).get(SENTENCE), Dialogue.class);
        return new NpcImpl(sentence);
    }

}
