package domain.statements;

import domain.PrgState;
import domain.expressions.Expression;

/**
 * Created by glinut on 1/23/2017.
 */
public class ForStmt implements Statement {
    String variable;
    Expression exp1;
    Expression exp2;
    Expression exp3;
    Statement statement;

    public ForStmt(String variable, Expression exp1, Expression exp2, Expression exp3, Statement statement) {
        this.variable = variable;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState p) {
        CompStmt stmt = new CompStmt(
                new AssignStmt(exp1, variable),

                new WhileStmt(exp2, new CompStmt(statement, new AssignStmt(exp3, variable)))
                );
        p.getExecStack().push(stmt);
        return null;

    }
}

