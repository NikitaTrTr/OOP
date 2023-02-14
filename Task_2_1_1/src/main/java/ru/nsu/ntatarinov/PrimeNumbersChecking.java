package ru.nsu.ntatarinov;

import java.util.List;
import java.lang.Math;
public class PrimeNumbersChecking {
    public static boolean isPrime(Integer number){
        for (int i = 2; i<=Math.sqrt(number); i++){
            if (number % i == 0 )
                {return false;}
        }
        return true;
    }
    public static boolean sequentialCheck(List<Integer> list) {
        return list.stream().allMatch(PrimeNumbersChecking::isPrime);
    }
}