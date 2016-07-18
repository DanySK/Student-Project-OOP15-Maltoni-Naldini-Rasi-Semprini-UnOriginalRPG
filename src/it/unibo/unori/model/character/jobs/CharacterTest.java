package it.unibo.unori.model.character.jobs;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroImpl;
import it.unibo.unori.model.character.Statistics;

/**
 * Test Class for Character,Hero and Foe.
 *
 */
public class CharacterTest {

    /**
     * Test for the hero class.
     */
    @Test
    public void heroTest() {
        assertEquals(Jobs.DUMP.getInitialStats().size(), Statistics.values().length);
        final Hero h = new HeroImpl("Boot", Jobs.DUMP);
        h.consumeMP(10);
        assertEquals(h.getCurrentMP(), Jobs.DUMP.getInitialStats().get(Statistics.TOTALMP) - 10);
        h.takeDamage(1000);
        h.takeDamage(1000);
        assertEquals(h.getRemainingHP(), 0);

    }

}
