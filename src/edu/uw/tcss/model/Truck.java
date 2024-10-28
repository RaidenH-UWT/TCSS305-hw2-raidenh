package edu.uw.tcss.model;

import java.util.EnumSet;
import java.util.Map;

/**
 *
 * @author Raiden H
 * @version 23-10-2024
 */
public class Truck extends AbstractVehicle {
    /**
     * EnumSet list of allowed terrain for Truck vehicles
     */
    private static final EnumSet<Terrain> ALLOWED_TERRAIN = EnumSet.of(
            Terrain.STREET, Terrain.LIGHT, Terrain.CROSSWALK);
    /**
     * this vehicle's poke tolerance
     */
    private static final int TRUCK_TOLERANCE = 0;
    /**
     * Constructor for the Truck class.
     *
     * @param theX x coordinate of the created truck
     * @param theY x coordinate of the created truck
     * @param theDir direction the created truck is facing
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, TRUCK_TOLERANCE);
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = ALLOWED_TERRAIN.contains(theTerrain);
        if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
            result = false;
        }
        return result;
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction choice = Direction.random();
        if (!ALLOWED_TERRAIN.contains(theNeighbors.get(getDirection()))
                && !ALLOWED_TERRAIN.contains(theNeighbors.get(getDirection().left()))
                && !ALLOWED_TERRAIN.contains(theNeighbors.get(getDirection().right()))) {
            choice = getDirection().reverse();
        } else {
            while (choice == getDirection().reverse()
                    || !ALLOWED_TERRAIN.contains(theNeighbors.get(choice))) {
                choice = Direction.random();
            }
        }
        return choice;
    }
}