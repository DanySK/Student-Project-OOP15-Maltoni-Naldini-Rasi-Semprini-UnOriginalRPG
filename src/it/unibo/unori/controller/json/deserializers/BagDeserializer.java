package it.unibo.unori.controller.json.deserializers;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.IntStream;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.BagImpl;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.Weapon;

public class BagDeserializer implements JsonDeserializer<Bag> {
    private static final String ARMORS = "armors";
    private static final String WEAPONS = "weapons";
    private static final String POTIONS = "potions";
    private static final String MISCELLANOUS = "miscellaneous";

    @Override
    public Bag deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
                    throws JsonParseException {
        final JsonObject jObj = (JsonObject) json;
        final Bag returnBag = new BagImpl();

        final Map<Armor, Integer> armors = context.deserialize(jObj.get(ARMORS), new TypeToken<Map<Armor, Integer>>() {
        }.getType());
        armors.forEach((a, i) -> IntStream.range(0, i).forEach(in -> returnBag.storeItem(a))); // TODO check

        final Map<Weapon, Integer> weapons = context.deserialize(jObj.get(WEAPONS),
                        new TypeToken<Map<Weapon, Integer>>() {
                        }.getType());
        weapons.forEach((w, i) -> IntStream.range(0, i).forEach(in -> returnBag.storeItem(w))); // TODO check

        final Map<Potion, Integer> potions = context.deserialize(jObj.get(POTIONS),
                        new TypeToken<Map<Potion, Integer>>() {
                        }.getType());
        potions.forEach((p, i) -> IntStream.range(0, i).forEach(in -> returnBag.storeItem(p))); // TODO check

        final Map<Item, Integer> miscellaneous = context.deserialize(jObj.get(MISCELLANOUS),
                        new TypeToken<Map<Item, Integer>>() {
                        }.getType());
        miscellaneous.forEach((m, i) -> IntStream.range(0, i).forEach(in -> returnBag.storeItem(m))); // TODO check

        return returnBag;
    }

}
