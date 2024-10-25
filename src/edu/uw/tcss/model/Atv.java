package edu.uw.tcss.model;

import java.util.Map;

/**
 *
 * @author Raiden H
 * @version 23-10-2024
 */
public class Atv extends AbstractVehicle {
    /**
     * Constructor for the Atv class.
     *
     * @param theX x coordinate of the created atv
     * @param theY x coordinate of the created atv
     * @param theDir direction the created atv is facing
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, 25);
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain != Terrain.WALL;
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