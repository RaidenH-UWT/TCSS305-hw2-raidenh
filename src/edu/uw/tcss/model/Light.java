/*
 * TCSS 305 - Road Rage
 */

package edu.uw.tcss.model;

/**
 * An enumeration of traffic light statuses.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler (acfowler@u.washington.edu)
 * @version 1.1
 */

@SuppressWarnings("PublicMethodNotExposedInInterface")
public enum Light {
    /**
     * Green means go.
     */
    GREEN,

    /**
     * Yellow means caution.
     */
    YELLOW,

    /**
     * Red means stop.
     */
    RED;

    /**
     * Returns the next light in sequence after this one. The sequence is GREEN,
     * YELLOW, RED.
     * 
     * @return the next light in sequence.
     */
    public Light advance() {
        Light result = Light.GREEN;

        switch (this) {
            case GREEN:
                result = YELLOW;
                break;

            case YELLOW:
                result = RED;
                break;

            case RED:
                break;

            default:
        }

        return result;
    }
}
