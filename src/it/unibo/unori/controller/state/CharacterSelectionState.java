package it.unibo.unori.controller.state;

import it.unibo.unori.controller.actionlistener.CharacterSelectionActionListener;
import it.unibo.unori.model.character.HeroTeamImpl;
import it.unibo.unori.view.Button;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;
import it.unibo.unori.view.layers.CharacterSelectionLayer;

/**
 * This constructor models the state that makes the player choose which job and name each hero should have.
 */
public class CharacterSelectionState extends AbstractGameState {

    /**
     * Default constructor.
     * 
     * @throws SpriteNotFoundException
     *             if some sprites were not found
     */
    public CharacterSelectionState() throws SpriteNotFoundException {
        super(new CharacterSelectionLayer(HeroTeamImpl.MAXHERO, CharacterSelectionState.getButton()));
    }

    private static Button getButton() {
        final Button button = new Button("OK");
        button.addActionListener(new CharacterSelectionActionListener());
        return button;
    }

}
