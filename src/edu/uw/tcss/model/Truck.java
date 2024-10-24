package edu.uw.tcss.model;

/**
 *
 * @author Raiden H
 * @version 23-10-2024
 */
public class Truck extends AbstractVehicle {
    /**
     *
     * @param theX x coordinate of the created truck
     * @param theY x coordinate of the created truck
     * @param theDir direction the created truck is facing
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, 0);
    }
}