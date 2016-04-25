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
    private final Map<ArmorPieces, Armor> armor;
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
                    final Map<ArmorPieces, Armor> armor, final Weapon weapon) {
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
    public void setWeapon(final Weapon w) throws WeaponAlreadyException {
        if (this.weapon.getName().equals("FISTS")) {
            this.weapon = w;
        } else {
            throw new WeaponAlreadyException();
        }
    }

    @Override
    public void unsetWeapon() throws NoWeaponException {
        // TODO Auto-generated method stub
    }

    @Override
    public Weapon getWeapon() throws NoWeaponException {
        return this.weapon;
    }

    @Override
    public void setArmor(final Armor ar) throws ArmorAlreadyException {
        if (this.armor.get(ar.getArmorClass()).getName().equals("NAKED")) {
            this.armor.replace(ar.getArmorClass(), ar);
        } else {
            throw new ArmorAlreadyException();
        }
    }

    @Override
    public void unsetArmor() throws NoArmorException {
        // TODO Auto-generated method stub
    }

    @Override
    public Armor getArmor(final ArmorPieces p) throws NoArmorException {
        return this.armor.get(p);
    }

}
