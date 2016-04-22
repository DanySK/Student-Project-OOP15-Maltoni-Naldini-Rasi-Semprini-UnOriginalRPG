package it.unibo.unori.model.character;

import it.unibo.unori.model.character.exceptions.ArmorAlreadyException;
import it.unibo.unori.model.character.exceptions.NoArmorException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Weapon;

/**
 * Implementation of Hero Interface.
 * This class design a hero object who can be controlled by the player:
 * He has all the methods and function of a character, but he can also equip and 
 * take off pieces of equipments.
 *
 */
public class HeroImpl  extends CharacterImpl implements Hero {

    /**
     * 
     */
    private static final long serialVersionUID = 7538947993488315753L;

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
    public void addExp(int expAcquired) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getRemainingExp() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setWeapon(Weapon w) throws WeaponAlreadyException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void unsetWeapon() throws NoWeaponException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Weapon getWeapon() throws NoWeaponException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setArmor(Armor ar) throws ArmorAlreadyException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void unsetArmor() throws NoArmorException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Armor getArmor() throws NoArmorException {
        // TODO Auto-generated method stub
        return null;
    }


    

}
