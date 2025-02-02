package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * The {@code PlayerCar} class represents a player's car in the game.
 * It extends the {@code PlayerVehicle} class and provides specific implementation
 * details for the car, such as its image and sliding behavior.
 */
public class PlayerCar extends PlayerVehicle {

    /**
     * Constructs a {@code PlayerCar} object at the specified start position
     * and lane width. Initializes the car's image and positioning.
     *
     * @param startY    The initial vertical position of the car.
     * @param laneWidth The width of the lane in which the car will operate.
     */
    public PlayerCar(double startY, double laneWidth) {
        super(startY, laneWidth);

        Image carImage = new Image("file:assets/cars/playerCar.png");
        vehicleImageView = new ImageView(carImage);
        vehicleImageView.setFitWidth(50);
        vehicleImageView.setFitHeight(100);

        this.x = calculateLaneX(currentLane);
        this.targetX = x;
        updatePosition();
    }

    /**
     * Updates the sliding animation of the car when moving between lanes.
     * The car slides towards its target horizontal position ({@code targetX})
     * at a defined speed. Once the target position is reached, the car
     * straightens and stops sliding.
     */
    @Override
    public void updateSliding() {
        if (isMoving) {
            double slideSpeed = 5;
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
