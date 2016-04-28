package it.unibo.unori.model.character;

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
        this.name = name;
        this.statistic = map;
        this.currentHP = this.statistic.get(Statistics.TOTALHP);
        this.currentMP = this.statistic.get(Statistics.TOTALMP);
        this.status = Status.NONE;
    }


    @Override
    public String getName() {
        return this.name;
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
        return this.statistic.get(Statistics.PHYISICDEF);
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




