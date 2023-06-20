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

public class SnakeGameMain extends Application {

    private int WIDTH;
    private final int HEIGHT = 800;
    private int ROWS;
    private int COLUMNS;
    private int  SQUARE_SIZE;
    private int FREQUENCY = 400;
    private int NUMBER_OF_FOOD;
    private int WIN_LENGTH;
    public Timeline timeline;
    private SnakeGameModel snakeGameModel;
    private SnakeGameView snakeGameView;
    private SnakeGameController snakeGameController;

    private int[][] walls;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.setProperties();
        this.snakeGameController = new SnakeGameController();
        this.snakeGameModel = new SnakeGameModel(ROWS, COLUMNS, WIN_LENGTH, NUMBER_OF_FOOD, walls);
        this.snakeGameView = new SnakeGameView(WIDTH, HEIGHT, ROWS, COLUMNS, primaryStage,
            snakeGameController);
        this.snakeGameController.setController(snakeGameView, snakeGameModel, this, primaryStage);
        this.snakeGameModel.setGameView(snakeGameView);
        primaryStage.show();
    }

    private void setProperties() throws FileNotFoundException {
        Gson propertiesParser = new Gson();
        Properties properties = propertiesParser.fromJson(
            new FileReader("src/main/resources/properties.json"), Properties.class);
        this.ROWS = properties.rows;
        this.COLUMNS = properties.columns;
        this.SQUARE_SIZE = HEIGHT / ROWS;
        this.WIDTH = COLUMNS * SQUARE_SIZE;
        this.NUMBER_OF_FOOD = properties.numberOfFood;
        this.WIN_LENGTH = properties.winLength;
        this.walls = properties.walls;
    }

    public void startMainGameCycle() throws InterruptedException {
        this.timeline = new Timeline(
            new KeyFrame(Duration.millis(FREQUENCY), e -> mainGameCycle()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void mainGameCycle() {
        snakeGameModel.makeGameStep();
        if (snakeGameModel.gameOver) {
            this.timeline.stop();
        }
    }

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
}
