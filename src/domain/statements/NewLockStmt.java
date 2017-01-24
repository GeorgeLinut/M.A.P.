package domain.statements;

import domain.PrgState;
import exceptions.InterpretorException;
import utils.LockAdressGenerator;

/**
 * Created by glinut on 1/23/2017.
 */
public class NewLockStmt implements Statement {
    String variable;
    Integer adress;

    public NewLockStmt(String variable) {
        this.variable = variable;
        this.adress = LockAdressGenerator.generateAdress();
    }

    @Override
    public PrgState execute(PrgState p) {
        try{
            if (p.getSt().has(variable)){
                p.getSt().setValue(variable,adress);
            }else {
                p.getSt().add(variable,adress);
            }
            p.getLockTable().add(adress,-1);
        }
        catch (Exception e){
            throw  new InterpretorException(e.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "NewLockStmt{" +
                "variable='" + variable + '\'' +
                ", adress=" + adress +
                '}';
    }
}
