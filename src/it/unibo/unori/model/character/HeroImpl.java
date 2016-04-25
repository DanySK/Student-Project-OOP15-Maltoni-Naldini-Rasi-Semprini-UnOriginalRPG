package it.unibo.unori.model.character;

import java.util.Map;

import it.unibo.unori.model.character.exceptions.ArmorAlreadyException;
import it.unibo.unori.model.character.exceptions.NoArmorException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
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
    private final Map<Armor.ArmorPieces, Armor> armor;
    private final ArmorPieces p = ArmorPieces.ARMOR;
    private Weapon weapon;
    private int level;
    private int totExp;

    /**
     * Standard constructor for HeroImpl.
     * @param name
     *              Hero's name.
     * @param map
     *              Hero's parameters.
     *@param armor
     *              Hero's initial equip.
     *@param weapon
     *              Hero's initial weapon.
     */
    public HeroImpl(final String name, final Map<Statistics, Integer> map, 
                    final Map<Armor.ArmorPieces, Armor> armor, final Weapon weapon) {
        super(name, map);
        this.armor = armor;
        this.weapon = weapon;
        this.level = 1;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getExpTot() {
        return this.totExp;
    }

    @Override
    public void addExp(final int expAcquired) {
        this.totExp += expAcquired;
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
