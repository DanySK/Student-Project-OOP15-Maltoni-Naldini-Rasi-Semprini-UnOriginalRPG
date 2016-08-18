package it.unibo.unori.view.layers.menus;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.Weapon;

/**
 *
 * The item in-game menu.
 *
 */
public class ItemMenu extends JPanel {
    private static final int BORDER = 5;
    private final Dimension size = new Dimension(160, 320);

    private int focusedButton;
    private final List<MenuButton> buttons = new LinkedList<MenuButton>();

    private final JLayeredPane layeredPane;
    private final JPanel buttonPanel = new JPanel();

    /**
     * Creates the item in-game menu.
     * @param bag the party bag
     * @param layeredPane the parent pane that contains this
     * @param bottom the layer that called this
     * @param x the x position
     * @param y the y position
     */
    public ItemMenu(final Bag bag, // TODO inventati lambda
                    final JLayeredPane layeredPane, final JPanel bottom,
                    final int x, final int y) {
        super();

        this.layeredPane = layeredPane;

        this.setBounds(x, y, size.width + 100, size.height + 100);

        if (bottom != null) {
            bottom.setEnabled(false);
        }

        buttonPanel.setLayout(new GridLayout(0, 1, BORDER, BORDER));
        buttonPanel.setMinimumSize(new Dimension(size.width, size.height * 2));
        buttonPanel.setPreferredSize(new Dimension(size.width, size.height * 2));
        buttonPanel.setMaximumSize(new Dimension(size.width, size.height * 2));

        final Map<Armor, Integer> armors = bag.getArmors();
        final Map<Weapon, Integer> weapons =  bag.getWeapons();
        final Map<Potion, Integer> potions = bag.getPotions();
        final Map<Item, Integer> miscellaneous = bag.getMiscellaneous();

        for (final Map.Entry<Armor, Integer> entry : armors.entrySet()) {
            final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    // layeredPane.add(new JobsMenu(entry.getKey(),
                                                    // layeredPane, ItemMenu.this,
                                                    // size.width + x + BORDER, y));
                }
            });

            buttons.add(button);
        }

        for (final Map.Entry<Weapon, Integer> entry : weapons.entrySet()) {
            final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    // layeredPane.add(new JobsMenu(entry.getKey(),
                                                    // layeredPane, ItemMenu.this,
                                                    // size.width + x + BORDER, y));
                }
            });

            buttons.add(button);
            buttonPanel.add(button);
        }

        for (final Map.Entry<Potion, Integer> entry : potions.entrySet()) {
            final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    // layeredPane.add(new JobsMenu(entry.getKey(),
                                                    // layeredPane, ItemMenu.this,
                                                    // size.width + x + BORDER, y));
                }
            });

            buttons.add(button);
        }

        for (final Map.Entry<Item, Integer> entry : miscellaneous.entrySet()) {
            final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
            button.addActionListener(new ActionListener() {
                public void actionPerformed(final ActionEvent e) {
                    // layeredPane.add(new JobsMenu(entry.getKey(),
                                                    // layeredPane, ItemMenu.this,
                                                    // size.width + x + BORDER, y));
                }
            });

            buttons.add(button);
        }

        final JScrollPane scrollPane = new JScrollPane(buttonPanel);

        this.add(scrollPane);

        scrollPane.setMinimumSize(size);
        scrollPane.setPreferredSize(size);
        scrollPane.setMaximumSize(size);

        buttons.get(focusedButton).requestFocus();

        final ActionMap actionMap = this.getActionMap();
        final InputMap inputMap = this.getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("UP", new ButtonAction(-1));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "UP");
        actionMap.put("DOWN", new ButtonAction(1));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "DOWN");
        actionMap.put("ENTER", new ButtonAction(0));
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "ENTER");

        this.requestFocus();
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
}