package ru.nsu.ntatarinov.operations;

import ru.nsu.ntatarinov.Operation;

/**
 * Sinus operation class.
 */
public class Sin implements Operation {

    @Override
    public int getArity() {
        return 1;
    }

    @Override
    public Double apply(Double... args) {
        return Math.sin(args[0]);
    }
}
