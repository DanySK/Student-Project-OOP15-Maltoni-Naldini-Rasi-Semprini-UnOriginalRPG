package it.unibo.unori.controller.action;

import java.awt.event.ActionEvent;
import java.util.Optional;

import javax.swing.AbstractAction;

import it.unibo.unori.controller.Controller;
import it.unibo.unori.controller.SingletonStateMachine;
import it.unibo.unori.controller.state.MapState;
import it.unibo.unori.model.menu.DialogueInterface;

public class InteractAction extends AbstractAction {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -5686132045447650426L;
    
    private Optional<DialogueInterface> currentDialogue;
    private final Controller controller;

    /**
     * Default constructor.
     */
    public InteractAction() {
        super();
        currentDialogue = Optional.empty();
        this.controller = SingletonStateMachine.getController();
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        if (this.controller.getCurrentStateClass().isInstance(MapState.class)) {
            final MapState currentState = (MapState) this.controller.getCurrentState();
            if (this.currentDialogue.isPresent()) {
                if (this.currentDialogue.get().isOver()) {
                    // ((MapLayer) currentState.getLayer()).hideDialog();
                    this.currentDialogue = Optional.empty();
                }
            } else {
                this.currentDialogue = Optional.of(currentState.interact());
            }
            // ((MapLayer) currentState.getLayer()).showDialogue(this.currentDialogue.get().showNext());
        }
        
    }
}