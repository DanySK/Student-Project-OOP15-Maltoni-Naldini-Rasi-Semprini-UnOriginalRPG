package it.unibo.unori.controller.state;

import java.util.List;

import it.unibo.unori.model.battle.Battle;
import it.unibo.unori.model.battle.BattleImpl;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.maps.GameMap;
import it.unibo.unori.view.View;

/**
 * This GameState models the state of battle with some encountered monsters.
 */
public class BattleState implements GameState {
    private View battleView;
    private Battle battleModel;

    /**
     * Default constructor. It instantiates a new battle with given foes.
     * @param party the list of the heroes in the party
     * @param map the map in which the battle started; used for the background
     * @param foes the list of enemies that the heroes must defeat
     * @param bag the bag containing the items of the party
     */
    public BattleState(final List<Hero> party, final GameMap map, final List<Foe> foes, final Bag bag) {
        this.battleView = new View();
        this.battleModel = new BattleImpl(party, null, bag); // TODO null will be replaced by Battle getter

        this.battleView.push(null); // TODO null will be replaced by a Battle Menu Layer
    }

    /*
     * Useful only if Party will, in the future, incorporate also the informations on Heroes and Items

    public BattleState(Party party, List<Foe> foes) {
        this(null, null, foes, null); // TODO nulls will be replaced by Party getters
    }

    */

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double elapsedTime) {
        // TODO Auto-generated method stub

    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        this.battleView.setVisible(true);
    }

    @Override
    public void onEnter() {
        // TODO Auto-generated method stub

        // TODO Probably useless method
    }

    @Override
    public void onExit() {
        // TODO Auto-generated method stub

        // TODO Probably useless method
    }

    /**
     * {@inheritDoc}. For this state, it is probably useless.
     */
    @Override
    public View getView() {
        // TODO Auto-generated method stub
        return this.battleView; // TODO probably it is better to use a defensive copy
    }

}
