package it.unibo.unori.controller.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.gson.JsonIOException;

import it.unibo.unori.controller.GameStatisticsImpl;
import it.unibo.unori.model.maps.SingletonParty;

/**
 * This JUnit test class checks if utility class Save works properly. I tested
 * save*ToPath and load*ToPath methods instead of default-path versions to use
 * the temporary folder, but they act exactly the same.
 */
public class SaveTest {
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
        Save.loadGameFromPath(folder.getRoot().getAbsolutePath() + "/Save.json");
    }

    /**
     * This tests if loadFromUtilityFile method throws IOException if the file
     * does not exist.
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
        final File f = folder.newFile("Save.json");
        Save.loadGameFromPath(f.getAbsolutePath());
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
        final GameStatisticsImpl test = new GameStatisticsImpl();
        Save.saveStatsToPath(test, f.getAbsolutePath());
        assertEquals(test, Save.loadStatsFromPath(f.getAbsolutePath()));

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

            Save.saveStatsToPath(gs, f.getAbsolutePath());
            final GameStatisticsImpl ret = Save.loadStatsFromPath(f.getAbsolutePath());
            // System.out.println(gs.toString());
            // System.out.println();
            // System.out.println(ret.toString());
            assertEquals(gs, ret);
        } else {
            fail(getFailOnDeleteMessage(f.getName()));
        }
    }

    /**
     * This tests the correct serialization and deserialization methods.
     * 
     * @throws IOException
     *             if something wrong happens
     */
    @Test
    public void testSerializeAndDeserializeJSON() throws IOException {
        final File f = folder.newFile("Try.json");
        // Objects
        final Boolean b = true;
        final String s = "Try";
        final Integer i = 1;
        final Double d = 1.0;
        // Lists
        final List<Boolean> l1 = new ArrayList<>();
        final List<String> l2 = new LinkedList<>();
        final List<Integer> l3 = new ArrayList<>();
        final List<Double> l4 = new LinkedList<>();
        // Maps
        final Map<Integer, Boolean> m1 = new HashMap<>();
        final Map<String, Double> m2 = new TreeMap<>();

        // Objects
        Save.serializeJSON(b, f.getAbsolutePath());
        assertEquals(b, Save.deserializeJSON(Boolean.class, f.getAbsolutePath()));

        if (f.delete()) {
            Save.serializeJSON(s, f.getAbsolutePath());
            assertEquals(s, Save.deserializeJSON(String.class, f.getAbsolutePath()));
        } else {
            fail(getFailOnDeleteMessage(f.getName()));
        }

        if (f.delete()) {
            Save.serializeJSON(i, f.getAbsolutePath());
            assertEquals(i, Save.deserializeJSON(Integer.class, f.getAbsolutePath()));
        } else {
            fail(getFailOnDeleteMessage(f.getName()));
        }

        if (f.delete()) {
            Save.serializeJSON(d, f.getAbsolutePath());
            assertEquals(d, Save.deserializeJSON(Double.class, f.getAbsolutePath()));
        } else {
            fail(getFailOnDeleteMessage(f.getName()));
        }

        // Lists
        if (f.delete()) {
            l1.add(b);
            Save.serializeJSON(l1, f.getAbsolutePath());
            assertEquals(l1, Save.deserializeJSON(l1.getClass(), f.getAbsolutePath()));
        } else {
            fail(getFailOnDeleteMessage(f.getName()));
        }

        if (f.delete()) {
            l2.add(s);
            Save.serializeJSON(l2, f.getAbsolutePath());
            assertEquals(l2, Save.deserializeJSON(l2.getClass(), f.getAbsolutePath()));
        } else {
            fail(getFailOnDeleteMessage(f.getName()));
        }

        if (f.delete()) {
            l3.add(i);
            Save.serializeJSON(l3, f.getAbsolutePath());
            assertEquals(l3, Save.deserializeJSON(l3.getClass(), f.getAbsolutePath()));
        } else {
            fail(getFailOnDeleteMessage(f.getName()));
        }

        if (f.delete()) {
            l4.add(d);
            Save.serializeJSON(l4, f.getAbsolutePath());
            assertEquals(l4, Save.deserializeJSON(l4.getClass(), f.getAbsolutePath()));
        } else {
            fail(getFailOnDeleteMessage(f.getName()));
        }

        // Maps
        if (f.delete()) {
            m1.put(i, b);
            Save.serializeJSON(m1, f.getAbsolutePath());
            assertEquals(m1, Save.deserializeJSON(m1.getClass(), f.getAbsolutePath()));
        } else {
            fail(getFailOnDeleteMessage(f.getName()));
        }

        if (f.delete()) {
            m2.put(s, d);
            Save.serializeJSON(m2, f.getAbsolutePath());
            assertEquals(m2, Save.deserializeJSON(m2.getClass(), f.getAbsolutePath()));
        } else {
            fail(getFailOnDeleteMessage(f.getName()));
        }
    }

    private String getFailOnDeleteMessage(final String fileName) {
        return new StringBuilder().append("Can't delete temporary ").append(fileName).append(" JSON test file")
                .toString();
    }

}
