package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.ItemImpl;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.Weapon;

public class ItemDeserializer implements JsonDeserializer<Item> {
    private static final String NAME = "name";
    private static final String DESC = "desc";

    @Override
    public Item deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        Item deserializedItem;
        if (typeOfT.getClass().isAssignableFrom(Armor.class)) {
            deserializedItem = context.deserialize(json, Armor.class);
        } else if (typeOfT.getClass().isAssignableFrom(Weapon.class)) {
            deserializedItem = context.deserialize(json, Weapon.class);
        } else if (typeOfT.getClass().isAssignableFrom(Potion.class)) {
            deserializedItem = context.deserialize(json, Potion.class);
        } else {
            final String name = ((JsonObject) json).get(NAME).getAsString();
            final String desc = ((JsonObject) json).get(DESC).getAsString();
            deserializedItem = new ItemImpl(name, desc);
        }

        return deserializedItem;
    }

}
