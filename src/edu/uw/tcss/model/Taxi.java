package edu.uw.tcss.model;

import java.util.EnumSet;
import java.util.Map;

/**
 *
 * @author Raiden H
 * @version 23-10-2024
 */
public class Taxi extends AbstractVehicle {
    /**
     * EnumSet list of allowed terrain for Truck vehicles
     */
    private static final EnumSet<Terrain> ALLOWED_TERRAIN = EnumSet.of(
            Terrain.STREET, Terrain.LIGHT, Terrain.CROSSWALK);
    /**
     * how long the taxi will wait at a red crosswalk
     */
    private int myPatience = 3;
    /**
     * Constructor for the Taxi class.
     *
     * @param theX x coordinate of the created taxi
     * @param theY x coordinate of the created taxi
     * @param theDir direction the created taxi is facing
     */
    public Taxi(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, 15);
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = ALLOWED_TERRAIN.contains(theTerrain);
        if (theTerrain == Terrain.LIGHT && theLight == Light.RED) {
            result = false;
        } else if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
            if (myPatience == 0) {
                myPatience = 3;
            } else {
                result = false;
                myPatience--;
            }
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