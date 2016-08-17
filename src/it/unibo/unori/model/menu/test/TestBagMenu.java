package it.unibo.unori.model.menu.test;

import org.junit.Test;

import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.BagImpl;
import it.unibo.unori.model.items.PotionFactory;
import it.unibo.unori.model.items.WeaponFactory;
import it.unibo.unori.model.menu.BagMenu;
import it.unibo.unori.model.menu.BagMenuInterface;

/**
 * Simple Test Class to test BagMenu.
 *
 */
public class TestBagMenu {
    
    /**
     * Standard test for BagMenu.
     */
    @Test
    public void testStandard() {
        final BagMenuInterface toTest;
        final PotionFactory factP = new PotionFactory();
        final Bag bag = new BagImpl();
        bag.storeItem(WeaponFactory.getBalestra());
        bag.storeItem(WeaponFactory.getBalestra());
        bag.storeItem(WeaponFactory.getBalestra());
        bag.storeItem(WeaponFactory.getBalestra());
        bag.storeItem(WeaponFactory.getCannone());
        bag.storeItem(WeaponFactory.getCannone());
        bag.storeItem(WeaponFactory.getCannone());
        bag.storeItem(WeaponFactory.getCannone());
        bag.storeItem(WeaponFactory.getCannone());
        bag.storeItem(WeaponFactory.getCannone());
        bag.storeItem(factP.getAspirinaMagica());
        bag.storeItem(factP.getAspirinaMagica());
        bag.storeItem(factP.getAspirinaMagica());
        bag.storeItem(factP.getAspirinaMagica());
        bag.storeItem(factP.getAspirinaMagica());
        bag.storeItem(factP.getAspirinaMagica());
        bag.storeItem(factP.getAspirinaMagica());
        bag.storeItem(factP.getAspirinaMagica());
        bag.storeItem(factP.getPozioneDio());
        bag.storeItem(factP.getPozioneDio());
        bag.storeItem(factP.getPozioneDio());
        bag.storeItem(factP.getPozioneDio());
        bag.storeItem(factP.getPozioneDio());
        System.out.println(bag.getMiscellaneous());
        System.out.println(bag.getMiscellaneous().values());
        toTest = new BagMenu(bag);
        bag.storeItem(WeaponFactory.getColtre());
        toTest.update(bag);
        System.out.println(bag.getMiscellaneous());
        System.out.println(toTest.getAllItems());
        System.out.println(toTest.getList());
        System.out.println(toTest.getSelected());
    }
}
