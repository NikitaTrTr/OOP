package ru.nsu.ntatarinov;

/**
 * Interface represents contract of calculator operations.
 */
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
