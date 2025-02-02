package game;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The GameWindow class is responsible for managing the gameplay interface, game logic,
 * player vehicle, obstacles, score, levels, and user input.
 * It contains methods to initialize the game UI, handle keyboard events, update game objects,
 * manage game state (play, pause, game over), and trigger animations.
 */
public class GameWindow {
    private GamePanel gamePanel;
    private List<Obstacle> obstacles;
    private double laneWidth;
    private final Random random = new Random();
    private boolean gameOver = false;
    private boolean paused = true;
    private VBox pauseMenu;
    public int score = 0;
    private int highScore = 0;
    public int level = 1;
    private double baseObstacleSpeed = 2.0;
    private Text scoreText;
    private Text levelText;
    private StackPane root;
    private AnimationTimer gameLoop;
    private PlayerVehicle playerVehicle;
    private Text startText;
    public static KeyCode playerControlLeft = KeyCode.LEFT;
    public static KeyCode playerControlRight = KeyCode.RIGHT;

    /**
     * Constructs a new GameWindow object, initializing the game panel, obstacles, and the player's vehicle.
     * Also sets up the user interface and the main game loop.
     *
     * @param stage the primary stage of the game window
     * @param classType the type of the player's vehicle (car, bike, truck)
     */
    public GameWindow(Stage stage, String classType) {
        laneWidth = (600 - 200) / 4.0;
        playerVehicle = createVehicle(classType);

        obstacles = new ArrayList<>();
        gamePanel = new GamePanel(playerVehicle, obstacles);

        initializeUI(stage);
        initializeKeyboardControls(stage, classType);
        startGameLoop();
    }

