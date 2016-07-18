package it.unibo.unori.model.character;

import java.util.Arrays;
import java.util.Map;

import it.unibo.unori.model.battle.utility.MagicGenerator;
import it.unibo.unori.model.character.exceptions.ArmorAlreadyException;
import it.unibo.unori.model.character.exceptions.NoArmorException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;
import it.unibo.unori.model.items.ArmorImpl;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponImpl;


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
    private final int specialBar;
    private int currentBar;
    private boolean defended;


    /**
     * Standard constructor for HeroImpl.
     * @param name
     *              Hero's name.
     * @param job
     *              Hero's job.
     *@param params
     *              Hero's basic parameters.
     *@param armor
     *              Hero's initial equip.
     *@param weapon
     *              Hero's initial weapon.
     */
    public HeroImpl(final String name, final Jobs job, 
            final Map <Statistics, Integer> params,
            final Map<ArmorPieces, Armor> armor, final Weapon weapon) {
        super(name, params);
        this.armor = armor;
        this.weapon = weapon;
        this.heroJob = job;
        this.specialBar = 100;
        this.currentBar = 0;
        this.addSpell(MagicGenerator.getBasic());
        this.defended = false;
    }

    /**
     * Constructor with less parameters.
     * @param name
     *              Character's name
     * @param job
     *              Character's job
     */
    public HeroImpl(final String name, final Jobs job) {
       this(name, job, job.getInitialStats(), job.getInitialArmor(),
               job.getInitialWeapon()); 
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
            throw new NoArmorException();
        } else {
            this.armor.replace(p, ArmorImpl.NAKED);
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

    @Override
    public int getTotBar() {
        return this.specialBar;
    }

    @Override
    public int getCurrentBar() {
        return this.currentBar;
    }

    @Override
    public boolean setCurrentBar(final int toFill) {
        if (toFill < 0) {
            throw new IllegalArgumentException();
        }
        if (this.currentBar + toFill >= this.specialBar) {
            this.currentBar = this.specialBar;
            return true;
        } else {
            this.currentBar += toFill;
            return false;
        }
    }

    @Override
    public void resetSpecialBar() {
        this.currentBar = 0;
    }

    @Override
    public void levelUp() {
        this.setLevel(this.getLevel() + 1);
        final Map<Statistics, Integer> m = this.heroJob.getGrowthStats();
        Arrays.asList(Statistics.values()).forEach(i -> {
            final Map<Statistics, Integer> s = this.getStatistics();
            s.replace(i, s.get(i) + m.get(i));
        });
    }

    @Override
    public boolean hasWeapon() {
        return this.weapon.equals(WeaponImpl.FISTS);
    }

    @Override
    public void setDefended() {
        this.defended = true;
    }

    @Override
    public boolean isDefended() {
        return this.defended;
    }

    @Override
    public void setUndefended() {
        this.defended = false;
    }

}
