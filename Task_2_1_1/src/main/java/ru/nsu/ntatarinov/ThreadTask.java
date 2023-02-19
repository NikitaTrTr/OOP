package ru.nsu.ntatarinov;

import java.util.ArrayList;
import java.util.List;

public class ThreadTask implements Runnable{
    private List<Integer> list;
    private MultithreadingChecker parent;
    public ThreadTask(List<Integer> list, MultithreadingChecker parent){ // возможно, стоит сделать result в MultithreadingChecker статической и результат хранить в классе
        this.list = list;
        this.parent = parent;
    }
    @Override
    public void run() {
        if (SequentialChecker.check(list)){
            parent.result = true;
        }
    }
}
