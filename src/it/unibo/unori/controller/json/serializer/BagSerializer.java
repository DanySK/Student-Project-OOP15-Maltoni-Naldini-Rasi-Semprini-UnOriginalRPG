package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.stream.IntStream;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.ArmorImpl;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.BagImpl;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.ItemImpl;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.PotionImpl;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponImpl;

public class BagSerializer implements JsonSerializer<Bag>, JsonDeserializer<Bag> {
    private static final String ARMORS = "armors";
    private static final String WEAPONS = "weapons";
    private static final String POTIONS = "potions";
    private static final String MISCELLANOUS = "miscellaneous";

    @Override
    public JsonElement serialize(final Bag src, final Type typeOfSrc, final JsonSerializationContext context) {
        final JsonObject jObj = new JsonObject();
        final JsonElement armors = context.serialize(src.getArmors(), new TypeToken<Map<ArmorImpl, Integer>>() {
        }.getType());
        jObj.add(ARMORS, armors);
        final JsonElement weapons = context.serialize(src.getWeapons(), new TypeToken<Map<WeaponImpl, Integer>>() {
        }.getType());
        jObj.add(WEAPONS, weapons);
        final JsonElement potions = context.serialize(src.getPotions(), new TypeToken<Map<PotionImpl, Integer>>() {
        }.getType());
        jObj.add(POTIONS, potions);
        final JsonElement miscellaneous = context.serialize(src.getMiscellaneous(),
                        new TypeToken<Map<ItemImpl, Integer>>() {
                        }.getType());
        jObj.add(MISCELLANOUS, miscellaneous);

        return jObj;
    }

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
