package it.unibo.unori.view.layers.battle;

import java.awt.Color;
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

import it.unibo.unori.controller.actionlistener.MagicAttackActionListener;
import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.view.layers.common.MenuButton;
import it.unibo.unori.view.layers.common.MenuStack;

/**
 *
 * A menu containing the enemies.
 *
 */
public class EnemiesMenu extends JPanel {
    /**
     * The magic menu size.
     */
    public static final Dimension SIZE = new Dimension(160, 240);

    private static final int BORDER = 5;

    private final MenuStack battleMenuStack;

    /**
     * Creates the enemies menu.
     *
     * @param battleMenuStack
     *            the menu stack
     * @param magic
     *            the magic used
     * @param foeTeam
     *            the foe team
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public EnemiesMenu(final MenuStack battleMenuStack, final MagicAttackInterface magic, final FoeSquad foeTeam,
            final int x, final int y) {

        super();

        this.battleMenuStack = battleMenuStack;

        this.setBackground(Color.WHITE);
        this.setBounds(x, y, SIZE.width, SIZE.height);
        this.setLayout(new GridLayout(0, 1, BORDER, BORDER));
        this.setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER, BORDER));

        for (final Foe foe : foeTeam.getAliveFoes()) {
            final MenuButton button = new MenuButton(foe.getName());
            button.addActionListener(new MagicAttackActionListener(magic, foe));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    battleMenuStack.pop();
                    battleMenuStack.pop();
                    battleMenuStack.pop();
                }
            });

            this.add(button);
        }

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("CLOSE", new CloseAction());
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "CLOSE");
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
