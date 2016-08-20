package it.unibo.unori.view.layers.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import it.unibo.unori.model.character.HeroTeam;
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

    /**
     * The ItemMenu size.
     */
    public static final Dimension SIZE = new Dimension(160, 320);

    private final MenuStack inGameStack;
    private final JPanel buttonPanel = new JPanel();

    /**
     * Creates the item in-game menu.
     *
     * @param inGameStack
     *            the in-game menu stack
     * @param heroTeam
     *            the hero team
     * @param bag
     *            the party bag
     * @param x
     *            the x position
     * @param y
     *            the y position
     */
    public ItemMenu(final MenuStack inGameStack, final HeroTeam heroTeam, final Bag bag, final int x, final int y) {
        super();

        this.inGameStack = inGameStack;

        this.setBackground(Color.WHITE);
        this.setBounds(x, y, SIZE.width, SIZE.height);
        this.setLayout(new GridLayout(0, 1, BORDER, BORDER));

        this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

        final List<MenuButton> buttons = new LinkedList<MenuButton>();

        final Map<Armor, Integer> armors = bag.getArmors();

        for (final Map.Entry<Armor, Integer> entry : armors.entrySet()) {
            final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    inGameStack.push(
                            new PartyMenu(inGameStack, entry.getKey(), heroTeam, bag, BORDER + SIZE.width + x, y));
                }
            });
            buttons.add(button);
        }

        final Map<Weapon, Integer> weapons = bag.getWeapons();

        for (final Map.Entry<Weapon, Integer> entry : weapons.entrySet()) {
            final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    inGameStack.push(
                            new PartyMenu(inGameStack, entry.getKey(), heroTeam, bag, BORDER + SIZE.width + x, y));
                }
            });
            buttons.add(button);
        }

        final Map<Potion, Integer> potions = bag.getPotions();
        for (final Map.Entry<Potion, Integer> entry : potions.entrySet()) {
            final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    inGameStack.push(
                            new PartyMenu(inGameStack, entry.getKey(), heroTeam, bag, BORDER + SIZE.width + x, y));
                }
            });
            buttons.add(button);
        }

        final Map<Item, Integer> miscellaneous = bag.getMiscellaneous();
        for (final Map.Entry<Item, Integer> entry : miscellaneous.entrySet()) {
            final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    inGameStack.push(
                            new PartyMenu(inGameStack, entry.getKey(), heroTeam, bag, BORDER + SIZE.width + x, y));
                }
            });
            buttons.add(button);
        }

        for (final MenuButton button : buttons) {
            this.add(button);
        }

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("CLOSE", new CloseAction());
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "CLOSE");
    }

    /**
     * Creates the item in-game menu.
     *
     * @param inGameStack
     *            the in-game menu stack
     * @param heroTeam
     *            the hero team
     * @param bag
     *            the party bag
     * @param x
     *            the x position
     * @param y
     *            the y position
     * @param battleActionListener
     *            the action to be called when using an object
     */
    public ItemMenu(final MenuStack inGameStack, final HeroTeam heroTeam, final Bag bag, final int x, final int y,
            final ActionListener battleActionListener) {
        super();

        this.inGameStack = inGameStack;

        this.setBackground(Color.WHITE);
        this.setBounds(x, y, SIZE.width, SIZE.height);
        this.setLayout(new GridLayout(0, 1, BORDER, BORDER));

        this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

        final List<MenuButton> buttons = new LinkedList<MenuButton>();

        final Map<Armor, Integer> armors = bag.getArmors();

        for (final Map.Entry<Armor, Integer> entry : armors.entrySet()) {
            final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    inGameStack.push(new PartyMenu(inGameStack, entry.getKey(), heroTeam, bag, BORDER + SIZE.width + x,
                            y, battleActionListener));
                }
            });
            buttons.add(button);
        }

        final Map<Weapon, Integer> weapons = bag.getWeapons();

        for (final Map.Entry<Weapon, Integer> entry : weapons.entrySet()) {
            final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    inGameStack.push(new PartyMenu(inGameStack, entry.getKey(), heroTeam, bag, BORDER + SIZE.width + x,
                            y, battleActionListener));
                }
            });
            buttons.add(button);
        }

        final Map<Potion, Integer> potions = bag.getPotions();
        for (final Map.Entry<Potion, Integer> entry : potions.entrySet()) {
            final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    inGameStack.push(new PartyMenu(inGameStack, entry.getKey(), heroTeam, bag, BORDER + SIZE.width + x,
                            y, battleActionListener));
                }
            });
            buttons.add(button);
        }

        final Map<Item, Integer> miscellaneous = bag.getMiscellaneous();
        for (final Map.Entry<Item, Integer> entry : miscellaneous.entrySet()) {
            final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    inGameStack.push(
                            new PartyMenu(inGameStack, entry.getKey(), heroTeam, bag, BORDER + SIZE.width + x, y));
                }
            });
            buttons.add(button);
        }

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

        for (final Component component : buttonPanel.getComponents()) {
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
