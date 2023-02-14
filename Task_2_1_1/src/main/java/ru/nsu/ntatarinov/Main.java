package ru.nsu.ntatarinov;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){
        List<Integer> list = Arrays.asList(17,107,10007);
        System.out.println(PrimeNumbersChecking.sequentialCheck(list));
    }
}
