package domain;

import utils.ExecStackImpl;

/**
 * Created by glinut on 12/2/2016.
 */
public class ForkStmt implements Statement {
    public Statement statement;

    public ForkStmt(Statement statement) {
        this.statement = statement;
    }

    @Override
    public PrgState execute(PrgState p) {
        PrgState newPrgState = new PrgState(new ExecStackImpl<Statement>(),p.getSt().clone(),p.getOut(),statement,p.getFt(),p.getHeap());
        return newPrgState;
    }

    @Override
    public String toString() {
        return "ForkStmt{" +
                "statement=" + statement +
                '}';
    }
}
