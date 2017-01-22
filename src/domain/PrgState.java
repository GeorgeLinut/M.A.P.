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

    public PrgState(ExecStack<Statement> execStack, SymbolTable<String, Integer> st, Output<Integer> out, Statement prg, FileTable<Integer, FileData> ft, Heap<Integer, Integer> heap) {
        id = PrgStateIdGenerator.generate();
        this.execStack = execStack;
        this.st = st;
        this.out = out;
        this.prg = prg;
        this.ft = ft;
        this.heap = heap;
        execStack.push(prg);
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
        return st;
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
        return buffer.toString();
    }
}
