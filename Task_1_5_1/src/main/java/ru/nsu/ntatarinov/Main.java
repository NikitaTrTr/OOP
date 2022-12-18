package ru.nsu.ntatarinov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String exp = input.readLine();
        System.out.println(Calculator.calculate(Arrays.asList(exp.split(" "))));
    }
}
