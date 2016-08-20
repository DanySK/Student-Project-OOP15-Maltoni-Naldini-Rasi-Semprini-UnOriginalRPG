package it.unibo.unori.controller.actionlistener;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * This ActionListener should be associated to save buttons to save the game.
 */
public class SaveActionListener extends AbstractUnoriActionListener {

    @Override
    public void actionPerformed(final ActionEvent event) {
        try {
            this.getController().saveGame();
        } catch (IOException e) {
            this.getController().showError(e.getMessage());
        }
    }

}
