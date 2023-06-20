package ru.nsu.ntatarinov;

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
import javafx.stage.Stage;


public class WelcomeWindow {

    private final Scene scene;
    private final Stage primaryStage;
    private final VBox root;
    private final Popup helpWindow;
    private Button startButton;
    private Button exitButton;
    private Button helpButton;
    private final SnakeGameController controller;

    public WelcomeWindow(Stage primaryStage, int width, int height,
        SnakeGameController controller) {
        this.root = new VBox();
        this.scene = new Scene(root, width, height);
        this.primaryStage = primaryStage;
        this.controller = controller;
        this.helpWindow = new Popup();
        BackgroundImage background = new BackgroundImage(
            new Image("welcome_background.png", width, height, false, true),
            BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
        root.setBackground(new Background(background));
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        this.setStartButton();
        this.setHelpButton();
        this.setExitButton();
    }

    private void setStartButton() {
        this.startButton = new Button("Start");
        startButton.setPrefWidth(100);
        startButton.setPrefHeight(30);
        root.getChildren().add(startButton);
        startButton.setOnAction(controller.getStartHandler(this.helpWindow));
    }

    private void setExitButton() {
        this.exitButton = new Button("exit");
        exitButton.setPrefWidth(100);
        exitButton.setPrefHeight(30);
        root.getChildren().add(exitButton);
        exitButton.setOnAction(controller.getExitHandler());
    }

    public void showWelcomeWindow() {
        this.primaryStage.setScene(this.scene);
    }

    public void setHelpButton() {
        this.helpButton = new Button("help");
        helpButton.setPrefWidth(100);
        helpButton.setPrefHeight(30);
        root.getChildren().add(helpButton);
        Label label = new Label("WASD or arrows to control snake.\nSPACE for pause.");
        label.setMinHeight(100);
        label.setMinWidth(300);
        label.setStyle(
            "-fx-alignment: center; -fx-background-color: gray; -fx-border-color: black");
        helpWindow.getContent().add(label);
        helpButton.setOnAction(controller.getHelpHandler(helpWindow));
    }
}
