package ru.nsu.ntatarinov;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SnakeTests {
    @Test
    void wallsPositionException(){
        int[][] walls = { {8, 5}, {8, 6}, {8, 7}, {8, 8}, {8, 9} };
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SnakeGameModel(16, 16, 10, 3, walls));
    }
}
