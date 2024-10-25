package edu.uw.tcss.model;

import java.util.EnumSet;
import java.util.Map;

/**
 *
 * @author Raiden H
 * @version 23-10-2024
 */
public class Car extends AbstractVehicle {
    /**
     * EnumSet list of allowed terrain for Car vehicles
     */
    private static final EnumSet<Terrain> ALLOWED_TERRAIN = EnumSet.of(
            Terrain.STREET, Terrain.LIGHT, Terrain.CROSSWALK);
    /**
     * Constructor for the Car class.
     *
     * @param theX x coordinate of the created car
     * @param theY x coordinate of the created car
     * @param theDir direction the created car is facing
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, 15);
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return false;
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        return null;
    }
}