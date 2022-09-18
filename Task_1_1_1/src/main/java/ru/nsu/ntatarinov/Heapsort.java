package ru.nsu.ntatarinov;

/**
 * Heapsort class for sorting arrays with heap sort algorithm.
 */
public class Heapsort {
    /**
     * Moves up new element to its appropriate index in the heap.
     *
     * @param n index of a moved element
     * @param array array to sort
     */
    private static void siftUp(int n, int[] array) {
        if (n > 0) {
            if (array[n] < array[(n - 1) / 2]) {
                int tmp = array[(n - 1) / 2];
                array[(n - 1) / 2] = array[n];
                array[n] = tmp;
                siftUp((n - 1) / 2, array);
            }
        }
    }

    /**
     * Moves down element to its appropriate index in the heap.
     */
    private static void siftDown(int n, int k, int[] array) {
        int sonId;
        if (2 * n + 1 <= k) {
            if (2 * n + 1 == k) {
                sonId = 2 * n + 1;
            } else {
                if (array[2 * n + 1] < array[2 * n + 2]) {
                    sonId = 2 * n + 1;
                } else {
                    sonId = 2 * n + 2;
                }
            }
            if (array[n] > array[sonId]) {
                int tmp = array[sonId];
                array[sonId] = array[n];
                array[n] = tmp;
                siftDown(sonId, k, array);
            }
        }
    }

    /**
     * Gives you a 0-indexed element (aka minimum) from a current heap.
     *
     * @param k current heap size
     * @param array array to sort
     * @return minimal element
     */
    private static int extractMin(int k, int[] array) {
        int min = array[0];
        array[0] = array[k];
        siftDown(0, k - 1, array);
        return min;
    }

    /**
     * sorts array from which heap was built.
     *
     * @param array array to sort
     */
    public static void sort(int[] array) {
        if (array.length != 0) {
            for (int i = 1; i < array.length; i++) {
                siftUp(i, array);
            }
            for (int i = array.length - 1; i > 0; i--) {
                array[i] = extractMin(i, array);
            }
            for (int i = 0; i < array.length / 2; i++) {
                int temp = array[i];
                array[i] = array[array.length - i - 1];
                array[array.length - i - 1] = temp;
            }
        }
    }
}