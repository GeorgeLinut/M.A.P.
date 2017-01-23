package domain.statements;

import domain.PrgState;

import java.io.Serializable;

/**
 * Created by glinut on 11/4/2016.
 */
public interface Statement extends Serializable {
    public PrgState execute(PrgState p);
}
