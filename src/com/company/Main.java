package com.company;

import controller.Controller;
import domain.*;
import repo.Repository;
import repo.RepositoryImpl;
import ui.UI;
import utils.*;
import view.*;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        Statement st1= new CompStmt(new AssignStmt(new ConstantExpression(5),"a"),new PrintStmt(new VarExpression("a")));
        Repository repo1= new RepositoryImpl("logs1.txt");
        Controller controller1 = new Controller(repo1);
        ExecStackImpl<Statement> execStack1 = new ExecStackImpl<>();
        SymbolTableImpl<String,Integer> symbolTable1= new SymbolTableImpl<>();
        HeapImpl<Integer,Integer>heap1=new HeapImpl<>();
        OutputImpl<Integer> output1 = new OutputImpl<>();
        FileTable<Integer,FileData> fileTable1 = new FileTableImpl<>();
        PrgState state1 = new PrgState(execStack1,symbolTable1,output1,st1,fileTable1,heap1);
        repo1.addPrg(state1);


        Statement st2= new CompStmt(
                new AssignStmt(new ArithmeticExpression('-', new ConstantExpression(2),new ConstantExpression(2)),"a"),
                new CompStmt(new IfStmt(new VarExpression("a"),new AssignStmt(
                        new ConstantExpression(2),"v"),
                        new AssignStmt(new ConstantExpression(3),"v")),new PrintStmt(new VarExpression("v"))));

        Repository repo2= new RepositoryImpl("logs2.txt");
        Controller controller2 = new Controller(repo2);
        ExecStackImpl<Statement> execStack2 = new ExecStackImpl<>();
        SymbolTableImpl<String,Integer> symbolTable2= new SymbolTableImpl<>();
        HeapImpl<Integer,Integer>heap2=new HeapImpl<>();
        OutputImpl<Integer> output2 = new OutputImpl<>();
        FileTable<Integer,FileData> fileTable2 = new FileTableImpl<>();
        PrgState state2 = new PrgState(execStack2,symbolTable2,output2,st2,fileTable2,heap2);
        repo2.addPrg(state2);
