package edu.uw.tcss.model;

import java.util.Map;

/**
 *
 * @author Raiden H
 * @version 23-10-2024
 */
public class Atv extends AbstractVehicle {
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, 25);
    }

    @Override
    public boolean canPass(Terrain theTerrain, Light theLight) {
        return false;
    }

    @Override
    public Direction chooseDirection(Map<Direction, Terrain> theNeighbors) {
        return null;
    }
}