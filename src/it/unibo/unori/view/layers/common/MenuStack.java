package it.unibo.unori.view.layers.common;

import java.util.Stack;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * The in-game menu stack.
 *
 */
public class MenuStack extends JLayeredPane {
    private final Stack<JPanel> stack = new Stack<JPanel>();

    /**
     * Push a sub-menu into the in-game menu.
     *
     * @param panel
     *            the menu to be pushed
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
