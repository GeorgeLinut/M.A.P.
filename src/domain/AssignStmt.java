package domain;

import utils.Heap;
import utils.SymbolTable;

/**
 * Created by glinut on 11/4/2016.
 */
public class AssignStmt implements Statement {
    private Expression expr;
    private String var;

    public AssignStmt(Expression expr, String var) {
        this.expr = expr;
        this.var = var;
    }

    @Override
    public PrgState execute(PrgState p) {
        SymbolTable<String,Integer> table=p.getSt();
        Heap<Integer,Integer>heap = p.getHeap();
        int result=expr.evaluate(table,heap);
        table.add(var,result);
        return null;
    }

    @Override
    public String toString() {
        return "AssignStmt{" +
                "expr=" + expr +
                ", var='" + var + '\'' +
                '}';
    }
}
