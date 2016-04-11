package it.unibo.unori.model.maps;

import it.unibo.unori.model.maps.exceptions.BlockedPathException;
import it.unibo.unori.model.menu.DummyMenu;

/**
 *Testing singleton strategy.
 */
public final class SingletonParty {

    private static Party party;

    private SingletonParty() {

    }
    /**
     * 
     * @return robbe
     */
    public static Party getParty() {
        synchronized (SingletonParty.class) {
            if (party == null) {
                party = new PartyImpl();
            }
        }
        return party;
    }

    private static class PartyImpl implements Party {

        @Override
        public Party getParty() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void setCurrentPosition(Position pos) throws IllegalArgumentException {
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
    
}
