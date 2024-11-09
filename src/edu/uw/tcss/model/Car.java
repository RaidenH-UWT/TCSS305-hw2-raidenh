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
     * this vehicle's poke tolerance
     */
    private static final int CAR_TOLERANCE = 15;
    /**
     * Constructor for the Car class.
     *
     * @param theX x coordinate of the created car
     * @param theY x coordinate of the created car
     * @param theDir direction the created car is facing
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, CAR_TOLERANCE);
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = ALLOWED_TERRAIN.contains(theTerrain);
        if (theTerrain == Terrain.LIGHT && theLight == Light.RED
                || theTerrain == Terrain.CROSSWALK && theLight != Light.GREEN) {
            result = false;
        }
        return result;
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction choice = getDirection().reverse();
        if (ALLOWED_TERRAIN.contains(theNeighbors.get(getDirection()))) {
            choice = getDirection();
        } else if (ALLOWED_TERRAIN.contains(theNeighbors.get(getDirection().left()))) {
            choice = getDirection().left();
        } else if (ALLOWED_TERRAIN.contains(theNeighbors.get(getDirection().right()))) {
            choice = getDirection().right();
        }
        return choice;
    }
}