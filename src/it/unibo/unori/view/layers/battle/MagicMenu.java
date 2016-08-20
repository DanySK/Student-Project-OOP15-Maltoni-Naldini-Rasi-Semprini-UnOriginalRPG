package it.unibo.unori.view.layers.battle;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.view.layers.common.MenuButton;
import it.unibo.unori.view.layers.common.MenuStack;

/**
 *
 * The menu that shows all the possible magics.
 *
 */
public class MagicMenu extends JPanel {
    /**
     * The magic menu size.
     */
    public static final Dimension SIZE = new Dimension(160, 240);

    private static final int BORDER = 5;
    private final MenuStack battleMenuStack;

    /**
     * Creates the magic menu.
     *
     * @param battleMenuStack
     *            the menu stack
     * @param heroTeam
     *            the hero team
     * @param foeTeam
     *            the foe team
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     *
     */
    public MagicMenu(final MenuStack battleMenuStack, final HeroTeam heroTeam, final FoeSquad foeTeam, final int x,
            final int y) {
        super();

        this.battleMenuStack = battleMenuStack;

        this.setBackground(Color.WHITE);
        this.setBounds(x, y, SIZE.width, SIZE.height);
        this.setLayout(new GridLayout(0, 1, BORDER, BORDER));
        this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

        for (final MagicAttackInterface magic : heroTeam.getAliveHeroes().get(0).getMagics()) {
            final MenuButton button = new MenuButton(magic.getStringToShow());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    battleMenuStack.push(new EnemiesMenu(battleMenuStack, magic, foeTeam, SIZE.width + x + BORDER, y));
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
            battleMenuStack.pop();
        }
    }
}
