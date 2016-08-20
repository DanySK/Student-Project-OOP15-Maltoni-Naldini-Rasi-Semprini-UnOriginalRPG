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

import it.unibo.unori.controller.actionlistener.ObjectUseActionListener;
import it.unibo.unori.model.character.Hero;
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
    private final int x, y;
    private static final int BORDER = 5;
    private final Dimension size = new Dimension(160, 320);

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

        this.x = x;
        this.y = y;
        this.inGameStack = inGameStack;

        this.setBackground(Color.WHITE);
        this.setBounds(x, y, size.width, size.height);
        this.setLayout(new GridLayout(0, 1, BORDER, BORDER));

        this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

        final List<MenuButton> buttons = new LinkedList<MenuButton>();

        if (!bag.getArmors().isEmpty()) {
            final Map<Armor, Integer> armors = bag.getArmors();

            for (final Map.Entry<Armor, Integer> entry : armors.entrySet()) {
                final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
                setAction(button, entry.getKey(), bag, heroTeam);
                buttons.add(button);
            }
        }

        if (!bag.getWeapons().isEmpty()) {
            final Map<Weapon, Integer> weapons = bag.getWeapons();

            for (final Map.Entry<Weapon, Integer> entry : weapons.entrySet()) {
                final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
                setAction(button, entry.getKey(), bag, heroTeam);
                buttons.add(button);
            }
        }

        if (!bag.getPotions().isEmpty()) {
            final Map<Potion, Integer> potions = bag.getPotions();

            for (final Map.Entry<Potion, Integer> entry : potions.entrySet()) {
                final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
                setAction(button, entry.getKey(), bag, heroTeam);
                buttons.add(button);
            }
        }

        if (!bag.getMiscellaneous().isEmpty()) {
            final Map<Item, Integer> weapons = bag.getMiscellaneous();

            for (final Map.Entry<Item, Integer> entry : weapons.entrySet()) {
                final MenuButton button = new MenuButton(entry.getKey().getName() + ", " + entry.getValue());
                setAction(button, entry.getKey(), bag, heroTeam);
                buttons.add(button);
            }
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

    private void setAction(final MenuButton button, final Item item, final Bag bag, final HeroTeam heroTeam) {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final List<MenuButton> partyButtons = new LinkedList<MenuButton>();

                for (final Hero hero : heroTeam.getAllHeroes()) {
                    final MenuButton partyButton = new MenuButton(hero.getName());

                    partyButton.addActionListener(new ObjectUseActionListener(item, hero, bag));
                    partyButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(final ActionEvent e) {
                            inGameStack.pop();
                            inGameStack.pop();
                        }
                    });

                    partyButtons.add(partyButton);
                }

                inGameStack.push(new PartyMenu(inGameStack, partyButtons, BORDER + size.width + x, y));
            }
        });
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
