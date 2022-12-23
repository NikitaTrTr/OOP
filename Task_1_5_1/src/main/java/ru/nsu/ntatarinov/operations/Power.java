package ru.nsu.ntatarinov.operations;

import ru.nsu.ntatarinov.Operation;

/**
 * Power operation class.
 */
public class Power implements Operation {

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public Double apply(Double... args) {
        return Math.pow(args[0], args[1]);
    }
}
