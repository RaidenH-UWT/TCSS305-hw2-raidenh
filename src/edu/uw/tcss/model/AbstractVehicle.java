package edu.uw.tcss.model;

import java.util.Map;

/**
 *
 * @author Raiden H
 * @version 23-10-2024
 */
public abstract class AbstractVehicle implements Vehicle {
    // an abstract class cannot create objects and can only be accessed from it's children
    // an abstract method can only be used in the abstract class and does not have a body,
    // it's overridden by children
    /**
     *
     */
    protected int myX;
    /**
     *
     */
    protected int myY;
    /**
     *
     */
    protected Direction myDir;

    protected AbstractVehicle(final int theX, final int theY, final Direction theDir) {
        super();
        myX = theX;
        myY = theY;
        myDir = theDir;
    }
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return false;
    }

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        return null;
    }

    @Override
    public void collide(final Vehicle theOther) {

    }

    @Override
    public int getDeathTime() {
        return 0;
    }

    @Override
    public String getImageFileName() {
        return "";
    }

    @Override
    public Direction getDirection() {
        return myDir;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public void poke() {

    }

    @Override
    public void reset() {

    }

    @Override
    public void setDirection(final Direction theDir) {

    }

    @Override
    public void setX(final int theX) {

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
