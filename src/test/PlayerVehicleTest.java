package test;

import game.PlayerCar;
import game.PlayerVehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerVehicleTest {

    @Test
    public void testMoveLeft() {
        PlayerVehicle vehicle = new PlayerCar(480, 100);
        vehicle.moveLeft();
        assertEquals(0, vehicle.currentLane, "The vehicle should move to the left lane.");
        vehicle.moveLeft();
        assertEquals(0, vehicle.currentLane, "The vehicle should not move beyond the leftmost lane.");
    }

    @Test
    public void testMoveRight() {
        PlayerVehicle vehicle = new PlayerCar(480, 100);
        vehicle.moveRight();
        assertEquals(2, vehicle.currentLane, "The vehicle should move to the right lane.");
        vehicle.moveRight();
        assertEquals(3, vehicle.currentLane, "The vehicle should not move beyond the rightmost lane.");
    }
}

