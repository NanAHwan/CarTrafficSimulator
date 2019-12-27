/*
 * (C) Copyright 2009-2012 Apollo Group, Inc. All Rights Reserved.
 */

import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * The Class Clock.
 *
 * The clock is observable by the road, and all road items.
 */
public class Clock extends Observable {

    /** The time in seconds. */
    private int timeInSeconds = 0;
    private final int MAXTIME = 100;
    private boolean running;
    /**
     *
     */
    public Clock() {
        this.timeInSeconds = 0;
    }

    /**
     * Gets the time in seconds.
     *
     * @return the timeInSeconds
     */
    public int getTimeInSeconds() {
        return this.timeInSeconds;
    }

    /**
     * Start.
     */
    public void start() {
        this.running = true;
        Logger.log("Clock.start()");

        // non-realtime non-asynchronous
        while (this.timeInSeconds < MAXTIME) {
            tick();
        }
        stop();
    }

    /**
     * Stop.
     */
    public void stop() {
        this.running = false;
        Logger.log("Clock.stop()");
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return String.valueOf(this.timeInSeconds);
    }

    /**
     * Tick.
     */
    private void tick() {
        // Logger.log("Clock.tick(): " + this.timeInSeconds);
        this.setChanged();
        this.notifyObservers(this.timeInSeconds);
        this.clearChanged();
        this.timeInSeconds++;
    }


    /**
     * @return the running
     */
    public boolean isRunning() {
        return running;
    }
}