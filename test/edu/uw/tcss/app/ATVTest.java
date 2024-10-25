package edu.uw.tcss.app;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.uw.tcss.model.Atv;
import edu.uw.tcss.model.Bicycle;
import edu.uw.tcss.model.Car;
import edu.uw.tcss.model.Direction;
import edu.uw.tcss.model.Human;
import edu.uw.tcss.model.Light;
import edu.uw.tcss.model.Taxi;
import edu.uw.tcss.model.Terrain;
import edu.uw.tcss.model.Truck;
import edu.uw.tcss.model.Vehicle;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the ATV class.
 *
 * @author Raiden H
 * @version Oct 23 2024
 */
public class ATVTest {
    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 10;
    /**
     * test map for testing chooseDirection().
     */
    private static final Map<Direction, Terrain> TEST_MAP = new HashMap<>();
    /**
     * test ATV object for testing ATV methods.
     */
    private Vehicle myTestATV;
    @BeforeEach
    void setup() {
        myTestATV = new Atv(0, 0, Direction.NORTH);
    }

    @Test
    void testGetImageFileName() {
        assertEquals("atv.gif", myTestATV.getImageFileName(),
                "Living atv image filename is incorrect.");
        myTestATV.collide(new Truck(0, 0, Direction.NORTH));
        assertEquals("atv_dead.gif", myTestATV.getImageFileName(),
                "Dead atv image filename is incorrect.");
    }
    @Test
    void testCanPass() {
        assertAll("Testing canPass()",
                () -> assertTrue(myTestATV.canPass(Terrain.GRASS, Light.GREEN),
                        "ATV should be able to pass grass."),
                () -> assertTrue(myTestATV.canPass(Terrain.STREET, Light.GREEN),
                        "ATV should be able to pass streets."),
                () -> assertFalse(myTestATV.canPass(Terrain.WALL, Light.GREEN),
                        "ATV should not be able to pass walls."),
                () -> assertTrue(myTestATV.canPass(Terrain.TRAIL, Light.GREEN),
                        "ATV should be able to pass trails."),
                () -> assertTrue(myTestATV.canPass(Terrain.LIGHT, Light.GREEN),
                        "ATV should be able to pass green lights."),
                () -> assertTrue(myTestATV.canPass(Terrain.LIGHT, Light.YELLOW),
                        "ATV should be able to pass yellow lights."),
                () -> assertTrue(myTestATV.canPass(Terrain.LIGHT, Light.RED),
                        "ATV should be able to pass red lights."),
                () -> assertTrue(myTestATV.canPass(Terrain.CROSSWALK, Light.GREEN),
                        "ATV should be able to pass green-light crosswalks."),
                () -> assertTrue(myTestATV.canPass(Terrain.CROSSWALK, Light.YELLOW),
                        "ATV should be able to pass yellow-light crosswalks."),
                () -> assertTrue(myTestATV.canPass(Terrain.CROSSWALK, Light.RED),
                        "ATV should be able to pass red-light crosswalks.")
        );
    }

    @Test
    void testChooseDirection() {
        TEST_MAP.put(Direction.NORTH, Terrain.GRASS);
        TEST_MAP.put(Direction.SOUTH, Terrain.STREET);
        TEST_MAP.put(Direction.EAST, Terrain.TRAIL);
        TEST_MAP.put(Direction.WEST, Terrain.CROSSWALK);
        for (int i = 0; i < TRIES_FOR_RANDOMNESS; i++) {
            assertNotEquals(Direction.SOUTH, myTestATV.chooseDirection(TEST_MAP),
                    "ATV should never go backwards.");
        }
    }

    @Test
    void testCollideTruck() {
        myTestATV.collide(new Truck(0, 0, Direction.NORTH));
        assertFalse(myTestATV.isAlive(), "ATV should not survive collision with Truck.");
    }

    @Test
    void testCollideCar() {
        myTestATV.collide(new Car(0, 0, Direction.NORTH));
        assertFalse(myTestATV.isAlive(), "ATV should not survive collision with Car.");
    }

    @Test
    void testCollideTaxi() {
        myTestATV.collide(new Taxi(0, 0, Direction.NORTH));
        assertFalse(myTestATV.isAlive(), "ATV should not survive collision with Taxi.");
    }

    @Test
    void testCollideAtv() {
        myTestATV.collide(new Atv(0, 0, Direction.NORTH));
        assertTrue(myTestATV.isAlive(), "ATV should survive collision with Atv.");
    }

    @Test
    void testCollideBicycle() {
        myTestATV.collide(new Bicycle(0, 0, Direction.NORTH));
        assertTrue(myTestATV.isAlive(), "ATV should survive collision with Bicycle.");
    }

    @Test
    void testCollideHuman() {
        myTestATV.collide(new Human(0, 0, Direction.NORTH));
        assertTrue(myTestATV.isAlive(), "ATV should survive collision with Human.");
    }

    @Test
    void testIsAlive() {
        assertTrue(myTestATV.isAlive(),
                "ATV be alive by default.");
        myTestATV.collide(new Truck(0, 0, Direction.NORTH));
        assertFalse(myTestATV.isAlive(),
                "ATV should be dead here.");
    }
}