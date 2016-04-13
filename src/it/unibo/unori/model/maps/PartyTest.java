package it.unibo.unori.model.maps;

import org.junit.Test;

/**
 * Test Class for the Party object.
 *
 */
public class PartyTest {

    private final GameMapFactory mapFactory = new GameMapFactory();

    /**
     * Test for the singleton strategy.
     */
    @Test
    public void testSingletonStrategy() {
        final Party party = SingletonParty.getParty();
        final Party party2 = SingletonParty.getParty();
        party.setCurrentMap(mapFactory.getStdRoom());

    }

}
