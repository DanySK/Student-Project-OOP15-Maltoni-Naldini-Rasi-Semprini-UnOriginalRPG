package it.unibo.unori.model.character;

import java.util.Optional;

import it.unibo.unori.model.character.exceptions.ArmorAlreadyException;
import it.unibo.unori.model.character.exceptions.NoArmorException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.menu.DummyMenu;

/**
 * A Class that implements a generic Character.
 *
 */
public class DummyCharacter implements Character {
    /**
     * 
     */
    private static final long serialVersionUID = -1306119386793011379L;
    private transient Optional<Weapon> weap;
    private transient Optional<Armor> arm;
    private int healthPoints;
    private int remainingHP;
    
    /**
     * something.
     * @return something.
     */
    public DummyMenu createMenu() {
        return new DummyMenu();
    }
    
    @Override
    public int getRemainingHP() {
        return this.remainingHP;
    }
    
    @Override
    public int getTotalHP() {
        return this.healthPoints;
    }
    
    @Override
    public int getAttack() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public int getDefense() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public int getMagicAtk() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public int getMagicDef() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public int getSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public int getLevel() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public int getExpTot() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public void addExp(final int expAcquired) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public int getRemainingExp() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public void setWeapon(final Weapon w) throws WeaponAlreadyException {
        if (this.weap.isPresent()) {
            throw new WeaponAlreadyException();
        } else {
            this.weap = Optional.of(w);
        }
    }
    
    @Override
    public Weapon getWeapon() {
        return this.weap.get();
    }

    @Override
    public void setArmor(final Armor ar) throws ArmorAlreadyException {
        if (this.arm.isPresent()) {
            throw new ArmorAlreadyException();
        } else {
            this.arm = Optional.of(ar);
        }
    }

    @Override
    public Armor getArmor() throws NoArmorException {
        return this.arm.get();
    }

    @Override
    public void unsetWeapon() throws NoWeaponException {
        if (this.weap.isPresent()) {
            this.weap = Optional.empty();
        } else {
            throw new NoWeaponException();
        }
    }

    @Override
    public void unsetArmor() throws NoArmorException {
        if (this.arm.isPresent()) {
            this.arm = Optional.empty();
        } else {
            throw new NoArmorException();
        }
    }

    @Override
    public void attacking(final int damage) {
        if (damage < this.getRemainingHP()) {
            this.remainingHP = 0;
        } else {
            this.remainingHP -= damage;
        }
    }

}
