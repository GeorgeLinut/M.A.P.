package domain.expressions;

import domain.expressions.Expression;
import exceptions.VariableNotFoundException;
import utils.Heap;
import utils.SymbolTable;

/**
 * Created by glinut on 11/1/2016.
 */
public class VarExpression implements Expression {
    private String name;

    public VarExpression(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(SymbolTable<String,Integer> s, Heap<Integer,Integer>heap) {
        if (s.has(name)){
            return s.getValue(name);
        }
        else
            throw new VariableNotFoundException("this variable doesn't exists");
    }

    @Override
    public String toString() {
        return "VarExpression{" +
                "name='" + name + '\'' +
                '}';
    }
}
