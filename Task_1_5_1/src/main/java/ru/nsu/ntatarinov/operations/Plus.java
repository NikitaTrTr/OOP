package ru.nsu.ntatarinov.operations;

import ru.nsu.ntatarinov.Operation;

public class Plus implements Operation {

    @Override
    public int getArity() {
        return 2;
    }

    @Override
    public Double apply(Double... args) {
        return Double.sum(args[0], args[1]);
    }
}
