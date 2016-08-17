package it.unibo.unori.model.maps;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.HeroTeamImpl;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.BagImpl;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.maps.cell.Cell;
import it.unibo.unori.model.maps.cell.CellState;
import it.unibo.unori.model.maps.cell.FoeCellImpl;
import it.unibo.unori.model.maps.exceptions.BlockedPathException;
import it.unibo.unori.model.maps.exceptions.NoKeyFoundException;
import it.unibo.unori.model.maps.exceptions.NoMapFoundException;
import it.unibo.unori.model.maps.exceptions.NoNPCFoundException;
import it.unibo.unori.model.maps.exceptions.NoObjectFoundException;
import it.unibo.unori.model.menu.Dialogue;
import it.unibo.unori.model.menu.DialogueInterface;

/**
 * SingletonParty is a class to make the Party interface match the Singleton
 * Pattern. SingletonParty has a Party object inside, which can be given with
 * the GetParty methods SingletonParty is a final class in order to not be
 * extended.
 */
public final class SingletonParty {

    private static Party party;

    private SingletonParty() {

    }

    /**
     * Method to get the party instance inside the class.
     * Synchronized(SingletonParty.class)construct is added in order to prevent
     * multiple allocation in a multi-thread system.
     * 
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
     * Load a existent party.
     * 
     * @param p
     *            party to load
     */
    public static void loadParty(final Party p) {
        party = p;
    }

    /**
     * PartyImpl is a class to implements the methods of Party interface.
     * PartyImpl is declared private to help encapsulation : the only way to use
     * an instance of partyImpl is by the methods getParty of the SingletonParty
     * class
     *
     */
    private static final class PartyImpl implements Party {

        /**
         * 
         */
        private static final long serialVersionUID = 5037069095324356034L;
        private Position currentPosition;
        private GameMap currentMap;
        private  Map<CardinalPoints, String> frames;
        private CardinalPoints orientation;
        private final Bag partyBag;
        private final HeroTeam heroteam;

        /**
         * Constructor for PartyImpl, set a standard map, position, cell and
         * frame.
         */
        PartyImpl() {
            this.currentMap = new GameMapImpl();
            this.currentPosition = this.currentMap.getInitialCellPosition();
            this.orientation = CardinalPoints.NORTH;
            this.partyBag = new BagImpl();
            this.heroteam = new HeroTeamImpl();
            this.frames = new HashMap<>();
        }

        private boolean framesCheck(final Map<CardinalPoints, String> frames) {
            return frames.keySet().containsAll(
                    Arrays.asList(CardinalPoints.values()));
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
        public void setFrames(final Map<CardinalPoints, String> frames)
                                        throws IllegalArgumentException {
            if (!this.framesCheck(frames)) {
                throw new IllegalArgumentException();
            }
            this.frames = frames;
        }

        @Override
        public String getCurrentFrame() {
            return this.frames.get(orientation);
        }


        @Override
        public Bag getPartyBag() {
            return this.partyBag;
        }

        public HeroTeam getHeroTeam() {
            return this.heroteam;
        }

        @Override
        public void moveParty(final CardinalPoints direction) throws BlockedPathException {
            this.orientation = direction;
            final Position nextPosition = new Position(this.currentPosition.getPosX() + this.orientation.getXSkidding(),
                    this.currentPosition.getPosY() + this.orientation.getYSkidding());
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
        public DialogueInterface interact() {
            final Position pos = new Position(this.currentPosition.getPosX() + this.orientation.getXSkidding(),
                    this.currentPosition.getPosY() + this.orientation.getYSkidding());
            final Cell c = this.currentMap.getCell(pos);
            try {
                return new Dialogue(FoeCellImpl.ENC + c.getBoss().getName());
            } catch (IllegalStateException e) {
            try {
                return c.talkToNpc();
            } catch (NoNPCFoundException e0) {
                try {
                    this.partyBag.storeItem(c.getObject());
                    this.currentMap.replaceCell(pos, currentPosition);
                    return new Dialogue("Che fortuna! Hai trovato " + c.getObject().getName());
                } catch (NoObjectFoundException e1) {
                    try {
                        final Item i = c.openChest(partyBag);
                        this.partyBag.storeItem(i);
                        return new Dialogue("Hai aperto un baule! Hai trovato " + i.getName());
                    } catch (NoKeyFoundException e2) {
                        return new Dialogue("Non hai chiavi nell'inventario! "
                                + "Cerca una chiave e ritorna");
                    } catch (Exception e3) {
                        return new Dialogue("Non c'è niente qui, meglio andare!");
                    }
                }
            }
        }
       }
    }

}
