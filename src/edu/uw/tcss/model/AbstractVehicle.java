package edu.uw.tcss.model;

import java.util.Map;

/**
 *
 * @author Raiden H
 * @version 23-10-2024
 */
//warning conflicts with CheckStyle warning, I'm giving CheckStyle higher priority.
@SuppressWarnings("StringBufferReplaceableByString")
public abstract class AbstractVehicle implements Vehicle {
    // an abstract class cannot create objects and can only be accessed from it's children
    // an abstract method can only be used in the abstract class and does not have a body,
    // it's overridden by children
    /**
     * x coordinate of this AbstractVehicle
     */
    protected int myX;
    /**
     * y coordinate of this abstract vehicle
     */
    protected int myY;
    /**
     * direction this AbstractVehicle is facing
     */
    protected Direction myDir;
    /**
     * amount of pokes before this AbstractVehicle revives
     */
    protected int myPokeTolerance;
    /**
     * current poke count of this AbstractVehicle
     */
    protected int myPokeCount;

    protected AbstractVehicle(final int theX, final int theY,
                              final Direction theDir, final int thePokeTolerance) {
        super();
        myX = theX;
        myY = theY;
        myDir = theDir;
        myPokeTolerance = thePokeTolerance;
        myPokeCount = 0;
    }

    @Override
    public abstract boolean canPass(Terrain theTerrain, Light theLight);

    @Override
    public abstract Direction chooseDirection(Map<Direction, Terrain> theNeighbors);

    @Override
    public void collide(final Vehicle theOther) {
        if (isAlive() && theOther.isAlive()) {
            if (getDeathTime() > theOther.getDeathTime()) {
                myPokeCount = myPokeTolerance;
            }
        }
    }

    @Override
    public int getDeathTime() {
        return myPokeTolerance - myPokeCount;
    }

    @Override
    public String getImageFileName() {
        final String filename;
        if (isAlive()) {
            filename = getClass().getSimpleName().toLowerCase() + ".gif";
        } else {
            filename = getClass().getSimpleName().toLowerCase() + "_dead.gif";
        }
        return filename;
    }

    @Override
    public Direction getDirection() {
        return myDir;
    }

    @Override
    public int getX() {
        return myX;
    }

    @Override
    public int getY() {
        return myY;
    }

    @Override
    public boolean isAlive() {
        return myPokeCount == 0;
    }

    @Override
    public void poke() {
        myPokeCount--;
        if (isAlive()) {
            setDirection(Direction.random());
        }
    }

    @Override
    public void reset() {
        //TODO: figure out if there's a better way to do this than just storing initial state.
    }

    @Override
    public void setDirection(final Direction theDir) {
        myDir = theDir;
    }

    @Override
    public void setX(final int theX) {
        myX = theX;
    }

    @Override
    public void setY(final int theY) {
        myY = theY;
    }

    @Override
    public String toString() {
        final StringBuilder output = new StringBuilder();
        output.append(getX());
        output.append(", ");
        output.append(getY());
        output.append("; ");
        output.append(getDirection());
        return output.toString();
    }
}
