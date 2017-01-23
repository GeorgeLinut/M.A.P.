package domain.expressions;

import exceptions.DivisionBy0Exception;
import exceptions.OperatorException;
import utils.Heap;
import utils.SymbolTable;

/**
 * Created by glinut on 11/1/2016.
 */
public class ArithmeticExpression implements Expression {
    private char operator;
    private Expression operand1,operand2;

    public ArithmeticExpression(char operator, Expression operand1, Expression operand2) {
        this.operator = operator;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public int evaluate(SymbolTable<String, Integer> s,Heap<Integer,Integer>heap) {
        int firstResult = operand1.evaluate(s,heap);
        int secondResult = operand2.evaluate(s,heap);
        switch (operator) {
            case '+':
                return firstResult + secondResult;
            case '-':
                return firstResult - secondResult;
            case '*':
                return firstResult * secondResult;
            case '/':
                if (secondResult == 0) {
                    throw new DivisionBy0Exception("Division by 0");
                }
                return firstResult / secondResult;
            default:
                throw new OperatorException(operator + " is not a valid operator");
        }
    }

    @Override
    public String toString() {
        return "ArithmeticExpression{" +
                "operand1=" + operand1 +
                ", operator=" + operator +
                ", operand2=" + operand2 +
                '}';
    }


}
