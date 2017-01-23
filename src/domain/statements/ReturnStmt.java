package domain.statements;

import domain.PrgState;

/**
 * Created by glinut on 1/22/2017.
 */
public class ReturnStmt implements Statement {
    @Override
    public PrgState execute(PrgState p) {
        p.popSymbolTable();
        return null;
    }
}
