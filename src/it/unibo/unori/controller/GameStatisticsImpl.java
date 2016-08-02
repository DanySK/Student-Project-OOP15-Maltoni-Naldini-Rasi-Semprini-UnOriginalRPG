package it.unibo.unori.controller;

/**
 * This class implements a GameStatistics with the counting time implemented with a
 * {@link it.unibo.unori.controller.TimeCounter} as an external strategy.
 * 
 * @see it.unibo.unori.controller.GameStatistics
 */
public class GameStatisticsImpl implements GameStatistics {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -1788767604314411774L;
    /**
     * Non-started state for internal GameStatistics timer.
     */
    private static final double NOT_STARTED = -1;
    /**
     * Default generated hashCode() prime.
     */
    private static final int HASHCODE_PRIME = 31;

    private int newGames;
    private int monstersMet;
    private int monstersKilled;
    private int bossesKilled;
    private int weaponsAcquired;
    private int armorsAcquired;
    private int totalExpGained;
    private double startingTime = NOT_STARTED;
    private double timeDelta;
    private double timePlayedPreviously;

    @Override
    public int getNewGames() {
        return newGames;
    }

    @Override
    public void increaseNewGames(final int increment) {
        this.newGames += increment;
    }

    @Override
    public int getMonstersMet() {
        return monstersMet;
    }

    @Override
    public void increaseMonstersMet(final int increment) {
        this.monstersMet += increment;
    }

    @Override
    public int getMonstersKilled() {
        return monstersKilled;
    }

    @Override
    public void increaseMonstersKilled(final int increment) {
        this.monstersKilled += increment;
    }

    @Override
    public int getBossesKilled() {
        return bossesKilled;
    }

    @Override
    public void increaseBossesKilled(final int increment) {
        this.bossesKilled += increment;
    }

    @Override
    public int getWeaponsAcquired() {
        return weaponsAcquired;
    }

    @Override
    public void increaseWeaponsAcquired(final int increment) {
        this.weaponsAcquired += increment;
    }

    @Override
    public int getArmorsAcquired() {
        return armorsAcquired;
    }

    @Override
    public void increaseArmorsAcquired(final int increment) {
        this.armorsAcquired += increment;
    }

    @Override
    public int getTotalExpGained() {
        return totalExpGained;
    }

    @Override
    public void increaseTotalExpGained(final int increment) {
        this.totalExpGained += increment;
    }

    @Override
    public void startCountingTime() {
        if (!this.isCountingTime()) {
            this.startingTime = System.currentTimeMillis();
        }
    }

    @Override
    public boolean isCountingTime() {
        return this.startingTime != NOT_STARTED;
    }

    @Override
    public void pauseCountingTime() {
        if (this.isCountingTime()) {
            final double endingTime = System.currentTimeMillis();
            this.timeDelta = endingTime - this.startingTime;
            this.startingTime = NOT_STARTED;
        }
    }

    @Override
    public void stopCountingTime() {
        this.pauseCountingTime();
        this.timePlayedPreviously += this.timeDelta;
        this.timeDelta = 0;
    }

    @Override
    public double getTimePlayed() {
        if (this.isCountingTime()) {
            this.timeDelta = System.currentTimeMillis() - this.startingTime;
        }
        return this.timeDelta;
    }

    @Override
    public double getTotalTimePlayed() {
        return this.timePlayedPreviously + this.getTimePlayed();
    }

    @Override
    public void increaseTotalTimePlayed(final double increment) {
        this.timePlayedPreviously += increment;
    }

    @Override
    public void reset() {
        this.armorsAcquired = 0;
        this.bossesKilled = 0;
        this.monstersKilled = 0;
        this.monstersMet = 0;
        this.newGames = 0;
        // this.timePlayed.stopTimer();
        // this.timePlayed.setAlreadyPlayedTime(0);
        this.startingTime = NOT_STARTED;
        this.timeDelta = 0;
        this.timePlayedPreviously = 0;
        this.totalExpGained = 0;
        this.weaponsAcquired = 0;
    }

    @Override
    public void restore(final GameStatistics saved) {
        this.armorsAcquired = saved.getArmorsAcquired();
        this.bossesKilled = saved.getBossesKilled();
        this.monstersKilled = saved.getMonstersKilled();
        this.monstersMet = saved.getMonstersMet();
        this.newGames = saved.getNewGames();
        // this.timePlayed.setAlreadyPlayedTime(this.getTimePlayed());
        if (saved.isCountingTime()) {
            this.startCountingTime();
        } else {
            this.stopCountingTime();
        }
        this.timeDelta = saved.getTimePlayed();
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
    /*
     * private void writeObject(final ObjectOutputStream out) throws IOException { final boolean restart =
     * this.timePlayed.isRunning(); this.increaseTotalTimePlayed(this.timePlayed.resetTimer());
     * out.defaultWriteObject(); if (restart) { this.resumeCountingTime(); } }
     */
    /*
     * private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
     * in.defaultReadObject(); // this.startCountingTime(); }
     */

    /**
     * HashCode method implemented using auto generation.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 1;
        hash = HASHCODE_PRIME * hash + this.armorsAcquired;
        hash = HASHCODE_PRIME * hash + this.bossesKilled;
        hash = HASHCODE_PRIME * hash + this.monstersKilled;
        hash = HASHCODE_PRIME * hash + this.monstersMet;
        hash = HASHCODE_PRIME * hash + this.newGames;
        long temp;
        temp = Double.doubleToLongBits(this.startingTime);
        hash = HASHCODE_PRIME * hash + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.timeDelta);
        hash = HASHCODE_PRIME * hash + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.timePlayedPreviously);
        hash = HASHCODE_PRIME * hash + (int) (temp ^ (temp >>> 32));
        hash = HASHCODE_PRIME * hash + this.totalExpGained;
        hash = HASHCODE_PRIME * hash + this.weaponsAcquired;
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final GameStatistics test = (GameStatistics) obj;

        return this.armorsAcquired == test.getArmorsAcquired() && this.bossesKilled == test.getBossesKilled()
                        && this.monstersKilled == test.getMonstersKilled() && this.monstersMet == test.getMonstersMet()
                        && this.newGames == test.getNewGames() && this.getTimePlayed() == test.getTimePlayed()
                        && this.getTotalTimePlayed() == test.getTotalTimePlayed()
                        && this.totalExpGained == test.getTotalExpGained()
                        && this.weaponsAcquired == test.getWeaponsAcquired();
    }

}
