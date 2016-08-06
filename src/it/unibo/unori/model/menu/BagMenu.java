package it.unibo.unori.model.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.Weapon;

/**
 * Class to model a menu for the Bag.
 *
 */
public class BagMenu implements BagMenuInterface {
    
    private Bag bag;
    private Optional<Item> selectedItem;
    private Optional<Integer> selectedQuantity;
    private List<Item> listOfItems;
    private List<Integer> listOfQuantity;
    
    /**
     * Standard constructor, simply pass the Bag as an argument.
     * @param b the Bag to show in the menu.
     */
    public BagMenu(final Bag b) {
        this.bag = b;
        
        this.listOfItems = new ArrayList<>();
        this.bag.getMiscellaneous().keySet().forEach(e -> {
            this.listOfItems.add(e);
        });
        this.listOfQuantity = new ArrayList<>();
        this.bag.getMiscellaneous().values().forEach(e -> {
            this.listOfQuantity.add(e);
        });
        
        this.selectedItem = Optional.of(this.listOfItems.get(0));
        this.selectedQuantity = Optional.of(this.listOfQuantity.get(0));
    }
    
    @Override
    public void scrollUp() {
        int nextIndex = this.listOfItems.indexOf(this.selectedItem.get()) + 1;
        if(nextIndex >= this.listOfItems.size()) {
            nextIndex = nextIndex - this.listOfItems.size();
        }
        if (this.selectedItem.isPresent() && this.selectedQuantity.isPresent()) {
            this.selectedItem = Optional.of(this.listOfItems.get(nextIndex));
            this.selectedQuantity = Optional.of(this.listOfQuantity.get(nextIndex));
        }
    }
    
    @Override
    public void scrollDown() {
        int nextIndex = this.listOfItems.indexOf(this.selectedItem.get()) - 1;
        if((nextIndex + 1) <= 0){
            nextIndex = this.listOfItems.size() - 1;
        }
        if (this.selectedItem.isPresent() && this.selectedQuantity.isPresent()) {
            this.selectedItem =  Optional.of(this.listOfItems.get(nextIndex));
            this.selectedQuantity = Optional.of(this.listOfQuantity.get(nextIndex));
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
    public Item getSelectedIem() {
        return this.selectedItem.get();
    }
    
    @Override
    public int getSelectedQuantity() {
        return this.selectedQuantity.get();
    }
    
    @Override
    public void update(final Bag b) {
        this.bag = b;
        
        this.listOfItems = new ArrayList<>();
        this.bag.getMiscellaneous().keySet().forEach(e -> {
            this.listOfItems.add(e);
        });
        this.listOfQuantity = new ArrayList<>();
        this.bag.getMiscellaneous().values().forEach(e -> {
            this.listOfQuantity.add(e);
        });
        
        this.selectedItem = Optional.of(this.listOfItems.get(0));
        this.selectedQuantity = Optional.of(this.listOfQuantity.get(0));
    }
    
    @Override
    public List<Map<Item, Integer>> getList() {
        final List<Map<Item, Integer>> toReturn = new ArrayList<>();
        
        this.listOfItems.forEach(e -> {
            final Map<Item, Integer> toAdd = new HashMap<>();
            toAdd.put(e, this.listOfQuantity.get(this.listOfItems.indexOf(e)));
            toReturn.add(toAdd);
        });
        return toReturn;
    }
}
