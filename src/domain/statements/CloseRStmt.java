package domain.statements;

import domain.PrgState;
import exceptions.InterpretorException;
import utils.FileData;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by glinut on 11/15/2016.
 */
public class CloseRStmt implements Statement {
    private String fileId;

    public CloseRStmt(String fileId) {
        this.fileId = fileId;
    }

    @Override
    public PrgState execute(PrgState p) {
        Integer idF=p.getSt().getValue(fileId);
        if (idF==null){
            throw new InterpretorException("File descriptor can't be found");
        }
        FileData data=p.getFt().get(idF);
        if (data==null){
            throw  new InterpretorException("File data can't be found");
        }
        BufferedReader bufferedReader = data.getReader();
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new InterpretorException("File can't be closed");
        }
        p.getFt().remove(idF);
        return null;
    }

    @Override
    public String toString() {
        return "CloseRStmt{" +
                "fileId='" + fileId + '\'' +
                '}';
    }
}
