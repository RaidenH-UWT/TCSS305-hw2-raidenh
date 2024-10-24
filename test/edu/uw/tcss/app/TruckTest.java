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
import java.util.HashMap;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Truck class.
 *
 * @author Raiden H
 * @version Oct 23 2024
 */
public class TruckTest {
    /**
     * test map for testing chooseDirection().
     */
    private static final HashMap<Direction, Terrain> TEST_MAP = new HashMap<>();
    /**
     * test Truck object for testing Truck methods.
     */
    private static final Truck TEST_TRUCK = new Truck(0, 0, Direction.NORTH);

    @Test
    void testGetImageFileName() {
        assertEquals("truck.gif", TEST_TRUCK.getImageFileName(),
                "Living truck image filename is incorrect.");
    }
    @Test
    void testCanPass() {
        assertAll("Testing canPass()",
                () -> assertFalse(TEST_TRUCK.canPass(Terrain.GRASS, Light.RED),
                        "Truck should not be able to pass grass."),
                () -> assertTrue(TEST_TRUCK.canPass(Terrain.STREET, Light.GREEN),
                        "Truck should be able to pass streets."),
                () -> assertFalse(TEST_TRUCK.canPass(Terrain.WALL, Light.GREEN),
                        "Truck should not be able to pass walls."),
                () -> assertFalse(TEST_TRUCK.canPass(Terrain.TRAIL, Light.GREEN),
                        "Truck should not be able to pass trails."),
                () -> assertTrue(TEST_TRUCK.canPass(Terrain.LIGHT, Light.GREEN),
                        "Truck should be able to pass green lights."),
                () -> assertTrue(TEST_TRUCK.canPass(Terrain.LIGHT, Light.YELLOW),
                        "Truck should be able to pass yellow lights."),
                () -> assertTrue(TEST_TRUCK.canPass(Terrain.LIGHT, Light.RED),
                        "Truck should be able to pass red lights."),
                () -> assertTrue(TEST_TRUCK.canPass(Terrain.CROSSWALK, Light.GREEN),
                        "Truck should be able to pass green-light crosswalks."),
                () -> assertTrue(TEST_TRUCK.canPass(Terrain.CROSSWALK, Light.YELLOW),
                        "Truck should be able to pass yellow-light crosswalks."),
                () -> assertFalse(TEST_TRUCK.canPass(Terrain.CROSSWALK, Light.RED),
                        "Truck should not be able to pass red-light crosswalks.")
        );
    }

    @Test
    void testChooseDirection() {
        TEST_MAP.put(Direction.NORTH, Terrain.GRASS);
        assertAll("Testing truck chooseDirection()",
                () -> assertNotEquals(Direction.SOUTH, TEST_TRUCK.chooseDirection(TEST_MAP),
                        "Truck should prefer any direction but backwards.")
        );
    }

    @Test
    void testCollide() {
        TEST_TRUCK.collide(new Car(0, 0, Direction.NORTH));
        assertTrue(TEST_TRUCK.isAlive(), "Truck should survive collision with Car");
        TEST_TRUCK.collide(new Taxi(0, 0, Direction.NORTH));
        assertTrue(TEST_TRUCK.isAlive(), "Truck should survive collision with Taxi");
        TEST_TRUCK.collide(new Atv(0, 0, Direction.NORTH));
        assertTrue(TEST_TRUCK.isAlive(), "Truck should survive collision with Atv");
        TEST_TRUCK.collide(new Bicycle(0, 0, Direction.NORTH));
        assertTrue(TEST_TRUCK.isAlive(), "Truck should survive collision with Bicycle");
        TEST_TRUCK.collide(new Human(0, 0, Direction.NORTH));
        assertTrue(TEST_TRUCK.isAlive(), "Truck should survive collision with Human");
    }

    @Test
    void testIsAlive() {
        assertTrue(TEST_TRUCK.isAlive(),
                "Truck should always be alive");
    }
}