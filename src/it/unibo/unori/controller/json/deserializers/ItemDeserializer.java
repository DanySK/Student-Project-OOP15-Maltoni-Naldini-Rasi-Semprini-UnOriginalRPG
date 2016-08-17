package it.unibo.unori.controller.json.deserializers;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.controller.json.JsonFileManager;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.ArmorFactory;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.ItemImpl;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.PotionFactory;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponFactory;

public class ItemDeserializer implements JsonDeserializer<Item> {
    private static final String NAME = "name";
    private static final String DESC = "desc";

    private static final String ITEM_PATH = "res/testItem.json";
    private static final String WEAPON_PATH_1 = "res/testWeapon.json";
    private static final String WEAPON_PATH_2 = "res/testWeaponItem.json";
    private static final String POTION_PATH_1 = "res/testPotion.json";
    private static final String POTION_PATH_2 = "res/testPotionList";
    private static final String POTION_PATH_3 = "res/testPotionItemList";
    private static final String ARMOR_PATH = "res/testArmor.json";

    public static void main(String[] args) throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();

        final Item itemImpl = new ItemImpl("Test item", "Test description for Test Item");
        jsonManager.serializeJSON(itemImpl, ITEM_PATH);

        final ArmorFactory aff = new ArmorFactory();
        jsonManager.serializeJSON(aff.getStdEquip(), ARMOR_PATH);

        final WeaponFactory wwf = new WeaponFactory();
        final List<Item> itemList = new ArrayList<>();
        final List<Item> weaponList = new ArrayList<>();

        itemList.add(wwf.getBalestra());
        itemList.add(wwf.getCannone());
        itemList.add(wwf.getCerbottana());

        weaponList.add(wwf.getBalestra());
        weaponList.add(wwf.getCannone());
        weaponList.add(wwf.getCerbottana());

        jsonManager.serializeJSON(itemList, WEAPON_PATH_1);
        jsonManager.serializeJSON(weaponList, WEAPON_PATH_2);

        final PotionFactory pdf = new PotionFactory();
        final Potion potion = pdf.getAspirinaMagica();
        final List<Item> potionItemList = new ArrayList<>();
        final List<Potion> potionList = new ArrayList<>();

        jsonManager.serializeJSON(potion, POTION_PATH_1);

        potionItemList.add(pdf.getAspirinaMagica());
        potionItemList.add(pdf.getCuraTotale());
        potionItemList.add(pdf.getGigaPozione());

        jsonManager.serializeJSON(potionItemList, POTION_PATH_3);

        potionList.add(pdf.getAspirinaMagica());
        potionList.add(pdf.getCuraTotale());
        potionList.add(pdf.getGigaPozione());

        jsonManager.serializeJSON(potionList, POTION_PATH_2);
    }
    
    @Override
    public Item deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {
        final JsonObject jObj = (JsonObject) json;
        Item deserializedItem = null;
        // System.out.println("class of typeOfT = " + TypeToken.get(typeOfT).getRawType());
        if (jObj.has("piece")) {
            System.out.println("E' un pezzo di armatura");
            // deserializedItem = context.deserialize(json, Armor.class);
        } else if (jObj.has("inflictedStatus")) {
            // System.out.println("E' un'arma");
            deserializedItem = context.deserialize(json, Weapon.class);
        } else if (jObj.has("statusRestorable")) {
            // System.out.println("E' una pozione");
            deserializedItem = context.deserialize(json, Potion.class);
        } else {
            // System.out.println("E' un normale oggetto");
            final String name = ((JsonObject) json).get(NAME).getAsString();
            // System.out.println("NAME = " + name);
            final String desc = ((JsonObject) json).get(DESC).getAsString();
            // System.out.println("DESCRIPTION = " + desc);
            deserializedItem = new ItemImpl(name, desc);
        }

        return deserializedItem;
    }

}
