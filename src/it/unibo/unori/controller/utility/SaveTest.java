package it.unibo.unori.controller.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.google.gson.JsonIOException;

import it.unibo.unori.controller.exceptions.CorruptedUtilityFileException;
import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.SingletonParty;

/**
 * This JUnit test class checks if utility class Save works properly.
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
     * @throws JsonIOException
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
     * @throws JsonIOException
     *             if the file does is not a JSON-serialized object
     * @throws IOException
     *             if something else wrong happens
     */
    @Test(expected = FileNotFoundException.class)
    public void testStatsFileNotFound() throws IOException {
        Save.loadStatsFromPath(folder.getRoot().getAbsolutePath() + "/Stats.json");
    }

    /**
     * This tests if loadSave method throws JsonIOException if the file does not exist.
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
     * This tests if loadFromUtilityFile method throws IOException if the file does not exist.
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

    @Test
    public void testSaveAndLoadGame() throws IOException {
        final File f = folder.newFile("Save.json");
        final Party firstParty = SingletonParty.getParty();
        Save.saveGameToPath(firstParty, f.getAbsolutePath());
        final Party secondParty = Save.loadGameFromPath(f.getAbsolutePath());
        assertEquals(firstParty, secondParty);
    }

    @Test
    public void testLoadStats() {
        fail("Not yet implemented");
    }

    @Test
    public void testSaveStats() {
        fail("Not yet implemented");
    }

    @Test
    public void testSerializeJSON() {
        fail("Not yet implemented");
    }

    @Test
    public void testDeserializeJSON() {
        fail("Not yet implemented");
    }

}
