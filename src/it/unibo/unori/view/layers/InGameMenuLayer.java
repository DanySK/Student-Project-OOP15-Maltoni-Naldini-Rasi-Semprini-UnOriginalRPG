package it.unibo.unori.view.layers;

import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.view.layers.ingame.MainMenu;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Dimension;
import java.util.Stack;

/**
 *
 * A menu that can be opened in-game.
 *
 */
public class InGameMenuLayer extends Layer {
    private static final long serialVersionUID = 1L;

    private static final int BORDER = 5;

    private static final Dimension SIZE = MapLayer.SIZE;

    /**
     * Creates the in-game menu.
     * @param heroTeam the heroes team
     * @param bag the party bag
     */
    public InGameMenuLayer(final HeroTeam heroTeam, final Bag bag) {
        super();

        this.setOpaque(false);
        this.setBounds(0, 0, SIZE.width, SIZE.height);

        final InGameMenuStack inGameStack = new InGameMenuStack();

        inGameStack.setPreferredSize(new Dimension(SIZE.width, SIZE.height));

        this.add(inGameStack);

        inGameStack.push(new MainMenu(inGameStack,
                                      heroTeam, bag,
                                      BORDER, BORDER));
    }

    /**
     *
     * The in-game menu stack.
     *
     */
    public static class InGameMenuStack extends JLayeredPane {
        private final Stack<JPanel> stack = new Stack<JPanel>();

        /**
         * Push a sub-menu into the in-game menu.
         * @param panel the menu to be pushed
         */
        public void push(final JPanel panel) {
            if (!stack.isEmpty()) {
                stack.peek().setEnabled(false);
            }

            this.add(panel);
            stack.push(panel);
        }
        /**
         * Remove the outpmost menu of the in-game menu.
         */
        public void pop() {
            stack.peek().setVisible(false);
            stack.peek().setEnabled(false);

            this.remove(stack.pop());

            if (!stack.isEmpty()) {
                stack.peek().setEnabled(true);
            }
        }
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
}