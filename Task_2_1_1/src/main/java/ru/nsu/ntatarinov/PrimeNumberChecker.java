package ru.nsu.ntatarinov;

public class PrimeNumberChecker {
    public static boolean isPrime(Integer number){
        for (int i = 2; i<=Math.sqrt(number)+1; i++){
            if (number % i == 0 )
            {return false;}
        }
        return true;
    }
}
