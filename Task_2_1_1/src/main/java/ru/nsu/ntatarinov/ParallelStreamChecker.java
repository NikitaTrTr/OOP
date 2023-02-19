package ru.nsu.ntatarinov;

import java.util.List;

public class ParallelStreamChecker {
    public static boolean check(List<Integer> list) {
        return !list.parallelStream().allMatch(PrimeNumberChecker::isPrime);
    }
}
