package ru.nsu.ntatarinov;
import java.util.List;

public class SequentialChecker {
    public static boolean check(List<Integer> list) {
        return !list.stream().allMatch(PrimeNumberChecker::isPrime);
    }
}
