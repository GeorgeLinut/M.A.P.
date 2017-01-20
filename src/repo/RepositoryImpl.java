package repo;

import domain.PrgState;
import domain.Statement;
import exceptions.InterpretorException;
import groovy.util.MapEntry;
import utils.*;

import java.io.*;
import java.util.*;

/**
 * Created by glinut on 11/4/2016.
 */
public class RepositoryImpl implements Repository {
    String file;
    private ArrayList<PrgState> ps;

    public RepositoryImpl() {
        ps = new ArrayList<PrgState>();
    }

    public RepositoryImpl(String file) {
        this.file = file;
        this.ps = new ArrayList<PrgState>();
    }

    @Override
    public void addPrg(PrgState prg) {
        ps.add(prg);
    }

    @Override
    public PrgState getCurrent() {
        return ps.get(0);
    }
    @Override
    public void removeCurrent(){

        ps.remove(0);
    }

    @Override
    public void logPrgStateExec(PrgState p) throws FileNotFoundException {
        try (PrintWriter logFile=new PrintWriter(new FileOutputStream(
                new File("concurent"+p.getId()+".txt"),
                true /* append = true */));){
            PrgState prgState = p;
            ExecStackImpl<Statement> execStack=(ExecStackImpl<Statement>)prgState.getExecStack();
            SymbolTableImpl<String,Integer> symbolTable = (SymbolTableImpl<String,Integer>)prgState.getSt();
            OutputImpl<Integer> output = (OutputImpl<Integer>)prgState.getOut();
            HeapImpl<Integer,Integer>heap=(HeapImpl<Integer,Integer>)prgState.getHeap();
            logFile.println("ExecStack");

            for(Statement statement:execStack.getAll()){
                logFile.println(statement);
            }

            logFile.println("SymbolTable");

            for(Map.Entry<String,Integer>entry:symbolTable.getAll().entrySet()){
                logFile.println(entry.getKey()+"-->"+entry.getValue());
            }

            logFile.println("Output");

            for (Integer v:output.getAll()){
                logFile.println(v);
            }

            logFile.println("Heap");
            for(Map.Entry<Integer,Integer>entry:(heap.all()).entrySet()){
                logFile.println(entry.getKey()+"-->"+entry.getValue());
            }
        }
    }

    @Override
    public void logPrgStateExec() throws FileNotFoundException, UnsupportedEncodingException {
        try (PrintWriter logFile=new PrintWriter(new FileOutputStream(
                new File(file),
                true /* append = true */));){
            PrgState prgState = this.getCurrent();
            ExecStackImpl<Statement> execStack=(ExecStackImpl<Statement>)prgState.getExecStack();
            SymbolTableImpl<String,Integer> symbolTable = (SymbolTableImpl<String,Integer>)prgState.getSt();
            OutputImpl<Integer> output = (OutputImpl<Integer>)prgState.getOut();
            HeapImpl<Integer,Integer>heap=(HeapImpl<Integer,Integer>)prgState.getHeap();
            logFile.println("ExecStack");

            for(Statement statement:execStack.getAll()){
                logFile.println(statement);
            }

            logFile.println("SymbolTable");

            for(Map.Entry<String,Integer>entry:symbolTable.getAll().entrySet()){
                logFile.println(entry.getKey()+"-->"+entry.getValue());
            }

            logFile.println("Output");

            for (Integer v:output.getAll()){
                logFile.println(v);
            }

            logFile.println("Heap");
            for(Map.Entry<Integer,Integer>entry:(heap.all()).entrySet()){
                logFile.println(entry.getKey()+"-->"+entry.getValue());
            }
        }
    }

    @Override
    public void serialize(PrgState prgState, String fname) {
        try(ObjectOutputStream ost= (new ObjectOutputStream(new FileOutputStream(fname)))){
            ost.writeObject(prgState);
        } catch (FileNotFoundException e) {
            throw new InterpretorException(e.getMessage());
        } catch (IOException e) {
            throw new InterpretorException(e.getMessage());
        }
    }

    @Override
    public PrgState deserialize(String fname) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fname))){
            Object prgState = in.readObject();
            if (!(prgState instanceof PrgState)){
                throw new InterpretorException("not a ProgramState");
            }
            return (PrgState)prgState;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<PrgState> getAll(){
        return ps;
    }

    @Override
    public void setAll(List<PrgState> _newPrgs) {
        this.ps= (ArrayList<PrgState>) _newPrgs;
    }


}
