package ru.nsu.ntatarinov;

import java.awt.Point;
import java.util.LinkedList;

public class SnakeBody {

    private static final int RIGHT = 1;
    private static final int LEFT = 3;
    private static final int UP = 0;
    private static final int DOWN = 2;
    public LinkedList<Point> body = new LinkedList<>();
    public Point head;
    public int currentDirection;
    private final int ROWS;
    private final int COLUMNS;
    public boolean allowedChangeDirection = true;

    public SnakeBody(int ROWS, int COLUMNS) {
        this.ROWS = ROWS;
        this.COLUMNS = COLUMNS;
        this.head = new Point(ROWS / 2, COLUMNS / 2);
        body.add(head);
        this.currentDirection = UP;
    }

    public void makeSnakeStep() {
        body.removeLast();
        body.addFirst(new Point(head.x, head.y));
        switch (currentDirection) {
            case RIGHT:
                moveRight();
            case LEFT:
                moveLeft();
            case UP:
                moveUp();
            case DOWN:
                moveDown();
        }
        allowedChangeDirection = true;
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

    public void resetSnakeBody() {
        body.clear();
        this.head = new Point(ROWS / 2, COLUMNS / 2);
        body.add(head);
        this.currentDirection = UP;
    }
}
