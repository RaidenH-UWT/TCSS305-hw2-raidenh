package edu.uw.tcss.model;

import java.util.EnumSet;
import java.util.Map;

/**
 *
 * @author Raiden H
 * @version 23-10-2024
 */
public class Human extends AbstractVehicle {
    /**
     * EnumSet list of allowed terrain for Human vehicles
     */
    private static final EnumSet<Terrain> ALLOWED_TERRAIN = EnumSet.of(
            Terrain.GRASS, Terrain.CROSSWALK);
    /**
     * Constructor for the Human class.
     *
     * @param theX x coordinate of the created human
     * @param theY x coordinate of the created human
     * @param theDir direction the created human is facing
     */
    public Human(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, 45);
    }

    //i really wanted to push this up to the abstract class cause it's very similar
    //unfortunately the unique light conditions make it impossible i think :(
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = ALLOWED_TERRAIN.contains(theTerrain);
        if (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN) {
            result = false;
        }
        return result;
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction choice = getDirection().reverse();
        if (theNeighbors.containsValue(Terrain.CROSSWALK)
                && theNeighbors.get(getDirection().reverse()) != Terrain.CROSSWALK) {
            for (final Direction dir : theNeighbors.keySet()) {
                if (theNeighbors.get(dir) == Terrain.CROSSWALK) {
                    choice = dir;
                }
            }
        } else {
            if (canPass(theNeighbors.get(getDirection()), Light.GREEN)
                || canPass(theNeighbors.get(getDirection().left()), Light.GREEN)
                || canPass(theNeighbors.get(getDirection().right()), Light.GREEN)) {
                while (choice == getDirection().reverse()) {
                    choice = Direction.random();
                }
            }
        }
        return choice;
    }
}