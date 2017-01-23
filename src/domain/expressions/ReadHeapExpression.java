package domain.expressions;

import domain.expressions.Expression;
import exceptions.InterpretorException;
import utils.Heap;
import utils.SymbolTable;

/**
 * Created by glinut on 11/25/2016.
 */
public class ReadHeapExpression implements Expression  {
    private String variable;

    public ReadHeapExpression(String variable) {
        this.variable = variable;
    }

    public int evaluate(SymbolTable<String, Integer> s, Heap<Integer,Integer> h) {
        try {
            Integer value = s.getValue(variable);
            Integer heapValue=h.getValue(value);
            return heapValue;
        }
        catch (Exception e){
            throw new InterpretorException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "ReadHeapExpression{" +
                "variable='" + variable + '\'' +
                '}';
    }
}

