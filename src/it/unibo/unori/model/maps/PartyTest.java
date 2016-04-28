package it.unibo.unori.model.maps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import it.unibo.unori.model.maps.Party.CardinalPoints;
import it.unibo.unori.model.maps.exceptions.BlockedPathException;
import it.unibo.unori.model.maps.exceptions.NoMapFoundException;

/**
 * Test Class for the Party object.
 *
 */
public class PartyTest {

    private final GameMapFactory mapFactory = new GameMapFactory();
    private static final int FIVE = 5; 
    private static final int MAXPOS = 99;

    /**
     * Test for the singleton strategy.
     * Check if two variables stare at the same object, changing map and position
     */
    @Test
    public void testSingletonStrategy() {
        final Party party = SingletonParty.getParty();
        final Party party2 = SingletonParty.getParty();
        party.setCurrentMap(mapFactory.getStdRoom());
        assertEquals(party.getCurrentPosition(), party2.getCurrentPosition());
        final Position pos = new Position(10, 10);
        final GameMap map = new GameMapImpl(20, 20);
        map.setInitialCellPosition(pos);
        party2.setCurrentMap(map);
        assertEquals(party2.getCurrentPosition(), map.getInitialCellPosition());
        assertTrue(party2.getCurrentPosition().equals(party.getCurrentPosition()));
    }

    /**
     * Test for the simple movements of the party.
     */
    @Test
    public void testSimpleMovements() {
        final Party party = SingletonParty.getParty();
        party.setCurrentMap(mapFactory.getStdRoom());
        assertEquals(party.getCurrentPosition(), new Position(1, 1));
        try {
            party.moveParty(CardinalPoints.SOUTH);
            party.moveParty(CardinalPoints.SOUTH);
            party.moveParty(CardinalPoints.SOUTH);
            party.moveParty(CardinalPoints.SOUTH);
            assertEquals(party.getCurrentPosition(), new Position(FIVE, 1));
            party.moveParty(CardinalPoints.EAST);
            party.moveParty(CardinalPoints.EAST);
            party.moveParty(CardinalPoints.EAST);
            assertEquals(party.getCurrentPosition(), new Position(FIVE, FIVE - 1));
            party.moveParty(CardinalPoints.WEST);
            party.moveParty(CardinalPoints.NORTH);
            party.moveParty(CardinalPoints.NORTH);
            assertEquals(party.getCurrentPosition(), new Position(3, 3));
        } catch (BlockedPathException e) {
            fail("No exception should be thrown...");
        }

    }

    /**
     * Test for the movements Exceptions.
     */
    @Test
    public void testMovementsExceptions() {
        final Party party = SingletonParty.getParty();
        party.setCurrentMap(mapFactory.getStdRoom());
        try {
            party.moveParty(CardinalPoints.NORTH);
            party.moveParty(CardinalPoints.WEST);
            fail("BlockedPathException expected!");
        } catch (BlockedPathException e) {
            System.out.println(e);
        } catch (Exception e) {
            fail("Throwed an unexpect kind of exception");
        }
    }

    /**
     * Test for switching map.
     * @throws NoMapFoundException 
     * @throws IllegalArgumentException 
     */
    @Test
    public void testLinkingMap() throws IllegalArgumentException, NoMapFoundException {
        final Party party = SingletonParty.getParty();
        party.setCurrentMap(mapFactory.getSouthLinkedMap());
        assertEquals(party.getCurrentPosition(), new Position(1, 1));
        try {
            party.moveParty(CardinalPoints.EAST);
            party.moveParty(CardinalPoints.EAST);
            party.moveParty(CardinalPoints.NORTH);
            assertEquals(party.getCurrentGameMap(), GameMapFactory.LINKINGMAP);
            System.out.println(party.getCurrentPosition().getPosX() + ", " + party.getCurrentPosition().getPosY());
            assertEquals(party.getCurrentPosition(), new Position(MAXPOS - 1, 3));
        } catch (BlockedPathException e) {
            fail("Party was supposed to change map");
        }
    }

}
