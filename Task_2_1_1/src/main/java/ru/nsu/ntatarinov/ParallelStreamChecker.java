package ru.nsu.ntatarinov;

import java.util.List;

public class ParallelStreamChecker extends PrimeNumberChecker{
    public ParallelStreamChecker(List<Integer> list){
        this.list = list;
    }
    @Override
    public boolean check() {
        return !list.parallelStream().allMatch(PrimeNumberChecker::isPrime);
    }
}
