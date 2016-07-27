package it.unibo.unori.controller.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;

import org.junit.Test;

import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.items.WeaponFactory;

/**
 * JUnit test for {@link it.unibo.unori.controller.utility.JobsSetup.java} class.
 */
public class JobsSetupTest {

    @Test
    public void testGetDefaultStats() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetDefaultIncrements() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetDefaultArmor() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetDefaultWeapon() throws Exception {
        final WeaponFactory wf = new WeaponFactory();

        for (Jobs j : Jobs.values()) {
            if (!j.equals(Jobs.DUMP)) {
                // assertEquals
            }
        }
    }

    /**
     * This tests if getPath() method works properly.
     * 
     * @throws Exception
     *             if something wrong happens
     */
    @Test
    public void testGetPathNoExceptions() throws Exception {
        assertEquals(JobsSetup.WARRIOR, JobsSetup.getPath(Jobs.WARRIOR));
        assertEquals(JobsSetup.PALADIN, JobsSetup.getPath(Jobs.PALADIN));
        assertEquals(JobsSetup.MAGE, JobsSetup.getPath(Jobs.MAGE));
        assertEquals(JobsSetup.RANGER, JobsSetup.getPath(Jobs.RANGER));
        assertEquals(JobsSetup.COOK, JobsSetup.getPath(Jobs.COOK));
        assertEquals(JobsSetup.CLOWN, JobsSetup.getPath(Jobs.CLOWN));
    }

    /**
     * This tests if getPath() method throws exceptions when it should.
     * 
     * @throws Exception
     *             to pass the test
     */
    @Test(expected = FileNotFoundException.class)
    public void testGetPathExceptions() throws Exception {
        JobsSetup.getPath(Jobs.DUMP);
        fail("The JSON file path must not exist for Jobs.DUMP");
    }
}
