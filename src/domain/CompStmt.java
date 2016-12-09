package domain;

import utils.ExecStack;

/**
 * Created by glinut on 11/4/2016.
 */
public class CompStmt implements Statement {
    private Statement first;
    private Statement second;

    public CompStmt(Statement first, Statement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public PrgState execute(PrgState p) {
        ExecStack<Statement> exS=p.getExecStack();
        //exS.push(first);
        exS.push(second);
        exS.push(first);
        return null;

    }

    @Override
    public String toString() {
        return "CompStmt{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
