package ru.nsu.ntatarinov.operations;

import ru.nsu.ntatarinov.Operation;

public class Log implements Operation {
    public int arity = 2;
    @Override
    public Double apply(Double... args) {
        if (args[0] <= 0 | args[0] == 1){
            throw new IllegalArgumentException("Base is invalid");
        }
        if (args[1] <= 0){
            throw new IllegalArgumentException("Argument is invalid");
        }
        return Math.log(args[1])/Math.log(args[0]);
    }
}
