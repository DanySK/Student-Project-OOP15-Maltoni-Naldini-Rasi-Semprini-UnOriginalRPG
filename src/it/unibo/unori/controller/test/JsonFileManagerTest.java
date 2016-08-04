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
import it.unibo.unori.model.maps.SingletonParty;

/**
 * This JUnit test class checks if JsonFileManager class works properly. I
 * tested save*ToPath and load*ToPath methods instead of default-path versions
 * to use the temporary folder, but they act exactly the same.
 */
public class JsonFileManagerTest {
    private static final String SAVE_FILE = "Save.json";
    private static final String STATS_FILE = "Stats.json";
    
    /**
     * This rule instantiates a temporary folder where to check files.
     */
    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    /**
     * This tests if loadSave method throws JsonIOException if the file does not
     * exist.
     * 
     * @throws FileNotFoundException
     *             if the file does is not a JSON-serialized object
     * @throws IOException
     *             if something else wrong happens
     */
    @Test(expected = FileNotFoundException.class)
    public void testSaveFileNotFound() throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();
        jsonManager.loadGameFromPath(folder.getRoot().getAbsolutePath() + "/" + SAVE_FILE);
    }

    /**
     * This tests if loadStats method throws IOException if the file does not
     * exist.
     * 
     * @throws FileNotFoundException
     *             if the file does is not a JSON-serialized object
     * @throws IOException
     *             if something else wrong happens
     */
    @Test(expected = FileNotFoundException.class)
    public void testStatsFileNotFound() throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();
        jsonManager.loadStatsFromPath(folder.getRoot().getAbsolutePath() + "/" + STATS_FILE);
    }

    /**
     * This tests if loadSave method throws JsonIOException if the file is not
     * valid.
     * 
     * @throws JsonIOException
     *             if the file does is not a JSON-serialized object
     * @throws IOException
     *             if something else wrong happens
     */
    @Test(expected = JsonIOException.class)
    public void testSaveFileNotValid() throws IOException {
        final File f = folder.newFile(SAVE_FILE);
        final JsonFileManager jsonManager = new JsonFileManager();
        jsonManager.loadGameFromPath(f.getAbsolutePath());
    }

    /**
     * This tests if loadStats method throws JsonIOException if the file is not
     * valid.
     * 
     * @throws JsonIOException
     *             if the file does is not a JSON-serialized object
     * @throws IOException
     *             if something else wrong happens
     */
    @Test(expected = JsonIOException.class)
    public void testStatsFileNotValid() throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();
        final File f = folder.newFile(STATS_FILE);
        jsonManager.loadStatsFromPath(f.getAbsolutePath());
    }

    /**
     * This tests if loadGame and saveGame methods work properly.
     * 
     * @throws IOException
     *             if something wrong happens
     */
    @Test
    public void testSaveAndLoadGame() throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();
        final File f = folder.newFile(SAVE_FILE);
        jsonManager.saveGameToPath(SingletonParty.getParty(), f.getAbsolutePath());
        assertEquals(SingletonParty.getParty(), jsonManager.loadGameFromPath(f.getAbsolutePath()));
    }

    /**
     * This tests if loadStats and saveStats methods work properly.
     * 
     * @throws IOException
     *             if something wrong happens
     */
    @Test
    public void testSaveAndLoadStats() throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();
        final File f = folder.newFile(STATS_FILE);
        final GameStatisticsImpl test = new GameStatisticsImpl();
        jsonManager.saveStatsToPath(test, f.getAbsolutePath());
        assertEquals(test, jsonManager.loadStatsFromPath(f.getAbsolutePath()));

        if (f.delete()) {
            final GameStatisticsImpl gs = new GameStatisticsImpl();
            gs.increaseArmorsAcquired(10);
            gs.increaseBossesKilled(10);
            gs.increaseMonstersKilled(10);
            gs.increaseMonstersMet(10);
            gs.increaseNewGames(10);
            gs.increaseTotalExpGained(10);
            gs.increaseTotalTimePlayed(10);
            gs.increaseWeaponsAcquired(10);
            // gs.stopCountingTime();

            jsonManager.saveStatsToPath(gs, f.getAbsolutePath());
            final GameStatisticsImpl ret = jsonManager.loadStatsFromPath(f.getAbsolutePath());
            // System.out.println(gs.toString());
            // System.out.println();
            // System.out.println(ret.toString());
            assertEquals(gs, ret);
        } else {
            fail("Can't delete temporary " + f.getName() + " JSON test file");
        }
    }

}
