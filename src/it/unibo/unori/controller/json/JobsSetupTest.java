package it.unibo.unori.controller.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.util.Optional;

import org.junit.Test;

import it.unibo.unori.model.character.jobs.GrowthFactory;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.character.jobs.StatisticsFactory;
import it.unibo.unori.model.items.ArmorFactory;
import it.unibo.unori.model.items.WeaponFactory;

/**
 * JUnit test for {@link it.unibo.unori.controller.utility.JobsSetup.java} class.
 */
public class JobsSetupTest {

    /**
     * This tests if the correct default statistics are returned.
     */
    @Test
    public void testGetDefaultStats() {
        final StatisticsFactory sf = new StatisticsFactory();
        final JobsSetup js = new JobsSetup();
        for (final Jobs j : Jobs.values()) {
            Optional<String> path = Optional.empty();
            try {
                path = Optional.of(JobsSetup.getPath(j));
                if (j.equals(Jobs.DUMP)) {
                    fail("It shouldn't find a path");
                }
            } catch (FileNotFoundException e) {
                if (!j.equals(Jobs.DUMP)) {
                    fail("It should find a path");
                }
            }

            if (path.isPresent()) {
                assertEquals(sf.getJobStats(j), js.getDefaultStatsMap(path.get()));
            }

            path = Optional.empty();
        }
    }

    /**
     * This tests if the correct default increment are returned.
     */
    @Test
    public void testGetDefaultIncrements() {
        final GrowthFactory gf = new GrowthFactory();
        final JobsSetup js = new JobsSetup();
        for (final Jobs j : Jobs.values()) {
            Optional<String> path = Optional.empty();
            try {
                path = Optional.of(JobsSetup.getPath(j));
                if (j.equals(Jobs.DUMP)) {
                    fail("It shouldn't find a path");
                }
            } catch (FileNotFoundException e) {
                if (!j.equals(Jobs.DUMP)) {
                    fail("It should find a path");
                }
            }

            if (path.isPresent()) {
                assertEquals(gf.getJobGrowth(j), js.getDefaultIncrementsMap(path.get()));
            }

            path = Optional.empty();
        }
    }

    /**
     * This tests if the correct default armors are returned.
     */
    @Test
    public void testGetDefaultArmor() {
        final ArmorFactory af = new ArmorFactory();
        final JobsSetup js = new JobsSetup();
        for (final Jobs j : Jobs.values()) {
            Optional<String> path = Optional.empty();
            try {
                path = Optional.of(JobsSetup.getPath(j));
                if (j.equals(Jobs.DUMP)) {
                    fail("It shouldn't find a path");
                }
            } catch (FileNotFoundException e) {
                if (!j.equals(Jobs.DUMP)) {
                    fail("It should find a path");
                }
            }

            if (path.isPresent()) {
                assertEquals(af.getStdEquip(), js.getDefaultArmorMap(path.get()));
            }

            path = Optional.empty();
        }
    }

    /**
     * This tests if the correct default weapons are returned.
     */
    @Test
    public void testGetDefaultWeapon() {
        final WeaponFactory wf = new WeaponFactory();
        final JobsSetup js = new JobsSetup();
        for (final Jobs j : Jobs.values()) {
            Optional<String> path = Optional.empty();
            try {
                path = Optional.of(JobsSetup.getPath(j));
                if (j.equals(Jobs.DUMP)) {
                    fail("It shouldn't find a path");
                }
            } catch (FileNotFoundException e) {
                if (!j.equals(Jobs.DUMP)) {
                    fail("It should find a path");
                }
            }

            if (path.isPresent()) {
                assertEquals(wf.getStdSword(), js.getDefaultArmorMap(path.get()));
            }

            path = Optional.empty();
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
