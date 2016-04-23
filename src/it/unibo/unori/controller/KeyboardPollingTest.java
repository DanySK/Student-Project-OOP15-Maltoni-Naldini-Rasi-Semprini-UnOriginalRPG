package it.unibo.unori.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import org.junit.Test;

/**
 * This is a JUnit test class for KeyAdapter implementation "KeyboardPolling".
 */
public class KeyboardPollingTest {
    /**
     * This test checks if the constructor initializes correctly the two internal lists.
     */
    @Test
    public void testPolling() {
        final KeyboardPolling k = new KeyboardPolling();
        for (int i = 0; i < KeyboardPolling.NUMBER_OF_KEYS; i++) {
            assertFalse(k.isDown(i));
            assertFalse(k.isPressed(i));
        }
    }

    /**
     * This test checks if the KeyAdapter react correctly to KeyEvents.
     */
    @Test
    public void testKeyEvent() {
        final KeyboardPolling k = new KeyboardPolling();
        final JFrame dummy = new JFrame();

        k.keyPressed(new KeyEvent(dummy, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a'));
        k.keyPressed(new KeyEvent(dummy, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_B, 'b'));
        k.keyPressed(new KeyEvent(dummy, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_C, 'c'));

        k.poll();
        assertTrue(k.isPressed(KeyEvent.VK_A));
        assertTrue(k.isDown(KeyEvent.VK_A));
        assertTrue(k.isPressed(KeyEvent.VK_B));
        assertTrue(k.isDown(KeyEvent.VK_B));

        k.poll();
        assertFalse(k.isPressed(KeyEvent.VK_A));
        assertTrue(k.isDown(KeyEvent.VK_A));
        assertFalse(k.isPressed(KeyEvent.VK_B));
        assertTrue(k.isDown(KeyEvent.VK_B));

        k.keyReleased(new KeyEvent(dummy, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'a'));

        k.poll();
        assertFalse(k.isPressed(KeyEvent.VK_A));
        assertFalse(k.isDown(KeyEvent.VK_A));
        assertFalse(k.isPressed(KeyEvent.VK_B));
        assertTrue(k.isDown(KeyEvent.VK_B));
    }
}
