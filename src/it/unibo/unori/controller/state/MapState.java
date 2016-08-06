package it.unibo.unori.controller.state;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.swing.AbstractAction;
import javax.swing.Action;

import it.unibo.unori.controller.Controller;
import it.unibo.unori.controller.SingletonStateMachine;
import it.unibo.unori.controller.exceptions.NotValidStateException;
import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.SingletonParty;
import it.unibo.unori.model.maps.exceptions.BlockedPathException;
import it.unibo.unori.model.menu.DialogueInterface;
import it.unibo.unori.view.layers.MapLayer;

/**
 * This GameState models the state of exploring a map (world, town or dungeon
 * room).
 */
public class MapState extends AbstractGameState {
    private final Party party;

    /**
     * Default constructor.
     * 
     * @param map
     *            the map to start from
     */
    public MapState(final GameMap map) {
        super(new MapLayer(/*(Map<Party.CardinalPoints, Action>) (() -> {
            final Map<Party.CardinalPoints, Action> returnMap = new HashMap<>();
            
            for (Party.CardinalPoints direction : Party.CardinalPoints.values()) {
                returnMap.put(direction, new MapState.MoveAction(direction));
            }
            
            return returnMap;
        }), new MapState.InteractAction(), new MapState.OpenMenuAction(), String[][] paths*/ null));
        party = SingletonParty.getParty();
        party.setCurrentMap(map);
        // TODO
    }

    @Override
    public void update(final double elapsedTime) {
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

    /**
     * Moves the party in the specified direction. Returns if it is possible.
     * 
     * @param direction
     *            the direction to move to
     * @return true if the movement was possible and was done
     */
    public boolean moveParty(final Party.CardinalPoints direction) {
        try {
            this.party.moveParty(direction);
        } catch (BlockedPathException e) {
            return false;
        }
        return true;
    }

    /**
     * Opens a dialogue from the model.
     * @return the dialogue from the model
     */
    public DialogueInterface interact() {
        return this.party.interact();
    }

    /**
     * Action that should be linked with movement dedicated buttons as WASD or
     * UP, DOWN, LEFT, RIGHT. This uses the controller to query the model if the
     * movement is possible: if yes, does the movement both on model and view,
     * if not, does nothing.
     */
    public class MoveAction extends AbstractAction {
        /**
         * Generated serial version UID.
         */
        private static final long serialVersionUID = 781647080297844098L;

        private final Party.CardinalPoints direction;

        /**
         * Default constructor.
         * 
         * @param direction
         *            the direction to move to
         */
        public MoveAction(final Party.CardinalPoints direction) {
            super();
            this.direction = direction;
        }

        @Override
        public void actionPerformed(final ActionEvent event) {
            if (MapState.this.moveParty(this.direction)) {
                // MapState.this.getLayer().moveParty(this.direction);
            }
        }
    }

    /**
     * Action that should be linked with interaction button(s). This makes the
     * player interact with cells near him/her.
     */
    public class InteractAction extends AbstractAction {
        /**
         * Generated serial version UID.
         */
        private static final long serialVersionUID = -6714062831714869023L;

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
            if (this.currentDialogue.isPresent()) {
                if (this.currentDialogue.get().isOver()) {
                    // ((MapLayer) MapState.this.getLayer()).hideDialog();
                    this.currentDialogue = Optional.empty();
                }
            } else {
                this.currentDialogue = Optional.of(MapState.this.interact());
            }
            // ((MapLayer)
            // MapState.this.getLayer()).showDialogue(this.currentDialogue.get().showNext());
        }
    }
    
    /**
     * Action that should be linked to menu dedicated buttons as for example ESC.
     * This will open the menu if possible.
     */
    // TODO It's not necessary to put it here
    public /*static*/ class OpenMenuAction extends AbstractAction {
        /**
         * Generated serial version UID.
         */
        private static final long serialVersionUID = 8283970384379034094L;
        
        private final Controller controller;

        /**
         * Default constructor.
         */
        public OpenMenuAction() {
            super();
            this.controller = SingletonStateMachine.getController();
        }

        @Override
        public void actionPerformed(final ActionEvent event) {
            if (this.controller.getCurrentStateClass().isInstance(MapState.class)) {
                try {
                    this.controller.openMenu();
                } catch (NotValidStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                // TODO
            }
        }

    }
}
