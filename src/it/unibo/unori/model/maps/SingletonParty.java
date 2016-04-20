package it.unibo.unori.model.maps;

import it.unibo.unori.model.maps.cell.Cell;
import it.unibo.unori.model.maps.cell.CellState;
import it.unibo.unori.model.maps.exceptions.BlockedPathException;
import it.unibo.unori.model.maps.exceptions.NoMapFoundException;
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

    /**
     * PartyImpl is a class to implements the methods of Party interface.
     * PartyImpl is declared private to help encapsulation : the only way to use
     * an instance of partyImpl is by the methods getParty of the SingletonParty class
     *
     */
    private static final class PartyImpl implements Party {

        /**
         * 
         */
        private static final long serialVersionUID = 5037069095324356034L;
        private Position currentPosition;
        private GameMap currentMap;
        private Object frame;
        private CardinalPoints orientation;

        /**
         * Constructor for PartyImpl, set a standard map, position, cell and frame.
         */
            PartyImpl() {
            this.currentMap = new GameMapImpl();
            this.currentPosition = this.currentMap.getInitialCellPosition();
            this.frame = new Object();
            this.orientation = CardinalPoints.NORTH;
        }

        @Override
        public Position getCurrentPosition() {
            return this.currentPosition;
        }


        @Override
        public void setCurrentMap(final GameMap map) {
            this.currentMap = map;
            this.currentPosition = this.currentMap.getInitialCellPosition();
        }

        @Override
        public GameMap getCurrentGameMap() {
            return this.currentMap;
        }

        @Override
        public void setCurrentFrame(final Object frame) {
            this.frame = frame;
        }

        @Override
        public Object getCurrentFrame() {
            return this.frame;
        }

        @Override
        public void moveParty(final CardinalPoints direction) throws BlockedPathException {
            this.orientation = direction;
            final Position nextPosition = new Position(this.currentPosition.getPosX() 
                                                   + this.orientation.getXSkidding(), 
                                                 this.currentPosition.getPosY()
                                                   + this.orientation.getYSkidding());
            final Cell tempCell = this.currentMap.getCell(nextPosition);
            if (tempCell.getState().equals(CellState.BLOCKED)) {
                try {
                    this.setCurrentMap(tempCell.getCellMap());
                } catch (NoMapFoundException e) {
                    throw new BlockedPathException(e);
                }
            } else {
                this.currentPosition = nextPosition;
            }

        }

        @Override
        public DummyMenu interact() {
            // TODO Auto-generated method stub
            return null;
        }

    }

}
