package domain.statements;

import domain.PrgState;
import domain.expressions.Expression;
import exceptions.InterpretorException;
import utils.SymbolTable;
import utils.SymbolTableImpl;

import java.util.ArrayList;

/**
 * Created by glinut on 1/22/2017.
 */
public class CallStmt implements Statement {
    ArrayList<Expression> actuals;
    String name;

    public CallStmt(ArrayList<Expression> actuals, String name) {
        this.actuals = actuals;
        this.name = name;
    }

    @Override
    public PrgState execute(PrgState p) {
        if (p.getProcTable().get(name)==null){
            throw new InterpretorException("this procedure does not exist");
        }
        ArrayList<String> args = p.getProcTable().get(name).getParams();
        if (args.size()!=actuals.size()){
            throw new InterpretorException("wrong number of params");
        }
        SymbolTable<String,Integer> nowSt = new SymbolTableImpl<>();
        for (int i=0;i<this.actuals.size();i++){
            int value = actuals.get(i).evaluate(p.getSt(),p.getHeap());
            nowSt.add(args.get(i),value);
        }
        p.pushSymbolTable(nowSt);
        p.getExecStack().push(new ReturnStmt());
        p.getExecStack().push(p.getProcTable().get(name).getStatement());
        return null;
    }
}
