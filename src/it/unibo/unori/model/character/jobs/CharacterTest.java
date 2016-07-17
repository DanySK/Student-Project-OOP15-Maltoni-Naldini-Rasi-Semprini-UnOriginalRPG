package it.unibo.unori.model.character.jobs;

import org.junit.Test;

import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroImpl;

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
        final Hero h = new HeroImpl("Boot", Jobs.DUMP);

    }

}
