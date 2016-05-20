package it.unibo.unori.model.character;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.unibo.unori.model.character.exceptions.ArmorAlreadyException;
import it.unibo.unori.model.character.exceptions.MagicNotFoundException;
import it.unibo.unori.model.character.exceptions.NoArmorException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.character.jobs.StatisticsFactory;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.ArmorImpl;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponImpl;
import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.battle.utility.MagicAttackGenerator;;


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
    private final Jobs heroJob;
    private int totExp;
    private int currentExp;

    /**
     * Standard constructor for HeroImpl.
     * @param name
     *              Hero's name.
     * @param job
     *              Hero's job.
     *@param armor
     *              Hero's initial equip.
     *@param weapon
     *              Hero's initial weapon.
     *@param magics
     *              Hero's initial magic attacks.
     */
    public HeroImpl(final String name, final Jobs job, 
            final Map<ArmorPieces, Armor> armor, final Weapon weapon) {
        super(name, new StatisticsFactory().getJobStats(job));
        this.armor = armor;
        this.weapon = weapon;
        this.heroJob = job;
    }



    @Override
    public int getExpTot() {
        return this.totExp;
    }

    @Override
    public void addExp(final int expAcquired) {
        if (this.currentExp + expAcquired > this.totExp) {
            final int plus = this.currentExp + expAcquired - this.totExp;
            this.setLevel(this.getLevel() + 1);
            this.currentExp += plus;
        } else {
            this.currentExp += expAcquired;
        }
    }

    @Override
    public int getRemainingExp() {
        return this.currentExp;
    }

    private boolean isNotPresentWeapon() {
        return this.weapon.equals(WeaponImpl.FISTS);
    }

    private boolean isNotPresentArmor(final ArmorPieces ar) {
        return this.armor.get(ar).equals(ArmorImpl.NAKED);
    }

    @Override
    public void setWeapon(final Weapon w) throws WeaponAlreadyException {
        if (this.isNotPresentWeapon()) {
            this.weapon = w;
        } else {
            throw new WeaponAlreadyException();
        }
    }

    @Override
    public void unsetWeapon() throws NoWeaponException {
        if (this.isNotPresentWeapon()) {
           throw new NoWeaponException();
        } else {
            this.weapon = WeaponImpl.FISTS;
        }
    }

    @Override
    public Weapon getWeapon() throws NoWeaponException {
        return this.weapon;
    }

    @Override
    public void setArmor(final Armor ar) throws ArmorAlreadyException {
        if (this.isNotPresentArmor(ar.getArmorClass())) {
            this.armor.replace(ar.getArmorClass(), ar);
        } else {
            throw new ArmorAlreadyException();
        }
    }

    @Override
    public void unsetArmor(final ArmorPieces p) throws NoArmorException {
        if (this.isNotPresentArmor(p)) {
            this.armor.replace(p, ArmorImpl.NAKED);
        } else {
            throw new NoArmorException();
        }
    }

    @Override
    public Armor getArmor(final ArmorPieces p) {
        return this.armor.get(p);
    }



    @Override
    public Jobs getJob() {
        return this.heroJob;
    }
}
