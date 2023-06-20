package ru.nsu.ntatarinov;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Game over screen.
 */
public class GameOverWindow {

    private final Scene scene;
    private final VBox root;
    private Label scoreValue;
    private final SnakeGameController controller;
    private final Stage primaryStage;
    private Label gameResultLabel;

    /**
     * Constructor of game over screen.
     *
     * @param primaryStage primary stage
     * @param width        width of stage
     * @param height       height of stage
     * @param controller   controller module
     */
    public GameOverWindow(Stage primaryStage, int width, int height,
        SnakeGameController controller) {
        this.primaryStage = primaryStage;
        this.controller = controller;
        this.root = new VBox();
        this.scene = new Scene(root, width, height);
        root.setStyle("-fx-background-color: #000000");
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        setGameOverLabel();
        setNewGameButton();
        setExitButton();
    }

    private void setGameOverLabel() {
        this.gameResultLabel = new Label();
        Font font = new Font(70);
        gameResultLabel.setFont(font);
        root.getChildren().add(gameResultLabel);
        scoreValue = new Label();
        scoreValue.setFont(new Font(30));
        root.getChildren().add(scoreValue);
    }

    private void setNewGameButton() {
        Button newGameButton = new Button("New game");
        newGameButton.setPrefWidth(100);
        newGameButton.setPrefHeight(30);
        root.getChildren().add(newGameButton);
        newGameButton.setOnAction(controller.getNewGameHandler());
    }

    private void setExitButton() {
        Button exitButton = new Button("exit");
        exitButton.setPrefWidth(100);
        exitButton.setPrefHeight(30);
        root.getChildren().add(exitButton);
        exitButton.setOnAction(controller.getExitHandler());
    }

    /**
     * Shows this windows on a stage.
     *
     * @param score      collected score
     * @param gameResult win or lose
     */
    public void showGameOver(int score, String gameResult) {
        gameResultLabel.setText(gameResult);
        if (gameResult.equals("WIN")) {
            gameResultLabel.setTextFill(Color.YELLOW);
        } else {
            gameResultLabel.setTextFill(Color.RED);
        }
        scoreValue.setText("Your score: " + score);
        primaryStage.setScene(scene);
    }
}
