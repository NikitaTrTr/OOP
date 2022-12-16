package ru.nsu.ntatarinov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Calculator {

    private static final List<String> ops = new ArrayList<>(
        Arrays.asList("+", "-", "*", "/", "log", "pow", "cos", "sin", "sqrt"));

    public static Double calculate(List<String> exp) {
        OperationFactory operations = new OperationFactory();
        Stack<String> stack = new Stack<>();
        for (int i = exp.size() - 1; i >= 0; i--) {
            String token = exp.get(i);
            if (!ops.contains(token)){
                stack.push(token);
            }
            else {
                Double arg1 = Double.valueOf(stack.pop());
                Double arg2 = Double.valueOf(stack.pop());
                stack.push(OperationFactory.getFunc(token).apply(arg1, arg2).toString());
            }
        }
        return Double.valueOf(stack.pop());
    }
}
