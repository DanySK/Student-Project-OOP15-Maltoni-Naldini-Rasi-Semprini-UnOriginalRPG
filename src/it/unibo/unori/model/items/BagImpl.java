package it.unibo.unori.model.items;

import java.util.HashMap;
import java.util.Map;

import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.exceptions.ArmorAlreadyException;
import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
import it.unibo.unori.model.items.exceptions.ItemNotFoundException;

/**
 * Implementation of Interface Bag.
 */
public class BagImpl implements Bag {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final Map<Armor, Integer> armors;
    private final Map<Weapon, Integer> weapons;
    private final Map<Potion, Integer> potions;
    private final Map<Item, Integer> miscellaneous;

    /**
     * Standard Constructor: it initializes the maps.
     */
    public BagImpl() {
        this.armors = new HashMap<>();
        this.weapons = new HashMap<>();
        this.potions = new HashMap<>();
        this.miscellaneous = new HashMap<>();
    }
    /**
     * A Constructor that allows to create a Bag based on an already-existing one.
     * @param existing the Bag that allows to initialize the new one.
     */
    public BagImpl(final Bag existing) {
        this.armors = existing.getArmors();
        this.weapons = existing.getWeapons();
        this.potions = existing.getPotions();
        this.miscellaneous = existing.getMiscellaneous();
    }


    private void insertArmor(final Armor ar) {
        if (this.armors.containsKey(ar)) {
            this.armors.replace(ar, this.armors.get(ar) + 1);
        } else {
            this.armors.put(ar, 1);
        }
    }

    private void insertWeapon(final Weapon w) {
        if (this.weapons.containsKey(w)) {
            this.weapons.replace(w, this.weapons.get(w) + 1);
        } else {
            this.weapons.put(w, 1);
        }
    }

    private void insertPotion(final Potion p) {
        if (this.potions.containsKey(p)) {
            this.potions.replace(p, this.potions.get(p) + 1);
        } else {
            this.potions.put(p, 1);
        }
    }

    private void insertGenericItem(final Item i) {
        if (this.miscellaneous.containsKey(i)) {
            this.miscellaneous.replace(i, this.miscellaneous.get(i) + 1);
        } else {
            this.miscellaneous.put(i, 1);
        }
    }

    private void removeArmor(final Armor ar) throws ItemNotFoundException {
        if (this.armors.containsKey(ar)) {
            if (this.armors.get(ar).equals(1)) {
                this.armors.remove(ar);
            } else {
                this.armors.replace(ar, this.armors.get(ar) - 1);
            }
        } else {
            throw new ItemNotFoundException();
        }
    }

    private void removeWeapon(final Weapon w) throws ItemNotFoundException {
        if (this.weapons.containsKey(w)) {
            if (this.weapons.get(w).equals(1)) {
                this.weapons.remove(w);
            } else {
                this.weapons.replace(w, this.weapons.get(w) - 1);
            }
        } else {
            throw new ItemNotFoundException();
        }
    }

    private void removePotion(final Potion p) throws ItemNotFoundException {
        if (this.potions.containsKey(p)) {
            if (this.potions.get(p).equals(1)) {
                this.potions.remove(p);
            } else {
                this.potions.replace(p, this.potions.get(p) - 1);
            }
        } else {
            throw new ItemNotFoundException();
        }
    }

    private void removeGenericItem(final Item i) throws ItemNotFoundException {
        if (this.miscellaneous.containsKey(i)) {
            if (this.miscellaneous.get(i).equals(1)) {
                this.miscellaneous.remove(i);
            } else {
                this.miscellaneous.replace(i, this.miscellaneous.get(i) - 1);
            }
        } else {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public void storeItem(final Item toAdd) {
        if (toAdd instanceof Armor) {
           this.insertArmor((Armor) toAdd);
        } else if (toAdd instanceof Weapon) {
            this.insertWeapon((Weapon) toAdd);
        } else if (toAdd instanceof Potion) {
            this.insertPotion((Potion) toAdd);
        } else {
            this.insertGenericItem(toAdd);
        }
    }

    @Override
    public void removeItem(final Item toRemove) throws ItemNotFoundException {
        if (toRemove instanceof Armor) {
            this.removeArmor((Armor) toRemove);
        } else if (toRemove instanceof Weapon) {
            this.removeWeapon((Weapon) toRemove);
        } else if (toRemove instanceof Potion) {
            this.removePotion((Potion) toRemove);
        } else {
            this.removeGenericItem(toRemove);
        }
    }

    @Override
    public void usePotion(final Hero my, final Potion p) {
        my.restoreHP(p.getRestore());
    }

    @Override
    public void equip(final Hero my, final Armor arm) throws ArmorAlreadyException {
        my.setArmor(arm);
    }

    @Override
    public void arm(final Hero my, final Weapon w) throws WeaponAlreadyException {
        my.setWeapon(w);
    }
    @Override
    public boolean contains(final Item i) {
        if (i instanceof Armor) {
            return this.armors.containsKey(i);
        } else if (i instanceof Weapon) {
            return this.weapons.containsKey(i);
        } else if (i instanceof Potion) {
            return this.potions.containsKey(i);
        } 
        return this.miscellaneous.containsKey(i);
    }

    @Override
    public boolean containsKey() {
        return this.miscellaneous.containsKey(ItemImpl.KEY);
    }
    @Override
    public Map<Armor, Integer> getArmors() {
        return new HashMap<>(this.armors);
    }

    @Override
    public Map<Weapon, Integer> getWeapons() {
        return new HashMap<>(this.weapons);
    }

    @Override
    public Map<Potion, Integer> getPotions() {
        return new HashMap<>(this.potions);
    }

    @Override
    public Map<Item, Integer> getMiscellaneous() {
        return new HashMap<>(this.miscellaneous);
    }
    
    /**
     * HashCode method implemented using auto generation.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((armors == null) ? 0 : armors.hashCode());
        result = prime * result + ((miscellaneous == null) ? 0 : miscellaneous.hashCode());
        result = prime * result + ((potions == null) ? 0 : potions.hashCode());
        result = prime * result + ((weapons == null) ? 0 : weapons.hashCode());
        return result;
    }
    
    /**
     * Equals method implemented for the serialization.
     * 
     * @see java.lang.Object#equals(Object obj).
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final BagImpl other = (BagImpl) obj;
        
        return this.armors == other.getArmors() && this.miscellaneous == other.getMiscellaneous()
                && this.potions == other.getPotions() && this.weapons == other.getWeapons();
        
    }

}
