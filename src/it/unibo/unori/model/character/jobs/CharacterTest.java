package it.unibo.unori.model.character.jobs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import org.junit.Test;

import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroImpl;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.exceptions.ArmorAlreadyException;
import it.unibo.unori.model.character.exceptions.NoArmorException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.ArmorFactory;
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
    public void heroBasicTest() {
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

        h.levelUp();
        assertEquals(h.getTotalHP(), 1600);
        assertEquals(h.getFireAtk(), 820);
        assertSame(h.getLevel(), 2);
        }
    /**
     * Test for equipments.
     */
    @Test
    public void heroEquipTest() {
        final Hero h = new HeroImpl("Boot", Jobs.DUMP);
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

        try {
            h.unsetArmor(ArmorPieces.SHIELD);
            fail("Exception should be thrown!");
        } catch (NoArmorException e) {
           System.out.println("OK");
        }

        final Armor shield = new ArmorFactory().getStdEquip().get(ArmorPieces.SHIELD);
        try {
            h.setArmor(shield);
        } catch (ArmorAlreadyException e) {
            fail("Shield should be equipped");
        }
    }

}
