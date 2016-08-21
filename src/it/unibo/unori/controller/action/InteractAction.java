package it.unibo.unori.controller.action;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.Optional;

import it.unibo.unori.controller.exceptions.UnexpectedStateException;
import it.unibo.unori.controller.state.BattleState;
import it.unibo.unori.controller.state.MapState;
import it.unibo.unori.model.character.FoeSquadImpl;
import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.Position;
import it.unibo.unori.model.maps.SingletonParty;
import it.unibo.unori.model.maps.cell.FoeCellImpl;
import it.unibo.unori.model.menu.DialogueInterface;
import it.unibo.unori.view.exceptions.SpriteNotFoundException;
import it.unibo.unori.view.layers.MapLayer;

/**
 * Action that should be linked with interaction button(s). This makes the
 * player interact with cells near him/her.
 */
public class InteractAction extends AbstractUnoriAction {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -5686132045447650426L;

    private Optional<DialogueInterface> currentDialogue;
    private String currentString;

    /**
     * Default constructor.
     */
    public InteractAction() {
        super();
        currentString = "";
        currentDialogue = Optional.empty();
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        System.out.println("ActionListener chiamato");
        if (MapState.class.isInstance(this.getController().getCurrentState())) {
            final MapState currentState = (MapState) this.getController().getCurrentState();
            final MapLayer currentLayer = (MapLayer) currentState.getLayer();
            // If another dialogue is open, it tries to next line ...
            if (this.currentDialogue.isPresent()) {
                // ... if it can't, it closes the dialogue ...
                if (this.currentDialogue.get().isOver()) {
                    (currentLayer).hideDialogue();
                    this.currentDialogue = Optional.empty();
                    if (this.currentString.startsWith(FoeCellImpl.ENC)) {
                        final Party party = SingletonParty.getParty();
                        try {
                            this.getController().getStack()
                                    .pushAndRender(new BattleState(party, new FoeSquadImpl(party.getCurrentGameMap()
                                            .getCell(new Position(
                                                    party.getCurrentPosition().getPosX()
                                                            + party.getOrientation().getXSkidding(),
                                                    party.getCurrentPosition().getPosY()
                                                            + party.getOrientation().getYSkidding()))
                                            .getBoss())));
                        } catch (SpriteNotFoundException e) {
                            this.getController().showError(e.getMessage());
                        }
                    }
                    this.currentString = "";
                    // ... else shows the next line
                } else {
                    currentString = this.currentDialogue.get().showNext();
                    (currentLayer).showDialogue(currentString);
                }
                // If there is no previous dialogue open, it loads a new one and
                // shows it
            } else {
                this.currentDialogue = Optional.of(currentState.interact());
                currentString = this.currentDialogue.get().showNext();
                (currentLayer).showDialogue(currentString);
            }

            if (currentState.checkMapChanges()) {
                try {
                    currentLayer.changeMap(currentState.getMap().getFrames(), new Point(
                            currentState.getCurrentPosition().getPosX(), currentState.getCurrentPosition().getPosY()));
                } catch (SpriteNotFoundException e) {
                    this.getController().showError(e.getMessage());
                }
            }
        } else if (BattleState.class.isInstance(this.getController().getCurrentState())) {
            final BattleState currentState = (BattleState) this.getController().getCurrentState();
            currentState.scrollMessage();
        } else {
            this.getController().showError(new UnexpectedStateException().getMessage());
        }

    }
}