package it.unibo.unori.controller.utility;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import it.unibo.unori.controller.exceptions.CorruptedUtilityFileException;

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
     * This tests if loadSave method throws IOException if the file does not exist.
     * 
     * @throws FileNotFoundException
     *             if the file does not exist
     * @throws IOException
     *             if some
     */
    @Test(expected = IOException.class)
    public void testSaveFileNotFound() throws IOException {
        folder.newFile("Save.json");
        
    }

    /**
     * This tests if loadFromUtilityFile method throws IOException if the file does not exist.
     * 
     * @throws IOException
     *             if the file does not exist
     * @throws CorruptedUtilityFileException
     *             if the file does not match the correct pattern
     */
    @Test(expected = IOException.class)
    public void testStatsFileNotFound() throws IOException, CorruptedUtilityFileException {
        Save.loadFromUtilityTextFile(folder.getRoot().getCanonicalPath() + File.separator + Save.STATISTICS_TEXT_FILE);
    }

    /**
     * This tests if loadFromUtilityFile method throws CorruptedUtilityFileException if the file does not match the
     * expected pattern.
     * 
     * @throws IOException
     *             if the file does not exist
     * @throws CorruptedUtilityFileException
     *             if the file does not match the correct pattern
     */
    @Test(expected = CorruptedUtilityFileException.class)
    public void testCorruptedSave() throws IOException, CorruptedUtilityFileException {
        folder.newFile(Save.SAVE_TEXT_FILE);
        Save.loadFromUtilityTextFile(folder.getRoot().getCanonicalPath() + File.separator + Save.SAVE_TEXT_FILE);
    }
    
    @Test
    public void testSaveAndLoadGame() {
        fail("Not yet implemented");
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
