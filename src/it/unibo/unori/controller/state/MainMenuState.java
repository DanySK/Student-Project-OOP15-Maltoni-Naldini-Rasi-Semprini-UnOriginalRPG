package it.unibo.unori.controller.state;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import it.unibo.unori.controller.actionlistener.MainMenuActionListener;
import it.unibo.unori.controller.json.JsonFileManager;
import it.unibo.unori.view.Button;
import it.unibo.unori.view.layers.MainMenuLayer;

/**
 * This GameState models the first state opened by the controller: the main
 * menu.
 */
public class MainMenuState extends AbstractGameState {
    /*
     * A model for this state is not needed: everything this state does are
     * modeled by controller classes.
     */

    /**
     * Default constructor; it instantiates a new main menu GameState.
     */
    public MainMenuState() {
        super(new MainMenuLayer(MainMenuState.getButtons()));
    }

    private static List<Button> getButtons() {
        final ActionListener listener = new MainMenuActionListener();
        final List<Button> returnList = new ArrayList<>();

        final Button newGame = new Button("Nuova partita");
        newGame.addActionListener(listener);
        newGame.setActionCommand(MainMenuActionListener.NEW_GAME);
        returnList.add(newGame);

        final Button loadGame = new Button("Carica parita");
        loadGame.addActionListener(listener);
        loadGame.setActionCommand(MainMenuActionListener.LOAD_GAME);
        loadGame.setEnabled(new File(System.getProperty("user.home") + JsonFileManager.SAVE_FILE).isFile()
                && new File(System.getProperty("user.home") + JsonFileManager.MAP_TYPE).isFile());
        returnList.add(loadGame);

        final Button closeGame = new Button("Esci");
        closeGame.addActionListener(listener);
        closeGame.setActionCommand(MainMenuActionListener.CLOSE_GAME);
        returnList.add(closeGame);

        return returnList;
    }
}
