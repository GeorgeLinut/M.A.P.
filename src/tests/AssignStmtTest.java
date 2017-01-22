package tests;

import domain.*;
import junit.framework.TestCase;
import utils.*;

/**
 * Created by glinut on 11/8/2016.
 */
public class AssignStmtTest extends TestCase {
    SymbolTableImpl<String,Integer> symbolTable = new SymbolTableImpl<>();
    ExecStackImpl<Statement> execStack = new ExecStackImpl<>();
    OutputImpl<Integer> output = new OutputImpl<>();
    Statement st1;
    Statement st2;
    Statement st3;
    Statement st4;
    FileTableImpl<Integer,FileData> fl = new FileTableImpl<>();
    HeapImpl<Integer,Integer> heap = new HeapImpl<>();
    ProcTable<String,ProcData> pt = new ProcTableImpl<>();

    PrgState prgState = new PrgState(execStack,symbolTable,output,new AssignStmt(new ConstantExpression(2),"aa"),fl,heap,pt);
    public void setUp(){
        st1=new AssignStmt(new ConstantExpression(1),"a");
        st2=new AssignStmt(new ArithmeticExpression('*',new ConstantExpression(2),new ConstantExpression(2)),"b");
        st3=new AssignStmt(new ConstantExpression(10),"c");
    }
    public void testExecute() throws Exception {
        st1.execute(prgState);
        assertEquals(prgState.getSt().getValue("a"),(Integer)1);
        st2.execute(prgState);
        assertEquals(prgState.getSt().getValue("b"),(Integer)4);
        st3.execute(prgState);
        assertEquals(prgState.getSt().getValue("c"),(Integer)10);
    }

    public void testToString() throws Exception {

    }

}