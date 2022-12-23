package ru.nsu.ntatarinov;

import java.util.HashMap;
import ru.nsu.ntatarinov.operations.Cos;
import ru.nsu.ntatarinov.operations.Divide;
import ru.nsu.ntatarinov.operations.Log;
import ru.nsu.ntatarinov.operations.Minus;
import ru.nsu.ntatarinov.operations.Multiply;
import ru.nsu.ntatarinov.operations.Plus;
import ru.nsu.ntatarinov.operations.Power;
import ru.nsu.ntatarinov.operations.Sin;
import ru.nsu.ntatarinov.operations.Sqrt;

/**
 * creates an Operation class from string representation of function.
 */
public class OperationFactory {

    private final HashMap<String, Operation> operators = new HashMap<>();

    /**
     * Factory producing Operation from a string representation of an operation.
     */
    public OperationFactory() {
        operators.put("+", new Plus());
        operators.put("pow", new Power());
        operators.put("-", new Minus());
        operators.put("/", new Divide());
        operators.put("*", new Multiply());
        operators.put("log", new Log());
        operators.put("cos", new Cos());
        operators.put("sin", new Sin());
        operators.put("sqrt", new Sqrt());
    }

    /**
     * Method for converting String to Operation.
     *
     * @param str string representation of an operation
     * @return applicable operation
     */
    public Operation getFunc(String str) {
        if (operators.containsKey(str)) {
            return operators.get(str);
        }
        return null;
    }

}
