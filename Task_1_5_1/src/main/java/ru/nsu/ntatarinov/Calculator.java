package ru.nsu.ntatarinov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Class for calculating expressions.
 */
public class Calculator {

    /**
     * Method for calculating expressions.
     *
     * @param exp string representation of expression in prefix form.
     * @return value of expression
     */
    public static Double calculate(List<String> exp) {
        OperationFactory operations = new OperationFactory();
        Stack<String> stack = new Stack<>();
        for (int i = exp.size() - 1; i >= 0; i--) {
            String token = exp.get(i);
            Operation operation = operations.getFunc(token);
            if (operation == null) {
                stack.push(token);
            } else {
                if (operation.getArity() == 2) {
                    Double arg1 = Double.valueOf(stack.pop());
                    Double arg2 = Double.valueOf(stack.pop());
                    stack.push(operation.apply(arg1, arg2).toString());
                } else {
                    Double arg1 = Double.valueOf(stack.pop());
                    stack.push(operation.apply(arg1).toString());
                }
            }
        }
        return Double.valueOf(stack.pop());
    }
}
