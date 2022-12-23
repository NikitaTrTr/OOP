package ru.nsu.ntatarinov.operations;

import ru.nsu.ntatarinov.Operation;

/**
 * Cosine operation class.
 */
public class Cos implements Operation {

    @Override
    public int getArity() {
        return 1;
    }

    @Override
    public Double apply(Double... args) {
        return Math.cos(args[0]);
    }
}
