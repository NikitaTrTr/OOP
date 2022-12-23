package ru.nsu.ntatarinov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Main class for using calculator in console.
 */
public class Main {

    /**
     * Main method, entry point in calculator.
     *
     * @param args ...
     * @throws IOException ...
     */
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String exp = input.readLine();
        System.out.println(Calculator.calculate(Arrays.asList(exp.split(" "))));
    }
}
