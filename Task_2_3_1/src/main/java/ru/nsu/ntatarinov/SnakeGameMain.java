package ru.nsu.ntatarinov;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Main class for Snake game.
 */
public class SnakeGameMain extends Application {

    private int width;
    private final int height = 800;
    private int rows;
    private int columns;
    private int squareSize;
    private final int frequency = 400;
    private int numberOfFood;
    private int winLength;
    private Timeline timeline;
    private SnakeGameModel snakeGameModel;
    private SnakeGameView snakeGameView;
    private SnakeGameController snakeGameController;

    private int[][] walls;

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws IOException {
        this.setProperties();
        this.snakeGameController = new SnakeGameController();
        this.snakeGameModel = new SnakeGameModel(rows, columns, winLength, numberOfFood, walls);
        this.snakeGameView = new SnakeGameView(width, height, rows, columns, primaryStage,
            snakeGameController);
        this.snakeGameController.setController(snakeGameView, snakeGameModel, this,
            primaryStage);
        this.snakeGameModel.setGameView(snakeGameView);
        primaryStage.show();
    }

    private void setProperties() throws FileNotFoundException {
        Gson propertiesParser = new Gson();
        Properties properties = propertiesParser.fromJson(
            new FileReader("src/main/resources/properties.json"), Properties.class);
        this.rows = properties.rows;
        this.columns = properties.columns;
        this.squareSize = height / rows;
        this.width = columns * squareSize;
        this.numberOfFood = properties.numberOfFood;
        this.winLength = properties.winLength;
        this.walls = properties.walls;
    }

    /**
     * Starts main game cycle.
     *
     * @throws InterruptedException Interrupted exception
     */
    public void startMainGameCycle() throws InterruptedException {
        this.timeline = new Timeline(
            new KeyFrame(Duration.millis(frequency), e -> mainGameCycle()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * One step of a game.
     */
    public void mainGameCycle() {
        snakeGameModel.makeGameStep();
        if (snakeGameModel.gameOver) {
            this.timeline.stop();
        }
    }

    /**
     * Turns on and off a pause.
     */
    public void turnPause() {
        if (snakeGameModel.isPause) {
            timeline.play();
            snakeGameModel.isPause = false;
            snakeGameView.turnOffPauseText();
        } else {
            timeline.stop();
            snakeGameModel.isPause = true;
            snakeGameView.showPauseText();
        }
    }

    /**
     * Get timeline object.
     *
     * @return timeline
     */
    public Timeline getTimeline() {
        return timeline;
    }
}
