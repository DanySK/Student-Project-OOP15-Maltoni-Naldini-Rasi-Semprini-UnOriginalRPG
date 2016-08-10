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
import it.unibo.unori.controller.json.JsonJobParameter;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.maps.SingletonParty;

/**
 * This JUnit test class checks if JsonFileManager class works properly. I
 * tested save*ToPath and load*ToPath methods instead of default-path versions
 * to use the temporary folder, but they act exactly the same.
 */
public class JsonFileManagerTest {
    private static final String SAVE_FILE = "Save.json";
    private static final String STATS_FILE = "Stats.json";
    private static final String DUMP_JOB_FILE = "Dump.json";
    
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
        final File file = folder.newFile(STATS_FILE);
        final GameStatisticsImpl test = new GameStatisticsImpl();
        jsonManager.saveStatsToPath(test, file.getAbsolutePath());
        assertEquals(test, jsonManager.loadStatsFromPath(file.getAbsolutePath()));

        if (file.delete()) {
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
        } else {
            fail("Can't delete temporary " + file.getName() + " JSON test file");
        }
    }
    
    @Test
    public void testSaveAndLoadJob() throws IOException {
        final JsonFileManager jsonManager = new JsonFileManager();
        final File file = folder.newFile(DUMP_JOB_FILE);
        final Jobs jobsTest = Jobs.DUMP;
        final JsonJobParameter parameterTest = new JsonJobParameter(jobsTest.getInitialStats(), jobsTest.getGrowthStats(), jobsTest.getInitialArmor(), jobsTest.getInitialWeapon());
        
        jsonManager.saveJob(parameterTest, DUMP_JOB_FILE);
        final JsonJobParameter loaded = jsonManager.loadJob(DUMP_JOB_FILE);
        assertEquals(parameterTest.getDefaultStats(), loaded.getDefaultStats());
        assertEquals(parameterTest.getDefaultIncrement(), loaded.getDefaultIncrement());
        assertEquals(parameterTest.getDefaultArmor(), loaded.getDefaultArmor());
        assertEquals(parameterTest.getDefaultWeapon(), loaded.getDefaultWeapon());
        
        
    }

}
