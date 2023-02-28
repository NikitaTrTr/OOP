package ru.nsu.ntatarinov;

import java.util.List;

/**
 * Prime checker that user sequential method of checking.
 */
public class SequentialChecker extends PrimeNumberChecker {

    /**
     * Create sequential checker.
     *
     * @param list data to check
     */
    public SequentialChecker(List<Integer> list) {
        this.list = list;
    }

    @Override
    public boolean check() {
        return !this.list.stream().allMatch(PrimeNumberChecker::isPrime);
    }
}
