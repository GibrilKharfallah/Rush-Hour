package game;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

/**
 * The {@code Obstacle} class represents an obstacle in the game.
 * Obstacles are vehicles or objects that move down the screen and serve as challenges for the player to avoid.
 * Each obstacle has an associated image, position, and speed.
 */
public class Obstacle {

    /**
     * The {@link ImageView} representing the visual appearance of the obstacle.
     */
    private ImageView obstacleImageView;

    /**
     * The current y-coordinate of the obstacle.
     */
    private double y;

    /**
     * The speed at which the obstacle moves downward on the screen.
     */
    private double speed = 2;

    /**
     * Constructs an {@code Obstacle} with a specified x-coordinate (lane) and initial speed.
     * The obstacle image is randomly selected from a predefined set.
     *
     * @param laneX        the x-coordinate (lane) where the obstacle is placed.
     * @param initialSpeed the initial downward speed of the obstacle.
     */
    public Obstacle(double laneX, double initialSpeed) {
        this.y = -100;
        this.speed = initialSpeed;

        Image obstacleImage = chooseRandomImage();
        obstacleImageView = new ImageView(obstacleImage);
        obstacleImageView.setFitWidth(50);
        obstacleImageView.setFitHeight(100);

        obstacleImageView.setX(laneX);
        obstacleImageView.setY(y);
    }

    /**
     * Randomly selects an obstacle image from a predefined set of images.
     *
     * @return a randomly selected {@link Image} representing the obstacle.
     */
    private Image chooseRandomImage() {
        Random random = new Random();
        String[] images = {
                "assets/cars/carObstacle1.png", "assets/cars/carObstacle2.png", "assets/cars/carObstacle3.png",
                "assets/cars/carObstacle4.png", "assets/cars/carObstacle5.png", "assets/cars/carObstacle6.png",
                "assets/cars/carObstacle7.png", "assets/cars/carObstacle8.png", "assets/cars/carObstacle9.png",
                "assets/cars/carObstacle10.png", "assets/cars/carObstacle11.png", "assets/cars/carObstacle12.png",
                "assets/cars/carObstacle13.png", "assets/cars/carObstacle14.png"
        };
        int index = random.nextInt(images.length);
        return new Image("file:" + images[index]);
    }

    /**
     * Updates the position of the obstacle by moving it downward based on its speed.
     */
    public void updatePosition() {
        y += speed;
        obstacleImageView.setY(y);
    }

    /**
     * Gets the {@link ImageView} associated with the obstacle.
     *
     * @return the {@link ImageView} of the obstacle.
     */
    public ImageView getImageView() {
        return obstacleImageView;
    }

    /**
     * Gets the current y-coordinate of the obstacle.
     *
     * @return the y-coordinate of the obstacle.
     */
    public double getY() {
        return y;
    }

    /**
     * Gets the current speed of the obstacle.
     *
     * @return the speed of the obstacle.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of the obstacle.
     *
     * @param speed the new speed of the obstacle.
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Gets the bounding rectangle of the obstacle for collision detection.
     *
     * @return a {@link Rectangle2D} representing the bounds of the obstacle.
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D(obstacleImageView.getX(), obstacleImageView.getY(),
                obstacleImageView.getFitWidth(), obstacleImageView.getFitHeight());
    }

    /**
     * Increases the speed of the obstacle by a specified increment.
     *
     * @param increment the amount by which to increase the obstacle's speed.
     */
    public void increaseSpeed(double increment) {
        this.speed += increment;
    }
}
