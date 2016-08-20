package it.unibo.unori.view.layers.battle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.view.layers.common.ItemMenu;
import it.unibo.unori.view.layers.common.MenuButton;
import it.unibo.unori.view.layers.common.MenuStack;

/**
 *
 * The battle main menu.
 *
 */
public class BattleMainMenu extends JPanel {
    private static final int BORDER = 5;
    private static final Dimension SIZE = new Dimension(160, 160);

    private final MenuStack battleMenuStack;

    /**
     * Creates a battle main menu.
     *
     * @param battleMenuStack
     *            the battle menu stack
     * @param heroTeam
     *            the hero team
     * @param foeTeam
     *            the foe team
     * @param bag
     *            the party bag
     * @param x
     *            the x position
     * @param y
     *            the y position
     */
    public BattleMainMenu(final MenuStack battleMenuStack, final HeroTeam heroTeam, final FoeSquad foeTeam,
            final Bag bag, final int x, final int y) {

        this.battleMenuStack = battleMenuStack;

        this.setBackground(Color.WHITE);
        this.setBounds(x, y, SIZE.width, SIZE.height);
        this.setLayout(new GridLayout(2, 2, BORDER, BORDER));

        this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

        final MenuButton items = new MenuButton("Items");
        items.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                battleMenuStack.push(new ItemMenu(battleMenuStack, heroTeam, bag, BORDER + SIZE.width + x, y));
            }
        });

        this.add(items);
    }
}