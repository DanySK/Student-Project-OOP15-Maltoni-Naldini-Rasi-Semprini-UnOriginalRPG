package it.unibo.unori.view.layers;

import javax.swing.JPanel;

/**
 *
 * This class represents a game layer.
 *
 */
public abstract class Layer extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Enable or disable this layer.
     */
    @Override
    public void setEnabled(final boolean b) {
    }
}
