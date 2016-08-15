package it.unibo.unori.controller.actionlistener;

import java.awt.event.ActionEvent;

public class CharacterSelectionActionListener extends AbstractUnoriActionListener {

    @Override
    public void actionPerformed(final ActionEvent event) {
        this.getController().startGame();
    }

}