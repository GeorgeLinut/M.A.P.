package domain.statements;

import domain.PrgState;
import exceptions.InterpretorException;

/**
 * Created by glinut on 1/24/2017.
 */
public class UnlockStmt implements Statement {
    private String variable;

    public UnlockStmt(String variable) {
        this.variable = variable;
    }

    @Override
    public PrgState execute(PrgState p) {
        Integer foundIndex = p.getSt().getValue(variable);
        if (foundIndex == null){
            throw new InterpretorException("this variable is not in the symbol table");
        }
        Integer state = p.getLockTable().getValue(foundIndex);
        if (state == null){

        }
        if (state == -1){

        }else {
            p.getLockTable().setValue(foundIndex,-1);
        }

        return null;
    }

    @Override
    public String toString() {
        return "UnlockStmt{" +
                "variable='" + variable + '\'' +
                '}';
    }
}
