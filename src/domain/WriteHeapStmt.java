package domain;

import exceptions.InterpretorException;

/**
 * Created by glinut on 11/26/2016.
 */
public class WriteHeapStmt implements Statement {
    String varName;
    Expression expression;

    public WriteHeapStmt(String varName, Expression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState p) {
        try{
            Integer variable = p.getSt().getValue(varName);
            p.getHeap().setValue(variable,expression.evaluate(p.getSt(),p.getHeap()));
        }
        catch (Exception e){
            throw new InterpretorException(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "WriteHeapStmt{" +
                "varName='" + varName + '\'' +
                ", expression=" + expression +
                '}';
    }
}
