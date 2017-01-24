package domain.statements;

import domain.PrgState;
import exceptions.InterpretorException;

/**
 * Created by glinut on 1/24/2017.
 */
public class LockStmt implements Statement {
    private String variable;

    public LockStmt(String variable) {
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
            throw new InterpretorException("invalid lock");
        }
        if (state == -1){
            p.getLockTable().setValue(foundIndex,p.getId());
        }else {
            p.getExecStack().push(new LockStmt(variable));
        }

        return null;
    }

    @Override
    public String toString() {
        return "LockStmt{" +
                "variable='" + variable + '\'' +
                '}';
    }
}
