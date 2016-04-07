package it.unibo.unori.model.maps;

import it.unibo.unori.model.maps.exceptions.BlockedPathException;
import it.unibo.unori.model.menu.DummyMenu;
/**
 * Implementation for the Party interface.
 * Adds new methods to regulate some parameters, which the controller
 * doesn't have to know about.
 */
public class PartyImpl implements Party {

    /**
     * 
     */
    private static final long serialVersionUID = 5418844472988090234L;

    @Override
    public Party getParty() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCurrentPosition(int posX, int posY) throws IllegalArgumentException {
        // TODO Auto-generated method stub

    }

    @Override
    public void setCurrentMap(GameMap map) {
        // TODO Auto-generated method stub

    }

    @Override
    public GameMap getCurrentGameMap() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setCurrentFrame(Object frame) {
        // TODO Auto-generated method stub

    }

    @Override
    public Object getCurrentFrame() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void moveParty(CardinalPoints direction) throws BlockedPathException {
        // TODO Auto-generated method stub

    }

    @Override
    public DummyMenu interact() {
        // TODO Auto-generated method stub
        return null;
    }

}
