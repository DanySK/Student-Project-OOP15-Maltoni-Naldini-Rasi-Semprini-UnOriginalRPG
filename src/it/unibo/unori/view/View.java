package it.unibo.unori.view;

import it.unibo.unori.view.layers.Layer;
import it.unibo.unori.view.layers.MapLayer;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.Stack;

/**
 *
 * This class displays the game layers with transparency,
 * behaving like a stack.
 *
 */
public final class View extends JFrame {
    private static final long serialVersionUID = 1L;

    private final JLayeredPane layeredPane;
    private static final String TITLE = "UnOriginal.RPG";

    private final Stack<Layer> stack = new Stack<Layer>();

    /**
     * The default dimension of the view.
     */
    public static final Dimension SIZE = new Dimension(640, 480); // TODO

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
     * Closes the view.
     */
    public void close() {
        final WindowEvent closingEvent = new WindowEvent(View.this,
                                                         WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closingEvent);
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
    public void resizeTo(final Layer layer) {
        this.getContentPane().setPreferredSize(layer.getSize());

        this.pack();
    }

    /**
     * Pushes a layer on top of the view.
     * @param layer the layer to be pushed
     */
    public void push(final Layer layer) {
        if (!stack.isEmpty()) {
            stack.peek().setEnabled(false);
            if (!(stack.peek() instanceof MapLayer)) {
                stack.peek().setVisible(false);
            }
        }

        stack.push(layer);
        this.layeredPane.add(stack.peek(), stack.stream().count());
        this.layeredPane.moveToFront(stack.peek());
    }

    /**
     * Removes the layer on top of the view.
     */
    public void pop() {
        if (!stack.isEmpty()) {
            stack.peek().setEnabled(true);
            stack.peek().setVisible(false);
        }
        this.layeredPane.remove(stack.pop());
    }

    /**
     * Displays the view thread safely.
     */
    public void run() {
        SwingUtilities.invokeLater(new Runnable() {
             @Override public void run() {
                 View.this.setVisible(true);
             }
         });
    }
}