package domain.statements;

import domain.expressions.ConstantExpression;
import domain.expressions.Expression;
import domain.PrgState;
import utils.ExecStackImpl;

/**
 * Created by glinut on 11/29/2016.
 */
public class WhileStmt implements Statement {
    Expression exp;
    Statement executeStatement;

    public WhileStmt(Expression exp, Statement executeStatement) {
        this.exp = exp;
        this.executeStatement = executeStatement;
    }

    @Override
    public String toString() {
        return "WhileStmt{" +
                "exp=" + exp +
                ", executeStatement=" + executeStatement +
                '}';
    }

    @Override
    public PrgState execute(PrgState p) {
        if (exp.evaluate(p.getSt(),p.getHeap())!=0) {
//            List<Statement> statements = ((ExecStackImpl<Statement>) p.getExecStack()).getAll().stream().collect(Collectors.toList());
//            ArrayList<Statement> statements1 = (ArrayList<Statement>)statements;
//            statements1.add(0,executeStatement);
//            statements1.add(0,new WhileStmt(new ConstantExpression(exp.evaluate(p.getSt(),p.getHeap())-1),executeStatement));
//            ((ExecStackImpl<Statement>) p.getExecStack()).setAll(statements1);
            ExecStackImpl<Statement> stack = (ExecStackImpl<Statement>) p.getExecStack();
            stack.push(this);
            stack.push(executeStatement);
        }
        return null;
    }


}
