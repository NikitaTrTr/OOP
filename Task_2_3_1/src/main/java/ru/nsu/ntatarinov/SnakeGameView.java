package ru.nsu.ntatarinov;

import java.awt.Point;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SnakeGameView {

    private final Stage primaryStage;
    private final GameField gameField;
    private final WelcomeWindow welcomeWindow;
    private final GameOverWindow gameOverWindow;

    public SnakeGameView(int width, int height, int rows, int columns, Stage primaryStage,
        SnakeGameController controller) {
        this.primaryStage = primaryStage;
        this.configureStage();
        this.welcomeWindow = new WelcomeWindow(primaryStage, width, height, controller);
        this.gameField = new GameField(primaryStage, width, height, rows, columns, controller);
        this.gameOverWindow = new GameOverWindow(primaryStage, width, height, controller);
        this.showWelcomeWindow();
    }

    private void configureStage() {
        this.primaryStage.setTitle("Solid Snake Game");
        this.primaryStage.setResizable(false);
        InputStream iconStream = getClass().getResourceAsStream("/snake_avatar.png");
        assert iconStream != null;
        Image image = new Image(iconStream);
        this.primaryStage.getIcons().add(image);
    }

    public void showWelcomeWindow() {
        this.welcomeWindow.showWelcomeWindow();
    }

    public void showGameOverWindow(int score, String gameResult) {
        this.gameOverWindow.showGameOver(score, gameResult);
    }

    public void updateView(LinkedList<Point> body, List<Point> foods, int score,
        int currentDirection, List<Point> walls) {
        gameField.clearObjects();
        gameField.drawSnake(currentDirection, body);
        gameField.drawFood(foods);
        gameField.drawWalls(walls);
        gameField.drawScore(score);
    }

    public void showGameField() {
        gameField.showGameField();
    }

    public Scene getScene() {
        return gameField.scene;
    }

    public void showPauseText() {
        gameField.showPauseText();
    }

    public void turnOffPauseText() {
        gameField.turnOffPauseText();
    }

    public void clear() {
        gameField.clearObjects();
    }
}