//        UI ui = new UI(controller2);
//        ui.run();

        Statement st3 = new CompStmt(new OpenRFileStmt("test.txt","file"),
                new CompStmt(new ReadRFileStmt("file","a"),
                new CompStmt(new ReadRFileStmt("file","b"),
                new CompStmt(new PrintStmt(new VarExpression("a")),
                        new CompStmt(new PrintStmt(new VarExpression("b")),
                                new CloseRStmt("file")) ))));

        Repository repo3= new RepositoryImpl("logs3.txt");
        Controller controller3 = new Controller(repo3);
        ExecStackImpl<Statement> execStack3 = new ExecStackImpl<>();
        SymbolTableImpl<String,Integer> symbolTable3= new SymbolTableImpl<>();
        HeapImpl<Integer,Integer>heap3=new HeapImpl<>();
        OutputImpl<Integer> output3 = new OutputImpl<>();
        FileTable<Integer,FileData> fileTable3 = new FileTableImpl<>();
        PrgState state3 = new PrgState(execStack3,symbolTable3,output3,st3,fileTable3,heap3);
        repo3.addPrg(state3);

        Statement st4= new CompStmt(new AssignStmt(new ConstantExpression(10),"v"),
                new CompStmt(new NewStmt("v",new ConstantExpression(20)),new PrintStmt(new VarExpression("v"))));

        Repository repo4= new RepositoryImpl("logs4.txt");
        Controller controller4 = new Controller(repo4);
        ExecStackImpl<Statement> execStack4 = new ExecStackImpl<>();
        SymbolTableImpl<String,Integer> symbolTable4= new SymbolTableImpl<>();
        HeapImpl<Integer,Integer>heap4=new HeapImpl<>();
        OutputImpl<Integer> output4 = new OutputImpl<>();
        FileTable<Integer,FileData> fileTable4 = new FileTableImpl<>();
        PrgState state4 = new PrgState(execStack4,symbolTable4,output4,st4,fileTable4,heap4);
        repo4.addPrg(state4);

        Statement st5 = new CompStmt(new AssignStmt(new ConstantExpression(10),"v"),
                new CompStmt(new NewStmt("v",new ConstantExpression(20)),
                        new CompStmt(new NewStmt("a",new ConstantExpression(22)) ,
                                new CompStmt(new PrintStmt(new ArithmeticExpression('+',new ConstantExpression(100),new ReadHeapExpression("v"))),
                                        new PrintStmt(new ArithmeticExpression('+',new ConstantExpression(100),new ReadHeapExpression("a")))))));

        Repository repo5= new RepositoryImpl("logs5.txt");
        Controller controller5 = new Controller(repo5);
        ExecStackImpl<Statement> execStack5 = new ExecStackImpl<>();
        SymbolTableImpl<String,Integer> symbolTable5= new SymbolTableImpl<>();
        HeapImpl<Integer,Integer>heap5=new HeapImpl<>();
        OutputImpl<Integer> output5 = new OutputImpl<>();
        FileTable<Integer,FileData> fileTable5 = new FileTableImpl<>();
        PrgState state5 = new PrgState(execStack5,symbolTable5,output5,st5,fileTable5,heap5);
        repo5.addPrg(state5);

        Statement st6 = new CompStmt(new AssignStmt(new ConstantExpression(10),"v"),
                new CompStmt(new NewStmt("v",new ConstantExpression(20)),
                        new CompStmt(new NewStmt("a",new ConstantExpression(22)) ,
                                new CompStmt(new WriteHeapStmt("a",new ConstantExpression(30)),
                                        new CompStmt(new PrintStmt(new ArithmeticExpression('+',new ConstantExpression(100),new ReadHeapExpression("a"))),
                                                new PrintStmt(new ReadHeapExpression("a")))))));

        Repository repo6= new RepositoryImpl("logs6.txt");
        Controller controller6 = new Controller(repo6);
        ExecStackImpl<Statement> execStack6 = new ExecStackImpl<>();
        SymbolTableImpl<String,Integer> symbolTable6= new SymbolTableImpl<>();
        HeapImpl<Integer,Integer>heap6=new HeapImpl<>();
        OutputImpl<Integer> output6 = new OutputImpl<>();
        FileTable<Integer,FileData> fileTable6 = new FileTableImpl<>();
        PrgState state6 = new PrgState(execStack6,symbolTable6,output6,st6,fileTable6,heap6);
        repo6.addPrg(state6);

        Statement st7 = new CompStmt(new AssignStmt(new ConstantExpression(10),"v"),
                new CompStmt(new NewStmt("v",new ConstantExpression(20)),
                        new CompStmt(new NewStmt("v",new ConstantExpression(22)) ,
                                new CompStmt(new PrintStmt(new ArithmeticExpression('+',new ConstantExpression(100),new ReadHeapExpression("v"))),
                                        new PrintStmt(new ArithmeticExpression('+',new ConstantExpression(100),new ReadHeapExpression("v")))))));


        Repository repo7= new RepositoryImpl("logs7.txt");
        Controller controller7 = new Controller(repo7);
        ExecStackImpl<Statement> execStack7 = new ExecStackImpl<>();
        SymbolTableImpl<String,Integer> symbolTable7= new SymbolTableImpl<>();
        HeapImpl<Integer,Integer>heap7=new HeapImpl<>();
        OutputImpl<Integer> output7 = new OutputImpl<>();
        FileTable<Integer,FileData> fileTable7 = new FileTableImpl<>();
        PrgState state7 = new PrgState(execStack7,symbolTable7,output7,st7,fileTable7,heap7);
        repo7.addPrg(state7);

        Repository repo9= new RepositoryImpl("logs9.txt");
        Controller controller9 = new Controller(repo9);

        Statement st10= new CompStmt(
                new AssignStmt(new ArithmeticExpression('-', new ConstantExpression(2),new ConstantExpression(2)),"a"),
                new CompStmt(new IfStmt(new BooleanExpression(">",new VarExpression("a"),new ConstantExpression(0)),new AssignStmt(
                        new ConstantExpression(2),"a"),
                        new AssignStmt(new ConstantExpression(3),"a")),new PrintStmt(new VarExpression("a"))));

        Repository repo10= new RepositoryImpl("logs10.txt");
        Controller controller10 = new Controller(repo10);
        ExecStackImpl<Statement> execStack10 = new ExecStackImpl<>();
        SymbolTableImpl<String,Integer> symbolTable10= new SymbolTableImpl<>();
        HeapImpl<Integer,Integer>heap10=new HeapImpl<>();
        OutputImpl<Integer> output10 = new OutputImpl<>();
        FileTable<Integer,FileData> fileTable10 = new FileTableImpl<>();
        PrgState state10 = new PrgState(execStack10,symbolTable10,output10,st10,fileTable10,heap10);
        repo10.addPrg(state10);

        Statement st11= new CompStmt(
                new AssignStmt(new ArithmeticExpression('-', new ConstantExpression(2),new ConstantExpression(2)),"a"),
                new CompStmt(new WhileStmt(new ConstantExpression(10),
                        new AssignStmt(new ArithmeticExpression('+',new VarExpression("a"),new ConstantExpression(1)),"a")),
                new  PrintStmt(new VarExpression("a"))));

        Repository repo11= new RepositoryImpl("logs11.txt");
        Controller controller11 = new Controller(repo11);
        ExecStackImpl<Statement> execStack11 = new ExecStackImpl<>();
        SymbolTableImpl<String,Integer> symbolTable11= new SymbolTableImpl<>();
        HeapImpl<Integer,Integer>heap11=new HeapImpl<>();
        OutputImpl<Integer> output11 = new OutputImpl<>();
        FileTable<Integer,FileData> fileTable11 = new FileTableImpl<>();
        PrgState state11 = new PrgState(execStack11,symbolTable11,output11,st11,fileTable11,heap11);
        repo11.addPrg(state11);

        Statement st12 = new CompStmt(new AssignStmt(new ConstantExpression(10),"v"),
                new NewStmt("a",new ConstantExpression(22)));
        Statement comSt = new CompStmt(new WriteHeapStmt("a",new ConstantExpression(30)),
                new CompStmt(new AssignStmt(new ConstantExpression(32),"v"),
                        new CompStmt(new PrintStmt(new VarExpression("v")),
                                new PrintStmt(new ReadHeapExpression("a")))));
        Statement st13 = new ForkStmt(comSt);
        Statement st112 = new CompStmt(st12,
                new CompStmt(st13,
                        new CompStmt(new PrintStmt(new VarExpression("v")),
                                new PrintStmt(new ReadHeapExpression("a")))));

        Repository repo12= new RepositoryImpl("logs12.txt");
        Controller controller12 = new Controller(repo12);
        ExecStackImpl<Statement> execStack12 = new ExecStackImpl<>();
        SymbolTableImpl<String,Integer> symbolTable12= new SymbolTableImpl<>();
        HeapImpl<Integer,Integer>heap12=new HeapImpl<>();
        OutputImpl<Integer> output12 = new OutputImpl<>();
        FileTable<Integer,FileData> fileTable12 = new FileTableImpl<>();
        PrgState state12 = new PrgState(execStack12,symbolTable12,output12,st112,fileTable12,heap12);
        repo12.addPrg(state12);
        TextMenu textMenu = new TextMenu();
        textMenu.addCommand(new RunExampleCommand("1",st1.toString(),controller1));
        textMenu.addCommand(new RunExampleCommand("2",st2.toString(),controller2));
        textMenu.addCommand(new RunExampleCommand("3",st3.toString(),controller3));
        textMenu.addCommand(new RunExampleCommand("4",st4.toString(),controller4));
        textMenu.addCommand(new RunExampleCommand("5",st5.toString(),controller5));
        textMenu.addCommand(new RunExampleCommand("6",st6.toString(),controller6));
        textMenu.addCommand(new RunExampleCommand("7",st7.toString(),controller7));
        textMenu.addCommand(new SerializeProgramStateCommand("8","Serialize example",controller7));
        textMenu.addCommand(new DeserializeProgramStateCommand("9","Deserialize example(use a.txt)",controller9));
        textMenu.addCommand(new RunExampleCommand("10","Comparator Example: "+st10.toString(),controller10));
        textMenu.addCommand(new RunExampleCommand("11","While Example"+st11.toString(),controller11));
        textMenu.addCommand(new RunConcurrentExampleCommand("12","Concurent Example",controller12));
        textMenu.addCommand(new ExitCommand("0"," Exit"));
        textMenu.show();


    }


}
