/*
 * (C) Copyright 2009-2012 Apollo Group, Inc. All Rights Reserved.
 */


import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * The Class TrafficLight.
 *
 * The traffic light is either red or green, and this is based on time.
 */
public class TrafficLight extends RoadItem {

    /** The current time in seconds. */
    private int currentTimeInSeconds = 0;

    /** The state. */
    private TrafficLightState state = TrafficLightState.RED;

    /** The TRACKPOSITION. */
    private final int TRACKPOSITION = 50;

    /**
     * Instantiates a new traffic light.
     */
    public TrafficLight() {
        this.setState(TrafficLightState.GREEN);
        this.setPosition(this.TRACKPOSITION);
        this.setName("Stop Light");
    }

    /**
     * Gets the current time in seconds.
     *
     * @return the currentTimeInSeconds
     */
    public int getCurrentTimeInSeconds() {
        return this.currentTimeInSeconds;
    }

    /**
     * Gets the state.
     *
     * @return the state
     */
    public TrafficLightState getState() {
        return this.state;
    }

    /**
     * Sets the value of state.
     *
     * @param state
     *            The value of state.
     */
    public void setState(final TrafficLightState state) {
        this.state = state;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.state.toString();
    }

    /** {@inheritDoc} */
    @Override
    public void update(final Observable arg0, final Object arg1) {
        int time = (Integer) arg1;
        this.currentTimeInSeconds = time;

        // use the clock time so we can simulate state
        // if update this observer with time = 35, the state will be correct for 35
        if ((currentTimeInSeconds > 0) && ((currentTimeInSeconds+1) % 10 == 0)) {
            this.toggleLight();
        }
    }

    /**
     * Toggle light.
     */
    private void toggleLight() {
        if (this.state == TrafficLightState.GREEN) {
            this.state = TrafficLightState.RED;
        } else if (this.state == TrafficLightState.RED) {
            this.state = TrafficLightState.GREEN;
        }
    }
}