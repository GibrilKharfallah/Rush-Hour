package game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * The Main class serves as the entry point for the Rush Hour Vehicle Selection application.
 * It provides a graphical user interface (GUI) to navigate through the main menu,
 * select vehicles, and view help and credits.
 */
public class Main extends Application {
    /**
     * Starts the JavaFX application by setting up the main stage, menus, and scene layout.
     *
     * @param primaryStage the main stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        // Create the main menu
        VBox menu = new VBox(30);
        menu.setPadding(new Insets(30));
        menu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-background-radius: 0; -fx-alignment: center;");
        Button playButton = createStyledButton("Play", "#4caf50");
        Button helpButton = createStyledButton("Help", "#ff9800");
        Button creditsButton = createStyledButton("Credits", "#ff5722");
        Button exitButton = createStyledButton("Exit", "#f44336");
        Button settingsButton = createStyledButton("Settings", "#2196f3");
        menu.getChildren().addAll(playButton, helpButton, creditsButton, settingsButton, exitButton);





        // Create the root layout
        BorderPane root = new BorderPane();

        // Background image for the scene
        File file = new File("C:/Users/Acer/epitech/T-JAV-501-PAR_7/CarGameProject/assets/MenuBackground.png");
        String imagePath = file.toURI().toString();
        root.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-background-image: url('" + imagePath + "'); "
                + "-fx-background-size: cover; -fx-background-position: left; -fx-background-repeat: no-repeat;");

        root.setCenter(menu);
        settingsButton.setOnAction(e -> {
            openSettingsMenu(primaryStage, root, menu);
        });

        VBox vehicleSelection = new VBox(20);
        vehicleSelection.setPadding(new Insets(30));
        vehicleSelection.setAlignment(Pos.CENTER);
        vehicleSelection.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-background-radius: 0;");
        Text vehicleTitle = new Text("Select Your Vehicle");
        vehicleTitle.setFont(Font.font("Verdana", FontWeight.NORMAL, 22));
        vehicleTitle.setFill(Color.LIGHTGRAY);

        HBox vehicleOptions = new HBox(30);
        vehicleOptions.setAlignment(Pos.CENTER);
        vehicleOptions.setPadding(new Insets(20));
        Button bikeButton = createVehicleButton("", "assets/cars/playerBike.png");
        Button carButton = createVehicleButton("", "assets/cars/playerCar.png");
        Button truckButton = createVehicleButton("", "assets/cars/playerTruck.png");
        vehicleOptions.getChildren().addAll(bikeButton, carButton, truckButton);

        Button returnButton = createStyledButton("Return", "#4caf50");
        returnButton.setOnAction(e -> root.setCenter(menu));

        StackPane vehicleSelectionPane = new StackPane();
        vehicleSelectionPane.getChildren().addAll(vehicleSelection);
        StackPane.setAlignment(returnButton, Pos.TOP_LEFT);
        StackPane.setMargin(returnButton, new Insets(10));
        vehicleSelectionPane.getChildren().add(returnButton);

        vehicleSelection.getChildren().addAll(vehicleTitle, vehicleOptions);

        vehicleSelection.setVisible(false);

        carButton.setOnAction(e -> launchGame(primaryStage, "car"));
        bikeButton.setOnAction(e -> launchGame(primaryStage, "bike"));
        truckButton.setOnAction(e -> launchGame(primaryStage, "truck"));

        Scene menuScene = new Scene(root, 800, 600);
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Rush Hour");
        primaryStage.getIcons().add(new Image("file:assets/gameLogo.png"));
        primaryStage.show();

        playButton.setOnAction(e -> {
            root.setCenter(vehicleSelectionPane);
            vehicleSelection.setVisible(true);
        });

        helpButton.setOnAction(e -> {
            Text helpText = new Text("Use arrow keys to move left or right.\nAvoid obstacles and drive as far as you possibly can !");
            helpText.setFont(Font.font("Roboto", FontWeight.BOLD, 25));
            helpText.setFill(Color.LIGHTGRAY);
            helpText.setTextAlignment(TextAlignment.CENTER);
            helpText.setStyle("-fx-line-spacing: 10;");

            Button returnButtonForHelp = createStyledButton("Return", "#4caf50");
            returnButtonForHelp.setOnAction(e2 -> root.setCenter(menu));

            VBox helpBox = new VBox(helpText);
            helpBox.setAlignment(Pos.CENTER);
            helpBox.setPadding(new Insets(30));
            helpBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-background-radius: 0;");

            StackPane helpPane = new StackPane();
            helpPane.getChildren().addAll(helpBox, returnButtonForHelp);
            StackPane.setAlignment(returnButtonForHelp, Pos.TOP_LEFT);
            StackPane.setMargin(returnButtonForHelp, new Insets(10));

            root.setCenter(helpPane);
        });

        creditsButton.setOnAction(e -> {
            Text creditsText = new Text("Developed by:\n\n" +
                    "Rayan Habes\n" +
                    "Gibril Kharfallah\n" +
                    "Junior Numen\n" +
                    "Special Thanks to our testers and contributors !");
            creditsText.setFont(Font.font("Roboto", FontWeight.BOLD, 25));
            creditsText.setFill(Color.LIGHTGRAY);
            creditsText.setTextAlignment(TextAlignment.CENTER);
            creditsText.setStyle("-fx-line-spacing: 10;");

            Button returnButtonForCredits = createStyledButton("Return", "#4caf50");
            returnButtonForCredits.setOnAction(e2 -> root.setCenter(menu));

            VBox creditsBox = new VBox(creditsText);
            creditsBox.setAlignment(Pos.CENTER);
            creditsBox.setPadding(new Insets(30));
            creditsBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-background-radius: 0;");

            StackPane creditsPane = new StackPane();
            creditsPane.getChildren().addAll(creditsBox, returnButtonForCredits);
            StackPane.setAlignment(returnButtonForCredits, Pos.TOP_LEFT);
            StackPane.setMargin(returnButtonForCredits, new Insets(10));

            root.setCenter(creditsPane);
        });

        exitButton.setOnAction(e -> primaryStage.close());
    }

    /**
     * Launches the game window with the selected vehicle type.
     *
     * @param stage     the stage of the application
     * @param classType the type of vehicle chosen ("car", "bike", "truck")
     */
    private void launchGame(Stage stage, String classType) {
        new GameWindow(stage, classType);
    }

