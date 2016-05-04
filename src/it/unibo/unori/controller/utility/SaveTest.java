package it.unibo.unori.controller.utility;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
     * This tests if loadFromUtilityFile method throws IOException if the file does not exist.
     * 
     * @throws IOException
     *             if the file does not exist
     * @throws CorruptedUtilityFileException
     *             if the file does not match the correct pattern
     */
    @Test(expected = IOException.class)
    public void testSaveFileNotFound() throws IOException, CorruptedUtilityFileException {
        Save.loadFromUtilityTextFile(folder.getRoot().getCanonicalPath() + File.separator + Save.SAVE_TEXT_FILE);
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
    public void testCorruptedStats() throws IOException, CorruptedUtilityFileException {
        folder.newFile(Save.STATISTICS_TEXT_FILE);
        Save.loadFromUtilityTextFile(folder.getRoot().getCanonicalPath() + File.separator + Save.STATISTICS_TEXT_FILE);
    }

    /**
     * This tests if createSaveFile method works properly.
     * 
     * @throws IOException
     *             if the file does not exist
     * @throws CorruptedUtilityFileException
     *             if the file does not match the correct pattern
     */
    @Test
    public void testCreateSaveFile() throws IOException, CorruptedUtilityFileException {
        Save.createSaveTextFile(folder.getRoot().getCanonicalPath());

        final List<String> testList = new ArrayList<>();
        testList.add("currentMap:0");
        testList.add("mapPosition:0,0");
        testList.add("char1:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        testList.add("char2:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        testList.add("char3:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        testList.add("char4:,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        testList.add("inventory:0x0");
        testList.add("money:0");

        List<String> l;
        l = Save.loadFromUtilityTextFile(folder.getRoot().getCanonicalPath() + File.separator + Save.SAVE_TEXT_FILE);

        assertEquals(l, testList);
    }

    /**
     * This tests if createStatsFile method works properly.
     * 
     * @throws IOException
     *             if the file does not exist
     * @throws CorruptedUtilityFileException
     *             if the file does not match the correct pattern
     */
    @Test
    public void testCreateStatsFile() throws IOException, CorruptedUtilityFileException {
        Save.createStatsTextFile(folder.getRoot().getCanonicalPath());

        final List<String> testList = new ArrayList<>();

        testList.add("newGames:0");
        testList.add("monstersMet:0");
        testList.add("monstersKilled:0");
        testList.add("bosses:0");
        testList.add("finalBosses:0");
        testList.add("weapons:0");
        testList.add("armors:0");
        testList.add("totalExp:0");

        List<String> l;
        l = Save.loadFromUtilityTextFile(folder.getRoot().getCanonicalPath() + File.separator + Save.STATISTICS_TEXT_FILE);

        assertEquals(l, testList);
    }
}
