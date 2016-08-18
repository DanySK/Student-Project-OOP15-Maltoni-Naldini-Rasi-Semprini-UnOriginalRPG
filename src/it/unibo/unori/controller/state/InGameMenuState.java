package it.unibo.unori.controller.state;

import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.maps.Party;
import it.unibo.unori.model.maps.SingletonParty;
import it.unibo.unori.view.layers.InGameMenuLayer;

/**
 * This GameState models the state of an in-game menu opened during exploration.
 */
public class InGameMenuState extends AbstractGameState {
    private final Party party;

    /**
     * Default constructor. It places a new layer with the in-game pop-up menu on the {@link it.unibo.unori.view.View}
     * of the {@link it.unibo.unori.controller.state.GameState} below in the stack.
     */
    public InGameMenuState() {
        super(new InGameMenuLayer(SingletonParty.getParty().getHeroTeam(), SingletonParty.getParty().getPartyBag()));
        this.party = SingletonParty.getParty();
    }

    /**
     * Returns the hero team.
     * @return the hero team
     */
    public HeroTeam getTeam() {
        return this.party.getHeroTeam();
    }

    /**
     * Returns the bag of the party.
     * @return the bag
     */
    public Bag getBag() {
        return this.party.getPartyBag();
    }

}
