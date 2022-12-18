package ru.nsu.ntatarinov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * some simple tests for Calculator.
 */
public class CalculatorTest {

    @Test
    void simpleTest() {
        List<String> expression = new ArrayList<>(
            Arrays.asList("-", "*", "+", "3", "7", "13", "*", "-", "3", "5", "+", "1", "9"));
        assertEquals(150.0, Calculator.calculate(expression));
    }

    @Test
    void testFromExample() {
        List<String> expression = new ArrayList<>(Arrays.asList("sin", "+", "-", "1", "2", "1"));
        assertEquals(0.0, Calculator.calculate(expression));
    }

    @Test
    void allOperationsTest() {
        List<String> expression = new ArrayList<>(
            Arrays.asList("pow", "sqrt", "sin", "cos", "/", "*", "+", "-", "log", "7", "11", "1",
                "5", "3", "13", "1.5"));
        assertTrue(Math.abs(0.4530616536614618 - Calculator.calculate(expression)) < 0.001);
    }

}
