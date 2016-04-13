package it.unibo.unori.model.character;

import java.util.Optional;

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
    
    /**
     * something.
     * @return something.
     */
        public DummyMenu createMenu() {
            return new DummyMenu();
        }
    
    @Override
    public int getRemainingHP() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public int getTotalHP() {
        // TODO Auto-generated method stub
        return 0;
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
    public void setWeapon(Weapon w) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public Weapon getWeapon() {
        // TODO Auto-generated method stub
        return null;
    }

}
