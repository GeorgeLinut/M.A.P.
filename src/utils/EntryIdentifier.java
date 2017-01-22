package utils;

import domain.PrgState;

/**
 * Created by glinut on 1/20/2017.
 */
public class EntryIdentifier {
    PrgState prgState;

    public EntryIdentifier(PrgState prgState){
        this.prgState = prgState;
    }

    public PrgState getPrgState(){
        return prgState;
    }

    @Override
    public String toString(){
        return Integer.toString(prgState.getId());
    }
}
