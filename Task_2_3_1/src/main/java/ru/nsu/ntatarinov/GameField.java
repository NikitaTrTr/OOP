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

/**
 * Game field class.
 */
public class GameField {

    public Scene scene;
    private final Stage primaryStage;
    private final GraphicsContext gcBackground;
    private final GraphicsContext gcObjects;
    private final StackPane root;
    private Label pauseLabel;
    private Button restartButton;
    private final int width;
    private final int height;
    private final int rows;
    private final int columns;
    private final int squareSize;
    private final SnakeGameController controller;

    /**
     * Game field constructor.
     *
     * @param primaryStage primary stage
     * @param width width of stage
     * @param height height f stage
     * @param rows number of rows
     * @param columns number of columns
     * @param controller controller module
     */
    public GameField(Stage primaryStage, int width, int height, int rows, int columns,
        SnakeGameController controller) {
        this.width = width;
        this.height = height;
        this.rows = rows;
        this.columns = columns;
        this.squareSize = this.width / this.columns;
        this.controller = controller;
        this.primaryStage = primaryStage;
        this.root = new StackPane();
        Canvas canvasObjects = new Canvas(this.width, this.height);
        Canvas canvasBackground = new Canvas(this.width, this.height);
        root.getChildren().add(canvasBackground);
        root.getChildren().add(canvasObjects);
        setPauseFrame();
        this.scene = new Scene(root, this.width, this.height);
        this.gcBackground = canvasBackground.getGraphicsContext2D();
        this.gcObjects = canvasObjects.getGraphicsContext2D();

    }

    void showGameField() {
        drawBackground();
        this.primaryStage.setScene(this.scene);
    }

    /**
     * Draw background of a game field.
     */
    public void drawBackground() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if ((i + j) % 2 == 0) {
                    gcBackground.setFill(Color.web("#566576"));
                } else {
                    this.gcBackground.setFill(Color.web("#415165"));
                }
                this.gcBackground.fillRect(i * squareSize, j * squareSize, squareSize,
                    squareSize);
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

    /**
     * Draw snake's body.
     *
     * @param currentDirection direction in which head should be directed
     * @param body coordinates of body's cells
     */
    public void drawSnake(int currentDirection, LinkedList<Point> body) {
        ImageView iv = new ImageView(
            new Image("snake_avatar.png", squareSize, squareSize, false, true));
        iv.setRotate(90 * currentDirection);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        this.gcObjects
            .drawImage(iv.snapshot(params, null), body.getFirst().getX() * squareSize,
                body.getFirst().getY() * squareSize);

        this.gcObjects.setFill(Color.web("#cbbb4f"));
        for (int i = 1; i < body.size(); i++) {
            this.gcObjects
                .fillRoundRect(body.get(i).getX() * squareSize, body.get(i).getY() * squareSize,
                    squareSize - 1,
                    squareSize - 1, 10, 10);
        }
    }

    /**
     * Draw foods on field.
     *
     * @param foods foods' location
     */
    public void drawFood(List<Point> foods) {
        for (Point food : foods) {
            gcObjects.setFill(Color.web("#f50408"));
            gcObjects.fillRoundRect(food.x * squareSize, food.y * squareSize, squareSize - 1,
                squareSize - 1, 35, 35);
        }
    }

    /**
     * Print score value.
     *
     * @param score score value
     */
    public void drawScore(int score) {
        gcObjects.setFill(Color.WHITE);
        gcObjects.setFont(new Font(35));
        gcObjects.fillText("Score: " + score, 10, 35);
    }

    /**
     * Draw walls.
     *
     * @param walls walls' position
     */
    public void drawWalls(List<Point> walls) {
        gcObjects.setFill(Color.GRAY);
        for (Point wallCell : walls) {
            gcObjects.fillRoundRect(wallCell.x * squareSize, wallCell.y * squareSize,
                squareSize - 1,
                squareSize - 1, 35, 35);
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
        this.gcObjects.clearRect(0, 0, width, height);
    }
}
