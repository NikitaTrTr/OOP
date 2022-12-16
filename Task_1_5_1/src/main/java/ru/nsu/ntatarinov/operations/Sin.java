package ru.nsu.ntatarinov.operations;

import ru.nsu.ntatarinov.Operation;

public class Sin implements Operation {
    public int arity = 1;
    @Override
    public Double apply(Double... args) {
        return Math.sin(args[0]);
    }
}
