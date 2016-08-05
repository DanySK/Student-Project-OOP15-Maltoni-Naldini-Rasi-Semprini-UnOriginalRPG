package it.unibo.unori.controller.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import it.unibo.unori.controller.GameStatistics;
import it.unibo.unori.controller.GameStatisticsImpl;

/**
 * JUnit test class for {@link it.unibo.unori.controller.GameStatistics}
 * interface implementation
 * {@link it.unibo.unori.controller.GameStatisticsImpl}.
 */
public class GameStatisticsImplTest {
    private static final int INCREMENT_1 = 10;
    private static final int INCREMENT_2 = 20;
    private static final int INCREMENT_3 = 100;

    /**
     * JUnit test for overridden methods
     * {@link it.unibo.unori.controller.GameStatisticsImpl#equals(Object)} and
     * {@link it.unibo.unori.controller.GameStatisticsImpl#hashCode()}. Test
     * checks if respects Joshua Bloch principle explained at chapter 3 of
     * Effective Java. The methods were tested together because of they are very
     * linked.
     */
    @Test
    public void testEqualsAndHashCode() {
        // Reflexivity of equals method
        final GameStatistics gs1 = new GameStatisticsImpl();
        assertEquals(gs1, gs1);
        // Reflexivity of hashCode method
        assertEquals(gs1.hashCode(), gs1.hashCode());

        // Symmetry of equals method
        final GameStatistics gs2 = new GameStatisticsImpl();
        assertNotSame(gs1, gs2);
        assertTrue(gs1.equals(gs2) && gs2.equals(gs1));
        // Symmetry of hashCode method
        assertEquals(gs1.hashCode(), gs2.hashCode());

        // Transitivity of equals method
        final GameStatistics gs3 = new GameStatisticsImpl();
        final GameStatistics gs4 = new GameStatisticsImpl();
        gs1.increaseArmorsAcquired(INCREMENT_2);
        gs2.increaseArmorsAcquired(INCREMENT_2);
        gs3.increaseArmorsAcquired(INCREMENT_1 * 2);
        gs4.increaseArmorsAcquired(INCREMENT_3);
        assertTrue(gs1.equals(gs2) && gs2.equals(gs1));
        assertTrue(gs2.equals(gs3) && gs3.equals(gs2));
        assertEquals(gs1, gs3);
        assertTrue(gs1.equals(gs2) && gs2.equals(gs1));
        assertFalse(gs2.equals(gs4) || gs4.equals(gs2));
        assertFalse(gs1.equals(gs4) || gs4.equals(gs1));
        // Transitivity of hashCode method
        assertTrue(gs1.hashCode() == gs2.hashCode() && gs2.hashCode() == gs3.hashCode()
                && gs1.hashCode() == gs3.hashCode());
        assertFalse(gs1.hashCode() == gs4.hashCode() || gs2.hashCode() == gs4.hashCode()
                || gs3.hashCode() == gs4.hashCode());

        // Consistency of equals method
        assertEquals(gs1, gs2);
        gs1.increaseBossesKilled(INCREMENT_1);
        assertFalse(gs1.equals(gs2) && gs2.equals(gs1));
        // Consistency of hashCode method
        assertNotSame(gs1.hashCode(), gs2.hashCode());

        // "Non-nullity"
        /*
         * I need to test that equals method returns false in compared with
         * null, so here I can suppress PMD rule "EqualsNull", but because
         * Eclipse doesn't support @SuppressWarnings("PMD.EqualsNull")
         * annnotation, I decided to use // NOPMD comment
         */
        assertFalse(gs1.equals(null)); // NOPMD
        assertFalse(gs2.equals(null)); // NOPMD
        assertFalse(gs3.equals(null)); // NOPMD
        assertFalse(gs4.equals(null)); // NOPMD
    }

