package ru.nsu.ntatarinov;

import java.util.ArrayList;
import java.util.List;
public class MultithreadingChecker extends PrimeNumberChecker{

    volatile public boolean result;
    private final int nThreads;

    public MultithreadingChecker(List<Integer> list, int nThreads) {
        this.list = list;
        this.nThreads = nThreads;
    }
    @Override
    public boolean check() throws InterruptedException {
        this.result = false;
        List<CheckingThread> threads = new ArrayList<>();
        for (int i = 0; i < this.nThreads; i++) {
            CheckingThread thread = new CheckingThread(this.list, i, this.nThreads, this);
            threads.add(thread);
            thread.start();
        }
        for (CheckingThread thread : threads){
            thread.join();
        }
        return result;
    }
}