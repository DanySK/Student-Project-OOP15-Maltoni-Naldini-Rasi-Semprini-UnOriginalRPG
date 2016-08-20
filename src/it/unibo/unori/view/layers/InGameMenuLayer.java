package it.unibo.unori.view.layers;

import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.view.layers.common.MenuStack;
import it.unibo.unori.view.layers.ingame.InGameMainMenu;

import java.awt.Component;
import java.awt.Dimension;

/**
 *
 * A menu that can be opened in-game.
 *
 */
public class InGameMenuLayer extends Layer {
    private static final long serialVersionUID = 1L;

    private static final int BORDER = 5;
    private static final Dimension SIZE = MapLayer.SIZE;

    /**
     * Creates the in-game menu.
     *
     * @param heroTeam
     *            the heroes team
     * @param bag
     *            the party bag
     */
    public InGameMenuLayer(final HeroTeam heroTeam, final Bag bag) {
        super();

        this.setOpaque(false);
        this.setBounds(0, 0, SIZE.width, SIZE.height);

        final MenuStack inGameStack = new MenuStack();

        inGameStack.setPreferredSize(new Dimension(SIZE.width, SIZE.height));

        this.add(inGameStack);

        inGameStack.push(new InGameMainMenu(inGameStack, heroTeam, bag, BORDER, BORDER));
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
