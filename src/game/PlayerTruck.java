package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The {@code PlayerTruck} class represents a player's truck in the game.
 * It extends the {@code PlayerVehicle} class and provides specific implementation
 * details for the car, such as its image and sliding behavior.
 */
public class PlayerTruck extends PlayerVehicle {
    /**
     * Constructs a new {@code PlayerTruck} object.
     * This constructor initializes a player-controlled truck with specific dimensions and positioning.
     * It loads the truck image, sets its size, and positions it in the starting lane.
     *
     * @param startY The initial vertical position of the truck on the game screen.
     * @param laneWidth The width of a single lane in the game.
     */
    public PlayerTruck(double startY, double laneWidth) {
        super(startY, laneWidth);

        Image truckImage = new Image("file:assets/cars/playerTruck.png");
        vehicleImageView = new ImageView(truckImage);
        vehicleImageView.setFitWidth(80);
        vehicleImageView.setFitHeight(150);

        this.x = calculateLaneX(currentLane);
        this.targetX = x;
        updatePosition();
    }

    /**
     * Updates the sliding movement of the truck.
     * This method is responsible for smoothly moving the truck between lanes.
     * It uses a slower sliding speed compared to other vehicles and updates the truck's
     * position until it reaches its target lane.
     */
    @Override
    public void updateSliding() {
        if (isMoving) {
            double slideSpeed = 3;
            if (x < targetX) {
                x = Math.min(x + slideSpeed, targetX);
            } else if (x > targetX) {
                x = Math.max(x - slideSpeed, targetX);
            }

            if (x == targetX) {
                isMoving = false;
                straighten();
            }

            updatePosition();
        }
    }
}
