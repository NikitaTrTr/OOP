package ru.nsu.ntatarinov;

import java.awt.Point;
import java.util.LinkedList;

/**
 * Snake body mechanics.
 */
public class SnakeBody {

    private static final int RIGHT = 1;
    private static final int LEFT = 3;
    private static final int UP = 0;
    private static final int DOWN = 2;
    public LinkedList<Point> body = new LinkedList<>();
    public Point head;
    public int currentDirection;
    private final int rows;
    private final int columns;
    public boolean allowedChangeDirection = true;

    /**
     * Constructor for a snake body.
     *
     * @param rows number of rows
     * @param columns number of columns
     */
    public SnakeBody(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.head = new Point(rows / 2, columns / 2);
        body.add(head);
        this.currentDirection = UP;
    }

    /**
     * Make single snake move.
     */
    public void makeSnakeStep() {
        body.removeLast();
        body.addFirst(new Point(head.x, head.y));
        switch (currentDirection) {
            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
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

    /**
     * Resets snake's state to its initial state.
     */
    public void resetSnakeBody() {
        body.clear();
        this.head = new Point(rows / 2, columns / 2);
        body.add(head);
        this.currentDirection = UP;
    }
}
