package ru.nsu.ntatarinov;


import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SnakeGameModel {
    private SnakeGameView view;
    public SnakeBody snakeBody;
    private final int ROWS;
    private final int COLUMNS;
    private final int WIN_LENGTH;
    private final int NUMBER_OF_FOOD;

    public boolean gameOver = false;
    public boolean isPause = false;
    private final List<Point> foods = new ArrayList<>();
    private final List<Point> walls;
    private int score = 0;
    public SnakeGameModel(int rows, int columns, int winLength, int numberOfFood, int[][] walls) {
        this.ROWS = rows;
        this.COLUMNS = columns;
        this.WIN_LENGTH = winLength;
        this.NUMBER_OF_FOOD = numberOfFood;
        this.snakeBody = new SnakeBody(ROWS, COLUMNS);
        this.walls = Arrays.stream(walls)
            .map(point -> new Point(point[0], point[1]))
            .map(this::checkCollisionWithSnake)
            .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        for (int i = 0; i<NUMBER_OF_FOOD; i++){
            this.spawnFood();
        }
    }
    public void setGameView(SnakeGameView view){
        this.view = view;
    }
    private Point checkCollisionWithSnake(Point wallCell){
        if (wallCell.equals(snakeBody.head)){
            throw new IllegalArgumentException("Snake spawns in a wall. Change walls' position");
        }
        return wallCell;
    }
    private void spawnFood(){
        int foodX = (int) (Math.random() * (COLUMNS-1));
        int foodY = (int) (Math.random() * (ROWS-1));
        Point food = new Point(foodX, foodY);
        while (this.foods.contains(food) || snakeBody.body.contains(food) || walls.contains(food)){
            foodX = (int) (Math.random() * (COLUMNS-1));
            foodY = (int) (Math.random() * (ROWS-1));
            food = new Point(foodX, foodY);
        }
        foods.add(food);
    }
    public void makeGameStep(){
        checkGameOver();
        this.snakeBody.makeSnakeStep();
        view.updateView(snakeBody.body, foods, score, snakeBody.currentDirection, walls);
        checkFoodEating();
    }
    public void checkFoodEating(){
        Point head = this.snakeBody.head;
        for (Point food: foods){
            if (food.equals(head)){
                this.snakeBody.increaseLen();
                foods.remove(food);
                spawnFood();
                score += 1;
                break;
            };
        }
    }
    private void checkGameOver(){
        if (snakeBody.body.size() == WIN_LENGTH+1){
            view.showGameOverWindow(score, "WIN");
            gameOver = true;
            return;
        }
        Point head = snakeBody.head;
        for (int i = 1; i < snakeBody.body.size(); i++){
            if (head.equals(snakeBody.body.get(i))){
                gameOver = true;
                view.showGameOverWindow(score, "LOSE");
                return;
            }
        }
        if (head.x>=COLUMNS || head.x<0 || head.y>=ROWS || head.y<0){
            gameOver = true;
            view.showGameOverWindow(score, "LOSE");
            return;
        }
        for (Point wallCell: walls){
            if (head.equals(wallCell)){
                gameOver = true;
                view.showGameOverWindow(score, "LOSE");
                return;
            }
        }

    }
    public void resetGame() {
        snakeBody.resetSnakeBody();
        view.clear();
        gameOver = false;
        score = 0;
    }

}
