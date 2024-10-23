package edu.uw.tcss.app;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.uw.tcss.model.Direction;
import edu.uw.tcss.model.Light;
import edu.uw.tcss.model.Terrain;
import edu.uw.tcss.model.Truck;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Truck class.
 *
 * @author Raiden H
 * @version Oct 23 2024
 */
public class TruckTest {
    /**
     *
     */
    private static final int TEST_X = 10;
    /**
     *
     */
    private static final int TEST_Y = 12;
    /**
     *
     */
    private static final Direction TEST_DIR = Direction.NORTH;
    /**
     *
     */
    private static final Truck TEST_TRUCK_ONE = new Truck(0, 0, TEST_DIR);
    /**
     *
     */
    private static final Truck TEST_TRUCK_TWO = new Truck(TEST_X, TEST_Y, TEST_DIR);

    @Test
    void testGetImageFileName() {
        assertEquals("truck.gif", TEST_TRUCK_ONE.getImageFileName(),
                "Truck image filename is incorrect.");
    }
    @Test
    void testCanPass() {
        assertAll("Testing canPass()",
                () -> assertFalse(TEST_TRUCK_ONE.canPass(Terrain.GRASS, Light.RED),
                        "Truck should not be able to pass grass."),
                () -> assertTrue(TEST_TRUCK_ONE.canPass(Terrain.STREET, Light.GREEN),
                        "Truck should be able to pass streets."),
                () -> assertTrue(TEST_TRUCK_ONE.canPass(Terrain.LIGHT, Light.GREEN),
                        "Truck should be able to pass green lights."),
                () -> assertTrue(TEST_TRUCK_ONE.canPass(Terrain.LIGHT, Light.YELLOW),
                        "Truck should be able to pass yellow lights."),
                () -> assertTrue(TEST_TRUCK_ONE.canPass(Terrain.LIGHT, Light.RED),
                        "Truck should be able to pass red lights."),
                () -> assertFalse(TEST_TRUCK_ONE.canPass(Terrain.WALL, Light.GREEN),
                        "Truck should not be able to pass walls."),
                () -> assertFalse(TEST_TRUCK_ONE.canPass(Terrain.TRAIL, Light.GREEN),
                        "Truck should not be able to pass trails."),
                () -> assertTrue(TEST_TRUCK_ONE.canPass(Terrain.CROSSWALK, Light.GREEN),
                        "Truck should be able to pass green-light crosswalks."),
                () -> assertTrue(TEST_TRUCK_ONE.canPass(Terrain.CROSSWALK, Light.YELLOW),
                        "Truck should be able to pass yellow-light crosswalks."),
                () -> assertFalse(TEST_TRUCK_ONE.canPass(Terrain.CROSSWALK, Light.RED),
                        "Truck should not be able to pass red-light crosswalks.")
        );
    }
}