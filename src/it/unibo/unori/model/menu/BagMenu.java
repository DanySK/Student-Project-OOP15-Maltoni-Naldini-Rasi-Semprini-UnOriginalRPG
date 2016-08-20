package it.unibo.unori.model.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import it.unibo.unori.model.battle.Battle;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.exceptions.ArmorAlreadyException;
import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.BagImpl;
import it.unibo.unori.model.items.Item;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.exceptions.HeroDeadException;
import it.unibo.unori.model.items.exceptions.HeroNotDeadException;
import it.unibo.unori.model.items.exceptions.ItemNotFoundException;
import it.unibo.unori.model.menu.utility.Pair;

/**
 * Class to model a menu for the Bag.
 *
 */
public class BagMenu implements BagMenuInterface {
    
    private Bag bag;
    private final Optional<Battle> batt;
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
        this.batt = Optional.empty();
    }
    
    /**
     * Another constructor, it has a Battle as a parameter.
     * @param battle the battle from which take a Bag.
     */
    public BagMenu(final Battle battle) {
        this.bag = battle.getItemBag();
        this.generateFromaBag();
        this.batt = Optional.of(battle);
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
        if (nextIndex >= this.listOfItems.size()) {
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
        if ((nextIndex + 1) <= 0) {
            nextIndex = this.listOfItems.size() - 1;
        }
        if (this.selected.isPresent()) {
            this.selected =  Optional.of(new Pair<Item, Integer>(
                    this.listOfItems.get(nextIndex), this.listOfQuantity.get(nextIndex)));
        }
    }
    
    @Override
    public Map<Item, Integer> getAllItems() {
        return new HashMap<>(this.bag.getMiscellaneous());
    }
    
    @Override
    public Map<Armor, Integer> getArmors() {
        return new HashMap<>(this.bag.getArmors());
    }
    
    @Override
    public Map<Weapon, Integer> getWeapons() {
        return new HashMap<>(this.bag.getWeapons());
    }
    
    @Override
    public Map<Potion, Integer> getPotions() {
        return new HashMap<>(this.bag.getPotions());
    }
    
    @Override
    public Bag getBag() {
        return new BagImpl(this.bag);
    }
    
    @Override
    public Pair<Item, Integer> getSelected() {
        return new Pair<>(this.selected.get());
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
    
    @Override
    public DialogueInterface useSelected(final Hero who) {
        final Item toUse = this.selected.get().getX();
        if (toUse instanceof Potion) {
            if (this.batt.isPresent()) {
                try {
                    return this.batt.get().usePotion(who, (Potion) toUse);
                } catch (ItemNotFoundException | HeroDeadException | HeroNotDeadException e) {
                    return new Dialogue(e.toString());
                }
            } else {
                return new Dialogue(this.new UsePotionMenu().usePotion(who));
            }
        } else if (toUse instanceof Weapon) {
            return new Dialogue(this.new UsePotionMenu().useWeap(who));
        } else if (toUse instanceof Armor) {
            return new Dialogue(this.new UsePotionMenu().useArm(who));
        } else {
            throw new IllegalStateException();
        }
    }
    
    /**
     * Inner class that allows to use the selected Item.
     *
     */
    public class UsePotionMenu {
        
        /**
         * Method to be called if the selected Item is a Potion.
         * @param onWho the Hero that uses the Item.
         * @return a confirmation String.
         */
        public String usePotion(final Hero onWho) {
           try {
            ((Potion) BagMenu.this.getSelected().getX()).using(onWho);
            return onWho.getName() 
                    + " ha usato " + BagMenu.this.getSelected().getX().getName();
            } catch (HeroDeadException | HeroNotDeadException e) {
                return e.toString();
            }
        }
        
        /**
         * Method to be called if the selected Item is a Weapon.
         * @param onWho the Hero that uses the Item.
         * @return a confirmation String.
         */
        public String useWeap(final Hero onWho) {
            try {
                onWho.setWeapon((Weapon) BagMenu.this.getSelected());
                return onWho.getName() 
                        + " si equipaggia con " 
                        + BagMenu.this.getSelected().getX().getName();
            } catch (WeaponAlreadyException e) {
                return e.toString();
            }
        }
        
        /**
         * Method to be called if the selected Item is an Armor.
         * @param onWho the Hero that uses the Item.
         * @return a confirmation String.
         */
        public String useArm(final Hero onWho) {
            try {
                onWho.setArmor((Armor) BagMenu.this.getSelected());
                return onWho.getName() 
                        + " si equipaggia con " 
                        + BagMenu.this.getSelected().getX().getName();
            } catch (ArmorAlreadyException e) {
                return e.toString();
            }
        }
    }
}
