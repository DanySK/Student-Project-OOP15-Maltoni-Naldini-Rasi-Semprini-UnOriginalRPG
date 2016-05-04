package it.unibo.unori.controller.state;

import it.unibo.unori.view.View;
import it.unibo.unori.view.layers.Layer;
import it.unibo.unori.view.layers.menus.MainMenuLayer;

/**
 * This GameState models the first state opened by the controller: the main menu.
 */
public class MainMenuState extends AbstractGameState {
    private final Object mainMenuModel; // TODO

    /**
     * Default constructor; it instantiates a new main menu GameState.
     */
    public MainMenuState() {
        super(new MainMenuLayer());

        /*
         * Potrebbe essere una buona opzione poter passare i bottoni alla view tramite una strategy esterna, magari
         * fornita dal model
         */
        this.mainMenuModel = new Object(); // TODO
    }

    @Override
    public void update(final double elapsedTime) {
        // TODO Model implementation needed
    }

    @Override
    public void onEnter() {
        // TODO Auto-generated method stub

        // TODO Probably useless method
    }

    @Override
    public void onExit() {
        // TODO Auto-generated method stub

        // TODO Probably useless method
    }
}
