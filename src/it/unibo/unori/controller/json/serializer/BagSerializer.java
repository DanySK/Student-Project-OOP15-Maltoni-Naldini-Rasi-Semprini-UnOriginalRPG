package it.unibo.unori.controller.json.serializer;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.model.items.ArmorImpl;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.ItemImpl;
import it.unibo.unori.model.items.PotionImpl;
import it.unibo.unori.model.items.WeaponImpl;

public class BagSerializer implements JsonSerializer<Bag> {
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
        System.out.println(armors);
        final JsonElement weapons = context.serialize(src.getWeapons(), new TypeToken<Map<WeaponImpl, Integer>>() {
        }.getType());
        System.out.println(weapons);
        jObj.add(WEAPONS, weapons);
        final JsonElement potions = context.serialize(src.getPotions(), new TypeToken<Map<PotionImpl, Integer>>() {
        }.getType());
        jObj.add(POTIONS, potions);
        System.out.println(potions);
        final JsonElement miscellaneous = context.serialize(src.getMiscellaneous(),
                        new TypeToken<Map<ItemImpl, Integer>>() {
                        }.getType());
        jObj.add(MISCELLANOUS, miscellaneous);
        System.out.println(miscellaneous);

        return jObj;
    }

}
