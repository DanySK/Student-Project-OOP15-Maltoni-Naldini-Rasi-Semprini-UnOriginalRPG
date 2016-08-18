package it.unibo.unori.view.layers.menus;

import it.unibo.unori.controller.action.CloseMenuAction;
import it.unibo.unori.controller.actionlistener.SaveActionListener;
import it.unibo.unori.model.items.Bag;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.InputMap;
import javax.swing.JLayeredPane;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import javax.swing.BorderFactory;
import javax.swing.AbstractAction;

/**
 *
 * The main in-game menu.
 *
 */
public class MainMenu extends JPanel {
    private static final int BORDER = 5;
    private final Dimension size = new Dimension(160, 160);

    private int focusedButton;
    private final JLayeredPane layeredPane;
    private final List<MenuButton> buttons = new LinkedList<MenuButton>();

    /**
     * Creates the first in-game menu.
     * @param bag the bag of the party
     * @param layeredPane the parent pane that contains this
     * @param bottom the layer that called this
     * @param x the x position
     * @param y the y position
     */
    public MainMenu(final Bag bag,
                    final JLayeredPane layeredPane, final JPanel bottom,
                    final int x, final int y) {
        super();

        this.layeredPane = layeredPane;

        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(0, 1, BORDER, BORDER));
        this.setBounds(x, y, size.width, size.height);

        this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

        if (bottom != null) {
            bottom.setEnabled(false);
        }

        final MenuButton party = new MenuButton("Party");
        party.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
            }
        });

        final MenuButton items = new MenuButton("Items");
        items.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                layeredPane.add(new ItemMenu(bag, layeredPane, MainMenu.this,
                                             x + size.width + BORDER, y));
            }
        });

        final MenuButton save = new MenuButton("Save");
        save.addActionListener(new SaveActionListener());

        buttons.add(party);
        buttons.add(items);
        buttons.add(save);

        for (final MenuButton button : buttons) {
            this.add(button);
        }

        buttons.get(focusedButton).requestFocus();

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("UP", new ButtonAction(-1));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
        actionMap.put("DOWN", new ButtonAction(1));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
        actionMap.put("ENTER", new ButtonAction(0));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");

        actionMap.put("CLOSE", new CloseAction(this));
        actionMap.put("CLOSE", new CloseMenuAction());
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

    private class ButtonAction extends AbstractAction {
        private final int direction;

        ButtonAction(final int direction) {
            super();
            this.direction = direction;
        }

        @Override
        public void actionPerformed(final ActionEvent e) {
            if (direction == 0) {
                buttons.get(focusedButton).doClick();
            } else {
                if (buttons.size() != 0) {
                    focusedButton += direction;

                    if (focusedButton < 0) {
                        focusedButton = buttons.size() - 1;
                    }
                    if (focusedButton > buttons.size() - 1) {
                        focusedButton = 0;
                    }

                    buttons.get(focusedButton).requestFocus();
                }
            }
        }
    }

    private class CloseAction extends AbstractAction {
        CloseAction(final JPanel panel) {
            super();
        }

        public void actionPerformed(final ActionEvent e) {
            MainMenu.this.setVisible(false);
            layeredPane.remove(MainMenu.this);
        }
    }
}