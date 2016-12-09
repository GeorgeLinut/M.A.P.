package tests;

import domain.*;
import junit.framework.TestCase;
import utils.ExecStackImpl;
import utils.OutputImpl;
import utils.SymbolTableImpl;

/**
 * Created by glinut on 11/8/2016.
 */
public class IfStmtTest extends TestCase {
    SymbolTableImpl<String,Integer> symbolTable = new SymbolTableImpl<>();
    ExecStackImpl<Statement> execStack = new ExecStackImpl<>();
    OutputImpl<Integer> output = new OutputImpl<>();
    Statement st1;
    Statement st2;
    Statement st3;
    Statement st4;
    PrgState prgState = new PrgState(symbolTable,execStack,output,new AssignStmt(new ConstantExpression(2),"aa"));
    public void setUp() throws Exception {
        st1= new IfStmt(new ConstantExpression(0),new AssignStmt(new ConstantExpression(1),"a"),new AssignStmt(new ConstantExpression(2),"b"));
        st2= new IfStmt(new ConstantExpression(1),new AssignStmt(new ConstantExpression(1),"a"),new AssignStmt(new ConstantExpression(2),"b"));
        st3= new IfStmt(new ConstantExpression(1),new PrintStmt(new ConstantExpression(2)),new AssignStmt(new ConstantExpression(2),"A"));
    }

    public void testExecute() throws Exception {
        st1.execute(prgState);
        assertEquals(prgState.getSt().getValue("b"),(Integer)2);
        st2.execute(prgState);
        assertEquals(prgState.getSt().getValue("a"),(Integer)1);
        st3.execute(prgState);
        assert(!prgState.getOut().isEmpty());
    }

    public void testToString() throws Exception {

    }
}