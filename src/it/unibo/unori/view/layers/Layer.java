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
     * Make this layer inactive.
     */
    @Override
    public void disable() { }
}
