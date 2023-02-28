package ru.nsu.ntatarinov;

import java.util.List;

/**
 * Thread class for multithreading checker.
 */
public class CheckingThread extends Thread {

    private final List<Integer> list;
    private final int numberOfThread;
    private final MultithreadingChecker parent;
    private final int numOfThreads;

    /**
     * Creates multithreading checker.
     *
     * @param list           list to check
     * @param numberOfThread id of thread among all threads
     * @param numOfThreads   number of threads used to check
     * @param parent         link to multithreading checker object
     */
    public CheckingThread(List<Integer> list, int numberOfThread, int numOfThreads,
        MultithreadingChecker parent) {
        this.list = list;
        this.parent = parent;
        this.numberOfThread = numberOfThread;
        this.numOfThreads = numOfThreads;
    }

    @Override
    public void run() {
        for (int i = 0; i < list.size(); i++) {
            if (i % numOfThreads == this.numberOfThread) {
                if (!PrimeNumberChecker.isPrime(this.list.get(i))) {
                    parent.result = true;
                }
            }
        }
    }
}
