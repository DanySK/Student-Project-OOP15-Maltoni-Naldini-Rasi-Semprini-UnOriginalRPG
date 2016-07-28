package it.unibo.unori.controller;

import java.io.Serializable;

/**
 * This interface models a contract for a class that stores every statistics in the game, and also keeps track of the
 * time elapsed during game. It gives only the possibility to increase the counts or to reset them, but not to set them
 * manually. In fact, the class is serializable, so you should probably restore it from a previously serialized one, or
 * add something during game, but not modify anything.
 */
public interface GameStatistics extends Serializable {
    /**
     * Gets the number of new games started.
     * 
     * @return the number of new games started
     */
    int getNewGames();

    /**
     * Increases the number of new games started.
     * 
     * @param increment
     *            the number to add to the current count of new games started.
     */
    void increaseNewGame(final int increment);

    /**
     * Gets the total number of monsters met.
     * 
     * @return the total number of monsters met
     */
    int getMonstersMet();

    /**
     * Increases the total number of monsters met.
     * 
     * @param increment
     *            the number to add to the current count of monsters met
     */
    void increaseMonstersMet(final int increment);

    /**
     * Gets the total number of monsters killed.
     * 
     * @return the total number of monsters killed
     */
    int getMonstersKilled();

    /**
     * Increases the total number of monsters killed.
     * 
     * @param increment
     *            the number to add to the current count of monsters killed
     */
    void increaseMonstersKilled(final int increment);

    /**
     * Gets the total number of bosses killed.
     * 
     * @return the total number of bosses killed
     */
    int getBossesKilled();

    /**
     * Increases the total number of bosses killed.
     * 
     * @param increment
     *            the number to add to the count of bosses killed
     */
    void increaseBossesKilled(final int increment);

    /**
     * Gets the total number of weapons acquired.
     * 
     * @return the total number of weapons acquired
     */
    int getWeaponsAcquired();

    /**
     * Increments the total number of weapons acquired.
     * 
     * @param increment
     *            the number to add to the count of weapons acquired
     */
    void increaseWeaponsAcquired(final int increment);

    /**
     * Gets the total number of armors acquired.
     * 
     * @return the total number of weapons acquired
     */
    int getArmorsAcquired();

    /**
     * Increments the total number of weapons acquired.
     * 
     * @param increment
     *            the number to add to the count of weapons acquired
     */
    void increaseArmorsAcquired(final int increment);

    /**
     * Gets the total experience gained by all characters during all plays.
     * 
     * @return the total exp gained by all characters during all plays
     */
    int getTotalExpGained();

    /**
     * Increments the total experience gained by all characters during all plays.
     * 
     * @param increment
     *            the number to add to the count of exp gained
     */
    void increaseTotalExpGained(final int increment);

    /**
     * Starts counting time.
     */
    void startCountingTime();

    /**
     * Checks if it is currently counting time.
     * 
     * @return true if it is counting time
     */
    boolean isCountingTime();

    /**
     * Pause counting time.
     */
    void pauseCountingTime();

    /**
     * Stops counting time.
     */
    void stopCountingTime();

    /**
     * Gets the time played in this game. If you starts new game, this resets.
     * 
     * @return the time played in this game in milliseconds
     */
    double getTimePlayed();

    /**
     * Gets the time played in every past game and in this current one. If you starts new game, this does not reset.
     * 
     * @return the total time played in milliseconds
     */
    double getTotalTimePlayed();

    /**
     * Increments the total time played. Good to use before resetting the current playing time (i.e. when starting a new
     * game).
     * 
     * @param increment
     *            the number of milliseconds to add to the count of the total played time
     */
    void increaseTotalTimePlayed(final double increment);

    /**
     * It resets everything.
     */
    void reset();

    /**
     * It restores the GameStatistics from another instance.
     * 
     * @param saved
     *            the GameStatistics to restore
     */
    void restore(final GameStatistics saved);

    /**
     * This override is needed because of overriding of {@link #equals(Object)} method.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    int hashCode();

    /**
     * Two GameStatistics object are equals when the internal fields are equals. It needs to be overridden.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    boolean equals(final Object obj);
}
