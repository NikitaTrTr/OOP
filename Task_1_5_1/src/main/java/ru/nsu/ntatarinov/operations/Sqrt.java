package ru.nsu.ntatarinov.operations;

import ru.nsu.ntatarinov.Operation;

public class Sqrt implements Operation {

    @Override
    public int getArity() {
        return 1;
    }

    @Override
    public Double apply(Double... args) {
        return Math.sqrt(args[0]);
    }
}
