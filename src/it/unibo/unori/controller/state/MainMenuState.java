package it.unibo.unori.controller.state;

import it.unibo.unori.model.menu.DummyMenu;
import it.unibo.unori.view.layers.MainMenuLayer;

/**
 * This GameState models the first state opened by the controller: the main menu.
 */
public class MainMenuState extends AbstractGameState {
    private final DummyMenu mainMenuModel; // TODO

    /**
     * Default constructor; it instantiates a new main menu GameState.
     */
    public MainMenuState() {
        super(new MainMenuLayer(null)); // TODO

        /*
         * Potrebbe essere una buona opzione poter passare i bottoni alla view tramite una strategy esterna, magari
         * fornita dal model
         */
        this.mainMenuModel = new DummyMenu(); // TODO
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
