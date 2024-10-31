package edu.uw.tcss.model;

/**
 *
 * @author Raiden H
 * @version 23-10-2024
 */
//warning conflicts with CheckStyle warning, I'm giving CheckStyle higher priority.
@SuppressWarnings("StringBufferReplaceableByString")
public abstract class AbstractVehicle implements Vehicle {
    /**
     * x coordinate of this AbstractVehicle
     */
    private int myX;
    /**
     * y coordinate of this abstract vehicle
     */
    private int myY;
    /**
     * direction this AbstractVehicle is facing
     */
    private Direction myDir;
    /**
     * amount of pokes before this AbstractVehicle revives
     */
    private final int myPokeTolerance; // laugh at my variable names, do it
    /**
     * current poke count of this AbstractVehicle
     */
    private int myPokeCount;
    /**
     * initial x coordinate of this AbstractVehicle
     */
    private final int myInitX;
    /**
     * initial y coordinate of this abstract vehicle
     */
    private final int myInitY;
    /**
     * initial direction this AbstractVehicle is facing
     */
    private final Direction myInitDir;


    protected AbstractVehicle(final int theX, final int theY,
                              final Direction theDir, final int thePokeTolerance) {
        super();
        myX = theX;
        myY = theY;
        myDir = theDir;
        myPokeTolerance = thePokeTolerance;
        myPokeCount = 0;

        myInitX = theX;
        myInitY = theY;
        myInitDir = theDir;
    }

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
        return myPokeTolerance;
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
        return myPokeTolerance - myPokeCount == myPokeTolerance;
    }

    //TODO: HAS TO SET TO A VALID DIRECTION FOR THE SPECIFIC VEHICLE
    @Override
    public void poke() {
        myPokeCount--;
        if (isAlive()) {
            setDirection(Direction.random());
            myPokeCount = 0;
        }
    }

    @Override
    public void reset() {
        //TODO: figure out if there's a better way to do this than just storing initial state.
        myX = myInitX;
        myY = myInitY;
        myDir = myInitDir;
        myPokeCount = 0;
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
