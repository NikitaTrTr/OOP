package ru.nsu.ntatarinov;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * some simple tests for Calculator.
 */
public class CalculatorTest {

    @Test
    void simpleTest() {
        List<String> expression = new ArrayList<>(Arrays.asList("-","*","+","3","7","13","*","-","3","5","+","1","9"));
        assertEquals(150.0, Calculator.calculate(expression));
    }


}
