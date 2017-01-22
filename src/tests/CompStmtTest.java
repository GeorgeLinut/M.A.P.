package tests;

import domain.*;
import junit.framework.TestCase;
import utils.*;

/**
 * Created by glinut on 11/8/2016.
 */
public class CompStmtTest extends TestCase {
    SymbolTableImpl<String,Integer> symbolTable = new SymbolTableImpl<>();
    ExecStackImpl<Statement> execStack = new ExecStackImpl<>();
    OutputImpl<Integer> output = new OutputImpl<>();

    Statement st1;
    Statement st2;
    Statement st3;
    Statement st4;
    Statement st5;
    Statement st6;
    FileTableImpl<Integer,FileData> fl = new FileTableImpl<>();
    HeapImpl<Integer,Integer> heap = new HeapImpl<>();
    ProcTable<String,ProcData> pt = new ProcTableImpl<>();
    PrgState prgState = new PrgState(execStack,symbolTable,output,new AssignStmt(new ConstantExpression(2),"aa"),fl,heap,pt);
    public void setUp() throws Exception {
        st1=new PrintStmt(new ConstantExpression(1));
        st2=new PrintStmt(new ConstantExpression(12));
        st3=new PrintStmt(new ArithmeticExpression('/',new ConstantExpression(4),new ConstantExpression(2)));
        st4=new AssignStmt(new ConstantExpression(1),"a");
        st5=new CompStmt(st1,st2);
        st6=new CompStmt(st3,st4);
    }


    public void testExecute() throws Exception {
        st5.execute(prgState);
        ExecStackImpl<Statement> execStack1 = (ExecStackImpl<Statement>)prgState.getExecStack();
        assertEquals(execStack1.pop(),st1);
        assertEquals(execStack1.pop(),st2);
        st6.execute(prgState);
        ExecStackImpl<Statement> execStack2 = (ExecStackImpl<Statement>)prgState.getExecStack();
        assertEquals(execStack2.pop(),st3);
        assertEquals(execStack2.pop(),st4);

    }

    public void testToString() throws Exception {

    }

}