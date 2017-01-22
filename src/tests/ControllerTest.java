package tests;

import controller.Controller;
import domain.*;
import junit.framework.TestCase;
import repo.RepositoryImpl;
import utils.*;

import java.util.ArrayList;

/**
 * Created by glinut on 11/8/2016.
 */
public class ControllerTest extends TestCase {
    SymbolTableImpl<String,Integer> symbolTable = new SymbolTableImpl<>();
    ExecStackImpl<Statement> execStack = new ExecStackImpl<>();
    OutputImpl<Integer> output = new OutputImpl<>();
    Statement st1=new PrintStmt(new ConstantExpression(1));
    Statement st2=new PrintStmt(new ConstantExpression(12));
    Statement st5=new CompStmt(st1,st2);
    FileTableImpl<Integer,FileData> fl = new FileTableImpl<>();
    HeapImpl<Integer,Integer> heap = new HeapImpl<>();
    PrgState prgState = new PrgState(execStack,symbolTable,output,st5,fl,heap);
    RepositoryImpl repository = new RepositoryImpl();
    Controller controller =new Controller(repository);
    public void setUp() throws Exception {
        repository.addPrg(prgState);
    }

    public void testExecuteAll() throws Exception {
        controller.executeAll();
        OutputImpl<Integer>OUT =(OutputImpl<Integer>)prgState.getOut();
        assertEquals(OUT.getAll().size(),2);
    }

    public void testExecuteOne() throws Exception {
        controller.executeOne();
        ExecStackImpl<Statement>execStack=(ExecStackImpl<Statement>)prgState.getExecStack();
        assertEquals(execStack.pop(),st1);
        assertEquals(execStack.pop(),st2);
    }

    public void testExecuteOneStmt() throws Exception {
        controller.executeOneStmt(prgState);
        ExecStackImpl<Statement>execStack=(ExecStackImpl<Statement>)prgState.getExecStack();
        assertEquals(execStack.pop(),st1);
        assertEquals(execStack.pop(),st2);
    }

}