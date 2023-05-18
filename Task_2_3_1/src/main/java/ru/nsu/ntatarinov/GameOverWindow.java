package ru.nsu.ntatarinov;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOverWindow {

    private Scene scene;
    private SnakeGame gameProccess;
    private VBox root;
    private Label scoreValue;

    public GameOverWindow(SnakeGame gameProccess) {
        this.root = new VBox();
        this.scene = new Scene(root, 800, 800);
        this.gameProccess = gameProccess;
        root.setStyle("-fx-background-color: #000000");
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        setGameOverLabel();
        setNewGameButton();
        setExitButton();
    }

    private void setGameOverLabel() {
        Label label = new Label("GAME OVER");
        Font font = new Font(70);
        label.setTextFill(Color.RED);
        label.setFont(font);
        root.getChildren().add(label);
        scoreValue = new Label();
        scoreValue.setFont(new Font(30));
        root.getChildren().add(scoreValue);
    }

    private void setNewGameButton() {
        Button newGameButton = new Button("New game");
        newGameButton.setPrefWidth(100);
        newGameButton.setPrefHeight(30);
        root.getChildren().add(newGameButton);
        newGameButton.setOnAction(event -> {
            gameProccess.showGameField();
            gameProccess.resetGame();
            try {
                gameProccess.startMainGameLoop();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setExitButton() {
        Button exitButton = new Button("exit");
        exitButton.setPrefWidth(100);
        exitButton.setPrefHeight(30);
        root.getChildren().add(exitButton);
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
    }

    public void showGameOver() {
        scoreValue.setText("Your score: " + gameProccess.score);
        gameProccess.mainStage.setScene(scene);
    }
}
