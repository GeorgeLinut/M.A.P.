package domain.statements;

import domain.PrgState;
import utils.*;

import java.util.Stack;

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
        Stack<SymbolTable<String,Integer>> newS = p.cloneStStack();
        PrgState newPrgState = new PrgState(new ExecStackImpl<Statement>(),p.getSt().clone(),p.getOut(),statement,p.getFt(),p.getHeap(),p.getLockTable());
        newPrgState.setSymbolTables(newS);
        return newPrgState;
    }

    @Override
    public String toString() {
        return "ForkStmt{" +
                "statement=" + statement +
                '}';
    }
}
