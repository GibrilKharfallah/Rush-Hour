package test;

import game.Obstacle;
import game.PlayerCar;
import game.PlayerVehicle;
import javafx.geometry.Rectangle2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionTest {

    @Test
    public void testCollisionDetection() {
        PlayerVehicle vehicle = new PlayerCar(480, 100);
        Obstacle obstacle = new Obstacle(150, 2);
        vehicle.getImageView().setX(150);
        vehicle.getImageView().setY(480);
        obstacle.getImageView().setX(150);
        obstacle.getImageView().setY(480);

        Rectangle2D vehicleBounds = vehicle.getBounds();
        Rectangle2D obstacleBounds = obstacle.getBounds();

        assertTrue(vehicleBounds.intersects(obstacleBounds), "The vehicle and obstacle should collide.");
    }
}

