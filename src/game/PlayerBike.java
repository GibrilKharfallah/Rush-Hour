package game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The {@code PlayerBike} class represents a bike controlled by the player in the game.
 * It extends the {@code PlayerVehicle} class and provides specific functionality for the bike,
 * including its image dimensions and movement behavior.
 */
public class PlayerBike extends PlayerVehicle {

    /**
     * Constructs a new {@code PlayerBike} instance, initializing its position and image.
     *
     * @param startY    the initial Y-coordinate of the bike.
     * @param laneWidth the width of each lane on the track.
     */
    public PlayerBike(double startY, double laneWidth) {
        super(startY, laneWidth);

        Image bikeImage = new Image("file:assets/cars/playerBike.png");
        vehicleImageView = new ImageView(bikeImage);
        vehicleImageView.setFitWidth(30);
        vehicleImageView.setFitHeight(60);

        this.x = calculateLaneX(currentLane);
        this.targetX = x;
        updatePosition();
    }

    /**
     * Updates the bike's sliding movement across lanes.
     * The bike slides faster than the default speed of the {@code PlayerVehicle}.
     * When the target X-coordinate is reached, the bike straightens and stops moving.
     */
    @Override
    public void updateSliding() {
        if (isMoving) {
            double slideSpeed = 8; // Faster sliding speed
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
