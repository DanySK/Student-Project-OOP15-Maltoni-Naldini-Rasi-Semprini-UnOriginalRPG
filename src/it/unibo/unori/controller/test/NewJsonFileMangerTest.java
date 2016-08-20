package it.unibo.unori.controller.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import it.unibo.unori.controller.json.JsonFileManager;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.ArmorImpl;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.ItemImpl;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.PotionImpl;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponImpl;

public class NewJsonFileMangerTest {
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
        final Item itemPotion = new PotionImpl(10, Statistics.TOTALHP, "ItemPotion", "A potion item", false);
        final Item itemWeapon = new WeaponImpl("ItemWeapon", "A weapon item", generateStdWeaponStats(), Status.NONE);
        final Item itemArmor = new ArmorImpl("ItemShield", ArmorPieces.SHIELD, "A shield item", generateStdArmorStats(),
                        Status.NONE);

        final File file = folder.newFile();
        Item deserialized;

        // ItemImpl
        jsonManager.saveItem(itemImpl, file.getAbsolutePath());
        deserialized = jsonManager.loadItem(file.getAbsolutePath());
        assertEquals(itemImpl, deserialized);

        // PotionImpl
        jsonManager.saveItem(itemPotion, file.getAbsolutePath());
        deserialized = jsonManager.loadItem(file.getAbsolutePath());
        assertEquals(itemPotion, deserialized);

        // WeaponImpl
        jsonManager.saveItem(itemWeapon, file.getAbsolutePath());
        deserialized = jsonManager.loadItem(file.getAbsolutePath());
        assertEquals(itemWeapon, deserialized);

        // ArmorImpl
        jsonManager.saveItem(itemArmor, file.getAbsolutePath());
        deserialized = jsonManager.loadItem(file.getAbsolutePath());
        assertEquals(itemArmor, deserialized);
    }

    @Test
    public void testWeapon() throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();

        // Items
        final Weapon itemWeapon = new WeaponImpl("ItemWeapon", "A weapon item", generateStdWeaponStats(), Status.NONE);

        final File file = folder.newFile();
        Weapon deserialized;

        // WeaponImpl
        jsonManager.saveItem(itemWeapon, file.getAbsolutePath());
        deserialized = (Weapon) jsonManager.loadItem(file.getAbsolutePath());
        assertEquals(itemWeapon, deserialized);
    }

    @Test
    public void testPotion() throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();

        // Items
        final Potion itemPotion = new PotionImpl(10, Statistics.TOTALHP, "ItemPotion", "A potion item", false);

        final File file = folder.newFile();
        Potion deserialized;

        // PotionImpl
        jsonManager.saveItem(itemPotion, file.getAbsolutePath());
        deserialized = (Potion) jsonManager.loadItem(file.getAbsolutePath());
        assertEquals(itemPotion, deserialized);
    }

    @Test
    public void testArmor() throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();

        // Items
        final Armor itemShield = new ArmorImpl("ItemShield", ArmorPieces.SHIELD, "A shield item",
                        generateStdArmorStats(), Status.NONE);

        final File file = folder.newFile();
        Armor deserialized;

        // ArmorImpl
        jsonManager.saveItem(itemShield, file.getAbsolutePath());
        deserialized = (Armor) jsonManager.loadItem(file.getAbsolutePath());
        assertEquals(itemShield, deserialized);
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
