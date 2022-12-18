package ru.nsu.ntatarinov;

public interface Operation {

    /**
     * shows a number of arguments of operation.
     *
     * @return a number of arguments of operation
     */
    int getArity();

    /**
     * applies operations to arguments in args.
     *
     * @param args arguments
     * @return result of operation
     */
    Double apply(Double... args);
}
