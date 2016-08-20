package it.unibo.unori.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.gson.JsonIOException;

import it.unibo.unori.controller.GameStatisticsImpl;
import it.unibo.unori.controller.json.JsonFileManager;
import it.unibo.unori.controller.json.JsonFoeParameters;
import it.unibo.unori.controller.json.JsonJobParameter;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.character.factory.FoesFactory;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.ArmorFactory;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.ItemImpl;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.PotionFactory;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponFactory;
import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.GameMapFactory;
import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.SingletonParty;

/**
 * This JUnit test class checks if JsonFileManager class works properly. I tested save*ToPath and load*ToPath methods
 * instead of default-path versions to use the temporary folder, but they act exactly the same.
 */
public class JsonFileManagerTest {
    private final JsonFileManager jsonManager = new JsonFileManager();
    /**
     * Temporary folder created before each test method, and deleted after each.
     */
    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    /**
     * This tests if loadGame and saveGame methods work properly.
     * 
     * @throws IOException
     *             if something wrong happens
     */
    @Test
    public void testSaveAndLoadGame() throws IOException {
        final File file = folder.newFile();
        final Party test = SingletonParty.getParty();
        jsonManager.savePartyToPath(test, file.getAbsolutePath());
        final Party deserialized = jsonManager.loadPartyFromPath(file.getAbsolutePath());
        assertEquals(test.getCurrentFrame(), deserialized.getCurrentFrame());
        assertEquals(test.getCurrentGameMap(), deserialized.getCurrentGameMap());
        assertEquals(test.getCurrentPosition(), deserialized.getCurrentPosition());
        assertEquals(test.getHeroTeam(), deserialized.getHeroTeam());
        assertEquals(test.getPartyBag(), deserialized.getPartyBag());
        deserialized.setFrame("/res/sprites/cook.png");
        jsonManager.savePartyToPath(deserialized, file.getAbsolutePath());
        assertEquals(deserialized.getCurrentFrame(),
                        jsonManager.loadPartyFromPath(file.getAbsolutePath()).getCurrentFrame());
    }

    /**
     * This tests if loadStats and saveStats methods work properly.
     * 
     * @throws IOException
     *             if something wrong happens
     */
    @Test
    public void testSaveAndLoadStats() throws IOException {
        final File file = folder.newFile();
        final GameStatisticsImpl test = new GameStatisticsImpl();
        jsonManager.saveStatsToPath(test, file.getAbsolutePath());
        assertEquals(test, jsonManager.loadStatsFromPath(file.getAbsolutePath()));

        final GameStatisticsImpl gameStats = new GameStatisticsImpl();
        gameStats.increaseArmorsAcquired(10);
        gameStats.increaseBossesKilled(10);
        gameStats.increaseMonstersKilled(10);
        gameStats.increaseMonstersMet(10);
        gameStats.increaseNewGames(10);
        gameStats.increaseTotalExpGained(10);
        gameStats.increaseTotalTimePlayed(10);
        gameStats.increaseWeaponsAcquired(10);

        jsonManager.saveStatsToPath(gameStats, file.getAbsolutePath());
        final GameStatisticsImpl ret = jsonManager.loadStatsFromPath(file.getAbsolutePath());
        assertEquals(gameStats, ret);
    }

    /**
     * This tests if loadJob and saveJob methods work properly.
     * 
     * @throws IOException
     *             if something wrong happens
     */
    @Test
    public void testSaveAndLoadJob() throws IOException {
        final File file = folder.newFile();
        final Jobs jobTest1 = Jobs.DUMP;
        final JsonJobParameter parameterTest = new JsonJobParameter(jobTest1.getInitialStats(),
                        jobTest1.getGrowthStats(), jobTest1.getInitialArmor(), jobTest1.getInitialWeapon());

        jsonManager.saveJob(parameterTest, file.getAbsolutePath());
        final JsonJobParameter loaded = jsonManager.loadJob(file.getAbsolutePath());
        assertEquals(parameterTest.getDefaultStats(), loaded.getDefaultStats());
        assertEquals(parameterTest.getDefaultIncrement(), loaded.getDefaultIncrement());
        assertEquals(parameterTest.getDefaultArmor(), loaded.getDefaultArmor());
        assertEquals(parameterTest.getDefaultWeapon(), loaded.getDefaultWeapon());
    }

