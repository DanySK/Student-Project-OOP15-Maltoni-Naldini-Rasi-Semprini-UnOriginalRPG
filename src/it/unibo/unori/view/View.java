package it.unibo.unori.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;

/**
 * 
 * The View class displays game layers with transparency,
 * it behaves like a stack.
 *
 */
public final class View extends JFrame {
    private Integer layers = 0;
    private final JLayeredPane layeredPane;
    private static final String TITLE = "UnOriginal RPG";

    /**
     * Creates an instance of the view.
     */
    public View() {
        super(TITLE);

        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layeredPane = getLayeredPane();
    }

    /**
     * Centers the view in the screen.
     */
    public void center() {
        setLocationRelativeTo(null);
    }

    /**
     * Resizes the view according to the specified layer.
     * @param layer the layer the view will resize to.
     */
    public void resizeTo(final JPanel layer) {
        getContentPane().setPreferredSize(layer.getSize());

        pack();
    }

    /**
     * Pushes a layer on top of the view.
     * 
     * @param layer the layer to be pushed on top of the view.
     */
    public void push(final JPanel layer) {
        layeredPane.add(layer, ++layers);
    }

    /**
     * Removes the layer on top of the view.
     */
    public void pop() {
        layeredPane.remove(layeredPane.highestLayer()); // TODO exception
    }
}
