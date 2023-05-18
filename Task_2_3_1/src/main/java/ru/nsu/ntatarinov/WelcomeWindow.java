package ru.nsu.ntatarinov;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;


public class WelcomeWindow {

    private Scene scene;
    private SnakeGame gameProccess;
    private VBox root;
    private Popup helpWindow;

    public WelcomeWindow(SnakeGame gameProccess) {
        this.root = new VBox();
        this.scene = new Scene(root, 800, 800);
        this.gameProccess = gameProccess;
        BackgroundImage background = new BackgroundImage(
            new Image("welcome_background.png", 800, 800, false, true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
        root.setBackground(new Background(background));
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        this.setStartButton();
        this.setHelpButton();
        this.setExitButton();
        this.showWelcomeWindow();
    }

    private void setStartButton() {
        Button startButton = new Button("Start");
        startButton.setPrefWidth(100);
        startButton.setPrefHeight(30);
        root.getChildren().add(startButton);
        startButton.setOnAction(event -> {
            gameProccess.showGameField();
            try {
                helpWindow.hide();
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
        exitButton.setOnAction(event -> Platform.exit());
    }

    public void showWelcomeWindow() {
        this.gameProccess.mainStage.setScene(this.scene);
    }

    public void setHelpButton() {
        Button helpButton = new Button("help");
        helpButton.setPrefWidth(100);
        helpButton.setPrefHeight(30);
        root.getChildren().add(helpButton);
        this.helpWindow = new Popup();
        Label label = new Label("WASD or arrows to control snake.\nSPACE for pause.");
        label.setMinHeight(100);
        label.setMinWidth(300);
        label.setStyle(
            "-fx-alignment: center; -fx-background-color: gray; -fx-border-color: black");
        helpWindow.getContent().add(label);
        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!helpWindow.isShowing()) {
                    helpWindow.show(gameProccess.mainStage);
                } else {
                    helpWindow.hide();
                }
            }
        });
    }
}
