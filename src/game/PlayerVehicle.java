package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

/**
 * Represents an abstract player vehicle in the game.
 * This class provides basic functionality for vehicle movement and positioning.
 */
public abstract class PlayerVehicle {
    protected ImageView vehicleImageView;
    protected double x, y;
    protected double targetX;
    public int currentLane;
    protected double laneWidth;
    protected boolean isMoving = false;

    protected double targetRotation = 0;
    protected double currentRotation = 0;
    protected double verticalOffset = 0;

    /**
     * Constructs a new PlayerVehicle.
     *
     * @param startY    The initial Y position of the vehicle.
     * @param laneWidth The width of each lane in the game.
     */
    public PlayerVehicle(double startY, double laneWidth) {
        this.laneWidth = laneWidth;
        this.y = startY;
        this.currentLane = 1;
    }

    /**
     * Retrieves the ImageView representing the vehicle.
     *
     * @return The ImageView of the vehicle.
     */
    public ImageView getImageView() {
        return vehicleImageView;
    }

    /**
     * Calculates the X coordinate for a given lane.
     *
     * @param lane The lane number.
     * @return The X coordinate for the center of the specified lane.
     */
    protected double calculateLaneX(int lane) {
        return 100 + lane * laneWidth + (laneWidth - vehicleImageView.getFitWidth()) / 2;
    }

    /**
     * Updates the position of the vehicle's ImageView based on current coordinates and vertical offset.
     */
    protected void updatePosition() {
        vehicleImageView.setX(x);
        vehicleImageView.setY(y + verticalOffset);
    }

    /**
     * Moves the vehicle to the left lane if possible.
     * Sets up the target position and rotation for smooth animation.
     */
    public void moveLeft() {
        if (currentLane > 0 && !isMoving) {
            currentLane--;
            targetX = calculateLaneX(currentLane);
            targetRotation = -20; // Rotate left
            isMoving = true;
        }
    }

    /**
     * Moves the vehicle to the right lane if possible.
     * Sets up the target position and rotation for smooth animation.
     */
    public void moveRight() {
        if (currentLane < 3 && !isMoving) {
            currentLane++;
            targetX = calculateLaneX(currentLane);
            targetRotation = 20;
            isMoving = true;
        }
    }

    /**
     * Resets the target rotation to straighten the vehicle.
     */
    public void straighten() {
        targetRotation = 0;
    }

    /**
     * Updates the vertical offset to create a bouncing effect and updates the vehicle's position.
     */
    public void updateBounce() {
        verticalOffset = 5 * Math.sin(System.currentTimeMillis() * 0.005);
        updatePosition();
    }

    /**
     * Abstract method to update the sliding movement of the vehicle.
     * This method should be implemented by subclasses to define custom sliding behavior.
     */
    public abstract void updateSliding();

    /**
     * Updates the rotation of the vehicle, smoothly interpolating between the current and target rotation.
     */
    public void updateRotation() {
        double rotationSpeed = 2;
        if (currentRotation < targetRotation) {
            currentRotation = Math.min(currentRotation + rotationSpeed, targetRotation);
        } else if (currentRotation > targetRotation) {
            currentRotation = Math.max(currentRotation - rotationSpeed, targetRotation);
        }
        vehicleImageView.setRotate(currentRotation);
    }

    /**
     * Retrieves the bounding rectangle of the vehicle.
     *
     * @return A Rectangle2D representing the bounds of the vehicle's ImageView.
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D(vehicleImageView.getX(), vehicleImageView.getY(),
                vehicleImageView.getFitWidth(), vehicleImageView.getFitHeight());
    }
}
