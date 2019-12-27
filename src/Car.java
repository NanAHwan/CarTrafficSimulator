/*
 * (C) Copyright 2009-2012 Apollo Group, Inc. All Rights Reserved.
 */

import java.util.Observable;

// TODO: Auto-generated Javadoc
/**
 * The Class Car.
 *
 * The car is moved along the road by Observable.update() as long as the car is
 * not at a red traffic light.
 */
public class Car extends RoadItem {

    /** The color. */
    private String color = "black";

    /** The index. */
    private int index = 0;

    /** The moving. */
    private boolean moving = false;

    /** The road. */
    private Road road;

    /** The initial position. */
    private int initialPosition = 0;

    public int getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(int initialPosition) {
        this.initialPosition = initialPosition;
    }

    /**
     * Instantiates a new car.
     *
     * @param road
     *            the road
     * @param name
     *            the name
     * @param initialPosition
     *            the initial position
     * @param index
     *            the index
     */
    public Car(final Road road, final String name, final int initialPosition,
               final int index) {
        this.road = road;
        this.setName(name);
        this.initialPosition = initialPosition;
        this.setPosition(initialPosition);
        this.setIndex(index);
        // TODO: moving needs to be initially false, but update is not currently
        // passing in the running state of the clock.
        this.moving = true;
    }

    /**
     * Gets the color.
     *
     * @return the color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Gets the index.
     *
     * @return the index
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * Checks if is moving.
     *
     * @return the moving
     */
    public boolean isMoving() {
        return this.moving;
    }

    /**
     * Sets the value of color.
     *
     * @param color
     *            The value of color.
     */
    public void setColor(final String color) {
        this.color = color;
    }

    /**
     * Sets the value of index.
     *
     * @param index
     *            The value of index.
     */
    public void setIndex(final int index) {
        this.index = index;
    }

    /** {@inheritDoc} */
    @Override
    public void update(final Observable clock, final Object timeWrapper) {
        int time = (Integer) timeWrapper;
        TrafficLight trafficLight = this.road.getTrafficLight();
        // we know it's 50...
        // If a car is at a red light, it waits until the light is green.
        int trafficLightPosition = trafficLight.getPosition();
        if (this.getPosition() == trafficLightPosition) {
            this.moving = (trafficLight.getState() == TrafficLightState.GREEN);
        }
        if (!this.moving) {
            return;
        }
        int position = this.initialPosition + time;
        this.setPosition(position);
    }

}