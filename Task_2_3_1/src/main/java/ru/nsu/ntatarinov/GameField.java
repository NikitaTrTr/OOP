package ru.nsu.ntatarinov;

import static ru.nsu.ntatarinov.SnakeGame.COLUMNS;
import static ru.nsu.ntatarinov.SnakeGame.HEIGHT;
import static ru.nsu.ntatarinov.SnakeGame.ROWS;
import static ru.nsu.ntatarinov.SnakeGame.SQUARE_SIZE;
import static ru.nsu.ntatarinov.SnakeGame.WIDTH;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameField {

    public Scene scene;
    private Stage stage;
    private SnakeGame gameProccess;
    private GraphicsContext gcBackground;
    private GraphicsContext gcObjects;
    private StackPane root;
    private StackPane pauseFrame;
    public int foodX, foodY;

    public GameField(Stage stage, SnakeGame gameProccess) {
        this.stage = stage;
        this.gameProccess = gameProccess;
        this.root = new StackPane();
        this.pauseFrame = new StackPane();
        Canvas canvasObjects = new Canvas(SnakeGame.WIDTH, SnakeGame.HEIGHT);
        Canvas canvasBackground = new Canvas(SnakeGame.WIDTH, SnakeGame.HEIGHT);
        Label label = new Label("Pause");
        label.setFont(new Font(40));
        label.setVisible(false);
        root.getChildren().add(canvasBackground);
        root.getChildren().add(canvasObjects);
        root.getChildren().add(label);
        this.scene = new Scene(root, SnakeGame.WIDTH, SnakeGame.HEIGHT);
        this.gcBackground = canvasBackground.getGraphicsContext2D();
        this.gcObjects = canvasObjects.getGraphicsContext2D();

    }

    void showGameField() {
        this.stage.setScene(this.scene);
    }

    public GraphicsContext getGraphicsContext() {
        return this.gcObjects;
    }

    public void drawBackground() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gcBackground.setFill(Color.web("#566576"));
                } else {
                    this.gcBackground.setFill(Color.web("#415165"));
                }
                this.gcBackground.fillRect(i * SQUARE_SIZE, j * SQUARE_SIZE, SQUARE_SIZE,
                    SQUARE_SIZE);
            }
        }
    }

    public void generateFood() {
        foodX = (int) (Math.random() * ROWS);
        foodY = (int) (Math.random() * COLUMNS);
        gcObjects.setFill(Color.web("#f50408"));
        gcObjects.fillRoundRect(foodX * SQUARE_SIZE, foodY * SQUARE_SIZE, SQUARE_SIZE - 1,
            SQUARE_SIZE - 1, 35, 35);
    }

    public void drawFood() {
        gcObjects.setFill(Color.web("#f50408"));
        gcObjects.fillRoundRect(foodX * SQUARE_SIZE, foodY * SQUARE_SIZE, SQUARE_SIZE - 1,
            SQUARE_SIZE - 1, 35, 35);
    }

    public void drawScore() {
        gcObjects.setFill(Color.WHITE);
        gcObjects.setFont(new Font(35));
        gcObjects.fillText("Score: " + gameProccess.score, 10, 35);
    }

    public void clearGcObjects() {
        gcObjects.clearRect(0, 0, WIDTH, HEIGHT);
    }

    public void showPauseText() {

        root.getChildren().get(root.getChildren().size() - 1).setVisible(true);
    }

    public void turnOffPauseText() {
        root.getChildren().get(root.getChildren().size() - 1).setVisible(false);
    }

}
