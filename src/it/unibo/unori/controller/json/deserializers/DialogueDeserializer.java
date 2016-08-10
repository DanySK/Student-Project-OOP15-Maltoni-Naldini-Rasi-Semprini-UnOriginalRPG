package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import it.unibo.unori.model.menu.Dialogue;

public class DialogueDeserializer implements JsonDeserializer<Dialogue/*Interface*/> {
    private static final String SENTENCE = "sentence";
    
    @Override
    public Dialogue deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final String sentence = ((JsonObject) json).get(SENTENCE).getAsString();
        
        return new Dialogue(sentence);
    }

}
