/**
 * 
 */
package it.unibo.unori.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardPolling implements KeyListener {
    /**
     * Small, private, enumeration used in this class to model the state of the keys of the keyboard.
     */
    private enum KeyState {
        /** The key is not pressed */
        NOT_PRESSED,
        /** The key is pressed, but before was not */
        PRESSED,
        /** The key is pressed, and so was before */
        HOLD_DOWN;
    }
    
    private static final int NUMBER_OF_KEYS = 256;
    
    // Polled keyboard state
    private KeyState[] keys = null;
    
    public KeyboardPolling() {
        keys = new KeyState[NUMBER_OF_KEYS];
        for(int i = 0; i < NUMBER_OF_KEYS; i++) {
            keys[i] = KeyState.NOT_PRESSED;
        }
    }
    
    

    @Override
    public void keyPressed(KeyEvent event) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyReleased(KeyEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent event) {
        // TODO Auto-generated method stub

    }

}
