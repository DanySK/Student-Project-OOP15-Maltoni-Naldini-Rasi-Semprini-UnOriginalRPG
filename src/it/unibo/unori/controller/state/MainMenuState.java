package it.unibo.unori.controller.state;

import it.unibo.unori.view.View;
import it.unibo.unori.view.layers.MainMenu;

/**
 * This GameState models the first state opened by the controller: the main menu.
 */
public class MainMenuState implements GameState {
    private final View mainMenuView;
    private final Object mainMenuModel; // TODO

    /**
     * Default constructor; it instantiates a new main menu GameState.
     */
    public MainMenuState() {
        this.mainMenuView = new View();
        this.mainMenuView.push(new MainMenu());

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
    public void render() {
        // TODO Not final implementation of the method: it will be when Model and view implementations are ready
        this.mainMenuView.setVisible(true);
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

    /**
     * {@inheritDoc}. For this state, it is probably useless.
     */
    @Override
    public View getView() {
        // TODO Auto-generated method stub
        return this.mainMenuView; // TODO probably it is better to use a defensive copy
    }

}
