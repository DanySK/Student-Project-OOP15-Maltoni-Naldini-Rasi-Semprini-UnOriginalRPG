/**
 * 
 */
package it.unibo.unori.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is used to poll the keyboard and get input to do things in game.
 */
public class KeyboardPolling extends KeyAdapter {
    public static final int NUMBER_OF_KEYS = 256;

    // Current state of the keyboard
    private final List<Boolean> currentKeys;

    // Polled keyboard state
    private final List<KeyState> keys;

    /**
     * Default constructor.
     */
    public KeyboardPolling() {
        this.currentKeys = Stream.generate(() -> Boolean.FALSE).limit(NUMBER_OF_KEYS).collect(Collectors.toList());
        this.keys = Stream.generate(() -> KeyState.NOT_PRESSED).limit(NUMBER_OF_KEYS).collect(Collectors.toList());
    }

    /**
     * The method updates an internal container, giving the opportunity to have three key states: not pressed, pressed
     * and holden down. It is synchronized with the method that manage KeyEvents.
     */
    public synchronized void poll() {
        for (int i = 0; i < NUMBER_OF_KEYS; i++) {
            if (currentKeys.get(i)) {
                if (keys.get(i).equals(KeyState.NOT_PRESSED)) {
                    keys.set(i, KeyState.PRESSED);
                } else {
                    keys.set(i, KeyState.HOLDEN_DOWN);
                }
            } else {
                keys.set(i, KeyState.NOT_PRESSED);
            }
        }
    }

    /**
     * Checks if a key is down (either pressed or long-pressed).
     * @param keyCode the KeyCode of the key needed
     * @return true if the key is currently pressed, false if it is not
     */
    public boolean isDown(final int keyCode) {
        return keys.get(keyCode).equals(KeyState.PRESSED)
            || keys.get(keyCode).equals(KeyState.HOLDEN_DOWN);
    }

    /**
     * Checks if a key is pressed (only one time) now, but wasn't before.
     * @param keyCode the KeyCode of the key needed
     * @return true if the key is currently pressed, false if it is not
     */
    public boolean isPressed(final int keyCode) {
        return keys.get(keyCode).equals(KeyState.PRESSED);
    }

    @Override
    public synchronized void keyPressed(final KeyEvent event) {
        this.setCurrentKeyState(event.getKeyCode(), true);
    }

    @Override
    public synchronized void keyReleased(final KeyEvent event) {
        this.setCurrentKeyState(event.getKeyCode(), false);
    }

    /**
     * Sets the state of the key,
     * @param keyCode the KeyCode of the key needed
     * @param currentState the state to set (true = pressed, false = unpressed)
     */
    private void setCurrentKeyState(final int keyCode, final boolean currentState) {
        if (keyCode >= 0 && keyCode < NUMBER_OF_KEYS) {
            this.currentKeys.set(keyCode, currentState);
        }
    }

    /**
     * Small, private, enumeration used in this class to model the state of the
     * keys of the keyboard.
     */
    private enum KeyState {
        /** The key is not pressed */
        NOT_PRESSED,
        /** The key is pressed, but before was not */
        PRESSED,
        /** The key is pressed, and so was before */
        HOLDEN_DOWN;
    }
}
