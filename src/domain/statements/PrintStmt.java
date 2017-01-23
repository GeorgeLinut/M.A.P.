package domain.statements;

import domain.expressions.Expression;
import domain.PrgState;
import utils.Output;

/**
 * Created by glinut on 11/4/2016.
 */
public class PrintStmt implements Statement {
    Expression expression;

    public PrintStmt(Expression expression) {
        this.expression = expression;
    }

    public String toString(){
        return "print( "+expression.toString()+" )";
    }
    @Override
    public PrgState execute(PrgState p) {
        Output<Integer> output=p.getOut();
        output.add(expression.evaluate(p.getSt(),p.getHeap()));
        return null;
    }

}
