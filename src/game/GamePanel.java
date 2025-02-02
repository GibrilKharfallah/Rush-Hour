package game;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Represents the game panel where all the visual elements of the game are drawn, including the road, player vehicle, obstacles,
 * and lane dividers. It handles the scrolling of the road and updates the screen at each frame.
 */
public class GamePanel extends Canvas {

    private PlayerVehicle playerVehicle;
    private List<Obstacle> obstacles;
    private double roadOffset = 0;

    /**
     * Constructs a new game panel with the specified player vehicle and obstacles.
     *
     * @param playerVehicle The player's vehicle to be drawn on the panel.
     * @param obstacles The list of obstacles to be drawn on the panel.
     */
    public GamePanel(PlayerVehicle playerVehicle, List<Obstacle> obstacles) {
        super(600, 600);
        this.playerVehicle = playerVehicle;
        this.obstacles = obstacles;
    }

    /**
     * Resets the game panel with a new player vehicle and a new list of obstacles.
     * This is called when restarting the game or changing the vehicle.
     *
     * @param newPlayerVehicle The new player vehicle to be used.
     * @param newObstacles The new list of obstacles to be displayed.
     */
    public void reset(PlayerVehicle newPlayerVehicle, List<Obstacle> newObstacles) {
        this.playerVehicle = newPlayerVehicle;
        this.obstacles = newObstacles;
    }

    /**
     * Draws the game elements including the road, player vehicle, obstacles, and lane dividers.
     * This method is called every frame to update the game visuals.
     */
    public void draw() {
        GraphicsContext gc = getGraphicsContext2D();
        roadOffset += 5;
        if (roadOffset >= getHeight()) {
            roadOffset = 0;
        }

        gc.setFill(Color.BLACK);
        gc.fillRect(100, -roadOffset, getWidth() - 200, getHeight() * 2);

        drawSidewalks(gc);

        drawLaneDividers(gc);

        ImageView playerImageView = playerVehicle.getImageView();
        gc.drawImage(
                playerImageView.getImage(),
                playerImageView.getX(),
                playerImageView.getY(),
                playerImageView.getFitWidth(),
                playerImageView.getFitHeight()
        );

        for (Obstacle obstacle : obstacles) {
            ImageView obstacleImageView = obstacle.getImageView();
            gc.drawImage(
                    obstacleImageView.getImage(),
                    obstacleImageView.getX(),
                    obstacleImageView.getY(),
                    obstacleImageView.getFitWidth(),
                    obstacleImageView.getFitHeight()
            );
        }
    }

    /**
     * Draws the grassy sidewalks on the left and right sides of the road.
     * This method is called within the draw method to render the sidewalks.
     *
     * @param gc The graphics context used for drawing.
     */
    private void drawSidewalks(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, 100, getHeight());
        gc.fillRect(getWidth() - 100, 0, 100, getHeight());
    }

    /**
     * Draws the lane dividers, including dashed and continuous lines, to divide the lanes on the road.
     * This method is called within the draw method to render the lane dividers.
     *
     * @param gc The graphics context used for drawing.
     */
    private void drawLaneDividers(GraphicsContext gc) {
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        double laneWidth = (getWidth() - 200) / 4;

        double dashedLineX1 = 100 + laneWidth;
        for (int y = -(int) roadOffset; y < getHeight() * 2; y += 40) {
            gc.strokeLine(dashedLineX1, y, dashedLineX1, y + 20);
        }

        double continuousLineX = 100 + 2 * laneWidth;
        gc.strokeLine(continuousLineX, -(int) roadOffset, continuousLineX, getHeight() * 2);

        double dashedLineX2 = 100 + 3 * laneWidth;
        for (int y = -(int) roadOffset; y < getHeight() * 2; y += 40) {
            gc.strokeLine(dashedLineX2, y, dashedLineX2, y + 20);
        }
    }
}
