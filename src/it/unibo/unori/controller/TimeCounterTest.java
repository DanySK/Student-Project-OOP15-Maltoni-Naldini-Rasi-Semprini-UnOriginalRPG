package it.unibo.unori.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit test class for interface TimeCounter and its implementation TimeCounterImpl.
 */
public class TimeCounterTest {
    private static final double ALREADY_PLAYED_TIME_TEST = 100;

    /**
     * Generic test for TimeCounter and TimeCounterImpl.
     */
    @Test
    public void genericTest() {
        final TimeCounter t = new TimeCounterImpl();
        assertTrue(t.isRunning());

        t.stopTimer();
        assertFalse(t.isRunning());

        final TimeCounter t2 = new TimeCounterImpl(false);
        assertFalse(t2.isRunning());

        t2.setAlreadyPlayedTime(100);
        final TimeCounter t3 = new TimeCounterImpl(ALREADY_PLAYED_TIME_TEST, false);
        assertFalse(t3.isRunning());
        assertEquals(new Double(t2.getAlreadyPlayedTime()), new Double(t3.getAlreadyPlayedTime()));

        t2.startTimer();
        assertTrue(t2.isRunning());

        t.resetTimer();
        assertFalse(t.isRunning());
        assertEquals(new Double(0), new Double(t.getAlreadyPlayedTime()));

        final TimeCounter t4 = new TimeCounterImpl(100);
        assertTrue(t4.isRunning());
        assertEquals(new Double(ALREADY_PLAYED_TIME_TEST), new Double(t3.getAlreadyPlayedTime()));

        double time = System.currentTimeMillis();
        t.startTimer();

        time = System.currentTimeMillis() - time;
        t.stopTimer();

        assertEquals(new Double(time), new Double(t.getPlayingTime()));
        assertEquals(new Double(time), new Double(t.getTotalTime()));

        t.setAlreadyPlayedTime(ALREADY_PLAYED_TIME_TEST);
        assertEquals(new Double(time), new Double(t.getPlayingTime()));
        assertEquals(new Double(time + ALREADY_PLAYED_TIME_TEST), new Double(t.getTotalTime()));
    }

}
