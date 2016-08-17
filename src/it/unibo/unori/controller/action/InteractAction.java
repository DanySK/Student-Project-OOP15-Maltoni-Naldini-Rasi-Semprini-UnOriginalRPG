package it.unibo.unori.controller.action;

import java.awt.event.ActionEvent;
import java.util.Optional;

import javax.swing.AbstractAction;

import it.unibo.unori.controller.Controller;
import it.unibo.unori.controller.SingletonStateMachine;
import it.unibo.unori.controller.exceptions.NotValidStateException;
import it.unibo.unori.controller.state.DialogState.ErrorSeverity;
import it.unibo.unori.controller.state.MapState;
import it.unibo.unori.model.menu.DialogueInterface;
import it.unibo.unori.view.layers.MapLayer;

/**
 * Action that should be linked with interaction button(s). This makes the
 * player interact with cells near him/her.
 */
public class InteractAction extends AbstractAction {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -5686132045447650426L;

    private Optional<DialogueInterface> currentDialogue; // TODO maybe it is more correct to put it inside the State, TODO check
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
        if (MapState.class.isInstance(this.controller.getCurrentState())) {
            final MapState currentState = (MapState) this.controller.getCurrentState();
            // If another dialogue is open, it tries to  next line ...
            if (this.currentDialogue.isPresent()) {
                // ... if it can't, it closes the dialogue ...
                if (this.currentDialogue.get().isOver()) {
                    ((MapLayer) currentState.getLayer()).hideDialogue();
                    this.currentDialogue = Optional.empty();
                // ... else shows the next line
                } else {
                    ((MapLayer) currentState.getLayer()).showDialogue(this.currentDialogue.get().showNext());
                }
            // If there is no previous dialogue open, it loads a new one and shows it
            } else {
                this.currentDialogue = Optional.of(currentState.interact());
                ((MapLayer) currentState.getLayer()).showDialogue(this.currentDialogue.get().showNext());
            }
        } else {
            System.out.println("Error current state is not MapState in InteractAction:");
            this.controller.showError(new NotValidStateException().getMessage(), ErrorSeverity.SERIUOS);
        }

    }
}