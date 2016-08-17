package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.unibo.unori.model.character.Hero;

public class HeroSerializer implements JsonSerializer<Hero> {

    @Override
    public JsonElement serialize(Hero src, Type typeOfSrc, JsonSerializationContext context) {
        // TODO Auto-generated method stub
        return null;
    }

}
