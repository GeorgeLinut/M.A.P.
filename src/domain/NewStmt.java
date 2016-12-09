package domain;

import exceptions.InterpretorException;
import utils.AdressGenerator;

/**
 * Created by glinut on 11/25/2016.
 */
public class NewStmt implements Statement {
    private String variable;
    private Integer address;
    private Expression expression;

    public NewStmt(String variable,Expression expression) {
        this.address= AdressGenerator.generateAdress();
        this.variable = variable;
        this.expression=expression;
    }

    @Override
    public PrgState execute(PrgState p) {
        try {
            Integer value = this.expression.evaluate(p.getSt(),p.getHeap());
            if (p.getSt().has(variable)) {
                p.getSt().setValue(variable, address);
            } else {
                p.getSt().add(variable, address);
            }
            p.getHeap().add(address,value);
        }
        catch (Exception e){
            throw new InterpretorException(e.getMessage());
        }
        return null;

    }

    @Override
    public String toString() {
        return "NewStmt{" +
                "variable='" + variable + '\'' +
                ", address=" + address +
                ", expression=" + expression +
                '}';
    }
}
