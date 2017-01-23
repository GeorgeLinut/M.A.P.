package tests;

import domain.*;
import domain.expressions.ConstantExpression;
import domain.statements.AssignStmt;
import domain.statements.Statement;
import junit.framework.TestCase;
import utils.*;

/**
 * Created by glinut on 11/8/2016.
 */
public class PrgStateTest extends TestCase {
    SymbolTableImpl<String,Integer> symbolTable = new SymbolTableImpl<>();
    ExecStackImpl<Statement> execStack = new ExecStackImpl<>();
    OutputImpl<Integer> output = new OutputImpl<>();
    Statement st1= new AssignStmt(new ConstantExpression(2),"aa");
    FileTableImpl<Integer, FileData> fl = new FileTableImpl<>();
    HeapImpl<Integer,Integer> heap = new HeapImpl<>();
    ProcTable<String,ProcData> pt = new ProcTableImpl<>();

    PrgState prgState = new PrgState(execStack,symbolTable,output,st1,fl,heap,pt);
    public void setUp() throws Exception {
        super.setUp();

    }

    public void testGetExecStack() throws Exception {
        assertEquals(execStack,prgState.getExecStack());
    }

    public void testSetExecStack() throws Exception {
        Statement st1=new AssignStmt(new ConstantExpression(2),"A");
        ExecStackImpl<Statement>execStack1 = new ExecStackImpl<>();
        execStack1.push(st1);
        prgState.setExecStack(execStack1);
        assertEquals(execStack1,prgState.getExecStack());
    }

    public void testGetSt() throws Exception {
        assertEquals(symbolTable,prgState.getSt());
    }

    public void testSetSt() throws Exception {
        SymbolTableImpl<String,Integer> st1= new SymbolTableImpl<>();
        st1.add("a",1);
        prgState.setSt(st1);
        assertEquals(st1,prgState.getSt());
    }

    public void testGetOut() throws Exception {
        assertEquals(output,prgState.getOut());
    }

    public void testSetOut() throws Exception {
        OutputImpl<Integer> output1=new OutputImpl<>();
        output1.add(2);
        prgState.setOut(output1);
        assertEquals(output1,prgState.getOut());
    }

    public void testGetPrg() throws Exception {
        assertEquals(st1,prgState.getPrg());
    }

    public void testSetPrg() throws Exception {
        Statement st1= new AssignStmt(new ConstantExpression(3),"a");
        prgState.setPrg(st1);
        assertEquals(st1,prgState.getPrg());
    }

    public void testToString() throws Exception {

    }

}