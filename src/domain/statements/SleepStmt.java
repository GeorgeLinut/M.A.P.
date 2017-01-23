package domain.statements;

import domain.PrgState;

/**
 * Created by glinut on 1/22/2017.
 */
public class SleepStmt implements Statement {
    private int number;

    public SleepStmt(int number) {
        this.number = number;
    }

    @Override
    public PrgState execute(PrgState p) {
        if (number==0){

        }
        else {
            p.getExecStack().push(new SleepStmt(number-1));
        }
        return null;
    }
}
