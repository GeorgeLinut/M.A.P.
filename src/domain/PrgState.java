package domain;

import domain.Statement;
import exceptions.InterpretorException;
import utils.*;

import java.io.Serializable;
import java.util.Stack;

/**
 * Created by glinut on 11/4/2016.
 */
public class PrgState implements Serializable {
    private Stack<SymbolTable<String,Integer>> symbolTables;
    private Integer id;
    private ExecStack<Statement> execStack;
    private SymbolTable<String,Integer> st;
    private Output<Integer> out;
    private Statement prg;
    private FileTable<Integer,FileData> ft;
    private  Heap<Integer,Integer>heap;
    private ProcTable<String,ProcData>procTable;


    public PrgState(ExecStack<Statement> execStack, SymbolTable<String, Integer> st, Output<Integer> out, Statement prg, FileTable<Integer, FileData> ft, Heap<Integer, Integer> heap,ProcTable<String,ProcData>procTable) {
        id = PrgStateIdGenerator.generate();
        this.execStack = execStack;
        this.symbolTables = new Stack<>();
        this.st = st;
        this.symbolTables.push(st);
        this.out = out;
        this.prg = prg;
        this.ft = ft;
        this.heap = heap;
        this.procTable =procTable;
        execStack.push(prg);
    }

    public void pushSymbolTable(SymbolTable<String,Integer> symbolTable){
        this.symbolTables.push(symbolTable);
        this.st = symbolTable;
    }

    public Stack<SymbolTable<String,Integer>> cloneStStack(){

            Stack<SymbolTable<String,Integer>> newStackp = new Stack<>();
            for (SymbolTable<String,Integer> sut:symbolTables){
                SymbolTable<String,Integer> newSt = sut.clone();
                newStackp.push(newSt);
            }
            return newStackp;

    }

    public void setSymbolTables(Stack<SymbolTable<String, Integer>> symbolTables) {
        this.symbolTables = symbolTables;
    }

    public SymbolTable<String,Integer> popSymbolTable(){
        SymbolTable<String,Integer>old= this.symbolTables.pop();
        this.st = this.symbolTables.peek();
        return old;
    }

    public Integer getId() {
        return id;
    }

    public Heap<Integer, Integer> getHeap() {
        return heap;
    }

    public void setHeap(Heap<Integer, Integer> heap) {
        this.heap = heap;
    }

    public FileTable<Integer, FileData> getFt() {
        return ft;
    }

    public void setFt(FileTable<Integer, FileData> ft) {
        this.ft = ft;
    }

    public ExecStack<Statement> getExecStack() {
        return execStack;
    }

    public void setExecStack(ExecStack<Statement> execStack) {
        this.execStack = execStack;
    }

    public SymbolTable<String, Integer> getSt() {
        return this.symbolTables.peek();
    }

    public void setSt(SymbolTable<String, Integer> st) {
        this.st = st;
    }

    public Output<Integer> getOut() {
        return out;
    }

    public void setOut(Output<Integer> out) {
        this.out = out;
    }

    public Statement getPrg() {
        return prg;
    }

    public void setPrg(Statement prg) {
        this.prg = prg;
    }

    public boolean isNotCompleted(){
        return !execStack.isEmpty();
    }

    public PrgState oneStep(){
        if (execStack.isEmpty()){
            throw new InterpretorException("empthy stack!");
        }
        Statement currentStatement = execStack.pop();
        return currentStatement.execute(this);
    }


    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Id:"+this.id+"\n");
        buffer.append("Execution Stack:\n");
        buffer.append(execStack.toString());
        buffer.append("\n");
        buffer.append("Symbol Table:\n");
        buffer.append(st.toString());
        buffer.append("\n");
        buffer.append("Output:\n");
        buffer.append(out.toString());
        buffer.append("\n");
        buffer.append("Proc Table:\n");
        buffer.append(st.toString());
        buffer.append("\n");
        return buffer.toString();
    }
}
