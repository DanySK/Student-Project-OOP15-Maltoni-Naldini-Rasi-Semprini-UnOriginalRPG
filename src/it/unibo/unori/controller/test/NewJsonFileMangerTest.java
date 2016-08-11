package it.unibo.unori.controller.test;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import it.unibo.unori.controller.json.JsonFileManager;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.ArmorFactory;
import it.unibo.unori.model.items.ArmorImpl;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.ItemImpl;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.PotionFactory;
import it.unibo.unori.model.items.PotionImpl;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponFactory;
import it.unibo.unori.model.items.WeaponImpl;

public class NewJsonFileMangerTest {
    private static final String TEST_FILE = "TestC.json";

    /**
     * This rule instantiates a temporary folder where to check files.
     */
    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testItem() throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();

        // Items
        final Item itemImpl = new ItemImpl("Item1", "Desc1");
        final Item itemImpl2 = new ItemImpl("Item2", "Desc2");
        final Item itemPotion = new PotionImpl(10, Statistics.TOTALHP, "ItemPotion", "A potion item", false);
        final Item itemWeapon = new WeaponImpl("ItemWeapon", "A weapon item", generateStdWeaponStats(), Status.NONE);
        final Item itemArmor = new ArmorImpl("ItemShield", ArmorPieces.SHIELD, "A shield item", generateStdArmorStats(),
                Status.NONE);

        folder.newFile(TEST_FILE);
        Item deserialized;
        List<Item> itemImplList;
        List<Item> mixedItemList;
        List<Item> deserializedList;

        // ItemImpl
        jsonManager.serializeJSON(itemImpl, TEST_FILE);
        deserialized = jsonManager.deserializeJSON(Item.class, TEST_FILE);
        assertEquals(itemImpl, deserialized);

        // PotionImpl
        jsonManager.serializeJSON(itemPotion, TEST_FILE);
        deserialized = jsonManager.deserializeJSON(Item.class, TEST_FILE);
        assertEquals(itemPotion, deserialized);

        // WeaponImpl
        jsonManager.serializeJSON(itemWeapon, TEST_FILE);
        deserialized = jsonManager.deserializeJSON(Item.class, TEST_FILE);
        assertEquals(itemWeapon, deserialized);

        // ArmorImpl
        jsonManager.serializeJSON(itemArmor, TEST_FILE);
        deserialized = jsonManager.deserializeJSON(Item.class, TEST_FILE);
        assertEquals(itemArmor, deserialized);

        // ItemImplList
        itemImplList = new ArrayList<>();
        itemImplList.add(itemImpl);
        itemImplList.add(itemImpl2);

        jsonManager.serializeJSON(itemImplList, TEST_FILE);
        deserializedList = jsonManager.loadList(Item.class, TEST_FILE);
        assertEquals(itemImplList.get(0), deserializedList.get(0));
        assertEquals(itemImplList.get(1), deserializedList.get(1));

        // MixedItemList
        mixedItemList = new ArrayList<>();
        mixedItemList.add(itemImpl);
        mixedItemList.add(itemImpl2);
        mixedItemList.add(itemPotion);
        mixedItemList.add(itemWeapon);
        mixedItemList.add(itemArmor);

