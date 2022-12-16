package ru.nsu.ntatarinov.operations;

import ru.nsu.ntatarinov.Operation;

public class Cos implements Operation {
    public int arity = 1;
    @Override
    public Double apply(Double... args) {
        return Math.cos(args[0]);
    }
}
