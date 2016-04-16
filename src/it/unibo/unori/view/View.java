package it.unibo.unori.view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLayeredPane;

/**
 * 
 * The View class displays game layers with transparency, it behaves like a stack.
 *
 */
public final class View extends JFrame {
    private final JLayeredPane layeredPane;
    private static final String TITLE = "UnOriginal RPG";

    /**
     * Creates an instance of the view.
     */
    public View() {
        super(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layeredPane = getLayeredPane();
    }

    /**
     * Removes the layer on top of the view.
     */
    public void pop() {
        layeredPane.remove(layeredPane.highestLayer());
    }

    /**
     * Pushes a layer onto the top of the view. Resize the view accordingly.
     * @param layer the layer to be pushed onto the view
     */
    public void push(final JPanel layer) {
        layeredPane.add(layer);

        getContentPane().setPreferredSize(layer.getSize());
        pack();
    }
}
