package ru.nsu.ntatarinov;

import static ru.nsu.ntatarinov.SnakeGame.SQUARE_SIZE;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Canvas canvas = new Canvas(800, 800);
        canvas.getGraphicsContext2D().setFill(Color.web("#566576"));
        canvas.getGraphicsContext2D().fillRect(0, 0, 800, 800);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
