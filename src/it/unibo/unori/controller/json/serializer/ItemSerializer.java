package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.Weapon;

public class ItemSerializer implements JsonSerializer<Item> {
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
}
