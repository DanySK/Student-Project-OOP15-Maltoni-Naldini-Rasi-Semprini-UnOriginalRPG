package it.unibo.unori.model.character;

import java.util.Arrays;
import java.util.Map;

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
    private int currentHP;
    private int currentMP;
    private int level;
    private Status status;
    private final Map<Statistics, Integer> statistic;

    /**
     * Constructor for CharacterImpl.
     * @param name
     *              name of the character.
     * @param map
     *              statistics of the character.
     */
    public CharacterImpl(final String name, final Map<Statistics, Integer> map) {
        this(name, map, 1);
    }

    /**
     * Constructor for CharacterImpl.
     * @param name
     *              name of the character.
     * @param map
     *              statistics of the character.
     * @param level
     *              the level of the character. 
     * @throws IllegalArgumentException if the level is equal or less than 0
     *                                  or the map does not define a value for all
     *                                  the statistics
     */
    public CharacterImpl(final String name, final Map<Statistics, Integer> map, 
                          final int level) throws IllegalArgumentException {
        this.name = name;
        if (checkParameters(map, level)) {
            throw new IllegalArgumentException();
        }
        this.statistic = map;
        this.currentHP = this.statistic.get(Statistics.TOTALHP);
        this.currentMP = this.statistic.get(Statistics.TOTALMP);
        this.status = Status.NONE;
        this.level = level;
    }

    // method to check the parameters.
    private boolean checkParameters(final Map<Statistics, Integer> map, final int level) {
        return level <= 0 || !map.keySet().containsAll(Arrays.asList(Statistics.values()));
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
    public void consumeMP(final int mpToConsume) {
        this.currentMP = this.currentMP - mpToConsume < 0 ? 0 
                                 : this.currentMP - mpToConsume;

    }

    @Override
    public void takeDamage(final int damage) {
        this.currentHP = this.currentHP - damage < 0 ? 0 
                : this.currentHP - damage;

    }

    @Override
    public void restoreDamage(final int hpToRestore) {
        this.currentHP = this.currentHP + hpToRestore > this.getTotalHP() ? this.getTotalHP()
                : this.currentHP + hpToRestore;

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
    public void setStatus(final Status state) {
        this.status = state;
    }


    @Override
    public Status getStatus() {
        return this.status;
    }


}




