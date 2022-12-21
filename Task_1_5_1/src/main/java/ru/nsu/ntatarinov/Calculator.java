package ru.nsu.ntatarinov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Calculator {


    public static Double calculate(List<String> exp) {
        OperationFactory operations = new OperationFactory();
        Stack<String> stack = new Stack<>();
        for (int i = exp.size() - 1; i >= 0; i--) {
            String token = exp.get(i);
            if (operations.getFunc(token) == null) {
                stack.push(token);
            } else {
                if (operations.getFunc(token).getArity() == 2) {
                    Double arg1 = Double.valueOf(stack.pop());
                    Double arg2 = Double.valueOf(stack.pop());
                    stack.push(operations.getFunc(token).apply(arg1, arg2).toString());
                } else {
                    Double arg1 = Double.valueOf(stack.pop());
                    stack.push(operations.getFunc(token).apply(arg1).toString());
                }
            }
        }
        return Double.valueOf(stack.pop());
    }
}
