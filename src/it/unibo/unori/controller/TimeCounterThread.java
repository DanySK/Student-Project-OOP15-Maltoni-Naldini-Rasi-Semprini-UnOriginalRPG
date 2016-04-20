package it.unibo.unori.controller;

/**
 * Runnable class for time counting purpose.
 */
public class TimeCounterThread implements Runnable {
    private double time = 0;
    private final double startingTime;
    private boolean cycle = false;

    /**
     * Constructor that instantiate timer for a loaded game.
     * 
     * @param startingTime
     *            the number of seconds played saved in the savegame loaded
     */
    public TimeCounterThread(final double startingTime) {
        this.startingTime = startingTime;
    }

    /**
     * Constructor that instantiate timer for a new game.
     */
    public TimeCounterThread() {
        this(0);
    }

    @Override
    public void run() {
        cycle = true;
        while (cycle) {
            this.time++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                cycle = false;
                e.printStackTrace();
            }
        }
    }

    /**
     * Get the total time played in seconds.
     * 
     * @return the time elapsed from when the player created new game
     */
    public double getTotalTime() {
        return this.startingTime + this.time;
    }

    /**
     * Get the time played in seconds.
     * 
     * @return the time elapsed from when the player loaded the save
     */
    public double getPlayingTime() {
        return this.time;
    }

    /**
     * This method stops the timer.
     */
    public void stopTimer() {
        this.cycle = false;
    }

    /**
     * This methods starts the timer if it was previously stopped.
     */
    public void startTimer() {
        this.cycle = true;
    }
}
