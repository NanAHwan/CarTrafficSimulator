/*
 * (C) Copyright 2009-2012 Apollo Group, Inc. All Rights Reserved.
 */


import java.util.Observable;
import java.util.Observer;


/**
 * The Class RoadItem.
 *
 * Road Items observe the clock.
 */
public class RoadItem implements Observer {

    /** The name. */
    private String name = "";
    /** The position. */
    private int position = 0;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the value of name.
     *
     * @param name
     *            The value of name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the value of position.
     *
     * @param position
     *            The value of position.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        // return this.name + "@" + this.position;
        return String.valueOf(this.position);
    }

    /** {@inheritDoc} */
    @Override
    public void update(Observable arg0, Object arg1) {
        int time = (Integer) arg1;
        if (time > 0) {
            this.position++;
        }
    }
}