package utils;

import domain.statements.Statement;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by glinut on 1/22/2017.
 */
public class ProcData implements Serializable{
    private Statement statement;
    private ArrayList<String> params;

    public ProcData(Statement statement, ArrayList<String> params) {
        this.statement = statement;
        this.params = params;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ArrayList<String> getParams() {
        return params;
    }

    public void setParams(ArrayList<String> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "ProcData{" +
                "statement=" + statement +
                ", params=" + params +
                '}';
    }
}
