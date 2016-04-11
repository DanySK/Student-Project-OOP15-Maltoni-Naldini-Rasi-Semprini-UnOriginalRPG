package it.unibo.unori.model.maps;

import it.unibo.unori.model.maps.exceptions.BlockedPathException;
import it.unibo.unori.model.menu.DummyMenu;

/**
 * SingletonParty is a class to make the Party interface match the Singleton Pattern.
 * SingletonParty has a Party object inside, which can be given with the GetParty
 * methods
 * SingletonParty is a final class in order to not be extended.
 */
public final class SingletonParty {

    private static Party party;

    private SingletonParty() {

    }
    /**
     * Method to get the party instance inside the class.
     * Synchronized(SingletonParty.class)construct is added in order to prevent multiple
     * allocation in a multi-thread system.
     * @return the single instance of Party created.
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

        /**
         * 
         */
        private static final long serialVersionUID = 5037069095324356034L;

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
