package tests;

import domain.*;
import domain.expressions.ConstantExpression;
import domain.statements.AssignStmt;
import domain.statements.Statement;
import junit.framework.TestCase;
import repo.RepositoryImpl;
import utils.*;

/**
 * Created by glinut on 11/8/2016.
 */
public class RepositoryImplTest extends TestCase {
    SymbolTableImpl<String,Integer> symbolTable = new SymbolTableImpl<>();
    ExecStackImpl<Statement> execStack = new ExecStackImpl<>();
    OutputImpl<Integer> output = new OutputImpl<>();
    FileTableImpl<Integer,FileData> fl = new FileTableImpl<>();
    HeapImpl<Integer,Integer> heap = new HeapImpl<>();
    ProcTable<String,ProcData> pt = new ProcTableImpl<>();

    PrgState prgState = new PrgState(execStack,symbolTable,output,new AssignStmt(new ConstantExpression(2),"aa"),fl,heap,pt);
    RepositoryImpl repository = new RepositoryImpl();
    public void setUp() throws Exception {
        super.setUp();
        repository.addPrg(prgState);
    }
    public void testAddPrg() throws Exception {
        repository.addPrg(prgState);
        assertEquals(repository.getAll().size(),2);
    }

    public void testGetCurrent() throws Exception {
        assertEquals(repository.getCurrent(),prgState);
    }

    public void testRemoveCurrent() throws Exception {
        repository.addPrg(prgState);
        assertEquals(repository.getAll().size(),2);
        repository.removeCurrent();
        assertEquals(repository.getAll().size(),1);
        repository.removeCurrent();
        assertEquals(repository.getAll().size(),0);
    }





}