package edu.uw.tcss.app;

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
    private static final int TRIES_FOR_RANDOMNESS = 25;
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
    void testCanPassGrass() {
        final Terrain grass = Terrain.GRASS;
        assertTrue(myTestATV.canPass(grass, Light.GREEN),
                "ATV should be able to pass green-light grass.");
        assertTrue(myTestATV.canPass(grass, Light.YELLOW),
                "ATV should be able to pass yellow-light grass.");
        assertTrue(myTestATV.canPass(grass, Light.RED),
                "ATV should be able to pass red-light grass.");
    }
    @Test
    void testCanPassStreet() {
        final Terrain street = Terrain.STREET;
        assertTrue(myTestATV.canPass(street, Light.GREEN),
                "ATV should be able to pass green-light street.");
        assertTrue(myTestATV.canPass(street, Light.YELLOW),
                "ATV should be able to pass yellow-light street.");
        assertTrue(myTestATV.canPass(street, Light.RED),
                "ATV should be able to pass red-light street.");
    }
    @Test
    void testCanPassLight() {
        final Terrain light = Terrain.LIGHT;
        assertTrue(myTestATV.canPass(light, Light.GREEN),
                "ATV should be able to pass green-light light.");
        assertTrue(myTestATV.canPass(light, Light.YELLOW),
                "ATV should be able to pass yellow-light light.");
        assertTrue(myTestATV.canPass(light, Light.RED),
                "ATV should be able to pass red-light light.");
    }
    @Test
    void testCanPassWall() {
        final Terrain wall = Terrain.WALL;
        assertFalse(myTestATV.canPass(wall, Light.GREEN),
                "ATV should not be able to pass green-light wall.");
        assertFalse(myTestATV.canPass(wall, Light.YELLOW),
                "ATV should not be able to pass yellow-light wall.");
        assertFalse(myTestATV.canPass(wall, Light.RED),
                "ATV should not be able to pass red-light wall.");
    }
    @Test
    void testCanPassTrail() {
        final Terrain trail = Terrain.TRAIL;
        assertTrue(myTestATV.canPass(trail, Light.GREEN),
                "ATV should be able to pass green-light trail.");
        assertTrue(myTestATV.canPass(trail, Light.YELLOW),
                "ATV should be able to pass yellow-light trail.");
        assertTrue(myTestATV.canPass(trail, Light.RED),
                "ATV should be able to pass red-light trail.");
    }
    @Test
    void testCanPassCrosswalk() {
        final Terrain crosswalk = Terrain.CROSSWALK;
        assertTrue(myTestATV.canPass(crosswalk, Light.GREEN),
                "ATV should be able to pass green-light crosswalk.");
        assertTrue(myTestATV.canPass(crosswalk, Light.YELLOW),
                "ATV should be able to pass yellow-light crosswalk.");
        assertTrue(myTestATV.canPass(crosswalk, Light.RED),
                "ATV should be able to pass red-light crosswalk.");
    }

    @Test
    void testChooseDirection() {
        TEST_MAP.put(Direction.NORTH, Terrain.GRASS);
        TEST_MAP.put(Direction.SOUTH, Terrain.STREET);
        TEST_MAP.put(Direction.EAST, Terrain.TRAIL);
        TEST_MAP.put(Direction.WEST, Terrain.CROSSWALK);
        boolean choseNorth = false;
        boolean choseWest = false;
        boolean choseEast = false;
        for (int i = 0; i < TRIES_FOR_RANDOMNESS; i++) {
            switch (myTestATV.chooseDirection(TEST_MAP)) {
                case NORTH:
                    choseNorth = true;
                    break;
                case WEST:
                    choseWest = true;
                    break;
                case EAST:
                    choseEast = true;
                    break;
                default:
                    break;
            }
            assertNotEquals(Direction.SOUTH, myTestATV.chooseDirection(TEST_MAP),
                    "ATV should never go backwards.");
        }
        assertTrue(choseNorth && choseWest && choseEast,
                "ATV should have random direction choice.");
    }

    @Test
    void testCollideTruck() {
        myTestATV.collide(new Truck(0, 0, Direction.NORTH));
        assertFalse(myTestATV.isAlive(), "ATV should not survive collision with Truck.");
    }

    @Test
    void testCollideATV() {
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