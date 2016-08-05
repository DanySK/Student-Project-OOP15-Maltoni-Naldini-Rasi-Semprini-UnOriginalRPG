package it.unibo.unori.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import java.awt.Dimension;

/**
 *
 * This class displays the game layers with transparency,
 * behaving like a stack.
 *
 */
public final class View extends JFrame {
    private Integer layers = 0;
    private final JLayeredPane layeredPane;
    private static final String TITLE = "UnOriginal.RPG";

    public static final Dimension SIZE = new Dimension(640, 480);

    /**
     * Creates an instance of the view.
     */
    public View() {
        super(TITLE);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        layeredPane = this.getLayeredPane();
    }

    /**
     * Centers the view to the screen.
     */
    public void centerToScreen() {
        this.setLocationRelativeTo(null);
    }

    /**
     * Resizes the view to the default size.
     */
    public void resize() {
        this.getContentPane().setPreferredSize(SIZE);

        this.pack();
    }

    /**
     * Resizes the view to the specified layer.
     * @param layer the layer the view will resize to
     */
    public void resizeTo(final JPanel layer) {
        this.getContentPane().setPreferredSize(layer.getSize());

        this.pack();
    }

    /**
     * Pushes a layer on top of the view.
     * @param layer the layer to be pushed
     */
    public void push(final JPanel layer) {
        this.layeredPane.add(layer, ++layers);
    }

    /**
     * Removes the layer on top of the view.
     */
    public void pop() {
        this.layeredPane.remove(layeredPane.highestLayer());
    }

    /**
     * Displays the view thread safely.
     */
    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
             @Override public void run() { View.this.setVisible(true); }
         });
    }
}