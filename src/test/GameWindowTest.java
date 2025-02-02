package test;

import game.GameWindow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameWindowTest {

    @Test
    public void testScoreAndLevelProgression() {
        GameWindow gameWindow = new GameWindow(null, "car");

        gameWindow.score = 9;
        gameWindow.updateScore();
        assertEquals(10, gameWindow.score, "The score should increase by 1.");
        assertEquals(2, gameWindow.level, "The level should increase when the score reaches 10.");

        gameWindow.updateScore();
        assertEquals(11, gameWindow.score, "The score should continue increasing.");
        assertEquals(2, gameWindow.level, "The level should not change until the next threshold is reached.");
    }
}

