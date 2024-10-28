package edu.uw.tcss.model;

import java.util.EnumSet;
import java.util.Map;

/**
 *
 * @author Raiden H
 * @version 23-10-2024
 */
public class Bicycle extends AbstractVehicle {
    /**
     * EnumSet list of allowed terrain for Truck vehicles
     */
    private static final EnumSet<Terrain> ALLOWED_TERRAIN = EnumSet.of(
            Terrain.STREET, Terrain.LIGHT, Terrain.CROSSWALK, Terrain.TRAIL);
    /**
     * Constructor for the Bicycle class.
     *
     * @param theX x coordinate of the created bicycle
     * @param theY x coordinate of the created bicycle
     * @param theDir direction the created bicycle is facing
     */
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, 35);
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = ALLOWED_TERRAIN.contains(theTerrain);
        if (theTerrain == Terrain.LIGHT && theLight != Light.GREEN
                || theTerrain == Terrain.CROSSWALK && theLight != Light.GREEN) {
            result = false;
        }
        return result;
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction choice = getDirection().reverse();
        if (theNeighbors.containsValue(Terrain.TRAIL)) {
            for (final Map.Entry<Direction, Terrain> entry : theNeighbors.entrySet()) {
                if (entry.getValue() == Terrain.TRAIL) {
                    choice = entry.getKey();
                }
            }
        } else if (ALLOWED_TERRAIN.contains(theNeighbors.get(getDirection()))) {
            choice = getDirection();
        } else if (ALLOWED_TERRAIN.contains(theNeighbors.get(getDirection().left()))) {
            choice = getDirection().left();
        } else if (ALLOWED_TERRAIN.contains(theNeighbors.get(getDirection().right()))) {
            choice = getDirection().right();
        }
        return choice;
    }
}