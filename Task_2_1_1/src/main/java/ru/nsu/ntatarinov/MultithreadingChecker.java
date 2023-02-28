package ru.nsu.ntatarinov;

import java.util.ArrayList;
import java.util.List;

/**
 * Prime checker that user multithreading method of checking.
 */
public class MultithreadingChecker extends PrimeNumberChecker {

    volatile boolean result;
    private final int numOfThreads;

    public MultithreadingChecker(List<Integer> list, int numOfThreads) {
        this.list = list;
        this.numOfThreads = numOfThreads;
    }

    @Override
    public boolean check() throws InterruptedException {
        this.result = false;
        List<CheckingThread> threads = new ArrayList<>();
        for (int i = 0; i < this.numOfThreads; i++) {
            CheckingThread thread = new CheckingThread(this.list, i, this.numOfThreads, this);
            threads.add(thread);
            thread.start();
        }
        for (CheckingThread thread : threads) {
            thread.join();
        }
        return result;
    }
}