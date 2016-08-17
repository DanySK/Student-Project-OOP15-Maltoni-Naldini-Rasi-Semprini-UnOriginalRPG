package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.unibo.unori.model.character.Npc;
import it.unibo.unori.model.menu.Dialogue;

public class NpcSerializer implements JsonSerializer<Npc> {
    private static final String SENTENCE = "sentence";

    @Override
    public JsonElement serialize(final Npc src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject jObj = new JsonObject();

        final JsonElement sentence = context.serialize(src.getDialogue(), Dialogue.class);
        jObj.add(SENTENCE, sentence);

        return jObj;
    }

}
