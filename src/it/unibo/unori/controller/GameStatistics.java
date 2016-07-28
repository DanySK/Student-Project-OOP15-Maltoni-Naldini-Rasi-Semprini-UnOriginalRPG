package it.unibo.unori.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This class stores every statistics in the game, and also keeps track of the time elapsed during game. It gives only
 * the possibility to increase the counts or to reset them, but not to set them manually. In fact, the class is
 * serializable, so you should probably restore it from a previously serialized one, or add something during game, but
 * not modify anything.
 */
public class GameStatistics implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3510818499443500756L;
    private int newGames;
    private int monstersMet;
    private int monstersKilled;
    private int bossesKilled;
    private int weaponsAcquired;
    private int armorsAcquired;
    private int totalExpGained;
    private final transient TimeCounter timePlayed = new TimeCounterImpl();
    private double timePlayedPreviously;

    /**
     * Gets the number of new games started.
     * 
     * @return the number of new games started
     */
    public int getNewGames() {
        return newGames;
    }

    /**
     * Increases the number of new games started.
     * 
     * @param increment
     *            the number to add to the current count of new games started.
     */
    public void increaseNewGame(final int increment) {
        this.newGames += increment;
    }

    /**
     * Gets the total number of monsters met.
     * 
     * @return the total number of monsters met
     */
    public int getMonstersMet() {
        return monstersMet;
    }

    /**
     * Increases the total number of monsters met.
     * 
     * @param increment
     *            the number to add to the current count of monsters met
     */
    public void increaseMonstersMet(final int increment) {
        this.monstersMet += increment;
    }

    /**
     * Gets the total number of monsters killed.
     * 
     * @return the total number of monsters killed
     */
    public int getMonstersKilled() {
        return monstersKilled;
    }

    /**
     * Increases the total number of monsters killed.
     * 
     * @param increment
     *            the number to add to the current count of monsters killed
     */
    public void increaseMonstersKilled(final int increment) {
        this.monstersKilled += increment;
    }

    /**
     * Gets the total number of bosses killed.
     * 
     * @return the total number of bosses killed
     */
    public int getBossesKilled() {
        return bossesKilled;
    }

    /**
     * Increases the total number of bosses killed.
     * 
     * @param increment
     *            the number to add to the count of bosses killed
     */
    public void increaseBossesKilled(final int increment) {
        this.bossesKilled += increment;
    }

    /**
     * Gets the total number of weapons acquired.
     * 
     * @return the total number of weapons acquired
     */
    public int getWeaponsAcquired() {
        return weaponsAcquired;
    }

    /**
     * Increments the total number of weapons acquired.
     * 
     * @param increment
     *            the number to add to the count of weapons acquired
     */
    public void increaseWeaponsAcquired(final int increment) {
        this.weaponsAcquired += increment;
    }

    /**
     * Gets the total number of armors acquired.
     * 
     * @return the total number of weapons acquired
     */
    public int getArmorsAcquired() {
        return armorsAcquired;
    }

    /**
     * Increments the total number of weapons acquired.
     * 
     * @param increment
     *            the number to add to the count of weapons acquired
     */
    public void increaseArmorsAcquired(final int increment) {
        this.armorsAcquired += increment;
    }

    /**
     * Gets the total experience gained by all characters during all plays.
     * 
     * @return the total exp gained by all characters during all plays
     */
    public int getTotalExpGained() {
        return totalExpGained;
    }

    /**
     * Increments the total experience gained by all characters during all plays.
     * 
     * @param increment
     *            the number to add to the count of exp gained
     */
    public void increaseTotalExpGained(final int increment) {
        this.totalExpGained += increment;
    }

    /**
     * Starts counting time.
     */
    public void startCountingTime() {
        this.timePlayed.startTimer();
    }

    /**
     * Stops counting time.
     */
    public void stopCountingTime() {
        this.timePlayed.stopTimer();
        this.timePlayedPreviously += this.timePlayed.getAlreadyPlayedTime();
    }

    /**
     * Gets the time played in this game. If you starts new game, this resets.
     * 
     * @return the time played in this game in milliseconds
     */
    public double getTimePlayed() {
        return this.timePlayed.getTotalTime();
    }

    /**
     * Gets the time played in every past game and in this current one. If you starts new game, this does not reset.
     * 
     * @return the total time played in milliseconds
     */
    public double getTotalTimePlayed() {
        return this.timePlayedPreviously + this.getTimePlayed();
    }

    /**
     * Increments the total time played. Good to use before resetting the current playing time (i.e. when starting a new
     * game).
     * 
     * @param increment
     *            the number of milliseconds to add to the count of the total played time
     */
    public void increaseTotalTimePlayed(final double increment) {
        this.timePlayedPreviously += increment;
    }

    /**
     * It resets everything.
     */
    public void reset() {
        this.armorsAcquired = 0;
        this.bossesKilled = 0;
        this.monstersKilled = 0;
        this.monstersMet = 0;
        this.newGames = 0;
        this.timePlayed.stopTimer();
        this.timePlayed.setAlreadyPlayedTime(0);
        this.timePlayedPreviously = 0;
        this.totalExpGained = 0;
        this.weaponsAcquired = 0;
    }

    /**
     * It restores the GameStatistics from another instance.
     * 
     * @param saved
     *            the GameStatistics to restore
     */
    public void restore(final GameStatistics saved) {
        this.armorsAcquired = saved.getArmorsAcquired();
        this.bossesKilled = saved.getBossesKilled();
        this.monstersKilled = saved.getMonstersKilled();
        this.monstersMet = saved.getMonstersMet();
        this.newGames = saved.getNewGames();
        this.timePlayed.setAlreadyPlayedTime(this.getTimePlayed());
        this.timePlayedPreviously = saved.getTotalTimePlayed() - saved.getTimePlayed();
        this.totalExpGained = saved.getTotalExpGained();
        this.weaponsAcquired = saved.getWeaponsAcquired();
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Number of new games: ").append(this.getNewGames())
                        .append("\nNumber of monsters met: ").append(this.getMonstersMet())
                        .append("\nNumber of monsters killed: ").append(this.getMonstersKilled())
                        .append("\nNumber of bosses killed: ").append(this.getBossesKilled())
                        .append("\nNumber of weapons acquired: ").append(this.getWeaponsAcquired())
                        .append("\nNumber of armors acquired: ").append(this.getArmorsAcquired())
                        .append("\nTotal Experience gained: ").append(this.getTotalExpGained())
                        .append("\nTime played this game: ").append(this.getTimePlayed())
                        .append("\nTotal time played: ").append(this.getTotalTimePlayed()).toString();
    }

    private void writeObject(final ObjectOutputStream out) throws IOException {
        final boolean restart = this.timePlayed.isRunning();
        this.increaseTotalTimePlayed(this.timePlayed.resetTimer());
        out.defaultWriteObject();
        if (restart) {
            this.resumeCountingTime();
        }
    }

    /**
     * Resumes counting time.
     */
    public void resumeCountingTime() {
        this.timePlayed.getAndResumeTimer();
    }

    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        // this.startCountingTime();
    }

}
