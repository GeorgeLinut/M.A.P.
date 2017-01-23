package domain.expressions;

import utils.Heap;
import utils.SymbolTable;

/**
 * Created by glinut on 11/29/2016.
 */
public class BooleanExpression implements Expression {
    private String operator;
    private Expression operand1,operand2;

    public BooleanExpression(String operator, Expression operand1, Expression operand2) {
        this.operator = operator;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public int evaluate(SymbolTable<String, Integer> s, Heap<Integer, Integer> heap) {
        boolean result = this.booleanEvaluate(s,heap);
        if (result){
            return 1;
        }
        return 0;
    }

    private boolean booleanEvaluate(SymbolTable<String, Integer> s, Heap<Integer, Integer> heap) {
        int firstResult = operand1.evaluate(s,heap);
        int secondResult = operand2.evaluate(s,heap);
        switch (operator){
            case "<":
                return (firstResult<secondResult);
            case "<=":
                return (firstResult<=secondResult);
            case "==":
                return (firstResult==secondResult);
            case "!=":
                return (firstResult!=secondResult);
            case ">":
                return (firstResult>secondResult);
            case ">=":
                return (firstResult>=secondResult);
        }
    return false;
    }

    @Override
    public String toString() {
        return "BooleanExpression{" +
                "operator='" + operator + '\'' +
                ", operand1=" + operand1 +
                ", operand2=" + operand2 +
                '}';
    }
}