    /**
     * Dynamically creates a player vehicle based on the provided vehicle type.
     *
     * @param classType the type of the player's vehicle ("car", "bike", "truck")
     * @return the created PlayerVehicle object
     * @throws IllegalArgumentException if the vehicle type is unknown
     */
    private PlayerVehicle createVehicle(String classType) {
        switch (classType.toLowerCase()) {
            case "car":
                return new PlayerCar(480, laneWidth);
            case "bike":
                return new PlayerBike(500, laneWidth);
            case "truck":
                return new PlayerTruck(450, laneWidth);
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + classType);
        }
    }

    /**
     * Initializes the user interface, including score, level, and start text.
     *
     * @param stage the primary stage of the game window
     */
    private void initializeUI(Stage stage) {
        gamePanel.draw();
        scoreText = new Text("Score : 0");
        scoreText.setFont(new Font(20));
        scoreText.setFill(Color.WHITE);
        scoreText.setTranslateY(-280);


        startText=new Text("Press enter to start");
        startText.setFont(new Font(20));
        startText.setFill(Color.WHITE);
        startText.setTranslateY(0);

        levelText = new Text("Level : 1");
        levelText.setFont(new Font(20));
        levelText.setFill(Color.WHITE);
        levelText.setTranslateY(-250);

        root = new StackPane(gamePanel, scoreText, levelText,startText);
        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Rush Hour");
        stage.getIcons().add(new Image("file:assets/gameLogo.png"));
        stage.show();
    }

    /**
     * Initializes keyboard controls for the game, handling player movement, pausing, and restarting.
     *
     * @param stage the primary stage of the game window
     * @param classType the type of the player's vehicle
     */
    private void initializeKeyboardControls(Stage stage, String classType) {
        root.getScene().setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (gameOver) {
                    restartGame(stage, classType);
                } else if (paused) {
                    togglePause();
                }
            } else if (event.getCode() == KeyCode.SPACE) {
                if (!gameOver) {
                    togglePause();
                }
            } else if (!gameOver && !paused) {
                if (event.getCode() == playerControlLeft) {
                    playerVehicle.moveLeft();
                } else if (event.getCode() == playerControlRight) {
                    playerVehicle.moveRight();
                }
            }
        });
    }



    /**
     * Starts the main game loop, which updates the score, spawns obstacles, and checks for collisions.
     */
    private void startGameLoop() {
        gameLoop = new AnimationTimer() {
            private int frameCount = 0;

            @Override
            public void handle(long now) {
                if (gameOver || paused) return;

                frameCount++;
                if (frameCount % 60 == 0) {
                    updateScore();
                }

                playerVehicle.updateRotation();
                playerVehicle.updateBounce();
                playerVehicle.updateSliding();

                if (frameCount % 80 == 0) {
                    spawnObstacle();
                }

                startText.setText("");
                updateGameObjects();

                checkCollisions();

                gamePanel.draw();
            }
        };
        gameLoop.start();
    }

    /**
     * Updates the score and level. Increases the level every 10 points and speeds up obstacles.
     */
    public void updateScore() {
        score++;
        scoreText.setText("Score : " + score);

        if (score % 10 == 0) { // Level up every 10 points
            level++;
            levelText.setText("Level : " + level);
            increaseObstacleSpeed();
            showLevelUpNotification();
        }
    }

    /**
     * Spawns a new obstacle in a random lane.
     */
    private void spawnObstacle() {
        int lane = random.nextInt(5);
        double laneX = 100 + lane * laneWidth + (laneWidth - 50) / 2;
        obstacles.add(new Obstacle(laneX, baseObstacleSpeed));
    }

    /**
     * Updates the positions of obstacles and the player vehicle.
     * Removes obstacles that have gone off-screen.
     */
    private void updateGameObjects() {
        for (int i = obstacles.size() - 1; i >= 0; i--) {
            Obstacle obstacle = obstacles.get(i);
            obstacle.updatePosition();
            if (obstacle.getY() > gamePanel.getHeight()) {
                obstacles.remove(i);
            }
        }
    }

    /**
     * Checks for collisions between the player vehicle and obstacles.
     */
    private void checkCollisions() {
        Rectangle2D playerBounds = playerVehicle.getBounds();
        for (Obstacle obstacle : obstacles) {
            if (playerBounds.intersects(obstacle.getBounds())) {
                triggerCrashAnimation();
                break;
            }
        }
    }

    /**
     * Triggers the crash animation and ends the game.
     */
    private void triggerCrashAnimation() {
        gameOver = true;
        showGameOverMenu();
    }

    /**
     * Displays the game over menu, showing the current score, high score, and option to restart.
     */
    private void showGameOverMenu() {
        // Update high score
        if (score > highScore) highScore = score;

        GaussianBlur blur = new GaussianBlur(10);
        gamePanel.setEffect(blur);

        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(new Font("Arial Black", 40));
        gameOverText.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.RED), new Stop(1, Color.ORANGE)));
        gameOverText.setEffect(new DropShadow(10, Color.DARKORANGE));

        Text scoreDetails = new Text("Score : " + score + "\nHigh Score : " + highScore + "\nPress Enter to Restart");
        scoreDetails.setFont(Font.font("Roboto", FontWeight.BOLD, 25));
        scoreDetails.setFill(Color.WHITE);
        scoreDetails.setTextAlignment(TextAlignment.CENTER);
        scoreDetails.setStyle("-fx-line-spacing: 10;");

        VBox gameOverBox = new VBox(20, gameOverText, scoreDetails);
        gameOverBox.setAlignment(Pos.CENTER);
        root.getChildren().add(gameOverBox);
    }

    /**
     * Restarts the game with the same vehicle type.
     *
     * @param stage the primary stage of the game window
     * @param classType the type of the player's vehicle
     */
    private void restartGame(Stage stage, String classType) {
        gameOver = false;
        paused = false;
        score = 0;
        level = 1;
        baseObstacleSpeed = 2.0;

        obstacles.clear();
        playerVehicle = createVehicle(classType);

        gamePanel = new GamePanel(playerVehicle, obstacles);
        scoreText.setText("Score : 0");
        levelText.setText("Level : 1");

        root.getChildren().clear();
        root.getChildren().addAll(gamePanel, scoreText, levelText);

        gameLoop.stop();
        startGameLoop();
    }

    /**
     * Pauses or resumes the game.
     */
    private void togglePause() {
        paused = !paused;
        if (paused) {
            gameLoop.stop();
            showPauseMenu();
        } else {
            root.getChildren().remove(pauseMenu);
            gamePanel.setEffect(null);
            gameLoop.start();
        }
    }

    /**
     * Displays the pause menu with an option to resume the game.
     */
    private void showPauseMenu() {
        GaussianBlur blur = new GaussianBlur(10);
        gamePanel.setEffect(blur);

        Text playText = new Text("PLAY");
        playText.setFont(new Font("Arial Black", 40));
        playText.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.LIGHTGREEN), new Stop(0.5, Color.LIME), new Stop(1, Color.DARKGREEN)));
        playText.setEffect(new DropShadow(10, Color.DARKGREEN));

        Button resumeButton = new Button();
        resumeButton.setStyle("-fx-shape: 'M 0 0 L 50 25 L 0 50 Z'; -fx-background-color: lime; -fx-min-width: 60px; -fx-min-height: 60px");
        resumeButton.setOnAction(e -> togglePause());

        pauseMenu = new VBox(20, playText, resumeButton);
        pauseMenu.setAlignment(Pos.CENTER);
        root.getChildren().add(pauseMenu);
    }

    /**
     * Increases the speed of obstacles based on the current level.
     */
    private void increaseObstacleSpeed() {
        baseObstacleSpeed += 1;
    }

    /**
     * Displays a level-up notification when the player reaches a new level.
     */
    private void showLevelUpNotification() {
        Text levelUpText = new Text("Level " + level + " !");
        levelUpText.setFont(new Font("Arial Black", 30));
        levelUpText.setFill(Color.YELLOW);

        root.getChildren().add(levelUpText);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> root.getChildren().remove(levelUpText)));
        timeline.play();
    }
}
