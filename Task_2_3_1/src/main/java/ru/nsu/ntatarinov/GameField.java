package ru.nsu.ntatarinov;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameField {

    public Scene scene;
    private final Stage primaryStage;
    private final GraphicsContext gcBackground;
    private final GraphicsContext gcObjects;
    private final StackPane root;
    private Label pauseLabel;
    private Button restartButton;
    private final int WIDTH;
    private final int HEIGHT;
    private final int ROWS;
    private final int COLUMNS;
    private final int SQUARE_SIZE;
    private final SnakeGameController controller;

    public GameField(Stage primaryStage, int width, int height, int rows, int columns,
        SnakeGameController controller) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.ROWS = rows;
        this.COLUMNS = columns;
        this.SQUARE_SIZE = WIDTH / COLUMNS;
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.root = new StackPane();
        Canvas canvasObjects = new Canvas(WIDTH, HEIGHT);
        Canvas canvasBackground = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvasBackground);
        root.getChildren().add(canvasObjects);
        setPauseFrame();
        this.scene = new Scene(root, WIDTH, HEIGHT);
        this.gcBackground = canvasBackground.getGraphicsContext2D();
        this.gcObjects = canvasObjects.getGraphicsContext2D();

    }

    void showGameField() {
        drawBackground();
        this.primaryStage.setScene(this.scene);
    }


    public void drawBackground() {
        for (int i = 0; i < COLUMNS; i++) {
            for (int j = 0; j < ROWS; j++) {
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

    private void setPauseFrame() {
        restartButton = new Button("Restart");
        restartButton.setPrefWidth(100);
        restartButton.setPrefHeight(30);
        restartButton.setOnAction(controller.getNewGameHandler());
        this.pauseLabel = new Label("Pause");
        pauseLabel.setFont(new Font(40));
        pauseLabel.setVisible(false);
        restartButton.setVisible(false);
        VBox pauseFrame = new VBox();
        pauseFrame.setAlignment(Pos.CENTER);
        pauseFrame.getChildren().add(pauseLabel);
        pauseFrame.getChildren().add(restartButton);
        root.getChildren().add(pauseFrame);
    }

    public void drawSnake(int currentDirection, LinkedList<Point> body) {
        ImageView iv = new ImageView(
            new Image("snake_avatar.png", SQUARE_SIZE, SQUARE_SIZE, false, true));
        iv.setRotate(90 * currentDirection);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        this.gcObjects
            .drawImage(iv.snapshot(params, null), body.getFirst().getX() * SQUARE_SIZE,
                body.getFirst().getY() * SQUARE_SIZE);

        this.gcObjects.setFill(Color.web("#cbbb4f"));
        for (int i = 1; i < body.size(); i++) {
            this.gcObjects
                .fillRoundRect(body.get(i).getX() * SQUARE_SIZE, body.get(i).getY() * SQUARE_SIZE,
                    SQUARE_SIZE - 1,
                    SQUARE_SIZE - 1, 10, 10);
        }
    }

    public void drawFood(List<Point> foods) {
        for (Point food : foods) {
            gcObjects.setFill(Color.web("#f50408"));
            gcObjects.fillRoundRect(food.x * SQUARE_SIZE, food.y * SQUARE_SIZE, SQUARE_SIZE - 1,
                SQUARE_SIZE - 1, 35, 35);
        }
    }


    public void drawScore(int score) {
        gcObjects.setFill(Color.WHITE);
        gcObjects.setFont(new Font(35));
        gcObjects.fillText("Score: " + score, 10, 35);
    }

    public void drawWalls(List<Point> walls) {
        gcObjects.setFill(Color.GRAY);
        for (Point wallCell : walls) {
            gcObjects.fillRoundRect(wallCell.x * SQUARE_SIZE, wallCell.y * SQUARE_SIZE,
                SQUARE_SIZE - 1,
                SQUARE_SIZE - 1, 35, 35);
        }
    }

    public void showPauseText() {
        pauseLabel.setVisible(true);
        restartButton.setVisible(true);
    }

    public void turnOffPauseText() {
        pauseLabel.setVisible(false);
        restartButton.setVisible(false);
    }

    public void clearObjects() {
        this.gcObjects.clearRect(0, 0, WIDTH, HEIGHT);
    }
}
