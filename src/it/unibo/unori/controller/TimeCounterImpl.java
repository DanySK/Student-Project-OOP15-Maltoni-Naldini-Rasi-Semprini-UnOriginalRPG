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
    private double startingTime;
    private double endingTime;
    private double timeDelta;
    private static final double NOT_STARTED = -1;
    private static final double NOT_STOPPED = -2;

    /**
     * This constructor starts a new timer with specified already played time and let you choose if start it or not.
     * 
     * @param alreadyPlayedTime
     *            the time previously played
     * @param startNow
     *            if the timer should start immediately during instantiation
     */
    public TimeCounterImpl(final double alreadyPlayedTime, final boolean startNow) {
        this.alreadyPlayedTime = alreadyPlayedTime;
        if (startNow) {
            this.startTimer();
        } else {
            this.startingTime = NOT_STARTED;
        }
    }

    /**
     * This constructor starts a new timer with specified already played time and starts it.
     * 
     * @param alreadyPlayedTime
     *            the time previously played
     */
    public TimeCounterImpl(final double alreadyPlayedTime) {
        this(alreadyPlayedTime, true);
    }

    /**
     * This constructor starts a new timer with no already played time, and let you choose if start it or not.
     * 
     * @param startNow
     *            if the timer should start immediately during instantiation
     */
    public TimeCounterImpl(final boolean startNow) {
        this(0, startNow);
    }

    /**
     * Default constructor; starts a new timer with no already played time and starts it.
     */
    public TimeCounterImpl() {
        this(0);
    }

    @Override
    public void setAlreadyPlayedTime(final double time) {
        this.alreadyPlayedTime = time;
    }

    @Override
    public double getAlreadyPlayedTime() {
        return this.alreadyPlayedTime;
    }

    @Override
    public double getTotalTime() {
        if (isRunning()) {
            return this.alreadyPlayedTime + this.getPlayingTime();
        } else {
            return this.alreadyPlayedTime;
        }
    }

    @Override
    public double getPlayingTime() {
        if (isRunning()) {
            this.stopTimer();
            return this.getAndResumeTimer();
        } else {
            return this.timeDelta;
        }
    }

    @Override
    public final void startTimer() {
        this.alreadyPlayedTime += this.timeDelta;
        this.timeDelta = 0;
        this.startingTime = System.currentTimeMillis();
        this.endingTime = NOT_STOPPED;
    }

    @Override
    public void stopTimer() {
        this.endingTime = System.currentTimeMillis();
        this.timeDelta = this.timeDelta + (this.endingTime - this.startingTime);
        this.startingTime = NOT_STARTED;
    }
    @Override
    public double getAndResumeTimer() {
        this.startingTime = System.currentTimeMillis();
        this.endingTime = NOT_STOPPED;
        return this.timeDelta;
    }

    @Override
    public double resetTimer() {
        this.stopTimer();
        final double retVal = this.getTotalTime();
        this.alreadyPlayedTime = 0;
        this.timeDelta = 0;
        return retVal;
    }

    @Override
    public boolean isRunning() {
        return this.startingTime != NOT_STARTED && this.endingTime == NOT_STOPPED;
    }
}
