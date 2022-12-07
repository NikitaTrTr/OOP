package ru.nsu.ntatarinov;
import java.lang.Math;
public class Operations {

    public double plus(double a, double b){
        return a+b;
    }
    public double minus(double a, double b){
        return a-b;
    }
    public double mul(double a, double b){
        return a*b;
    }
    public double div(double a, double b){
        if (b == 0){
            throw new IllegalArgumentException("Argument 'divisor' is 0");
        }
        return  a/b;
    }
    public double log(double base, double arg){
        if (base <= 0 | base == 1){
            throw new IllegalArgumentException("Base is invalid");
        }
        if (arg <= 0){
            throw new IllegalArgumentException("Argument is invalid");
        }
        return Math.log(arg)/Math.log(base);
    }
    public double pow(double base, double degree){
        return Math.pow(base, degree);
    }
}
