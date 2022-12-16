package ru.nsu.ntatarinov;

import java.util.HashMap;
import java.util.function.Function;

import ru.nsu.ntatarinov.operations.Power;
import ru.nsu.ntatarinov.operations.Cos;
import ru.nsu.ntatarinov.operations.Sin;
import ru.nsu.ntatarinov.operations.Log;
import ru.nsu.ntatarinov.operations.Plus;
import ru.nsu.ntatarinov.operations.Minus;
import ru.nsu.ntatarinov.operations.Sqrt;
import ru.nsu.ntatarinov.operations.Divide;
import ru.nsu.ntatarinov.operations.Multiply;

public class OperationFactory {

    private static final HashMap<String, Operation> operators = new HashMap<>();
    public OperationFactory(){
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
    public static Operation getFunc(String str){
        return operators.get(str);
    }

}
