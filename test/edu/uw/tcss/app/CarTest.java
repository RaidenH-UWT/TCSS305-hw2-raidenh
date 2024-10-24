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

public class CarTest {

    @Test
    void testGetImageFileName() {
        assertEquals("truck.gif", TEST_TRUCK_ONE.getImageFileName(),
                "Living truck image filename is incorrect.");
        assertEquals("truck_dead.gif", TEST_TRUCK_ONE.getImageFileName(),
                "Dead truck image filename is incorrect.");
    }
}