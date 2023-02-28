package ru.nsu.ntatarinov;

import java.util.List;

public class CheckingThread extends Thread{
    private final List<Integer> list;
    private final int n;
    private final MultithreadingChecker parent;
    private final int nThreads;
    public CheckingThread(List<Integer> list, int numberOfThread, int nThreads, MultithreadingChecker parent){
        this.list = list;
        this.parent = parent;
        this.n = numberOfThread;
        this.nThreads = nThreads;
    }
    @Override
    public void run() {
        for (int i = 0; i<list.size(); i++) {
            if (i % nThreads == this.n) {
                if (!PrimeNumberChecker.isPrime(this.list.get(i))) {
                    parent.result = true;
                }
            }
        }
    }
}
