package tests;

import domain.*;
import junit.framework.TestCase;
import utils.ExecStackImpl;
import utils.OutputImpl;
import utils.SymbolTableImpl;

/**
 * Created by glinut on 11/8/2016.
 */
public class PrintStmtTest extends TestCase {
    SymbolTableImpl<String,Integer> symbolTable = new SymbolTableImpl<>();
    ExecStackImpl<Statement> execStack = new ExecStackImpl<>();
    OutputImpl<Integer> output = new OutputImpl<>();
    Statement st1;
    Statement st2;
    Statement st3;
    Statement st4;
    PrgState prgState = new PrgState(symbolTable,execStack,output,new AssignStmt(new ConstantExpression(2),"aa"));
    public void setUp() throws Exception {
        st1=new PrintStmt(new ConstantExpression(1));
        st2=new PrintStmt(new ConstantExpression(12));
        st3=new PrintStmt(new ArithmeticExpression('/',new ConstantExpression(4),new ConstantExpression(2)));
    }

    public void testExecute() throws Exception {
        st1.execute(prgState);
        OutputImpl<Integer> output;
        output = (OutputImpl<Integer>)prgState.getOut();
        assert(output.getAll().contains(1));
        st2.execute(prgState);
        OutputImpl<Integer> output2;
        output2 = (OutputImpl<Integer>)prgState.getOut();
        assert(output2.getAll().contains(12));
        st3.execute(prgState);
        OutputImpl<Integer> output3;
        output3 = (OutputImpl<Integer>)prgState.getOut();
        assert(output3.getAll().contains(2));
    }

    public void testToString() throws Exception {

    }

}