package it.unibo.unori.controller;

/**
 * This implementation of TimeCounter calculates elapsed time by saving the starting time and calculating when needed.
 */
public class TimeCounterImpl implements TimeCounter {
    /**
     * 
     */
    private static final long serialVersionUID = -7207035105151035362L;
    private double alreadyPlayedTime;
    private double startingHour;
    private static final double NOT_STARTED = -1;

    TimeCounterImpl(final double alreadyPlayedTime, final boolean startNow) {
        this.alreadyPlayedTime = alreadyPlayedTime;
        if (startNow) {
            this.startTimer();
        } else {
            this.startingHour = NOT_STARTED;
        }
    }

    TimeCounterImpl(final double alreadyPlayedTime) {
        this(alreadyPlayedTime, true);
    }

    TimeCounterImpl(final boolean startNow) {
        this(0, startNow);
    }

    TimeCounterImpl() {
        this(0);
    }

    @Override
    public double getTotalTime() {
        if (this.startingHour == NOT_STARTED) {
            return this.alreadyPlayedTime;
        } else {
            return this.alreadyPlayedTime + this.getPlayingTime();
        }

    }

    @Override
    public double getPlayingTime() {
        if (this.startingHour == NOT_STARTED) {
            return 0;
        } else {
            return System.currentTimeMillis() - this.startingHour;
        }
    }

    @Override
    public void stopTimer() {
        this.alreadyPlayedTime += this.getPlayingTime();
        this.startingHour = NOT_STARTED;
    }

    @Override
    public final void startTimer() {
        this.startingHour = System.currentTimeMillis();
    }

    @Override
    public void setAlreadyPlayedTime(final double time) {
        this.alreadyPlayedTime = time;
    }

    @Override
    public double getAlreadyPlayedTime() {
        return this.alreadyPlayedTime;
    }
}
