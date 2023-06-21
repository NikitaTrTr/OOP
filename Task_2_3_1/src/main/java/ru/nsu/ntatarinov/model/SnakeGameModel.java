package ru.nsu.ntatarinov.model;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import ru.nsu.ntatarinov.view.SnakeBody;

/**
 * Model of a Snake game.
 */
public class SnakeGameModel {

    public SnakeBody snakeBody;
    private final int rows;
    private final int columns;
    private final int winLength;
    private final int numberOfFood;
    public boolean gameOver = false;
    public boolean winGame;
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

    public LinkedList<Point> getSnakeBody() {
        return snakeBody.body;
    }

    public List<Point> getFoods() {
        return foods;
    }

    public int getScore() {
        return score;
    }

    public int getRemainingScore() {
        return winLength - score;
    }

    public int getDirection() {
        return snakeBody.currentDirection;
    }

    public List<Point> getWalls() {
        return walls;
    }

    public void makeSnakeStep() {
        this.snakeBody.makeSnakeStep();
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

    /**
     * Checks if game is over.
     */
    public void checkGameOver() {
        if (snakeBody.body.size() == winLength + 1) {
            winGame = true;
            gameOver = true;
            return;
        }
        Point head = snakeBody.head;
        for (int i = 1; i < snakeBody.body.size(); i++) {
            if (head.equals(snakeBody.body.get(i))) {
                gameOver = true;
                winGame = false;
                return;
            }
        }
        if (head.x >= columns || head.x < 0 || head.y >= rows || head.y < 0) {
            gameOver = true;
            winGame = false;
            return;
        }
        for (Point wallCell : walls) {
            if (head.equals(wallCell)) {
                gameOver = true;
                winGame = false;
                return;
            }
        }

    }

    /**
     * Reset a game field to its initial state.
     */
    public void resetGame() {
        snakeBody.resetSnakeBody();
        gameOver = false;
        score = 0;
    }

}