        jsonManager.serializeJSON(mixedItemList, TEST_FILE);
        deserializedList = jsonManager.loadList(Item.class, TEST_FILE);
        assertEquals(mixedItemList, deserializedList);

    }

    @Test
    public void testWeapon() throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();

        // Items
        final Weapon itemWeapon = new WeaponImpl("ItemWeapon", "A weapon item", generateStdWeaponStats(), Status.NONE);
        List<Weapon> weaponList;

        folder.newFile(TEST_FILE);
        Weapon deserialized;
        List<Weapon> deserializedList;

        // WeaponImpl
        jsonManager.serializeJSON(itemWeapon, TEST_FILE);
        deserialized = jsonManager.deserializeJSON(Weapon.class, TEST_FILE);
        assertEquals(itemWeapon, deserialized);

        // WeaponList
        weaponList = new ArrayList<>();
        weaponList.add(WeaponFactory.getBalestra());
        weaponList.add(WeaponFactory.getCannone());
        weaponList.add(WeaponFactory.getCerbottana());
        weaponList.add(WeaponFactory.getChiodo());
        weaponList.add(WeaponFactory.getClava());
        weaponList.add(WeaponFactory.getColtre());
        weaponList.add(WeaponFactory.getFionda());
        weaponList.add(WeaponFactory.getLancia());
        weaponList.add(WeaponFactory.getLanciafiamme());
        weaponList.add(WeaponFactory.getMaledizione());
        weaponList.add(WeaponFactory.getMazza());
        weaponList.add(WeaponFactory.getOcarina());
        weaponList.add(WeaponFactory.getPugnale());
        weaponList.add(WeaponFactory.getSpadaMistica());

        jsonManager.serializeJSON(weaponList, TEST_FILE);
        deserializedList = jsonManager.loadList(Weapon.class, TEST_FILE);
        assertEquals(weaponList, deserializedList);
    }

    @Test
    public void testPotion() throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();
        final PotionFactory factory = new PotionFactory();

        // Items
        final Potion itemPotion = new PotionImpl(10, Statistics.TOTALHP, "ItemPotion", "A potion item", false);
        final List<Potion> potionList;

        folder.newFile(TEST_FILE);
        Potion deserialized;

        // PotionImpl
        jsonManager.serializeJSON(itemPotion, TEST_FILE);
        deserialized = jsonManager.deserializeJSON(Potion.class, TEST_FILE);
        assertEquals(itemPotion, deserialized);

        // PotionList
        potionList = new ArrayList<>();
        potionList.add(factory.getAspirinaMagica());
        potionList.add(factory.getCuraTotale());
        potionList.add(factory.getGigaPozione());
        potionList.add(factory.getGranPozione());
        potionList.add(factory.getIntruglio());
        potionList.add(factory.getPasticcheMagiche());
        potionList.add(factory.getPozione());
        potionList.add(factory.getPozioneDio());
        potionList.add(factory.getPozioneVita());
        potionList.add(factory.getRimedioDellaNonna());
        potionList.add(factory.getSaluteInProvetta());
        potionList.add(factory.getStdPotion());
        potionList.add(factory.getTrapiantoMana());

        jsonManager.serializeJSON(potionList, TEST_FILE);
        
        assertEquals(potionList, jsonManager.loadList(Potion.class, TEST_FILE));

    }

    @Test
    public void testArmor() throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();
        final ArmorFactory factory = new ArmorFactory();

        // Items
        final Armor itemShield = new ArmorImpl("ItemShield", ArmorPieces.SHIELD, "A shield item",
                generateStdArmorStats(), Status.NONE);
        final Armor itemHelmet = new ArmorImpl("ItemHelmet", ArmorPieces.HELMET, "An helmet item",
                generateStdArmorStats(), Status.NONE);
        final Armor itemArmor = new ArmorImpl("ItemArmor", ArmorPieces.ARMOR, "An armor item", generateStdArmorStats(),
                Status.NONE);
        final Armor itemGloves = new ArmorImpl("ItemGloves", ArmorPieces.GLOVES, "A gloves item",
                generateStdArmorStats(), Status.NONE);
        final Armor itemTrousers = new ArmorImpl("ItemTrousers", ArmorPieces.TROUSERS, "A trousers item",
                generateStdArmorStats(), Status.NONE);
        final List<Armor> armorList;

        folder.newFile(TEST_FILE);
        Armor deserialized;

        // ArmorImpl
        jsonManager.serializeJSON(itemShield, TEST_FILE);
        deserialized = jsonManager.deserializeJSON(Armor.class, TEST_FILE);
        assertEquals(itemShield, deserialized);

        // ArmorList
        armorList = new ArrayList<>();
        factory.getStdEquip().forEach((ap, a) -> armorList.add(a));
        armorList.add(itemShield);
        armorList.add(itemHelmet);
        armorList.add(itemArmor);
        armorList.add(itemGloves);
        armorList.add(itemTrousers);

        jsonManager.serializeJSON(armorList, TEST_FILE);
        assertEquals(armorList, jsonManager.loadList(Armor.class, TEST_FILE));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testErrorOnCollection() throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();
        jsonManager.serializeJSON(new ArrayList<Double>(), TEST_FILE);
        jsonManager.deserializeJSON(List.class, TEST_FILE);
    }

    private Map<Statistics, Integer> generateStdWeaponStats() {
        final Map<Statistics, Integer> stats = new HashMap<>();
        stats.put(Statistics.PHYSICATK, 1);
        stats.put(Statistics.FIREATK, 0);
        stats.put(Statistics.ICEATK, 0);
        stats.put(Statistics.THUNDERATK, 0);
        return stats;
    }

    private Map<Statistics, Integer> generateStdArmorStats() {
        final Map<Statistics, Integer> stats = new HashMap<>();
        stats.put(Statistics.PHYSICDEF, 1);
        stats.put(Statistics.FIREDEF, 0);
        stats.put(Statistics.ICEDEF, 0);
        stats.put(Statistics.THUNDERDEF, 0);
        return stats;
    }

}
