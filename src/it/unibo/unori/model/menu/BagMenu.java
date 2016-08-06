package it.unibo.unori.model.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.unori.model.battle.Battle;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.menu.utility.Pair;

/**
 * Class to model a menu for the Bag.
 *
 */
public class BagMenu implements BagMenuInterface {
    
    private Bag bag;
    private Optional<Pair<Item, Integer>> selected;
    private List<Item> listOfItems;
    private List<Integer> listOfQuantity;
    
    /**
     * Standard constructor, simply pass the Bag as an argument.
     * @param b the Bag to show in the menu.
     */
    public BagMenu(final Bag b) {
        this.bag = b;
        this.generateFromaBag();
    }
    
    /**
     * Another constructor, it has a Battle as a parameter
     */
    public BagMenu(final Battle battle) {
        this.bag = battle.getItemBag();
        this.generateFromaBag();
    }
    
    //Method to be called in the Constructor.
    private void generateFromaBag() {
        this.listOfItems = new ArrayList<>();
        this.bag.getMiscellaneous().keySet().forEach(e -> {
            this.listOfItems.add(e);
        });
        this.listOfQuantity = new ArrayList<>();
        this.bag.getMiscellaneous().values().forEach(e -> {
            this.listOfQuantity.add(e);
        });

        this.selected = 
                Optional.of(new Pair<Item, Integer>(
                        this.listOfItems.get(0), this.listOfQuantity.get(0)));
    }
    
    @Override
    public void scrollUp() {
        int nextIndex = this.listOfItems.indexOf(this.selected.get().getX()) + 1;
        if(nextIndex >= this.listOfItems.size()) {
            nextIndex -= this.listOfItems.size();
        }
        if (this.selected.isPresent()) {
            this.selected = Optional.of(new Pair<Item, Integer>(
                    this.listOfItems.get(nextIndex), this.listOfQuantity.get(nextIndex)));
        }
    }
    
    @Override
    public void scrollDown() {
        int nextIndex = this.listOfItems.indexOf(this.selected.get().getX()) - 1;
        if((nextIndex + 1) <= 0){
            nextIndex = this.listOfItems.size() - 1;
        }
        if (this.selected.isPresent()) {
            this.selected =  Optional.of(new Pair<Item, Integer>(
                    this.listOfItems.get(nextIndex), this.listOfQuantity.get(nextIndex)));
        }
    }
    
    @Override
    public Map<Item, Integer> getAllItems() {
        return this.bag.getMiscellaneous();
    }
    
    @Override
    public Map<Armor, Integer> getArmors() {
        return this.bag.getArmors();
    }
    
    @Override
    public Map<Weapon, Integer> getWeapons() {
        return this.bag.getWeapons();
    }
    
    @Override
    public Map<Potion, Integer> getPotions() {
        return this.bag.getPotions();
    }
    
    @Override
    public Bag getBag() {
        return this.bag;
    }
    
    @Override
    public Pair<Item, Integer> getSelected() {
        return this.selected.get();
    }
    
    @Override
    public void update(final Bag b) {
        this.bag = b;
        this.generateFromaBag();
    }
    
    @Override
    public List<Pair<Item, Integer>> getList() {
        final List<Pair<Item, Integer>> toReturn = new ArrayList<>();
        
        this.listOfItems.forEach(e -> {
            final Pair<Item, Integer> toAdd = 
                    new Pair<>(e, this.listOfQuantity.get(this.listOfItems.indexOf(e)));
            toReturn.add(toAdd);
        });
        return toReturn;
    }
}
