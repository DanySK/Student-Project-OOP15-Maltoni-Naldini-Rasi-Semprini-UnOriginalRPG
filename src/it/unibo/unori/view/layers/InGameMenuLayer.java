package it.unibo.unori.view.layers;

import it.unibo.unori.view.layers.menus.MainMenu;

import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.character.HeroTeam;

import javax.swing.JLayeredPane;

import java.awt.Component;
import java.awt.Dimension;

/**
 *
 * A menu that can be opened in-game.
 *
 */
public class InGameMenuLayer extends Layer {
	private static final long serialVersionUID = 1L;

	private static final Dimension SIZE = MapLayer.SIZE;
    public final JLayeredPane layeredPane = new JLayeredPane();

    /**
     * Creates the in-game menu.
     */
    public InGameMenuLayer(final HeroTeam heroTeam, final Bag bag) {
        super();

        this.setOpaque(false);
        this.setBounds(0, 0, SIZE.width, SIZE.height);

        layeredPane.setPreferredSize(new Dimension(SIZE.width, SIZE.height));

        this.add(layeredPane);

        layeredPane.add(new MainMenu(bag, layeredPane, null, 5, 5));
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