# **Rush Hour Game - README**

## **Project Summary**
"Rush Hour" is a Java-based racing game built with JavaFX. The player controls a vehicle through a road full of obstacles, aiming to avoid collisions and achieve the highest possible score. The game features multiple levels, with increasing difficulty as the player progresses.

## **Game Design Document (GDD)**

### **1. Game Concept**
"Rush Hour" is an endless racing game where the player must navigate through traffic, avoiding obstacles while collecting points. The player must stay alive as long as possible while the difficulty increases with each level.

### **2. Gameplay Mechanics**
- **Controls**:
    - **Left Arrow**: Move the vehicle left
    - **Right Arrow**: Move the vehicle right
    - **Spacebar**: Pause the game
    - **Enter**: Restart the game after a collision or start a new game

- **Levels**: The game increases in difficulty every 10 points. The speed of obstacles rises with each level.
- **Obstacles**: Vehicles that spawn on the road and must be avoided.
- **Score**: The score increases as the player survives, and it resets upon a crash.

### **3. Story**
In "Rush Hour", the player is a vehicle navigating through heavy traffic. The goal is to avoid obstacles and keep driving as long as possible to rack up points, increasing the difficulty level with every milestone.

### **4. Game Style**
- **Graphics**: The game uses simple 2D graphics with a pixel-art aesthetic. Vehicles are drawn using JavaFX's `ImageView`.
- **UI**: The user interface includes score display, level information, and in-game notifications.

### **5. Technical Requirements**
- **Platform**: The game is compatible with Windows, macOS, and any platform supporting JavaFX.
- **Programming Language**: Java
- **Framework**: JavaFX for graphical user interface and animation.

---

## **Installation and Usage**

### **Prerequisites**
- **Java**: Java 8 or higher is required (ensure that `java` and `javac` are installed and configured on your system).
- **JavaFX**: This library is used for the game's graphical interface. Make sure JavaFX is properly set up.

### **Installation Steps**
1. **Clone the project**:
    ```bash
    git clone git@github.com:EpitechMscProPromo2027/T-JAV-501-PAR_7.git
    ```

2. **Navigate to the project folder**:
    ```bash
    cd CarGameProject
    ```

3. **Ensure JavaFX is installed**. You can download JavaFX from [https://openjfx.io/](https://openjfx.io/).

4. **Compile and run the project**:
    - If you're using the command line, ensure that your `PATH` is set for `javac` and `java` commands, and also for the JavaFX library.
    - Example compilation command:
      ```bash
      javac --module-path path\to\javafx-sdk-23.0.1\lib --add-modules javafx.controls,javafx.graphics,javafx.media -d bin src/game/*.java
      ```

    - Example run command:
      ```bash
       java --module-path paht\to\javafx-sdk-23.0.1\lib --add-modules javafx.controls,javafx.graphics -cp bin Main 
      ```

### **Deployment**
- The game is intended to run in a Java environment with JavaFX.
- Game assets (images) are stored in an `assets` folder, which is included in the project.

### **Features**
- **Controls**: Left and right arrow keys to move the vehicle. Spacebar to pause, and Enter to restart the game after a crash.
- **Score System**: The score increases as you move forward in time. The score resets when the player crashes.
- **Progressive Difficulty**: The speed of obstacles increases with every 10 points, adding more challenge at each level.

---

## **Folder and Class Structure**

Here is a basic example of the folder structure and class organization that you can modify based on your specific project setup:

### **Folder Structure**
```plaintext
CarGameProject/
│
├── .idea/
│   ├── librairies/
│   ├── .gitignore
│   ├── misc.xml
│   ├── modules.xml
│   ├── vsc.xml
│   └── workspace.xml
│
├── .vscode/
│   ├── launch.json
│   └── tasks.json
│
├── assets/                   # Game assets (images)
│   ├── cars/
│   │   ├── carObstacle1.png
│   │   ├── carObstacle2.png
│   │   ├── carObstacle3.png
│   │   ├── carObstacle4.png
│   │   ├── carObstacle5.png
│   │   ├── carObstacle6.png
│   │   ├── carObstacle7.png
│   │   ├── carObstacle8.png
│   │   ├── carObstacle9.png
│   │   ├── carObstacle10.png
│   │   ├── carObstacle11.png
│   │   ├── carObstacle12.png
│   │   ├── carObstacle13.png
│   │   ├── carObstacle14.png
│   │   ├── playerBike.png
│   │   ├── playerCar.png
│   │   └── playerTruck.png
│   │
│   ├── gameLogo.png
│   ├── MenuBackground.png
│   ├── tree1.png
│   ├── tree2.png
│   └── tree3.png
│
├── bin/                  # Game classes
│   ├── game                 
│   │   ├── GameWindow.java    
│   │   ├── GamePanel.java     
│   │   ├── PlayerVehicle.java   
│   │   ├── PlayerCar.java       
│   │   ├── PlayerBike.java      
│   │   ├── Main.java            
│   │   ├── PlayerTruck.java     
│   └── └── Obstacle.java 
│   │ 
│   ├── GameWindow.class
│   ├── GameWindow$1.class
│   ├── GamePanel.class 
│   ├── PlayerVehicle.class
│   ├── PlayerCar.class 
│   ├── PlayerBike.class
│   ├── PlayerTruck.class
│   └── Obstacle.class
│
├── out/                     # Compiled classes output
│   ├── production/
│   │   ├── CarGameProject/
│   │   ├── game                 
│   │   │   ├── GameWindow.java    
│   │   │   ├── GamePanel.java     
│   │   │   ├── PlayerVehicle.java   
│   │   │   ├── PlayerCar.java       
│   │   │   ├── PlayerBike.java      
│   │   │   ├── Main.java            
│   │   │   ├── PlayerTruck.java     
│   │   └── └── Obstacle.java 
│   │   │
│   │   ├── test         
│   │   │   ├── CollisionTest.java
│   │   │   ├── GameWindowTest.java
│   │   └── └── PlayerVehicleTest.java
│   │   │   │ 
│   │   │   ├── GamePanel.class
│   │   │   ├── GameWindow.class
│   │   │   ├── Main.class
│   │   │   ├── PlayerBike.class
│   │   │   ├── PlayerCar.class
│   │   │   ├── PlayerTruck.class
│   └── └── └── PlayerVehicle.class
│
├── src/                      # Source code
│   ├── game                    # Principal Game Package
│   │   ├── GameWindow.java       # Main game window and logic
│   │   ├── GamePanel.java        # Canvas where game is drawn
│   │   ├── PlayerVehicle.java    # Abstract class for player vehicles
│   │   ├── PlayerCar.java        # Car implementation of PlayerVehicle
│   │   ├── PlayerBike.java       # Bike implementation of PlayerVehicle
│   │   ├── Main.java             # Main runnable class
│   │   ├── PlayerTruck.java      # Truck implementation of PlayerVehicle
│   └── └── Obstacle.java         # Obstacle class
│   │
│   ├── test                    # Unit Tests Package
│   │   ├── CollisionTest.java        # Collision test
│   │   ├── GameWindowTest.java       # Level Management testing
│   └── └── PlayerVehicleTest.java    # Vehicle Controls test
│
├── .gitignore
├── CarGameProject.iml
└── README.md                 # Project README
```
