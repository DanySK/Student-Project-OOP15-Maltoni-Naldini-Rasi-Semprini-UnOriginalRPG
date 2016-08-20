package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.ItemImpl;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.Weapon;

public class ItemSerializer implements JsonSerializer<Item>, JsonDeserializer<Item> {
    private static final String NAME = "name";
    private static final String DESC = "desc";

    @Override
    public JsonElement serialize(final Item src, final Type typeOfSrc, final JsonSerializationContext context) {
        if (Weapon.class.isInstance(src)) {
            return context.serialize(src, Weapon.class);
        } else if (Armor.class.isInstance(src)) {
            return context.serialize(src, Armor.class);
        } else if (Potion.class.isInstance(src)) {
            return context.serialize(src, Potion.class);
        } else {
            final JsonObject jObj = new JsonObject();
            final String name = src.getName();
            jObj.addProperty(NAME, name);
            final String desc = src.getDescription();
            jObj.addProperty(DESC, desc);
            return jObj;
        }
    }

    @Override
    public Item deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
                    throws JsonParseException {
        final JsonObject jObj = (JsonObject) json;
        Item deserializedItem = null;
        if (jObj.has("piece")) {
            deserializedItem = context.deserialize(json, Armor.class);
        } else if (jObj.has("inflictedStatus")) {
            deserializedItem = context.deserialize(json, Weapon.class);
        } else if (jObj.has("statusRestorable")) {
            deserializedItem = context.deserialize(json, Potion.class);
        } else {
            final String name = ((JsonObject) json).get(NAME).getAsString();
            final String desc = ((JsonObject) json).get(DESC).getAsString();
            deserializedItem = new ItemImpl(name, desc);
        }

        return deserializedItem;
    }
}
