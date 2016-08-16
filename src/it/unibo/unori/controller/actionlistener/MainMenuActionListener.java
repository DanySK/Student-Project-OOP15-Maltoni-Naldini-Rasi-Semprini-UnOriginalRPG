package it.unibo.unori.controller.actionlistener;

import java.awt.event.ActionEvent;

import it.unibo.unori.controller.exceptions.UnknownButtonException;

/**
 * ActionListener for the Buttons in the MainMenuLayer of a MainMenuState.
 */
public class MainMenuActionListener extends AbstractUnoriActionListener {
    /**
     * Default ActionCommand to create a new game.
     */
    public static final String NEW_GAME = "New game";
    /**
     * Default ActionCommand to load a previously saved game.
     */
    public static final String LOAD_GAME = "Load game";
    /**
     * Default ActionCommand to close the game.
     */
    public static final String CLOSE_GAME = "Close game";

    @Override
    public void actionPerformed(final ActionEvent event) {
        final String command = event.getActionCommand();
        try {
            switch (command) {
            case NEW_GAME:
                this.getController().newGame();
                break;
            case LOAD_GAME:
                this.getController().loadGame();
                break;
            case CLOSE_GAME:
                this.getController().closeGame();
                break;
            default:
                throw new UnknownButtonException(command);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
