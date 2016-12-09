package domain;

import utils.Heap;
import utils.SymbolTable;

/**
 * Created by glinut on 11/1/2016.
 */
public class ConstantExpression implements Expression {
    private int value;

    public ConstantExpression(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(SymbolTable<String, Integer> s, Heap<Integer,Integer>heap) {
        return value;
    }

    @Override
    public String toString() {
        return "ConstantExpression{" +
                "value=" + value +
                '}';
    }

}
