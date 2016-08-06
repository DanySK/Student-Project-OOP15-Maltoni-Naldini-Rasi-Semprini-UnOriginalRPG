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
    
    private BagMenuInterface toTest;
    
    @Test
    public void testStandard() {
        final WeaponFactory fact = new WeaponFactory();
        final PotionFactory factP = new PotionFactory();
        final Bag bag = new BagImpl();
        bag.storeItem(fact.getBalestra());
        bag.storeItem(fact.getBalestra());
        bag.storeItem(fact.getBalestra());
        System.out.println(bag.getMiscellaneous().size());
        bag.storeItem(fact.getBalestra());
        bag.storeItem(fact.getCannone());
        bag.storeItem(fact.getCannone());
        bag.storeItem(fact.getCannone());
        bag.storeItem(fact.getCannone());
        bag.storeItem(fact.getCannone());
        bag.storeItem(fact.getCannone());
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
        this.toTest = new BagMenu(bag);
        bag.storeItem(fact.getColtre());
        this.toTest.update(bag);
        System.out.println(bag.getMiscellaneous());
        System.out.println(toTest.getAllItems());
        System.out.println(this.toTest.getList());
        System.out.println(this.toTest.getSelected());
        System.out.println(this.toTest.scrollDown());
        System.out.println(this.toTest.getSelected());
        System.out.println(this.toTest.scrollDown());
        System.out.println(this.toTest.getSelected());
        System.out.println(this.toTest.scrollUp());
        System.out.println(this.toTest.getSelected());
        System.out.println(this.toTest.scrollUp());
        System.out.println(this.toTest.getSelected());
        System.out.println(this.toTest.scrollDown());
        System.out.println(this.toTest.getSelected());
    }
}
