package ru.nsu.ntatarinov.operations;

import ru.nsu.ntatarinov.Operation;

public class Divide implements Operation {
    public int arity = 2;
    @Override
    public Double apply(Double... args) {
        if (args[1] == 0.0){
            throw new IllegalArgumentException("Zero division");
        }
        return args[0]/args[1];
    }
}

