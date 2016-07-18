package it.unibo.unori.model.character.jobs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroImpl;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.exceptions.NoArmorException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.ArmorImpl;
import it.unibo.unori.model.items.WeaponImpl;

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
        h.restoreDamage(1000);
        h.restoreDamage(1000);
        assertEquals(h.getRemainingHP(), h.getTotalHP());
        try {
            h.unsetWeapon();
            assertEquals(h.getWeapon(), WeaponImpl.FISTS);
            h.unsetArmor(ArmorPieces.SHIELD);
            assertEquals(h.getArmor(ArmorPieces.SHIELD), ArmorImpl.NAKED);
        } catch (NoWeaponException e) {
            fail("No Exception should be thrown");
        } catch (NoArmorException e) {
            fail("No Exception should be thrown");
        }

    }

}
