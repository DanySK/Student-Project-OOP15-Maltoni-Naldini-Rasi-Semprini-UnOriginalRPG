package it.unibo.unori.model.character;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.exceptions.MagicNotFoundException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
import it.unibo.unori.model.items.Weapon;
import it.unibo.unori.model.items.WeaponImpl;

/**
 * Class to design a generic Character.
 *
 */
public class CharacterImpl implements Character {

    /**
     * 
     */
    private static final long serialVersionUID = -95447626445744515L;
    private final String name;
    private final String battleFrame;
    private int currentHP;
    private int currentMP;
    private int level;
    private Weapon weapon;
    private Status status;
    private final Map<Statistics, Integer> statistic;
    private final List<MagicAttackInterface> spellList;

    /**
     * Constructor for CharacterImpl.
     * @param name
     *              name of the character.
     * @param battleFrame
     *              path of the battleFrame of the character
     * @param map
     *              statistics of the character.
     * @param w
     *              weapon of the character
     */
    public CharacterImpl(final String name, final String battleFrame, 
            final Map<Statistics, Integer> map, final Weapon w) {
        this(name, battleFrame, map, 1, w);
    }

    /**
     * Constructor for CharacterImpl.
     * @param name
     *              name of the character.
     * @param battleFrame
     *              path of the battleFrame of the character
     * @param map
     *              statistics of the character.
     * @param level
     *              the level of the character.
     * @param w
     *              weapon of the character 
     * @throws IllegalArgumentException if the level is equal or less than 0
     *                                  or the map does not define a value for all
     *                                  the statistics
     */
    public CharacterImpl(final String name, final String battleFrame,
             final Map<Statistics, Integer> map, final int level, final Weapon w)
                        throws IllegalArgumentException {
        this(name, battleFrame, map, level, new LinkedList<MagicAttackInterface>(), w);
    }

    /**
     * Constructor for CharacterImpl.
     * @param name
     *              name of the character.
     * @param map
     *              statistics of the character.
     * @param battleFrame
     *              path of the battleFrame of the character
     * @param level
     *              the level of the character.
     * @param spellList
     *              the starter spellList of the character. 
     * @param w
     *              weapon of the character
     * @throws IllegalArgumentException if the level is equal or less than 0
     *                                  or the map does not define a value for all
     *                                  the statistics
     */
    public CharacterImpl(final String name, final String battleFrame, final Map<Statistics, Integer> map, 
            final int level, final List<MagicAttackInterface> spellList, final Weapon w) {
        this.name = name;
        this.battleFrame = battleFrame;
        if (checkParameters(map, level)) {
            throw new IllegalArgumentException("I parametri immessi non corrispondono,"
                    + "può essere un errore dovuto alla lettura da file, assicurarsi che i file"
                    + "siano presenti e riprovare");
        }
        this.statistic = map;
        this.currentHP = this.statistic.get(Statistics.TOTALHP);
        this.currentMP = this.statistic.get(Statistics.TOTALMP);
        this.status = Status.NONE;
        this.level = level;
        this.spellList = spellList;
        this.weapon = w;
    }

    // method to check the parameters.
    private boolean checkParameters(final Map<Statistics, Integer> map, final int level) {
        return level <= 0 || !map.keySet().containsAll(Arrays.asList(Statistics.values()));
    }

    // Method to check if a param is negative
    private void checkParam(final int param) throws IllegalArgumentException {
        if (param < 0) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isNotPresentWeapon() {
        return this.weapon.equals(WeaponImpl.FISTS);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setLevel(final int level) throws IllegalArgumentException {
        if (level <= 0) {
            throw new IllegalArgumentException();
        }
        this.level = level;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getRemainingHP() {
        return this.currentHP;
    }

    @Override
    public int getTotalHP() {
        return this.statistic.get(Statistics.TOTALHP);
    }

    @Override
    public int getTotalMP() {
        return this.statistic.get(Statistics.TOTALMP);
    }

    @Override
    public void consumeMP(final int mpToConsume) {
        this.checkParam(mpToConsume);
        this.currentMP = this.currentMP - mpToConsume < 0 ? 0 
                                 : this.currentMP - mpToConsume;

    }

    @Override
    public void takeDamage(final int damage) {
        this.checkParam(damage);
        this.currentHP = this.currentHP - damage < 0 ? 0 
                : this.currentHP - damage;
        if (this.currentHP == 0) {
            this.setStatus(Status.DEAD);
        }
    }

    @Override
    public void restoreHP(final int hpToRestore) {
        this.checkParam(hpToRestore);
        this.currentHP = this.currentHP + hpToRestore > this.getTotalHP() ? this.getTotalHP()
                : this.currentHP + hpToRestore;

    }

    @Override
    public void restoreMP(final int mpToRestore) {
        this.checkParam(mpToRestore);
        this.currentMP = this.currentMP + mpToRestore > this.getTotalHP() ? this.getTotalMP()
                : this.currentHP + mpToRestore;
    }

    @Override
    public int getAttack() {
        return this.statistic.get(Statistics.PHYSICATK);
    }

    @Override
    public int getDefense() {
        return this.statistic.get(Statistics.PHYSICDEF);
    }


    @Override
    public int getSpeed() {
        return this.statistic.get(Statistics.SPEED);
    }

    @Override
    public int getFireDefense() {
        return this.statistic.get(Statistics.FIREDEF);
    }

    @Override
    public int getThunderDefense() {
        return this.statistic.get(Statistics.THUNDERDEF);
    }

    @Override
    public int getIceDefense() {
        return this.statistic.get(Statistics.ICEDEF);
    }

    @Override
    public int getFireAtk() {
        return this.statistic.get(Statistics.FIREATK);
    }

    @Override
    public int getThunderAttack() {
        return this.statistic.get(Statistics.THUNDERATK);
    }

    @Override
    public int getIceAttack() {
        return this.statistic.get(Statistics.ICEDEF);
    }

    @Override
    public int getExpFactor() {
        return this.statistic.get(Statistics.EXPFACTOR);
    }

    @Override
    public void setStatus(final Status state) {
        this.status = state;
    }


    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public int getCurrentMP() {
        return this.currentMP;
    }

    @Override
    public List<MagicAttackInterface> getMagics() {
        return new LinkedList<>(this.spellList);
    }

    @Override
    public void addSpell(final MagicAttackInterface spell) {
        this.spellList.add(spell);
    }

    @Override
    public void removeSpell(final MagicAttackInterface mag) 
            throws MagicNotFoundException {
        if (this.spellList.contains(mag)) {
            this.spellList.remove(mag);
        } else {
            throw new MagicNotFoundException();
        }
    }

    @Override
    public Map<Statistics, Integer> getStatistics() {
        return new HashMap<>(this.statistic);
    }

    @Override
    public String getBattleFrame() {
        return this.battleFrame;
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
    public boolean hasWeapon() {
        return !this.isNotPresentWeapon();
    }

    @Override
    public String toString() {
        return this.name;
    }

}