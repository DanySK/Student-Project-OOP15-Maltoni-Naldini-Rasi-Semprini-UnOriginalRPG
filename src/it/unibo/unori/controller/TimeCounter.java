package it.unibo.unori.controller;

/**
 *
 */
public interface TimeCounter {
    /**
     * Get the total time played in milliseconds.
     * 
     * @return the time elapsed from when the player created new game
     */
    double getTotalTime();

    /**
     * Get the time played in milliseconds.
     * 
     * @return the time elapsed from when the player loaded the save
     */
    double getPlayingTime();

    /**
     * Sets the time played in previous moments.
     * 
     * @param time
     *            the played time in previous moments in milliseconds
     */
    void setAlreadyPlayedTime(final double time);

    /**
     * Gets the time played in previous moments.
     * 
     * @return the played time in previous moments in milliseconds
     */
    double getAlreadyPlayedTime();

    /**
     * This method stops the timer.
     */
    void stopTimer();

    /**
     * This methods starts the timer if it was previously stopped.
     */
    void startTimer();
}
