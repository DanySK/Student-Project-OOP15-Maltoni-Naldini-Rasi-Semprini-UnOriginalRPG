package it.unibo.unori.view.layers.common;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.KeyStroke;

import it.unibo.unori.controller.actionlistener.ObjectUseActionListener;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.view.layers.ingame.PartyInfoMenu;

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
    public static final Dimension SIZE = ItemMenu.SIZE;

    private static final int BORDER = 5;
    private final MenuStack inGameStack;

    /**
     * Creates the party menu.
     *
     * @param inGameStack
     *            the in-game menu stack
     * @param item
     *            the item to be used
     * @param heroTeam
     *            the hero team
     * @param bag
     *            the party bag
     * @param x
     *            the x position
     * @param y
     *            the y position
     */
    public PartyMenu(final MenuStack inGameStack, final Item item, final HeroTeam heroTeam, final Bag bag, final int x,
            final int y) {
        super();

        this.inGameStack = inGameStack;

        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(0, 1, BORDER, BORDER));
        this.setBounds(x, y, SIZE.width, SIZE.height);

        this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

        for (final Hero hero : heroTeam.getAllHeroes()) {
            final MenuButton button = new MenuButton(hero.getName());
            button.addActionListener(new ObjectUseActionListener(item, hero, bag));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    inGameStack.pop();
                    inGameStack.pop();
                }
            });
            this.add(button);
        }

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("CLOSE", new CloseAction());
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "CLOSE");
    }

    /**
     * Creates the party menu, in which every button call an action given.
     *
     * @param inGameStack
     *            the in-game menu stack
     * @param item
     *            the item to be used
     * @param heroTeam
     *            the hero team
     * @param bag
     *            the party bag
     * @param x
     *            the x position
     * @param y
     *            the y position
     * @param battleActionListener
     *            the action to be called by every button
     *
     */
    public PartyMenu(final MenuStack inGameStack, final Item item, final HeroTeam heroTeam, final Bag bag, final int x,
            final int y, final ActionListener battleActionListener) {
        super();

        this.inGameStack = inGameStack;

        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(0, 1, BORDER, BORDER));
        this.setBounds(x, y, SIZE.width, SIZE.height);

        this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

        for (final Hero hero : heroTeam.getAllHeroes()) {
            final MenuButton button = new MenuButton(hero.getName());
            button.addActionListener(new ObjectUseActionListener(item, hero, bag));
            button.addActionListener(battleActionListener);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    inGameStack.pop();
                    inGameStack.pop();
                }
            });
            this.add(button);
        }

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("CLOSE", new CloseAction());
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "CLOSE");
    }

    /**
     * Creates a party menu to show infos.
     *
     * @param inGameStack
     *            the menu stack
     * @param heroTeam
     *            the hero team
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public PartyMenu(final MenuStack inGameStack, final HeroTeam heroTeam, final int x, final int y) {
        super();

        this.inGameStack = inGameStack;

        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(0, 1, BORDER, BORDER));
        this.setBounds(x, y, SIZE.width, SIZE.height);

        this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

        for (final Hero hero : heroTeam.getAllHeroes()) {
            final MenuButton button = new MenuButton(hero.getName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    inGameStack.push(new PartyInfoMenu(inGameStack, hero, SIZE.width + x + BORDER, y));
                }
            });
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
