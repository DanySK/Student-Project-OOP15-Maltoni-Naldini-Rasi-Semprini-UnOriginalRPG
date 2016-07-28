package it.unibo.unori.controller.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import it.unibo.unori.controller.GameStatistics;
import it.unibo.unori.model.maps.SingletonParty;

/**
 * This JUnit test class checks if utility class Save works properly. I tested save*ToPath and load*ToPath methods
 * instead of default-path versions to use the temporary folder, but they act exactly the same.
 */
public class SaveTest {
    /**
     * This rule instantiates a temporary folder where to check files.
     */
    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    /**
     * This tests if loadSave method throws JsonIOException if the file does not exist.
     * 
     * @throws FileNotFoundException
     *             if the file does is not a JSON-serialized object
     * @throws IOException
     *             if something else wrong happens
     */
    @Test(expected = FileNotFoundException.class)
    public void testSaveFileNotFound() throws IOException {
        Save.loadGameFromPath(folder.getRoot().getAbsolutePath() + "/Save.json");
    }

    /**
     * This tests if loadFromUtilityFile method throws IOException if the file does not exist.
     * 
     * @throws FileNotFoundException
     *             if the file does is not a JSON-serialized object
     * @throws IOException
     *             if something else wrong happens
     */
    @Test(expected = FileNotFoundException.class)
    public void testStatsFileNotFound() throws IOException {
        Save.loadStatsFromPath(folder.getRoot().getAbsolutePath() + "/Stats.json");
    }

    /**
     * This tests if loadSave method throws JsonIOException if the file is not valid.
     * 
     * @throws JsonIOException
     *             if the file does is not a JSON-serialized object
     * @throws IOException
     *             if something else wrong happens
     */
    @Test(expected = JsonIOException.class)
    public void testSaveFileNotValid() throws IOException {
        final File f = folder.newFile("Save.json");
        Save.loadGameFromPath(f.getAbsolutePath());
    }

    /**
     * This tests if loadStats method throws JsonIOException if the file is not valid.
     * 
     * @throws JsonIOException
     *             if the file does is not a JSON-serialized object
     * @throws IOException
     *             if something else wrong happens
     */
    @Test(expected = JsonIOException.class)
    public void testStatsFileNotValid() throws IOException {
        final File f = folder.newFile("Stats.json");
        Save.loadStatsFromPath(f.getAbsolutePath());
    }

    /**
     * This tests if loadGame and saveGame methods work properly.
     * 
     * @throws IOException
     *             if something wrong happens
     */
    @Test
    public void testSaveAndLoadGame() throws IOException {
        final File f = folder.newFile("Save.json");
        Save.saveGameToPath(SingletonParty.getParty(), f.getAbsolutePath());
        assertEquals(SingletonParty.getParty(), Save.loadGameFromPath(f.getAbsolutePath()));
    }

    /**
     * This tests if loadStats and saveStats methods work properly.
     * 
     * @throws IOException
     *             if something wrong happens
     */
    @Test
    public void testSaveAndLoadStats() throws IOException {
        final File f = folder.newFile("Stats.json");

        Save.saveStatsToPath(new GameStatistics(), f.getAbsolutePath());
        assertEquals(new GameStatistics(), Save.loadStatsFromPath(f.getAbsolutePath()));

        if (f.delete()) {
            final GameStatistics gs = new GameStatistics();
            gs.increaseArmorsAcquired(10);
            gs.increaseBossesKilled(10);
            gs.increaseMonstersKilled(10);
            gs.increaseMonstersMet(10);
            gs.increaseNewGame(10);
            gs.increaseTotalExpGained(10);
            gs.increaseTotalTimePlayed(10);
            gs.increaseWeaponsAcquired(10);
            // gs.stopCountingTime();

            Save.saveStatsToPath(gs, f.getAbsolutePath());
            final GameStatistics ret = Save.loadStatsFromPath(f.getAbsolutePath());
            System.out.println(gs.toString());
            System.out.println();
            System.out.println(ret.toString());
            assertEquals(gs, ret);
        } else {
            fail("Can't delete temporary " + f.getName() + " JSON test file");
        }
    }

    @Test
    public void testSerializeJSON() throws IOException {
        final File f = folder.newFile("Try.json");
        final String s = "Try";
        Save.serializeJSON(s, f.getAbsolutePath());
        assertEquals(s, Save.deserializeJSON(String.class, f.getAbsolutePath()));

        if (f.delete()) {
            final Integer i = 10;
            Save.serializeJSON(i, f.getAbsolutePath());
            assertEquals(i, Save.deserializeJSON(Integer.class, f.getAbsolutePath()));

            if (f.delete()) {
                Map<String, Integer> m = new HashMap<>();
                m.put(s, i);
                Save.serializeJSON(m, f.getAbsolutePath());
                System.out.println(m.getClass());
                
                assertEquals(m, Save.deserializeJSON(new TypeToken<Map<String, Integer>>() { }.getClass(), f.getAbsolutePath()));
            } else {
                fail("Can't delete temporary " + f.getName() + " JSON test file");
            }
        } else {
            fail("Can't delete temporary " + f.getName() + " JSON test file");
        }
    }

    @Test
    public void testDeserializeJSON() {
        fail("Not yet implemented");
    }

}
