package ru.nsu.ntatarinov.operations;

import ru.nsu.ntatarinov.Operation;

public class Multiply implements Operation {
    public int arity = 2;
    @Override
    public Double apply(Double... args) {
        return args[0]*args[1];
    }
}
