package ru.nsu.ntatarinov;

import java.io.InputStream;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.animation.Animation;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;


public class SnakeGame extends Application {

    static final int WIDTH = 800;
    static final int HEIGHT = WIDTH;
    static final int ROWS = 16;
    static final int COLUMNS = ROWS;
    static final int SQUARE_SIZE = WIDTH / ROWS;
    private GameField gameField;
    private WelcomeWindow welcomeWindow;
    private GraphicsContext graphicsContext;
    private SnakeBody snake;
    public boolean gameOver;
    private GameOverWindow gameOverWindow;
    public int score = 0;
    public Stage mainStage;
    private Timeline timeline;
    private int frequency = 500;
    private boolean isPause = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {

        this.mainStage = mainStage;
        this.configureStage();
        this.gameField = new GameField(mainStage, this);
        this.welcomeWindow = new WelcomeWindow(this);
        this.gameOverWindow = new GameOverWindow(this);
        this.snake = new SnakeBody(gameField, this);
        this.showWelcomeWindow();

        mainStage.show();

    }

    private void configureStage() {
        this.mainStage.setTitle("Solid Snake Game");

        InputStream iconStream = getClass().getResourceAsStream("/snake_avatar.png");
        Image image = new Image(iconStream);
        this.mainStage.getIcons().add(image);
    }

    public void showGameField() {
        this.gameField.showGameField();
    }

    public void showWelcomeWindow() {
        this.welcomeWindow.showWelcomeWindow();
    }

    public void mainGameLoop() {
        if (gameOver) {
            this.timeline.stop();
            gameOverWindow.showGameOver();
            return;
        }
        gameField.clearGcObjects();
        gameField.drawScore();
        checkEatingFood();
        snake.makeSnakeStep();
        gameOver = snake.gameOver();
    }

    public void startMainGameLoop() throws InterruptedException {
        this.timeline = new Timeline(new KeyFrame(Duration.millis(frequency), e -> mainGameLoop()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        gameField.drawBackground();
        gameField.generateFood();
    }

    public void checkEatingFood() {
        if (gameField.foodX == snake.head.getX() && gameField.foodY == snake.head.getY()) {
            snake.increaseLen();
            score += 1;
            frequency = (int) (frequency * 0.9);
            timeline.stop();
            timeline.getKeyFrames().clear();
            timeline.getKeyFrames()
                .add(new KeyFrame(Duration.millis(frequency), e -> mainGameLoop()));
            timeline.play();
            gameField.generateFood();
            gameField.drawFood();
        } else {
            gameField.drawFood();
        }
    }

    public void resetGame() {
        snake.resetSnakeBody();
        gameField.clearGcObjects();
        gameOver = false;
        score = 0;
        frequency = 500;
    }

    public void turnPause() {
        if (isPause) {
            timeline.play();
            isPause = false;
            gameField.turnOffPauseText();
        } else {
            timeline.stop();
            isPause = true;
            gameField.showPauseText();
        }
    }
}
