package ru.nsu.ntatarinov;

import java.util.List;

/**
 * Base class for all checker.
 */
public abstract class PrimeNumberChecker {

    /**
     * Array to check.
     */
    public List<Integer> list;

    /**
     * Checks if list contains a non-prime number.
     *
     * @return true if list contains a non-prime number
     * @throws InterruptedException exception in thread interrupt case
     */
    public abstract boolean check() throws InterruptedException;

    /**
     * Checks a number if it is a prime number.
     *
     * @param number a number to check
     * @return true if number is a prime number
     */
    public static boolean isPrime(Integer number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
