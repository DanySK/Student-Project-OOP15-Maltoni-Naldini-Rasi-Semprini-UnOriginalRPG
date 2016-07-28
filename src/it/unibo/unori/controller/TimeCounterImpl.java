package it.unibo.unori.controller;

/**
 * This implementation of TimeCounter calculates elapsed time by saving the starting time and calculating when needed.
 */
public class TimeCounterImpl implements TimeCounter {
    private static final long serialVersionUID = -7207035105151035362L;
    private double alreadyPlayedTime;
    private double startingTime;
    private double timeDelta;
    private static final double NOT_STARTED = -1;

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
        if (this.isRunning()) {
            this.pauseTimer(); // Pause the counting makes it update the timeDelta
            this.startTimer();
        }
        return this.timeDelta;
    }

    @Override
    public final void startTimer() {
        if (!this.isRunning()) {
            this.startingTime = System.currentTimeMillis();
        }
    }

    @Override
    public void stopTimer() {
        this.pauseTimer();
        this.alreadyPlayedTime += this.timeDelta;
        this.timeDelta = 0;
    }

    @Override
    public void pauseTimer() {
        final double endingTime = System.currentTimeMillis();
        this.timeDelta = endingTime - this.startingTime;
        this.startingTime = NOT_STARTED;
    }

    @Override
    public double resetTimer() {
        this.stopTimer();
        final double retVal = this.getTotalTime();
        this.alreadyPlayedTime = 0;
        return retVal;
    }

    @Override
    public final boolean isRunning() {
        return this.startingTime != NOT_STARTED;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(alreadyPlayedTime);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(startingTime);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(timeDelta);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
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
        final TimeCounterImpl other = (TimeCounterImpl) obj;
        return new Double(this.alreadyPlayedTime).equals(new Double(other.alreadyPlayedTime))
                        && new Double(this.startingTime).equals(new Double(other.startingTime))
                        && new Double(this.timeDelta).equals(new Double(other.timeDelta));
    }
}