    /**
     * This tests if loadFoe and saveFoe methods work properly.
     * 
     * @throws IOException
     *             if something wrong happens
     */
    @Test
    public void testSaveAndLoadFoe() throws IOException {
        final File file = folder.newFile();
        final JsonFoeParameters parameterTest = new JsonFoeParameters(FoesFactory.getBasicStats(), Status.NONE,
                        FoesFactory.getBasicWeap(), FoesFactory.getBasicMag());

        jsonManager.saveFoe(parameterTest, file.getAbsolutePath());
        final JsonFoeParameters loaded = jsonManager.loadFoe(file.getAbsolutePath());
        // assertEquals(parameterTest.getImmunity(), loaded.getImmunity());
        // TODO assertEquals(parameterTest.getMagics(), loaded.getMagics()); // TODO check
        assertEquals(parameterTest.getStats(), loaded.getStats());
        assertEquals(parameterTest.getWeapon(), loaded.getWeapon());
    }

    /**
     * This tests if loadMap and saveMap methods work properly.
     * 
     * @throws IOException
     *             if something wrong happens
     */
    @Test
    public void testSaveAndLoadMap() throws IOException {
        final File file = folder.newFile();
        final GameMapFactory factory = new GameMapFactory();

        final GameMap map = factory.create4NPCRoomMap();
        jsonManager.saveMap(map, file.getAbsolutePath());
        final GameMap deserialized = jsonManager.loadMap(file.getAbsolutePath());
        assertEquals(map, deserialized);
    }

    /**
     * This method tests if saveItem() and loadItem() methods work correctly. It's tested with ItemImpl, WeaponImpl,
     * ArmorImpl and PotionImpl.
     * 
     * @throws IOException
     *             if something wrong happens
     */
    @Test
    public void testSaveAndLoadItem() throws IOException {
        final PotionFactory pFactory = new PotionFactory();
        final WeaponFactory wFactory = new WeaponFactory();
        final ArmorFactory aFactory = new ArmorFactory();

        // Items
        final Item itemImpl = new ItemImpl("Item", "Description");
        final Item itemPotion = pFactory.getAspirinaMagica();
        final Potion potion = pFactory.getCuraTotale();
        final Item itemWeapon = wFactory.getBalestra();
        final Weapon weapon = wFactory.getCannone();
        final Item itemArmor = aFactory.getStdEquip().get(ArmorPieces.ARMOR);
        final Armor armor = aFactory.getBronzeEquip().get(ArmorPieces.SHIELD);

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
        jsonManager.saveItem(potion, file.getAbsolutePath());
        deserialized = jsonManager.loadItem(file.getAbsolutePath());
        assertEquals(potion, deserialized);

        // WeaponImpl
        jsonManager.saveItem(itemWeapon, file.getAbsolutePath());
        deserialized = jsonManager.loadItem(file.getAbsolutePath());
        assertEquals(itemWeapon, deserialized);
        jsonManager.saveItem(weapon, file.getAbsolutePath());
        deserialized = jsonManager.loadItem(file.getAbsolutePath());
        assertEquals(weapon, deserialized);

        // ArmorImpl
        jsonManager.saveItem(itemArmor, file.getAbsolutePath());
        deserialized = jsonManager.loadItem(file.getAbsolutePath());
        assertEquals(itemArmor, deserialized);
        jsonManager.saveItem(armor, file.getAbsolutePath());
        deserialized = jsonManager.loadItem(file.getAbsolutePath());
        assertEquals(armor, deserialized);
    }

    /**
     * This tests if loadGameFromPath method throws JsonIOException if the file does not exist. I could've used every
     * other load* method to test this because they call the same method.
     * 
     * @throws FileNotFoundException
     *             if the file does is not a JSON-serialized object
     * @throws IOException
     *             if something else wrong happens
     */
    @Test(expected = FileNotFoundException.class)
    public void testFileNotFoundException() throws IOException {
        final File file = folder.newFile();
        final String path = file.getAbsolutePath();
        if (file.delete()) {
            jsonManager.loadPartyFromPath(path);
        }
        fail("It should throw exception");
    }

    /**
     * This tests if loadGameFromPath method throws JsonIOException if the file is not valid. I could've used every
     * other load* method to test this because they call the same method.
     * 
     * @throws JsonIOException
     *             if the file does is not a JSON-serialized object
     * @throws IOException
     *             if something else wrong happens
     */
    @Test(expected = JsonIOException.class)
    public void testSaveFileNotValid() throws IOException {
        final File file = folder.newFile();
        jsonManager.loadPartyFromPath(file.getAbsolutePath());
    }
}
