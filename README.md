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
    git clone git@github.com:GibrilKharfallah/Rush-Hour.git
    ```

2. **Ensure JavaFX is installed**. You can download JavaFX from [https://openjfx.io/](https://openjfx.io/).

3. **Compile and run the project**:
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

## 📝 License

This project is open-source and available under the [MIT License](https://opensource.org/licenses/MIT).

---
Feel free to contribute, modify the code, or apply it to other datasets for experimentation. 
