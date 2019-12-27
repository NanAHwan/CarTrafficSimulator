/*
 * (C) Copyright 2009-2012 Apollo Group, Inc. All Rights Reserved.
 */


// TODO: Auto-generated Javadoc
/**
 * The Enum TrafficLightState.
 *
 * @author bxtrembl
 */
public enum TrafficLightState {

    GREEN("Green"),
    RED("Red");

    /** Gets the by string value. */
    private String value;

    /**
     * Instantiates a new status.
     *
     * @param value
     *            the value
     */
    private TrafficLightState(String value) {
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Gets the by string value.
     *
     * @param value
     *            the value
     * @return the by string value
     */
    public static TrafficLightState getByStringValue(final String value) {
        if (value != null) {
            for (final TrafficLightState statusEnum : TrafficLightState.values()) {
                if (statusEnum.getValue().equals(value)) {
                    return statusEnum;
                }
            }
        }
        return GREEN;
    }

    /**
     * Gets by name.
     *
     * @param name
     *            the name
     * @return the by name
     */
    public static TrafficLightState getByName(final String name) {
        if (name != null) {
            for (final TrafficLightState statusEnum : TrafficLightState.values()) {
                if (statusEnum.name().equals(name)) {
                    return statusEnum;
                }
            }
        }
        return GREEN;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return getValue();
    }

}