    /**
     * JUnit test for getters and setters of GameStatistics, except for
     * time-related ones. Also
     * {@link it.unibo.unori.controller.GameStatistics#reset()} and
     * {@link it.unibo.unori.controller.GameStatistics#restore(GameStatistics)}
     * methods are tested.
     */
    @Test
    public void testGettersAndSetters() {
        final GameStatistics gs1 = new GameStatisticsImpl();

        assertEquals(0, gs1.getArmorsAcquired());
        gs1.increaseArmorsAcquired(INCREMENT_1);
        assertEquals(INCREMENT_1, gs1.getArmorsAcquired());

        assertEquals(0, gs1.getBossesKilled());
        gs1.increaseBossesKilled(INCREMENT_1);
        assertEquals(INCREMENT_1, gs1.getBossesKilled());

        assertEquals(0, gs1.getMonstersKilled());
        gs1.increaseMonstersKilled(INCREMENT_1);
        assertEquals(INCREMENT_1, gs1.getMonstersKilled());

        assertEquals(0, gs1.getMonstersMet());
        gs1.increaseMonstersMet(INCREMENT_1);
        assertEquals(INCREMENT_1, gs1.getMonstersMet());

        assertEquals(0, gs1.getNewGames());
        gs1.increaseNewGames(INCREMENT_1);
        assertEquals(INCREMENT_1, gs1.getNewGames());

        assertEquals(0, gs1.getTotalExpGained());
        gs1.increaseTotalExpGained(INCREMENT_1);
        assertEquals(INCREMENT_1, gs1.getTotalExpGained());

        assertEquals(0, gs1.getWeaponsAcquired());
        gs1.increaseWeaponsAcquired(INCREMENT_1);
        assertEquals(INCREMENT_1, gs1.getWeaponsAcquired());

        final GameStatistics gs2 = new GameStatisticsImpl();
        gs2.restore(gs1);
        assertEquals(gs1, gs2);

        gs1.reset();
        assertEquals(0, gs1.getArmorsAcquired());
        assertEquals(0, gs1.getBossesKilled());
        assertEquals(0, gs1.getMonstersKilled());
        assertEquals(0, gs1.getMonstersMet());
        assertEquals(0, gs1.getNewGames());
        assertEquals(0, gs1.getTotalExpGained());
        assertEquals(0, gs1.getWeaponsAcquired());
    }

    /**
     * JUnit test for time-related methods. Also
     * {@link it.unibo.unori.controller.GameStatistics#reset()} and
     * {@link it.unibo.unori.controller.GameStatistics#restore(GameStatistics)}
     * methods are tested.
     */
    @Test
    public void testTimer() {
        final GameStatistics gs1 = new GameStatisticsImpl();

        assertEquals(new Double(0), new Double(gs1.getTimePlayed()));
        assertEquals(new Double(0), new Double(gs1.getTotalTimePlayed()));
        assertFalse(gs1.isCountingTime());

        final double start = System.currentTimeMillis();
        gs1.startCountingTime();
        assertTrue(gs1.isCountingTime());

        assertEquals(new Double(System.currentTimeMillis() - start), new Double(gs1.getTimePlayed()));
        assertEquals(new Double(System.currentTimeMillis() - start), new Double(gs1.getTotalTimePlayed()));

        try {
            Thread.sleep(INCREMENT_3 * INCREMENT_1); // Sleep for 1 second
        } catch (InterruptedException e) {
            fail("Thread.sleep() threw exception: " + e.getMessage());
        }

        final double stop = System.currentTimeMillis();
        gs1.pauseCountingTime();

        assertEquals(new Double(stop - start), new Double(gs1.getTimePlayed()));
        assertEquals(new Double(stop - start), new Double(gs1.getTotalTimePlayed()));

        gs1.stopCountingTime();

        assertEquals(new Double(0), new Double(gs1.getTimePlayed()));
        assertEquals(new Double(stop - start), new Double(gs1.getTotalTimePlayed()));

        gs1.increaseTotalTimePlayed(INCREMENT_2);
        assertEquals(new Double(stop - start + INCREMENT_2), new Double(gs1.getTotalTimePlayed()));

        final GameStatistics gs2 = new GameStatisticsImpl();

        gs2.restore(gs1);
        assertEquals(new Double(gs1.getTimePlayed()), new Double(gs2.getTimePlayed()));
        assertEquals(new Double(gs1.getTotalTimePlayed()), new Double(gs2.getTotalTimePlayed()));
        assertSame(gs1.isCountingTime(), gs2.isCountingTime());

        gs1.reset();
        assertEquals(new Double(0), new Double(gs1.getTimePlayed()));
        assertEquals(new Double(0), new Double(gs1.getTotalTimePlayed()));
        assertFalse(gs1.isCountingTime());

    }

}
