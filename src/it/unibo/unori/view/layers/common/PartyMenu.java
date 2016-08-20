package it.unibo.unori.view.layers.common;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;

import javax.swing.BorderFactory;
import javax.swing.AbstractAction;

/**
 *
 * The main in-game menu.
 *
 */
public class PartyMenu extends JPanel {
    /**
     * the size of this menu.
     */
    public static final Dimension SIZE = new Dimension(160, 160);

    private static final int BORDER = 5;
    private final MenuStack inGameStack;

    /**
     * Creates the first in-game menu.
     * @param inGameStack the in-game menu stack
     * @param buttons the buttons to be added
     * @param x the x position
     * @param y the y position
     */
    public PartyMenu(final MenuStack inGameStack,
                     final List<MenuButton> buttons,
                     final int x, final int y) {
        super();

        this.inGameStack = inGameStack;

        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(0, 1, BORDER, BORDER));
        this.setBounds(x, y, SIZE.width, SIZE.height);

        this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

        for (final MenuButton button : buttons) {
            this.add(button);
        }

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("CLOSE", new CloseAction());
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "CLOSE");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnabled(final boolean b) {
        for (final Component component : this.getComponents()) {
            component.setEnabled(b);
        }
    }

    private class CloseAction extends AbstractAction {
        CloseAction() {
            super();
        }

        public void actionPerformed(final ActionEvent e) {
            inGameStack.pop();
        }
    }
}