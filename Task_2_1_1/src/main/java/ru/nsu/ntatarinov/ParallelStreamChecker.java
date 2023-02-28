package ru.nsu.ntatarinov;

import java.util.List;

/**
 * Prime checker that user parallel stream method of checking.
 */
public class ParallelStreamChecker extends PrimeNumberChecker {

    /**
     * Creates parallel stream checker.
     *
     * @param list list to check
     */
    public ParallelStreamChecker(List<Integer> list) {
        this.list = list;
    }

    @Override
    public boolean check() {
        return !list.parallelStream().allMatch(PrimeNumberChecker::isPrime);
    }
}
