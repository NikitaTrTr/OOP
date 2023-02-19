package ru.nsu.ntatarinov;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadingChecker {

    volatile public boolean result;
    private List<List<Integer>> data;
    private int nThreads;
    private ExecutorService checkers;
    public MultithreadingChecker(List<Integer> list, int nThreads) {
        this.data = new ArrayList<>();
        this.nThreads = nThreads;
        this.checkers = Executors.newFixedThreadPool(this.nThreads);
        for (int i = 0; i < nThreads; i++) {
            data.add(new ArrayList<>());
        }
        for (int i = 0; i < list.size(); i++) {
            data.get(i % nThreads).add(list.get(i));
        }
    }

    public boolean check() throws ExecutionException, InterruptedException {
        this.result = false;
        ExecutorService checkers = Executors.newFixedThreadPool(this.nThreads);
        for (int i = 0; i < this.nThreads; i++) {
            ThreadTask task = new ThreadTask(this.data.get(i), this);
            checkers.submit(task).get();
        }
        checkers.shutdown();
        return result;
    }
}
