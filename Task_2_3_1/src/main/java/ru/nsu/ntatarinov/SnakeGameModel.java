package ru.nsu.ntatarinov;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Model of a Snake game.
 */
public class SnakeGameModel {

    private SnakeGameView view;
    public SnakeBody snakeBody;
    private final int rows;
    private final int columns;
    private final int winLength;
    private final int numberOfFood;

    public boolean gameOver = false;
    public boolean isPause = false;
    private final List<Point> foods = new ArrayList<>();
    private final List<Point> walls;
    private int score = 0;

    /**
     * Constructor of a game model module.
     *
     * @param rows         number of rows on a field
     * @param columns      number of columns on a field
     * @param winLength    required length for win
     * @param numberOfFood number of food on a field
     * @param walls        walls coordinates
     */
    public SnakeGameModel(int rows, int columns, int winLength, int numberOfFood, int[][] walls) {
        this.rows = rows;
        this.columns = columns;
        this.winLength = winLength;
        this.numberOfFood = numberOfFood;
        this.snakeBody = new SnakeBody(this.rows, this.columns);
        this.walls = Arrays.stream(walls)
            .map(point -> new Point(point[0], point[1]))
            .map(this::checkCollisionWithSnake)
            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        for (int i = 0; i < this.numberOfFood; i++) {
            this.spawnFood();
        }
    }

    public void setGameView(SnakeGameView view) {
        this.view = view;
    }

    private Point checkCollisionWithSnake(Point wallCell) {
        if (wallCell.equals(snakeBody.head)) {
            throw new IllegalArgumentException("Snake spawns in a wall. Change walls' position");
        }
        return wallCell;
    }

    private void spawnFood() {
        int foodX = (int) (Math.random() * (columns - 1));
        int foodY = (int) (Math.random() * (rows - 1));
        Point food = new Point(foodX, foodY);
        while (this.foods.contains(food) || snakeBody.body.contains(food) || walls.contains(food)) {
            foodX = (int) (Math.random() * (columns - 1));
            foodY = (int) (Math.random() * (rows - 1));
            food = new Point(foodX, foodY);
        }
        foods.add(food);
    }

    /**
     * Make a step of game field.
     */
    public void makeGameStep() {
        checkGameOver();
        this.snakeBody.makeSnakeStep();
        view.updateView(snakeBody.body, foods, score, winLength-score, snakeBody.currentDirection, walls);
        checkFoodEating();
    }

    /**
     * Checks if snake ate food.
     */
    public void checkFoodEating() {
        Point head = this.snakeBody.head;
        for (Point food : foods) {
            if (food.equals(head)) {
                this.snakeBody.increaseLen();
                foods.remove(food);
                spawnFood();
                score += 1;
                break;
            }
        }
    }

    private void checkGameOver() {
        if (snakeBody.body.size() == winLength + 1) {
            view.showGameOverWindow(score, "WIN");
            gameOver = true;
            return;
        }
        Point head = snakeBody.head;
        for (int i = 1; i < snakeBody.body.size(); i++) {
            if (head.equals(snakeBody.body.get(i))) {
                gameOver = true;
                view.showGameOverWindow(score, "LOSE");
                return;
            }
        }
        if (head.x >= columns || head.x < 0 || head.y >= rows || head.y < 0) {
            gameOver = true;
            view.showGameOverWindow(score, "LOSE");
            return;
        }
        for (Point wallCell : walls) {
            if (head.equals(wallCell)) {
                gameOver = true;
                view.showGameOverWindow(score, "LOSE");
                return;
            }
        }

    }

    /**
     * Reset a game field to its initial state.
     */
    public void resetGame() {
        snakeBody.resetSnakeBody();
        view.clear();
        gameOver = false;
        score = 0;
    }

}
