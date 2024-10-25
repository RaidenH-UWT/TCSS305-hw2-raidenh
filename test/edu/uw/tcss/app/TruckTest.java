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
 * Test class for the Truck class.
 *
 * @author Raiden H
 * @version Oct 23 2024
 */
public class TruckTest {
    /**
     * test map for testing chooseDirection().
     */
    private static final Map<Direction, Terrain> TEST_MAP = new HashMap<>();
    /**
     * test Truck object for testing Truck methods.
     */
    private Vehicle myTestTruck;
    @BeforeEach
    void setup() {
        myTestTruck = new Truck(0, 0, Direction.NORTH);
    }

    @Test
    void testGetImageFileName() {
        assertEquals("truck.gif", myTestTruck.getImageFileName(),
                "Living truck image filename is incorrect.");
    }
    @Test
    void testCanPass() {
        assertAll("Testing canPass()",
                () -> assertFalse(myTestTruck.canPass(Terrain.GRASS, Light.GREEN),
                        "Truck should not be able to pass grass."),
                () -> assertTrue(myTestTruck.canPass(Terrain.STREET, Light.GREEN),
                        "Truck should be able to pass streets."),
                () -> assertFalse(myTestTruck.canPass(Terrain.WALL, Light.GREEN),
                        "Truck should not be able to pass walls."),
                () -> assertFalse(myTestTruck.canPass(Terrain.TRAIL, Light.GREEN),
                        "Truck should not be able to pass trails."),
                () -> assertTrue(myTestTruck.canPass(Terrain.LIGHT, Light.GREEN),
                        "Truck should be able to pass green lights."),
                () -> assertTrue(myTestTruck.canPass(Terrain.LIGHT, Light.YELLOW),
                        "Truck should be able to pass yellow lights."),
                () -> assertTrue(myTestTruck.canPass(Terrain.LIGHT, Light.RED),
                        "Truck should be able to pass red lights."),
                () -> assertTrue(myTestTruck.canPass(Terrain.CROSSWALK, Light.GREEN),
                        "Truck should be able to pass green-light crosswalks."),
                () -> assertTrue(myTestTruck.canPass(Terrain.CROSSWALK, Light.YELLOW),
                        "Truck should be able to pass yellow-light crosswalks."),
                () -> assertFalse(myTestTruck.canPass(Terrain.CROSSWALK, Light.RED),
                        "Truck should not be able to pass red-light crosswalks.")
        );
    }

    @Test
    void testChooseDirection() {
        TEST_MAP.put(Direction.NORTH, Terrain.GRASS);
        assertAll("Testing truck chooseDirection()",
                () -> assertNotEquals(Direction.SOUTH, myTestTruck.chooseDirection(TEST_MAP),
                        "Truck should prefer any direction but backwards.")
        );
    }

    @Test
    void testCollide() {
        myTestTruck.collide(new Car(0, 0, Direction.NORTH));
        assertTrue(myTestTruck.isAlive(), "Truck should survive collision with Car");
        myTestTruck.collide(new Taxi(0, 0, Direction.NORTH));
        assertTrue(myTestTruck.isAlive(), "Truck should survive collision with Taxi");
        myTestTruck.collide(new Atv(0, 0, Direction.NORTH));
        assertTrue(myTestTruck.isAlive(), "Truck should survive collision with Atv");
        myTestTruck.collide(new Bicycle(0, 0, Direction.NORTH));
        assertTrue(myTestTruck.isAlive(), "Truck should survive collision with Bicycle");
        myTestTruck.collide(new Human(0, 0, Direction.NORTH));
        assertTrue(myTestTruck.isAlive(), "Truck should survive collision with Human");
    }

    @Test
    void testIsAlive() {
        assertTrue(myTestTruck.isAlive(),
                "Truck should always be alive");
    }
}