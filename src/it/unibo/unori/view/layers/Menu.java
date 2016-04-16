package it.unibo.unori.view.layers;

import it.unibo.unori.view.components.Button;

import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

/**
 * 
 * Menu.
 *
 */
public abstract class Menu extends JPanel {
    /**
     * Disable all the controls inside this menu.
     */
    public void disable() { };

    private void mapButtons(final Button[] button,
                            final int key1, final int key2) {
        for (int i = 0; i < button.length; i++) {
            final int b = i;

            button[b].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(final KeyEvent e) {
                    if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                        ((Button) e.getComponent()).doClick();
                    }

                    if (e.getKeyCode() == key1) {
                        if (b > 0) {
                            button[b - 1].requestFocus();
                        }
                    }
                    if (e.getKeyCode() == key2) {
                        if (b < button.length - 1) {
                            button[b + 1].requestFocus();
                        }
                    }
                }
            });
        }
    }

    /**
     * Maps a list of buttons to the vertical arrow keys.
     *
     * @param button the list of buttons to be mapped.
     */
    public void mapButtonsVertically(final Button... button) {
        mapButtons(button, KeyEvent.VK_UP, KeyEvent.VK_DOWN);
    }

    /**
     * Maps a list of buttons to the horizontal arrow keys.
     *
     * @param button the list of buttons to be mapped.
     */
    public void mapButtonsHorizontally(final Button... button) {
        mapButtons(button, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
    }

    /**
     * Maps a grid of buttons to the arrow keys.
     *
     * @param button the 2D grid of buttons to be mapped.
     */
    public void mapButtons(final Button[]... button) {
        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button[j].length; j++) {
                final int x = i;
                final int y = j;

                button[x][y].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(final KeyEvent e) {
                        if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                            ((Button) e.getComponent()).doClick();
                        }

                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                               if (x > 0) {
                                   button[x - 1][y].requestFocus();
                               }
                        }
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                               if (x < button.length - 1) {
                                   button[x + 1][y].requestFocus();
                               }
                        }

                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            if (y > 0) {
                                button[x][y - 1].requestFocus();
                            }
                        }
                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            if (y < button[x].length - 1) {
                                button[x][y + 1].requestFocus();
                            }
                        }
                    }
                });
            }
        }
    }
}
