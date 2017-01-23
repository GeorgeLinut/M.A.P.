package domain.statements;

import domain.PrgState;
import exceptions.InterpretorException;
import utils.FileData;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by glinut on 11/15/2016.
 */
public class ReadRFileStmt implements Statement {
    private String f;
    private String VarName;

    public ReadRFileStmt(String f, String varName) {
        this.f = f;
        VarName = varName;
    }

    @Override
    public PrgState execute(PrgState p) {
        Integer desc= p.getSt().getValue(f);
        if (desc==null){
            throw new InterpretorException("You can't read from this file");
        }
        FileData fileData=p.getFt().get(desc);
        if (fileData==null){
            throw new InterpretorException("File does not exits");
        }
        BufferedReader bufferedReader = fileData.getReader();
        try {
            String str = bufferedReader.readLine();
            int var;
            if (str==null){
                var=0;
            }
            else {
                var=Integer.parseInt(str);
            }
            p.getSt().add(VarName,var);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String toString() {
        return "ReadRFileStmt{" +
                "f='" + f + '\'' +
                ", VarName='" + VarName + '\'' +
                '}';
    }
}
