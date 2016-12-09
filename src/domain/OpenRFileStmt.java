package domain;

import exceptions.InterpretorException;
import utils.FileData;
import utils.FileIdGenerator;
import utils.FileTable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by glinut on 11/9/2016.
 */
public class OpenRFileStmt implements Statement {
    private String filename,fileID;

    public boolean contains(PrgState p){
        Integer des=p.getSt().getValue(fileID);
        if (des==null){
            return true;
        }
        return p.getFt().get(des)!=null;
    }

    public OpenRFileStmt(String filename, String fileID) {
        this.filename = filename;
        this.fileID = fileID;
    }

    @Override
    public PrgState execute(PrgState p) {
        FileTable<Integer, FileData>ft=p.getFt();
        if (!contains(p)){
            throw new InterpretorException("File already opened");
        }
        else {
            try {
                BufferedReader bufferedReader= new BufferedReader(new FileReader(filename));
                int id = FileIdGenerator.generateId();
                FileData fileData = new FileData(filename, bufferedReader);
                p.getFt().add(id, fileData);
                p.getSt().add(fileID, id);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "OpenRFileStmt{" +
                "filename='" + filename + '\'' +
                ", fileID='" + fileID + '\'' +
                '}';
    }
}
