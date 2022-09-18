package ru.nsu.ntatarinov;

/**
 * Heapsort class for sorting arrays with heap sort algorithm.
 */
public class Heapsort {

    /**
     * binary heap.
     */
    private int[] heap;
    /**
     * size of heap.
     */
    private int heapLen;

    /**
     * method for building heapsort object.
     *
     * @param array array you want to sort
     */
    public Heapsort(int[] array) {
        this.heap = new int[array.length];
        if (array.length == 0) {
            return;
        }
        heap[0] = array[0];
        this.heapLen = 0;
        for (int i = 1; i < array.length; i++) {
            add(array[i], heapLen++);
        }
    }

    /**
     * Moves up new element to its appropriate index in the heap.
     *
     * @param n index of a moved element
     */
    private void siftUp(int n) {
        if (n > 0) {
            if (heap[n] < heap[(n - 1) / 2]) {
                int tmp = heap[(n - 1) / 2];
                heap[(n - 1) / 2] = heap[n];
                heap[n] = tmp;
                siftUp((n - 1) / 2);
            }
        }
    }

    /**
     * Moves down element to its appropriate index in the heap.
     */
    private void siftDown(int n, int k) {
        int sonId;
        if (2 * n + 1 <= k) {
            if (2 * n + 1 == k) {
                sonId = 2 * n + 1;
            } else {
                if (heap[2 * n + 1] < heap[2 * n + 2]) {
                    sonId = 2 * n + 1;
                } else {
                    sonId = 2 * n + 2;
                }
            }
            if (heap[n] > heap[sonId]) {
                int tmp = heap[sonId];
                heap[sonId] = heap[n];
                heap[n] = tmp;
                siftDown(sonId, k);
            }
        }
    }

    /**
     * adds new element to heap.
     *
     * @param x element
     * @param k the first empty place in heap
     */
    private void add(int x, int k) {
        heap[k + 1] = x;
        siftUp(k + 1);
    }

    /**
     * Gives you a 0-indexed element (aka minimum) from a current heap.
     *
     * @param k current heap size
     * @return minimal element
     */
    private int extractMin(int k) {
        int min = heap[0];
        heap[0] = heap[k];
        siftDown(0, k - 1);
        return min;
    }

    /**
     * sorts array from which heap was built.
     *
     * @return sorted array
     */
    public int[] sort() {
        if (heap.length == 0) {
            return heap;
        }
        int[] array = new int[heapLen + 1];
        for (int i = 0; i < array.length; i++) {
            array[i] = extractMin(heapLen--);
        }
        return array;
    }
}