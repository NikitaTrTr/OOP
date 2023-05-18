package ru.nsu.ntatarinov;

import static ru.nsu.ntatarinov.SnakeGame.COLUMNS;
import static ru.nsu.ntatarinov.SnakeGame.HEIGHT;
import static ru.nsu.ntatarinov.SnakeGame.ROWS;
import static ru.nsu.ntatarinov.SnakeGame.SQUARE_SIZE;
import static ru.nsu.ntatarinov.SnakeGame.WIDTH;

import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class SnakeBody {

    private static final int RIGHT = 1;
    private static final int LEFT = 3;
    private static final int UP = 0;
    private static final int DOWN = 2;
    private List<Point> body = new ArrayList<>();
    public Point head;
    private int currentDirection;
    private GameField gameField;
    private Image avatar;
    private SnakeGame gameProcess;
    private boolean allowedChangeDirrection = true;

    public SnakeBody(GameField gameField, SnakeGame gameProcess) {
        for (int i = 0; i < 3; i++) {
            body.add(new Point(COLUMNS / 2, ROWS / 2 + i));
        }
        this.head = body.get(0);
        this.currentDirection = UP;
        this.gameField = gameField;
        this.gameProcess = gameProcess;
        this.avatar = new Image("snake_avatar.png", SQUARE_SIZE, SQUARE_SIZE, false, true);
        this.setControls();

    }

    public void drawSnake() {
        ImageView iv = new ImageView(avatar);
        iv.setRotate(90 * currentDirection);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        gameField.getGraphicsContext()
            .drawImage(iv.snapshot(params, null), head.getX() * SQUARE_SIZE,
                head.getY() * SQUARE_SIZE);

        gameField.getGraphicsContext().setFill(Color.web("#cbbb4f"));
        for (int i = 1; i < body.size(); i++) {
            gameField.getGraphicsContext()
                .fillRoundRect(body.get(i).getX() * SQUARE_SIZE, body.get(i).getY() * SQUARE_SIZE,
                    SQUARE_SIZE - 1,
                    SQUARE_SIZE - 1, 10, 10);
        }
    }

    public void makeSnakeStep() {
        drawSnake();
        for (int i = this.body.size() - 1; i >= 1; i--) {
            this.body.get(i).x = body.get(i - 1).x;
            this.body.get(i).y = body.get(i - 1).y;
        }
        switch (currentDirection) {
            case RIGHT -> moveRight();
            case LEFT -> moveLeft();
            case UP -> moveUp();
            case DOWN -> moveDown();
        }
        allowedChangeDirrection = true;
    }

    private void moveRight() {
        this.head.x++;
    }

    private void moveLeft() {
        this.head.x--;
    }

    private void moveUp() {
        this.head.y--;
    }

    private void moveDown() {
        this.head.y++;
    }

    public void increaseLen() {
        body.add(new Point(-1, -1));
    }

    private void setControls() {
        gameField.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode code = event.getCode();
                if (!allowedChangeDirrection) {
                    return;
                }
                if (code == KeyCode.RIGHT || code == KeyCode.D) {
                    if (currentDirection != LEFT) {
                        currentDirection = RIGHT;
                        allowedChangeDirrection = false;
                    }
                } else if (code == KeyCode.LEFT || code == KeyCode.A) {
                    if (currentDirection != RIGHT) {
                        currentDirection = LEFT;
                        allowedChangeDirrection = false;
                    }
                } else if (code == KeyCode.UP || code == KeyCode.W) {
                    if (currentDirection != DOWN) {
                        currentDirection = UP;
                        allowedChangeDirrection = false;
                    }
                } else if (code == KeyCode.DOWN || code == KeyCode.S) {
                    if (currentDirection != UP) {
                        currentDirection = DOWN;
                        allowedChangeDirrection = false;
                    }
                } else if (code == KeyCode.SPACE) {
                    gameProcess.turnPause();
                }

            }
        });
    }

    public boolean gameOver() {
        if (head.getX() < 0 || head.getY() < 0 || head.getX() * SQUARE_SIZE >= WIDTH
            || head.getY() * SQUARE_SIZE >= HEIGHT) {
            return true;
        }
        for (int i = 1; i < body.size(); i++) {
            if (head.getX() == body.get(i).getX() && head.getY() == body.get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    public void resetSnakeBody() {
        body.clear();
        for (int i = 0; i < 3; i++) {
            body.add(new Point(COLUMNS / 2, ROWS / 2 + i));
        }
        this.head = body.get(0);
        this.currentDirection = UP;
    }
}
