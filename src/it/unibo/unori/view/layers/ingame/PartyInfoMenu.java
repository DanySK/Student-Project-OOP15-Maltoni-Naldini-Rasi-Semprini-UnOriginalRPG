package it.unibo.unori.view.layers.ingame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.view.layers.common.MenuStack;

/**
 *
 * The party info menu.
 *
 */
public class PartyInfoMenu extends JPanel {
    private final MenuStack inGameStack;
    private static final Dimension SIZE = new Dimension(160, 160);

    /**
     * Creates the party info menu.
     *
     * @param inGameStack
     *            the in-game stack
     * @param hero
     *            the hero whos infos will be displayed
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     */
    public PartyInfoMenu(final MenuStack inGameStack, final Hero hero, final int x, final int y) {
        super();

        this.inGameStack = inGameStack;

        this.setBackground(Color.WHITE);
        this.setBounds(x, y, SIZE.width, SIZE.height);

        final JLabel statistics = new JLabel(getStatistics(hero));

        this.add(statistics);

        final ActionMap actionMap = getActionMap();
        final InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);

        actionMap.put("CLOSE", new CloseAction());
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "CLOSE");
    }

    private String getStatistics(final Hero hero) {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<html>");

        stringBuilder.append("HP: ");
        stringBuilder.append(hero.getRemainingHP());
        stringBuilder.append("<br>");
        stringBuilder.append("MP: ");
        stringBuilder.append(hero.getCurrentMP());
        stringBuilder.append("<br>");
        stringBuilder.append("EXP: ");
        stringBuilder.append(hero.getRemainingExp());
        stringBuilder.append("<br>");
        stringBuilder.append("Arma: ");
        try {
            stringBuilder.append(hero.getWeapon().getName());
        } catch (final NoWeaponException e) {
            stringBuilder.append("Nessuna");
            e.printStackTrace();
        }
        stringBuilder.append("<br>");
        stringBuilder.append("Armatura: ");
        stringBuilder.append(hero.getArmor(ArmorPieces.ARMOR).getName());
        stringBuilder.append("<br>");
        stringBuilder.append("Guanti: ");
        stringBuilder.append(hero.getArmor(ArmorPieces.GLOVES).getName());
        stringBuilder.append("<br>");
        stringBuilder.append("Elmo: ");
        stringBuilder.append(hero.getArmor(ArmorPieces.HELMET).getName());
        stringBuilder.append("<br>");
        stringBuilder.append("Scudo: ");
        stringBuilder.append(hero.getArmor(ArmorPieces.SHIELD).getName());
        stringBuilder.append("<br>");
        stringBuilder.append("Pantaloni: ");
        stringBuilder.append(hero.getArmor(ArmorPieces.TROUSERS).getName());

        stringBuilder.append("</html>");

        final String statisticsText = stringBuilder.toString();

        return statisticsText;
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
