package it.unibo.unori.controller.state;

import it.unibo.unori.model.battle.Battle;
import it.unibo.unori.model.battle.BattleImpl;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.maps.Party;
import it.unibo.unori.view.layers.BattleLayer;

/**
 * This GameState models the state of battle with some encountered monsters.
 */
public class BattleState extends AbstractGameState {
    /**
     * The maximum number of enemies that could take part of a battle.
     */
    public static final int MAX_NUMBER_OF_FOES = 4;
    private final Battle battleModel;
    private int currentTurn;

    /**
     * Default constructor. It instantiates a new battle with given foes.
     * 
     * @param party
     *            the team of heroes in the party
     * @param foes
     *            the team of enemies that the heroes must defeat
     * @param bag
     *            the bag containing the items of the party
     */
    public BattleState(final HeroTeam party, final FoeSquad foes, final Bag bag) {
        super(new BattleLayer(party, foes, bag));
        this.battleModel = new BattleImpl(party, foes, bag);
    }

    /**
     * This constructor takes everything from a party instance instead of from separates objects.
     * 
     * @param party
     *            the party containing the team of heroes and the bag
     * @param foes
     *            the team of enemies that the heroes must defeat
     */
    public BattleState(final Party party, final FoeSquad foes) {
        this(party.getHeroTeam(), foes, party.getPartyBag());
    }

    /**
     * This method return the battle model of the current state. Useful for actions and action listeners.
     * 
     * @return the battle model
     */
    public Battle getModel() {
        return this.battleModel;
    }

}
