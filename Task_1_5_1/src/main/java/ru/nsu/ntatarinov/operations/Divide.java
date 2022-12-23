package ru.nsu.ntatarinov.operations;

import ru.nsu.ntatarinov.Operation;

/**
 *  Dividing operation class.
 */
public class Divide implements Operation {

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public Double apply(Double... args) {
        if (args[1] == 0.0) {
            throw new IllegalArgumentException("Zero division");
        }
        return args[0] / args[1];
    }
}