    /**
     * The main entry point of the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creates a styled button with a given label and border color.
     *
     * @param text  the label of the button
     * @param color the border color of the button
     * @return the styled Button instance
     */
    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setStyle(String.format(
                "-fx-font-size: 20; -fx-pref-width: 160; -fx-pref-height: 50; -fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: %s; -fx-border-width: 2; -fx-border-radius: 15; -fx-cursor: hand; -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 10, 0.2, 0, 4);",
                color));
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.BLACK);
        shadow.setRadius(10);
        button.setEffect(shadow);
        return button;
    }

    /**
     * Creates a button with a vehicle image and name.
     *
     * @param vehicleName the name of the vehicle
     * @param imagePath   the path to the image of the vehicle
     * @return the styled Button instance with an image
     */
    private Button createVehicleButton(String vehicleName, String imagePath) {
        ImageView imageView = loadImage(imagePath);
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);
        Button button = new Button(vehicleName, imageView);
        button.setStyle(
                "-fx-font-size: 16; -fx-pref-width: 180; -fx-pref-height: 100; -fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: gray; -fx-border-radius: 10; -fx-cursor: hand;");
        return button;
    }

    /**
     * Loads an image from the specified file path.
     *
     * @param imagePath the path to the image
     * @return an ImageView with the loaded image
     */
    private ImageView loadImage(String imagePath) {
        try {
            File file = new File(imagePath);
            if (!file.exists()) {
                throw new FileNotFoundException("Image file not found: " + file.getAbsolutePath());
            }
            return new ImageView(new Image(file.toURI().toString()));
        } catch (Exception e) {
            System.err.println("Error loading image: " + imagePath);
            e.printStackTrace();
            return new ImageView();
        }

    }

    /**
     * Opens the {@code Settings} for the game, allowing the user to choose vehicle controls.
     *
     * @param stage the stage of the application
     * @param root  the root layout of the application
     * @param menu  the main menu layout
     */
    private void openSettingsMenu(Stage stage, BorderPane root, VBox menu) {
        VBox settingsBox = new VBox(20);
        settingsBox.setAlignment(Pos.CENTER);
        settingsBox.setPadding(new Insets(30));
        settingsBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-background-radius: 0;");

        Label title = new Label("Choose Vehicle Controls");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        title.setTextFill(Color.LIGHTGRAY);

        ToggleGroup keyGroup = new ToggleGroup();

        ToggleButton arrowKeysOption = new ToggleButton("Left Arrow - Right Arrow");
        arrowKeysOption.setToggleGroup(keyGroup);
        arrowKeysOption.setSelected(true);
        arrowKeysOption.setStyle("-fx-font-size: 16; -fx-background-color: lightgray; -fx-cursor: hand;");

        ToggleButton qdKeysOption = new ToggleButton("Q - D");
        qdKeysOption.setToggleGroup(keyGroup);
        qdKeysOption.setStyle("-fx-font-size: 16; -fx-background-color: lightgray; -fx-cursor: hand;");

        keyGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == arrowKeysOption) {
                updateCarControls("arrow");
            } else if (newToggle == qdKeysOption) {
                updateCarControls("qd");
            }
        });

        Button returnButton = createStyledButton("Return", "#4caf50");
        returnButton.setOnAction(e -> root.setCenter(menu));

        settingsBox.getChildren().addAll(title, arrowKeysOption, qdKeysOption, returnButton);

        root.setCenter(settingsBox);
    }

    /**
     * Updates the {@code Control Scheme} for the player's vehicle in the game.
     * This method sets the key bindings for left and right movement based on the specified control scheme.
     *
     * @param controlScheme A String indicating the desired control scheme.
     *                      Valid values are "arrow" for arrow key controls,
     *                      and "qd" for Q and D key controls.
     *                      Any other value will result in no changes to the controls.
     */
    private void updateCarControls(String controlScheme) {
        if ("arrow".equals(controlScheme)) {
            GameWindow.playerControlLeft = KeyCode.LEFT;
            GameWindow.playerControlRight = KeyCode.RIGHT;
        } else if ("qd".equals(controlScheme)) {
            GameWindow.playerControlLeft = KeyCode.Q;
            GameWindow.playerControlRight = KeyCode.D;
        }
    }
}
