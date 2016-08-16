package it.unibo.unori.view.layers;

import it.unibo.unori.view.View;
import it.unibo.unori.view.layers.menus.MainMenu;

import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.character.HeroTeam;

import javax.swing.JLayeredPane;

import java.awt.Dimension;

/**
 *
 * A menu that can be opened in-game.
 *
 */
public class InGameMenuLayer extends Layer {
    private static final Dimension SIZE = View.SIZE;

    public final Bag bag;
    public final JLayeredPane layeredPane = new JLayeredPane();

    /**
     * Creates the in-game menu.
     */
    public InGameMenuLayer(final HeroTeam heroTeam, final Bag bag) {
        super();

        this.bag = bag;

        this.setOpaque(false);
        this.setBounds(0, 0, SIZE.width, SIZE.height);

        layeredPane.setPreferredSize(new Dimension(SIZE.width, SIZE.height));

        final MainMenu mainMenu = new MainMenu();
        layeredPane.add(mainMenu);

        this.add(layeredPane);
    }
}