package it.unibo.unori.view.layers.ingame;

import it.unibo.unori.controller.action.CloseMenuAction;
import it.unibo.unori.controller.actionlistener.SaveActionListener;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.view.layers.common.ItemMenu;
import it.unibo.unori.view.layers.common.MenuButton;
import it.unibo.unori.view.layers.common.MenuStack;
import it.unibo.unori.view.layers.common.PartyMenu;

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
import javax.swing.ActionMap;
import javax.swing.KeyStroke;
import javax.swing.BorderFactory;
import javax.swing.AbstractAction;

/**
 *
 * The main in-game menu.
 *
 */
public class InGameMainMenu extends JPanel {
    private static final int BORDER = 5;
    private static final Dimension SIZE = new Dimension(160, 160);

    private final List<MenuButton> buttons = new LinkedList<MenuButton>();

    private final MenuStack inGameStack;

    /**
     * Creates the first in-game menu.
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
    public InGameMainMenu(final MenuStack inGameStack, final HeroTeam heroTeam, final Bag bag, final int x,
            final int y) {
        super();

        this.inGameStack = inGameStack;

        this.setBackground(Color.WHITE);
        this.setBounds(x, y, SIZE.width, SIZE.height);
        this.setLayout(new GridLayout(0, 1, BORDER, BORDER));

        this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

        final MenuButton party = new MenuButton("Party");
        party.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final List<MenuButton> buttons = new LinkedList<MenuButton>();

                for (final Hero hero : heroTeam.getAllHeroes()) {
                    final MenuButton button = new MenuButton(hero.getName());
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(final ActionEvent e) {
                            inGameStack.push(new PartyInfoMenu(inGameStack, hero,
                                    BORDER + SIZE.width + x + BORDER + PartyMenu.SIZE.width, y));
                        }
                    });
                    buttons.add(button);
                }

                inGameStack.push(new PartyMenu(inGameStack, buttons, BORDER + SIZE.width + x, y));
            }
        });

        final MenuButton items = new MenuButton("Items");
        items.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                inGameStack.push(new ItemMenu(inGameStack, heroTeam, bag, BORDER + SIZE.width + x, y));
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

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("CLOSE", new CloseAction());
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

    private class CloseAction extends AbstractAction {
        CloseAction() {
            super();
        }

        public void actionPerformed(final ActionEvent e) {
            inGameStack.pop();
        }
    }
}
