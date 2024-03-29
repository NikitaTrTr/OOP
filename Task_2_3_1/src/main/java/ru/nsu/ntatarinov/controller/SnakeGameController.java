package ru.nsu.ntatarinov.controller;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.stage.Popup;
import javafx.stage.Stage;
import ru.nsu.ntatarinov.SnakeGameMain;
import ru.nsu.ntatarinov.model.SnakeGameModel;
import ru.nsu.ntatarinov.view.SnakeGameView;

/**
 * Controller module.
 */
public class SnakeGameController {

    private static final int RIGHT = 1;
    private static final int LEFT = 3;
    private static final int UP = 0;
    private static final int DOWN = 2;
    private SnakeGameView gameView;
    private SnakeGameModel gameModel;
    private SnakeGameMain gameProcess;
    private Stage primaryStage;

    public SnakeGameController() {
    }

    /**
     * Constructor of a controller.
     *
     * @param gameView     view module
     * @param gameModel    model module
     * @param gameProcess  main game process
     * @param primaryStage primary stage
     */
    public void setController(SnakeGameView gameView, SnakeGameModel gameModel,
        SnakeGameMain gameProcess, Stage primaryStage) {
        this.gameView = gameView;
        this.gameModel = gameModel;
        this.gameProcess = gameProcess;
        this.primaryStage = primaryStage;
        setSnakeControls();
    }

    /**
     * Returns an event handler for help button.
     *
     * @param helpWindow window with tutorial
     * @return event handler for help button
     */
    public EventHandler<ActionEvent> getHelpHandler(Popup helpWindow) {
        return event -> {
            if (!helpWindow.isShowing()) {
                helpWindow.show(primaryStage);
            } else {
                helpWindow.hide();
            }
        };
    }

    public EventHandler<ActionEvent> getExitHandler() {
        return event -> Platform.exit();
    }

    /**
     * Returns an event handler for start button.
     *
     * @param helpWindow window with tutorial
     * @return event handler for start button
     */
    public EventHandler<ActionEvent> getStartHandler(Popup helpWindow) {
        return event -> {
            gameView.showGameField();
            try {
                helpWindow.hide();
                gameProcess.startMainGameCycle();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    /**
     * Returns an event handler for restart button.
     *
     * @return event handler for restart button
     */
    public EventHandler<ActionEvent> getNewGameHandler() {
        return event -> {
            gameView.showGameField();
            gameView.turnOffPauseText();
            gameProcess.getTimeline().stop();
            gameView.clear();
            gameModel.resetGame();
            try {
                gameProcess.startMainGameCycle();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private void setSnakeControls() {
        gameView.getScene().setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            int currentDirection = gameModel.snakeBody.currentDirection;
            if (!gameModel.snakeBody.allowedChangeDirection) {
                return;
            }
            if (code == KeyCode.RIGHT || code == KeyCode.D) {
                if (currentDirection != LEFT) {
                    gameModel.snakeBody.currentDirection = RIGHT;
                    gameModel.snakeBody.allowedChangeDirection = false;
                }
            } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                if (currentDirection != RIGHT) {
                    gameModel.snakeBody.currentDirection = LEFT;
                    gameModel.snakeBody.allowedChangeDirection = false;
                }
            } else if (code == KeyCode.UP || code == KeyCode.W) {
                if (currentDirection != DOWN) {
                    gameModel.snakeBody.currentDirection = UP;
                    gameModel.snakeBody.allowedChangeDirection = false;
                }
            } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                if (currentDirection != UP) {
                    gameModel.snakeBody.currentDirection = DOWN;
                    gameModel.snakeBody.allowedChangeDirection = false;
                }
            } else if (code == KeyCode.ESCAPE) {
                gameProcess.turnPause();
            }
        });
    }

    /**
     * Make single game step.
     */
    public void makeGameStep() {
        LinkedList<Point> body = gameModel.getSnakeBody();
        List<Point> foods = gameModel.getFoods();
        int score = gameModel.getScore();
        int remainingScore = gameModel.getRemainingScore();
        int direction = gameModel.getDirection();
        List<Point> walls = gameModel.getWalls();
        gameModel.checkGameOver();
        gameModel.makeSnakeStep();
        gameView.updateView(body, foods, score, remainingScore, direction, walls);
        gameModel.checkFoodEating();
        if (gameModel.gameOver && gameModel.winGame) {
            gameView.showGameOverWindow(score, "WIN");
            return;
        }
        if (gameModel.gameOver) {
            gameView.showGameOverWindow(score, "LOSE");
        }
    }
}
