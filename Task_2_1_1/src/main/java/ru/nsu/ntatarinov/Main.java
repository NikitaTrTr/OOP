package ru.nsu.ntatarinov;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> numbers = List.of(999999001, 999999017, 999999029, 999999043,
            999999059, 999999067, 999999103, 999999107, 999999113, 999999131, 999999137, 999999151,
            999999163, 999999181, 999999191, 999999193, 999999197, 999999223, 999999229, 999999323,
            999999337, 999999353, 999999391, 999999433, 999999487, 999999491, 999999503, 999999527,
            999999541, 999999587, 999999599, 999999607, 999999613, 999999667, 999999677, 999999733,
            999999739, 999999751, 999999757, 999999761, 999999797, 999999883, 999999893, 999999929,
            999999937, 4);
        MultithreadingChecker checker = new MultithreadingChecker(numbers, 64);
        System.out.println(checker.check());
        System.out.println(SequentialChecker.check(numbers));
        System.out.println(ParallelStreamChecker.check(numbers));
    }
}
