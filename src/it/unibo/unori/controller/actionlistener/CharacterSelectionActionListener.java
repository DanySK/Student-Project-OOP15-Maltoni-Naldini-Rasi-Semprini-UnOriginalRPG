package it.unibo.unori.controller.actionlistener;

import java.awt.event.ActionEvent;

/**
 * This ActionListener should be assigned to the OK button of a character selection to call the controller and make it
 * start the actual game.
 */
public class CharacterSelectionActionListener extends AbstractUnoriActionListener {

    @Override
    public void actionPerformed(final ActionEvent event) {
        this.getController().startGame();
    }

}