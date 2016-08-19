package it.unibo.unori.controller.action;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.Optional;

import it.unibo.unori.controller.exceptions.UnexpectedStateException;
import it.unibo.unori.controller.state.MapState;
import it.unibo.unori.model.menu.DialogueInterface;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;
import it.unibo.unori.view.layers.MapLayer;

/**
 * Action that should be linked with interaction button(s). This makes the player interact with cells near him/her.
 */
public class InteractAction extends AbstractUnoriAction {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -5686132045447650426L;

    private Optional<DialogueInterface> currentDialogue;

    /**
     * Default constructor.
     */
    public InteractAction() {
        super();
        currentDialogue = Optional.empty();
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (MapState.class.isInstance(this.getController().getCurrentState())) {
            final MapState currentState = (MapState) this.getController().getCurrentState();
            final MapLayer currentLayer = (MapLayer) currentState.getLayer();
            // If another dialogue is open, it tries to next line ...
            if (this.currentDialogue.isPresent()) {
                // ... if it can't, it closes the dialogue ...
                if (this.currentDialogue.get().isOver()) {
                    (currentLayer).hideDialogue();
                    this.currentDialogue = Optional.empty();
                    // ... else shows the next line
                } else {
                    (currentLayer).showDialogue(this.currentDialogue.get().showNext());
                }
                // If there is no previous dialogue open, it loads a new one and shows it
            } else {
                this.currentDialogue = Optional.of(currentState.interact());
                (currentLayer).showDialogue(this.currentDialogue.get().showNext());
            }

            if (currentState.checkMapChanges()) {
                try {
                    currentLayer.changeMap(currentState.getMap().getFrames(),
                                    new Point(currentState.getCurrentPosition().getPosX(),
                                                    currentState.getCurrentPosition().getPosY()));
                } catch (SpriteNotFoundException e) {
                    this.getController().showError(e.getMessage());
                }
            }
        } else {
            this.getController().showError(new UnexpectedStateException().getMessage());
        }

    }
}