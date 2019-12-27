/*
 * (C) Copyright 2009-2012 Apollo Group, Inc. All Rights Reserved.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

// TODO: Auto-generated Javadoc
/**
 * The Class Road.
 * The container for cars and the single traffic light.
 * Owns and controls the clock which determines the state of road items.
 *
 */
public class Road implements Observer {

    /** The cars. */
    private List<Car> cars;

    /** The clock. */
    private Clock clock;

    /** The traffic light. */
    private TrafficLight trafficLight;

    /**
     * The main method.
     *
     * @param args
     *            the arguments
     */
    public static void main(final String[] args) {
        Road road = new Road();
        road.addCar("Bob", 24);
        road.addCar("Betty", 12);
        road.addCar("Max", 0);
        road.getClock().start();
    }

    /**
     * Instantiates a new road.
     */
    public Road() {
        this.cars = new ArrayList<Car>();
        this.trafficLight = new TrafficLight();

        this.clock = new Clock();
        this.clock.addObserver(trafficLight);
        this.clock.addObserver(this);
    }

    /**
     * Adds the car.
     *
     * @param carName
     *            the car name
     * @return the int
     */
    public int addCar(final String carName, final int initialPosition) {
        int index = this.cars.size();
        Car newCar = new Car(this, carName, initialPosition, index);
        this.clock.addObserver(newCar);
        this.cars.add(newCar);
        Logger.log("Road.addCar: added car " + index + " to the road at position "
                + initialPosition + ".");
        return index;
    }

    /**
     * Gets the cars.
     *
     * @return the cars
     */
    public List<Car> getCars() {
        return this.cars;
    }

    /**
     * @return the clock
     */
    public Clock getClock() {
        return clock;
    }

    /**
     * @return the trafficLight
     */
    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    /** {@inheritDoc} */
    @Override
    public void update(Observable o, Object arg) {
        int time = (Integer) arg;
        Logger.log(time + " " + this.trafficLight + " " + this.cars.get(0) + " " + this.cars.get(1)
                + " " + this.cars.get(2));
    }
}