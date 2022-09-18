package ru.nsu.ntatarinov;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * Different tests for heapsort.
 */
public class HeapsortTest {

    @Test
    void testEmpty() {
        int[] array = {};
        int[] result = {};
        Heapsort.sort(array);
        assertArrayEquals(result, array);
    }

    @Test
    void testSingleElement() {
        int[] array = {5};
        int[] result = {5};
        Heapsort.sort(array);
        assertArrayEquals(result, array);
    }

    @Test
    void testSorted() {
        int[] array = {1, 2, 3, 4, 5};
        int[] result = {1, 2, 3, 4, 5};
        Heapsort.sort(array);
        assertArrayEquals(result, array);
    }

    @Test
    void testReversedSorted() {
        int[] array = {5, 4, 3, 2, 1};
        int[] result = {1, 2, 3, 4, 5};
        Heapsort.sort(array);
        assertArrayEquals(result, array);
    }

    @Test
    void testBigSize() {
        Random rd = new Random();
        int[] array = new int[10000000];
        int[] result = new int[10000000];
        for (int i = 0; i < 10000000; i++) {
            array[i] = rd.nextInt();
            result[i] = array[i];
        }
        Arrays.sort(result);
        Heapsort.sort(array);
        assertArrayEquals(result, array);
    }
}
