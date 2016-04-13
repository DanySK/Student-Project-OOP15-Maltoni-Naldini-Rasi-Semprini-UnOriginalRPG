package it.unibo.unori.model.maps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Test Class for the Party object.
 *
 */
public class PartyTest {

    private final GameMapFactory mapFactory = new GameMapFactory();

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

}
