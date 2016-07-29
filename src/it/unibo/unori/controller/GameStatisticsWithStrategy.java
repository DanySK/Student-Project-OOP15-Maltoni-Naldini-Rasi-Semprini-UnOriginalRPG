package it.unibo.unori.controller;

/**
 * This class implements a GameStatistics with the counting time implemented with a
 * {@link it.unibo.unori.controller.TimeCounter} as an external strategy.
 * 
 * @see it.unibo.unori.controller.GameStatistics
 */
public class GameStatisticsWithStrategy implements GameStatistics {
    private static final long serialVersionUID = 4311702831282895612L;
    private int newGames;
    private int monstersMet;
    private int monstersKilled;
    private int bossesKilled;
    private int weaponsAcquired;
    private int armorsAcquired;
    private int totalExpGained;
    private final TimeCounter timePlayed = new TimeCounterImpl(0, false);

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
        /*
         * if (!this.isCountingTime()) { this.startingTime = System.currentTimeMillis(); }
         */ this.timePlayed.startTimer();
    }

    @Override
    public boolean isCountingTime() {
        // return this.startingTime != NOT_STARTED;
        return this.timePlayed.isRunning();
    }

    @Override
    public void pauseCountingTime() {
        /*
         * final double endingTime = System.currentTimeMillis(); this.timeDelta = endingTime - this.startingTime;
         * this.startingTime = NOT_STARTED;
         */
        this.timePlayed.pauseTimer();
    }

    @Override
    public void stopCountingTime() {
        /*
         * this.pauseCountingTime(); this.timePlayedPreviously += this.timeDelta; this.timeDelta = 0;
         */
        this.timePlayed.stopTimer();
    }

    @Override
    public double getTimePlayed() {
        /*
         * if (this.isCountingTime()) { this.pauseCountingTime(); // Pause the counting makes it update the timeDelta
         * this.startCountingTime(); } return this.timeDelta;
         */
        return this.timePlayed.getPlayingTime();
    }

    @Override
    public double getTotalTimePlayed() {
        // return this.timePlayedPreviously + this.getTimePlayed();
        return this.timePlayed.getTotalTime();
    }

    @Override
    public void increaseTotalTimePlayed(final double increment) {
        // this.timePlayedPreviously += increment;
        this.timePlayed.setAlreadyPlayedTime(this.timePlayed.getAlreadyPlayedTime() + increment);
    }

    @Override
    public void reset() {
        this.armorsAcquired = 0;
        this.bossesKilled = 0;
        this.monstersKilled = 0;
        this.monstersMet = 0;
        this.newGames = 0;
        this.timePlayed.resetTimer();
        /*
         * this.startingTime = NOT_STARTED; this.timeDelta = 0; this.timePlayedPreviously = 0;
         */
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
        this.timePlayed.setAlreadyPlayedTime(saved.getTotalTimePlayed());
        /*
         * this.timeDelta = saved.getTimePlayed(); this.timePlayedPreviously = saved.getTotalTimePlayed() -
         * saved.getTimePlayed(); this.totalExpGained = saved.getTotalExpGained(); this.weaponsAcquired =
         * saved.getWeaponsAcquired();
         */
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

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + armorsAcquired;
        result = prime * result + bossesKilled;
        result = prime * result + monstersKilled;
        result = prime * result + monstersMet;
        result = prime * result + newGames;
        result = prime * result + ((timePlayed == null) ? 0 : timePlayed.hashCode());
        result = prime * result + totalExpGained;
        result = prime * result + weaponsAcquired;
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
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
