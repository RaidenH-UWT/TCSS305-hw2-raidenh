package edu.uw.tcss.app;

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
    void testCanPassGrass() {
        final Terrain grass = Terrain.GRASS;
        assertFalse(myTestCar.canPass(grass, Light.GREEN),
                "Car should not be able to pass green-light grass.");
        assertFalse(myTestCar.canPass(grass, Light.YELLOW),
                "Car should not be able to pass yellow-light grass.");
        assertFalse(myTestCar.canPass(grass, Light.RED),
                "Car should not be able to pass red-light grass.");
    }
    @Test
    void testCanPassStreet() {
        final Terrain street = Terrain.STREET;
        assertTrue(myTestCar.canPass(street, Light.GREEN),
                "Car should be able to pass green-light street.");
        assertTrue(myTestCar.canPass(street, Light.YELLOW),
                "Car should be able to pass yellow-light street.");
        assertTrue(myTestCar.canPass(street, Light.RED),
                "Car should be able to pass red-light street.");
    }
    @Test
    void testCanPassLight() {
        final Terrain light = Terrain.LIGHT;
        assertTrue(myTestCar.canPass(light, Light.GREEN),
                "Car should be able to pass green-light light.");
        assertTrue(myTestCar.canPass(light, Light.YELLOW),
                "Car should be able to pass yellow-light light.");
        assertFalse(myTestCar.canPass(light, Light.RED),
                "Car should not be able to pass red-light light.");
    }
    @Test
    void testCanPassWall() {
        final Terrain wall = Terrain.WALL;
        assertFalse(myTestCar.canPass(wall, Light.GREEN),
                "Car should not be able to pass green-light wall.");
        assertFalse(myTestCar.canPass(wall, Light.YELLOW),
                "Car should not be able to pass yellow-light wall.");
        assertFalse(myTestCar.canPass(wall, Light.RED),
                "Car should not be able to pass red-light wall.");
    }
    @Test
    void testCanPassTrail() {
        final Terrain trail = Terrain.TRAIL;
        assertFalse(myTestCar.canPass(trail, Light.GREEN),
                "Car should not be able to pass green-light trail.");
        assertFalse(myTestCar.canPass(trail, Light.YELLOW),
                "Car should not be able to pass yellow-light trail.");
        assertFalse(myTestCar.canPass(trail, Light.RED),
                "Car should not be able to pass red-light trail.");
    }
    @Test
    void testCanPassCrosswalk() {
        final Terrain crosswalk = Terrain.CROSSWALK;
        assertTrue(myTestCar.canPass(crosswalk, Light.GREEN),
                "Car should be able to pass green-light crosswalk.");
        assertFalse(myTestCar.canPass(crosswalk, Light.YELLOW),
                "Car should not be able to pass yellow-light crosswalk.");
        assertFalse(myTestCar.canPass(crosswalk, Light.RED),
                "Car should not be able to pass red-light crosswalk.");
    }

    @Test
    void testChooseDirection() {
        TEST_MAP.put(Direction.NORTH, Terrain.GRASS);
        TEST_MAP.put(Direction.SOUTH, Terrain.WALL);
        TEST_MAP.put(Direction.EAST, Terrain.TRAIL);
        TEST_MAP.put(Direction.WEST, Terrain.CROSSWALK);
        assertEquals(Direction.WEST, myTestCar.chooseDirection(TEST_MAP),
                "Car should prefer going left if blocked.");
        myTestCar.setDirection(Direction.WEST);
        assertEquals(Direction.WEST, myTestCar.chooseDirection(TEST_MAP),
                "Car should prefer going forward if possible.");
        myTestCar.setDirection(Direction.SOUTH);
        assertEquals(Direction.WEST, myTestCar.chooseDirection(TEST_MAP),
                "Car should prefer right if blocked twice.");
        myTestCar.setDirection(Direction.EAST);
        assertEquals(Direction.WEST, myTestCar.chooseDirection(TEST_MAP),
                "Car should reverse as a last resort");
    }

    @Test
    void testCollideTruck() {
        myTestCar.collide(new Truck(0, 0, Direction.NORTH));
        assertFalse(myTestCar.isAlive(), "Car should not survive collision with Truck");
    }

    @Test
    void testCollideCar() {
        myTestCar.collide(new Car(0, 0, Direction.NORTH));
        assertTrue(myTestCar.isAlive(), "Car should survive collision with Car");
    }

    @Test
    void testCollideTaxi() {
        myTestCar.collide(new Taxi(0, 0, Direction.NORTH));
        assertTrue(myTestCar.isAlive(), "Car should survive collision with Taxi");
    }

    @Test
    void testCollideAtv() {
        myTestCar.collide(new Atv(0, 0, Direction.NORTH));
        assertTrue(myTestCar.isAlive(), "Car should survive collision with Atv");
    }

    @Test
    void testCollideBicycle() {
        myTestCar.collide(new Bicycle(0, 0, Direction.NORTH));
        assertTrue(myTestCar.isAlive(), "Car should survive collision with Bicycle");
    }

    @Test
    void testCollideHuman() {
        myTestCar.collide(new Human(0, 0, Direction.NORTH));
        assertTrue(myTestCar.isAlive(), "Car should survive collision with Human");
    }

    @Test
    void testIsAlive() {
        assertTrue(myTestCar.isAlive(),
                "Car should be alive by default.");
        myTestCar.collide(new Truck(0, 0, Direction.NORTH));
        assertFalse(myTestCar.isAlive(),
                "Car should be dead here");
    }
}