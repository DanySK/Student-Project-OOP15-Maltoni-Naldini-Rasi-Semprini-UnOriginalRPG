package it.unibo.unori.controller.state;

import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.maps.SingletonParty;
import it.unibo.unori.view.layers.InGameMenuLayer;

/**
 * This GameState models the state of an in-game menu opened during exploration.
 */
public class InGameMenuState extends AbstractGameState {

    /**
     * Default constructor. It places a new layer with the in-game pop-up menu on the {@link it.unibo.unori.view.View}
     * of the {@link it.unibo.unori.controller.state.GameState} below in the stack.
     */
    public InGameMenuState() {
        this(SingletonParty.getParty().getHeroTeam(), SingletonParty.getParty().getPartyBag());
        // TODO check
    }
    
    public InGameMenuState(final HeroTeam party, final Bag bag) {
        super(new InGameMenuLayer(party, bag));
        // TODO check
    }

}
