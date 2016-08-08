package it.unibo.unori.controller.state;

import it.unibo.unori.controller.actionlistener.CharacterSelectionActionListener;
import it.unibo.unori.model.character.HeroTeamImpl;
import it.unibo.unori.view.Button;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;
import it.unibo.unori.view.layers.CharacterSelectionLayer;

public class CharacterSelectionState extends AbstractGameState {

    public CharacterSelectionState() throws SpriteNotFoundException {
        super(new CharacterSelectionLayer(HeroTeamImpl.MAXHERO, CharacterSelectionState.getButton()));
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update(double elapsedTime) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onEnter() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onExit() {
        // TODO Auto-generated method stub

    }
    
    private static Button getButton() {
        Button button = new Button("OK");
        button.addActionListener(new CharacterSelectionActionListener());
        return button;
    }

}
