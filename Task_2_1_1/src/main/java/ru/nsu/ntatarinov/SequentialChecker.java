package ru.nsu.ntatarinov;
import java.util.List;

public class SequentialChecker extends PrimeNumberChecker{
    public SequentialChecker(List<Integer> list){
        this.list = list;
    }
    @Override
    public boolean check() {
        return !this.list.stream().allMatch(PrimeNumberChecker::isPrime);
    }
}
