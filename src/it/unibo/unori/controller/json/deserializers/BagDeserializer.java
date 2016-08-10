package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.BagImpl;

public class BagDeserializer implements JsonDeserializer<Bag> {
    // private static final String ARMORS = "armors";
    // private static final String WEAPONS = "weapons";
    // private static final String POTIONS = "potions";
    // private static final String MISCELLANOUS = "miscellaneous";

    @Override
    public Bag deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        /*final JsonObject jObj = (JsonObject) json;
        final Bag returnBag = new BagImpl();

        final Map<Armor, Integer> armors = new HashMap<Armor, Integer>(
                context.deserialize(jObj.get(ARMORS), new TypeToken<Map<Armor, Integer>>() {
                }.getType())); // TODO Probably unnecessary new map)
        armors.forEach((a, i) -> {
            for (int count = 0; count < i; count++) {
                returnBag.storeItem(a);
            }
        });

        final Map<Weapon, Integer> weapons = new HashMap<Weapon, Integer>(
                context.deserialize(jObj.get(WEAPONS), new TypeToken<Map<Weapon, Integer>>() {
                }.getType())); // TODO Probably unnecessary new map)
        weapons.forEach((w, i) -> {
            for (int count = 0; count < i; count++) {
                returnBag.storeItem(w);
            }
        });

        final Map<Potion, Integer> potions = new HashMap<Potion, Integer>(
                context.deserialize(jObj.get(POTIONS), new TypeToken<Map<Potion, Integer>>() {
                }.getType())); // TODO Probably unnecessary new map)
        potions.forEach((p, i) -> {
            for (int count = 0; count < i; count++) {
                returnBag.storeItem(p);
            }
        });

        final Map<Item, Integer> miscellaneous = new HashMap<Item, Integer>(
                context.deserialize(jObj.get(MISCELLANOUS), new TypeToken<Map<Item, Integer>>() {
                }.getType())); // TODO Probably unnecessary new map)
        miscellaneous.forEach((m, i) -> {
            for (int count = 0; count < i; count++) {
                returnBag.storeItem(m);
            }
        });

        return returnBag;*/ return context.deserialize(json, BagImpl.class); // TODO I'm not sure this would work, but for now it's OK
    }

}
