package it.unibo.unori.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import it.unibo.unori.controller.json.JsonFileManager;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.ArmorImpl;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.ItemImpl;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.PotionImpl;
import it.unibo.unori.model.items.WeaponImpl;

public class NewJsonFileMangerTest {
    private static final String TEST_FILE = "Test.json";
    private static final String DELETE_FAILED = "Fail on delete temporary file";
    private static final int MAGIC_NUMBER = 512;

    /**
     * This rule instantiates a temporary folder where to check files.
     */
    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testItem() throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();

        // Items
        final Item itemImpl = new ItemImpl("Item", "Desc1");
        final Item itemPotion = new PotionImpl(10, Statistics.TOTALHP, "ItemPotion", "A potion item", false);
        final Item itemWeapon = new WeaponImpl("ItemWeapon", "A weapon item", generateStdWeaponStats(), Status.NONE);
        final Item itemShield = new ArmorImpl("ItemShield", ArmorPieces.SHIELD, "A shield item",
                generateStdArmorStats(), Status.NONE);
        final Item itemHelmet = new ArmorImpl("ItemHelmet", ArmorPieces.HELMET, "An helmet item",
                generateStdArmorStats(), Status.NONE);
        final Item itemArmor = new ArmorImpl("ItemArmor", ArmorPieces.ARMOR, "An armor item", generateStdArmorStats(),
                Status.NONE);
        final Item itemGloves = new ArmorImpl("ItemGloves", ArmorPieces.GLOVES, "A gloves item",
                generateStdArmorStats(), Status.NONE);
        final Item itemTrousers = new ArmorImpl("ItemTrousers", ArmorPieces.TROUSERS, "A trousers item",
                generateStdArmorStats(), Status.NONE);

        File test = folder.newFile(TEST_FILE);
        jsonManager.serializeJSON(itemImpl, TEST_FILE);
        assertEquals(itemImpl, jsonManager.deserializeJSON(Item.class, TEST_FILE));

        if (test.delete()) {
            if (test.createNewFile()) {
                jsonManager.serializeJSON(itemPotion, TEST_FILE);
                // assertEquals(itemPotion, jsonManager.deserializeJSON(Item.class, TEST_FILE));
                System.out.println("NAME: " + itemPotion.getName() + " VS " + jsonManager.deserializeJSON(Item.class, TEST_FILE).getName());
                System.out.println("DESCRIPTION: " + itemPotion.getDescription() + " VS " + jsonManager.deserializeJSON(Item.class, TEST_FILE).getDescription());
                System.out.println("POINTS: " + ((Potion) itemPotion).getRestore() + " VS " + ((Potion) jsonManager.deserializeJSON(Item.class, TEST_FILE)).getRestore());
                System.out.println("STAT TO RESTORE: " + ((Potion) itemPotion).getStatisticToRestore() + " VS " + ((Potion) jsonManager.deserializeJSON(Item.class, TEST_FILE)).getStatisticToRestore());
                System.out.println("STATUS RESTORABLE: " + ((Potion) itemPotion).isStatusChanging() + " VS " + ((Potion) jsonManager.deserializeJSON(Item.class, TEST_FILE)).isStatusChanging());
                // System.out.println(readStream(new FileInputStream(test)));
            } else {
                fail("crazy fail");
            }

        } else {
            fail(DELETE_FAILED);
        }
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

    private static String readStream(final InputStream is) {
        StringBuilder sb = new StringBuilder(MAGIC_NUMBER);
        try {
            Reader r = new InputStreamReader(is, "UTF-8");
            int c = 0;
            while ((c = r.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

}
