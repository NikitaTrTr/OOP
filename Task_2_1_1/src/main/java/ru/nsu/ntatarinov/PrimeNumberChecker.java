package ru.nsu.ntatarinov;

import java.util.List;

public abstract class PrimeNumberChecker {
    public List<Integer> list;
    public abstract boolean check() throws InterruptedException;
    public static boolean isPrime(Integer number){
        for (int i = 2; i<=Math.sqrt(number)+1; i++){
            if (number % i == 0 )
            {return false;}
        }
        return true;
    }
}
