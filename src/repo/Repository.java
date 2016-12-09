package repo;

import domain.PrgState;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by glinut on 11/4/2016.
 */
public interface Repository {
    public void addPrg(PrgState prg);
    public List<PrgState> getAll();
    public void setAll(List<PrgState> _newPrgs);
    public void removeCurrent();
    public void logPrgStateExec() throws FileNotFoundException, UnsupportedEncodingException;
    public void logPrgStateExec(PrgState p) throws FileNotFoundException;
    public void serialize(PrgState prgState,String fname);
    public PrgState deserialize(String fname);
    PrgState getCurrent();
}
