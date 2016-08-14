package it.unibo.unori.controller.state;

import java.util.List;

import it.unibo.unori.model.battle.Battle;
import it.unibo.unori.model.battle.BattleImpl;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.HeroTeamImpl;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.maps.GameMap;

/**
 * This GameState models the state of battle with some encountered monsters.
 */
public class BattleState extends AbstractGameState {
    private final Battle battleModel;
    private int currentTurn; 

    /**
     * Default constructor. It instantiates a new battle with given foes.
     * @param party the list of the alive heroes in the party
     * @param map the map in which the battle started; used for the background
     * @param foes the list of enemies that the heroes must defeat
     * @param bag the bag containing the items of the party
     */
    public BattleState(final List<Hero> party, final GameMap map, final FoeSquad foes, final Bag bag) {
        super(null/* new BattleLayer() TODO */);
        this.battleModel = new BattleImpl(new HeroTeamImpl(party), null, bag); // TODO null will be replaced by Battle getter
    }
    
    public BattleState(final HeroTeam party, final GameMap map, final FoeSquad foes, final Bag bag) {
        super(/* new BattleLayer() TODO */ null);
        this.battleModel = new BattleImpl(party, null, bag); // TODO null will be replaced by Battle getter
    }

    /*
     * Useful only if Party will, in the future, incorporate also the informations on Heroes and Items

    public BattleState(Party party, List<Foe> foes) {
        this(null, null, foes, null); // TODO nulls will be replaced by Party getters
    }

    */

}
