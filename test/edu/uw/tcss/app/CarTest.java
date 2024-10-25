package edu.uw.tcss.app;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
 * Test class for the Car class.
 *
 * @author Raiden H
 * @version Oct 23 2024
 */
public class CarTest {
    /**
     * test map for testing chooseDirection().
     */
    private static final Map<Direction, Terrain> TEST_MAP = new HashMap<>();
    /**
     * test Car object for testing Car methods.
     */
    private Vehicle myTestCar;
    @BeforeEach
    void setup() {
        myTestCar = new Car(0, 0, Direction.NORTH);
    }

    @Test
    void testGetImageFileName() {
        assertEquals("car.gif", myTestCar.getImageFileName(),
                "Living car image filename is incorrect.");
        myTestCar.collide(new Truck(0, 0, Direction.NORTH));
        assertEquals("car_dead.gif", myTestCar.getImageFileName(),
                "Dead car image filename is incorrect.");
    }
    @Test
    void testCanPass() {
        assertAll("Testing canPass()",
                () -> assertFalse(myTestCar.canPass(Terrain.GRASS, Light.GREEN),
                        "Car should not be able to pass grass."),
                () -> assertTrue(myTestCar.canPass(Terrain.STREET, Light.GREEN),
                        "Car should be able to pass streets."),
                () -> assertFalse(myTestCar.canPass(Terrain.WALL, Light.GREEN),
                        "Car should not be able to pass walls."),
                () -> assertFalse(myTestCar.canPass(Terrain.TRAIL, Light.GREEN),
                        "Car should not be able to pass trails."),
                () -> assertTrue(myTestCar.canPass(Terrain.LIGHT, Light.GREEN),
                        "Car should be able to pass green lights."),
                () -> assertTrue(myTestCar.canPass(Terrain.LIGHT, Light.YELLOW),
                        "Car should be able to pass yellow lights."),
                () -> assertTrue(myTestCar.canPass(Terrain.LIGHT, Light.RED),
                        "Car should be able to pass red lights."),
                () -> assertTrue(myTestCar.canPass(Terrain.CROSSWALK, Light.GREEN),
                        "Car should be able to pass green-light crosswalks."),
                () -> assertFalse(myTestCar.canPass(Terrain.CROSSWALK, Light.YELLOW),
                        "Car should not be able to pass yellow-light crosswalks."),
                () -> assertFalse(myTestCar.canPass(Terrain.CROSSWALK, Light.RED),
                        "Car should not be able to pass red-light crosswalks.")
        );
    }

    @Test
    void testChooseDirection() {
        TEST_MAP.put(Direction.NORTH, Terrain.GRASS);
        TEST_MAP.put(Direction.SOUTH, Terrain.WALL);
        TEST_MAP.put(Direction.EAST, Terrain.LIGHT);
        TEST_MAP.put(Direction.WEST, Terrain.CROSSWALK);
        assertEquals(Direction.WEST, myTestCar.chooseDirection(TEST_MAP),
                "Car should prefer going left if blocked.");
        myTestCar.setDirection(Direction.WEST);
        assertEquals(Direction.WEST, myTestCar.chooseDirection(TEST_MAP),
                "Car should prefer going forward if possible.");
        myTestCar.setDirection(Direction.SOUTH);
        assertEquals(Direction.WEST, myTestCar.chooseDirection(TEST_MAP),
                "Car should prefer right if blocked twice.");
    }

    @Test
    void testCollide() {
        myTestCar.collide(new Truck(0, 0, Direction.NORTH));
        assertFalse(myTestCar.isAlive(), "Car should not survive collision with Truck");
        myTestCar.collide(new Taxi(0, 0, Direction.NORTH));
        assertTrue(myTestCar.isAlive(), "Car should survive collision with Taxi");
        myTestCar.collide(new Atv(0, 0, Direction.NORTH));
        assertTrue(myTestCar.isAlive(), "Car should survive collision with Atv");
        myTestCar.collide(new Bicycle(0, 0, Direction.NORTH));
        assertTrue(myTestCar.isAlive(), "Car should survive collision with Bicycle");
        myTestCar.collide(new Human(0, 0, Direction.NORTH));
        assertTrue(myTestCar.isAlive(), "Car should survive collision with Human");
    }

    @Test
    void testIsAlive() {
        assertTrue(myTestCar.isAlive(),
                "Car be alive by default.");
        myTestCar.collide(new Truck(0, 0, Direction.NORTH));
        assertFalse(myTestCar.isAlive(),
                "Car should be dead here");
    }
}