package edu.uw.tcss.model;

import java.util.EnumSet;
import java.util.Map;

/**
 *
 * @author Raiden H
 * @version 23-10-2024
 */
public class Atv extends AbstractVehicle {
    /**
     * EnumSet list of allowed terrain for Truck vehicles
     */
    private static final EnumSet<Terrain> ALLOWED_TERRAIN = EnumSet.of(
            Terrain.STREET, Terrain.LIGHT, Terrain.CROSSWALK, Terrain.GRASS, Terrain.TRAIL);
    /**
     * this vehicle's poke tolerance
     */
    private static final int ATV_TOLERANCE = 25;
    /**
     * Constructor for the Atv class.
     *
     * @param theX x coordinate of the created atv
     * @param theY x coordinate of the created atv
     * @param theDir direction the created atv is facing
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, ATV_TOLERANCE);
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return ALLOWED_TERRAIN.contains(theTerrain);
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction choice = getDirection().reverse();
        while (choice == getDirection().reverse()) {
            choice = Direction.random();
        }
        return choice;
    }
}