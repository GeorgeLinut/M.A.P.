package domain;

/**
 * Created by glinut on 11/4/2016.
 */
public class IfStmt implements Statement {
    Expression exp;
    Statement thenStmt;
    Statement elseStmt;

    public IfStmt(Expression exp, Statement thenStmt, Statement elseStmt) {
        this.exp = exp;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    public String toString(){
        return "If("+ exp.toString()+")\n"+"then( "+
                thenStmt.toString()+"\n"+"else( "+
                elseStmt.toString()+" )";
    }
    @Override
    public PrgState execute(PrgState p) {
        if (exp.evaluate(p.getSt(),p.getHeap())!=0)
            thenStmt.execute(p);
        else
            elseStmt.execute(p);
        return null;
    }

}
