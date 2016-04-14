package it.unibo.unori.view;

import java.util.Stack;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

/**
 * 
 * The View class displays and contains game layers, it behaves like a stack.
 *
 */
public final class View extends JFrame {
    private static final String TITLE = "UnoRPG";

    /**
     * Main window's dimension.
     */
    public static final Dimension SIZE = new Dimension(800, 600);

    private final JLayeredPane layeredPane;
    private final Stack<JPanel> layerStack = new Stack<>();

    /**
     * Creates an instance of View.
     */
    public View() {
        super(TITLE);
        setSize(SIZE.width, SIZE.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layeredPane = getLayeredPane();

        SwingUtilities.invokeLater(new Runnable() {
            @Override public void run() {
                setVisible(true);
            }
        });
    }

    /**
     * Removes the layer at the top of the stack.
     * @throws EmptyStackException  if this stack is empty.
     */
    public void popLayer() {
        layeredPane.remove(layerStack.pop());
    }

    /**
     * Pushes a layer onto the top of the stack.
     * @param gameLayer  the layer to be pushed onto the stack.
     */
    public void pushLayer(final JPanel gameLayer) {
        layeredPane.add(layerStack.push(gameLayer));
    }
}
