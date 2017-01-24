package userInterface;

import controller.Controller;
import domain.*;
import domain.expressions.*;
import domain.statements.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repo.Repository;
import repo.RepositoryImpl;
import utils.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by glinut on 1/20/2017.
 */
public class SubordinateController {
    private Stage subordinateStage;
    private VBox subordinateRoot;
    private List<ProgramCapsule> programs;
    private ListView<ProgramCapsule> programsView;
    private Controller controller;
    private Button optionButton;

    public SubordinateController(Stage subordinateStage){
        this.subordinateStage = subordinateStage;
        optionButton = new Button("Select");
    }

    public void startSubordinateStage(){
        initExamples();
        initSubordinateGUI();
        initButton();
        subordinateStage.show();
    }

    private void initButton(){
        optionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ProgramCapsule programCapsule = programsView.getSelectionModel().getSelectedItem();
                controller = programCapsule.getController();
                subordinateStage.hide();
            }
        });
    }

    public void initSubordinateGUI(){
        subordinateRoot = new VBox(20);
        subordinateStage.setTitle("Select a Program");
        subordinateStage.setScene(new Scene(subordinateRoot,800,600));

        programsView = new ListView<>(FXCollections.observableArrayList(programs));
        programsView.getSelectionModel().select(0);

        subordinateRoot.getChildren().add(new Label("Choose one of the programs:"));
        subordinateRoot.getChildren().add(programsView);
        subordinateRoot.getChildren().add(optionButton);

    }

    private void initExamples(){
        Statement st1= new CompStmt(
                new AssignStmt(new ArithmeticExpression('+',new VarExpression("a"),new VarExpression("b")),"v"),
                new PrintStmt(new VarExpression("v"))
        );
        ArrayList<Expression> e1 = new ArrayList<>();
        e1.add(new ArithmeticExpression('*',new ConstantExpression(10),new VarExpression("v")));
        e1.add(new VarExpression("w"));
        ArrayList<Expression> e2 = new ArrayList<>();
        e2.add(new VarExpression("v"));
        e2.add(new VarExpression("w"));
        Statement st100 = new CompStmt(
                new AssignStmt(new ArithmeticExpression('*',new VarExpression("a"),new VarExpression("b")),"v"),
                new PrintStmt(new VarExpression("v"))
        );
        Repository repo1= new RepositoryImpl("logs1.txt");
        Controller controller1 = new Controller(repo1);
        ExecStackImpl<Statement> execStack1 = new ExecStackImpl<>();
        SymbolTableImpl<String,Integer> symbolTable1= new SymbolTableImpl<>();
        HeapImpl<Integer,Integer>heap1=new HeapImpl<>();
        OutputImpl<Integer> output1 = new OutputImpl<>();
        FileTable<Integer,FileData> fileTable1 = new FileTableImpl<>();
        ProcTable<String,ProcData> pt = new ProcTableImpl<>();
        String s ="sum";
        String s1 ="product";
        ArrayList<String> vars = new ArrayList<>();
        vars.add("a");
        vars.add("b");
        ProcData procData = new ProcData(st1,vars);
        ProcData procData1 = new ProcData(st100,vars);
        pt.add(s,procData);
        Statement finalSt = new CompStmt(
                new AssignStmt(new ConstantExpression(2),"v"),
                new CompStmt(
                        new AssignStmt(new ConstantExpression(5),"w"),
                        new CompStmt(
                                new CallStmt(e1,"sum"),
                                new CompStmt(
                                        new PrintStmt(new VarExpression("v")),
                                        new CompStmt(
                                                new ForkStmt(new CallStmt(e2,"product")),
                                                new ForkStmt(new CallStmt(e2,"sum"))
                                        )
                                )
                        )
                )
        );
        pt.add(s1,procData1);
        PrgState state1 = new PrgState(execStack1,symbolTable1,output1,finalSt,fileTable1,heap1,pt);
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
        ProcTable<String,ProcData> pt2 = new ProcTableImpl<>();

        PrgState state2 = new PrgState(execStack2,symbolTable2,output2,st2,fileTable2,heap2,pt2);
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
        ProcTable<String,ProcData> pt3 = new ProcTableImpl<>();

        PrgState state3 = new PrgState(execStack3,symbolTable3,output3,st3,fileTable3,heap3,pt3);
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
        ProcTable<String,ProcData> pt4 = new ProcTableImpl<>();

        PrgState state4 = new PrgState(execStack4,symbolTable4,output4,st4,fileTable4,heap4,pt4);
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
        ProcTable<String,ProcData> pt5 = new ProcTableImpl<>();

        PrgState state5 = new PrgState(execStack5,symbolTable5,output5,st5,fileTable5,heap5,pt5);
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
        ProcTable<String,ProcData> pt6 = new ProcTableImpl<>();

        PrgState state6 = new PrgState(execStack6,symbolTable6,output6,st6,fileTable6,heap6,pt6);
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
        ProcTable<String,ProcData> pt7 = new ProcTableImpl<>();

        PrgState state7 = new PrgState(execStack7,symbolTable7,output7,st7,fileTable7,heap7,pt7);
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
        ProcTable<String,ProcData> pt10 = new ProcTableImpl<>();

        PrgState state10 = new PrgState(execStack10,symbolTable10,output10,st10,fileTable10,heap10,pt10);
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
        ProcTable<String,ProcData> pt11 = new ProcTableImpl<>();

        PrgState state11 = new PrgState(execStack11,symbolTable11,output11,st11,fileTable11,heap11,pt11);
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
        ProcTable<String,ProcData> pt12 = new ProcTableImpl<>();

        PrgState state12 = new PrgState(execStack12,symbolTable12,output12,st112,fileTable12,heap12,pt12);
        repo12.addPrg(state12);

        Statement stt13 = new CompStmt(
                new AssignStmt(new ConstantExpression(0),"v"),
                new CompStmt(
                        new WhileStmt(new BooleanExpression("<",new VarExpression("v"),new ConstantExpression(3)),
                                new CompStmt(
                                        new ForkStmt(new CompStmt(
                                        new PrintStmt(new VarExpression("v")),
                                        new AssignStmt(new ArithmeticExpression('+',new VarExpression("v"),new ConstantExpression(1)),
                                                "v")
                                )),new CompStmt(
                                        new PrintStmt(new VarExpression("v")),
                                        new AssignStmt(new ArithmeticExpression('+',new VarExpression("v"),new ConstantExpression(1)),
                                                "v")))),
                                new CompStmt(
                                        new SleepStmt(5),
                                        new PrintStmt(new ArithmeticExpression('*',new VarExpression("v"),new ConstantExpression(10)))

                                )
                )
        );



        Repository repo13 = new RepositoryImpl("logs13.txt");
        Controller controller13 = new Controller(repo13);
        ExecStackImpl<Statement> execStack13 = new ExecStackImpl<>();
        SymbolTableImpl<String,Integer> symt13 = new SymbolTableImpl<>();
        HeapImpl<Integer,Integer> heap13 = new HeapImpl<>();
        OutputImpl<Integer> output13 = new OutputImpl<>();
        FileTable<Integer,FileData> fileT13 = new FileTableImpl<>();
        ProcTable<String,ProcData> pro13 = new ProcTableImpl<>();

        PrgState state13 = new PrgState(execStack13,symt13,output13,stt13,fileT13,heap13,pro13);
        repo13.addPrg(state13);

        Statement forkStmt = new ForkStmt(new CompStmt(
                new PrintStmt(new VarExpression("v")),
                new AssignStmt(new ArithmeticExpression('+',new VarExpression("v"),new ConstantExpression(1)),"v")
        ));
        Statement st14 = new CompStmt(
                new AssignStmt(new ConstantExpression(20),"v"),
                new CompStmt(
                        new ForStmt("v",new ConstantExpression(0),new BooleanExpression("<",new VarExpression("v"),new ConstantExpression(3)),new ArithmeticExpression('+',new VarExpression("v"),new ConstantExpression(1)),forkStmt)
                        ,new PrintStmt(new ArithmeticExpression('*',new VarExpression("v"),new ConstantExpression(10)))
                )
        );

        Repository repo14 = new RepositoryImpl("logs14.txt");
        Controller controller14 = new Controller(repo14);
        ExecStackImpl<Statement> execStack14 = new ExecStackImpl<>();
        SymbolTableImpl<String,Integer> symt14 = new SymbolTableImpl<>();
        HeapImpl<Integer,Integer> heap14 = new HeapImpl<>();
        OutputImpl<Integer> output14 = new OutputImpl<>();
        FileTable<Integer,FileData> fileT14 = new FileTableImpl<>();
        ProcTable<String,ProcData> pro14 = new ProcTableImpl<>();

        PrgState state14 = new PrgState(execStack14,symt14,output14,st14,fileT14,heap14,pro14);
        repo14.addPrg(state14);

        Statement f1 = new ForkStmt(new CompStmt(
                new LockStmt("x"),
                new CompStmt(
                        new WriteHeapStmt("v1",new ArithmeticExpression('+',new
                                ReadHeapExpression("v1"),new ConstantExpression(-1))
                        ),new UnlockStmt("x"))
        ));

        Statement f2 = new ForkStmt(
                new CompStmt(
                        f1,new CompStmt(
                        new LockStmt("x"),
                        new CompStmt(
                                new WriteHeapStmt("v1",new ArithmeticExpression('+',new
                                        ReadHeapExpression("v1"),new ConstantExpression(1))
                                ),new UnlockStmt("x"))
                )
                )
        );

        Statement f3 = new ForkStmt(
                new CompStmt(
                        new ForkStmt(
                                new WriteHeapStmt("v2",new ArithmeticExpression('+',new
                                        ReadHeapExpression("v2"),new ConstantExpression(1))
                                )),new CompStmt(
                        new WriteHeapStmt("v2",new ArithmeticExpression('+',new
                                ReadHeapExpression("v2"),new ConstantExpression(1))),
                        new UnlockStmt("x")
                )
                )
        );

        Statement st15 = new CompStmt(
                new NewStmt("v1",new ConstantExpression(20)),
                new CompStmt(new NewStmt("v2",new ConstantExpression(30)),
                        new CompStmt(new NewLockStmt("x"),new CompStmt(
                                f3,f2
                        )
                        ))
        );

        Repository repo15 = new RepositoryImpl("logs15.txt");
        Controller controller15 = new Controller(repo15);
        ExecStackImpl<Statement> execStack15 = new ExecStackImpl<>();
        SymbolTableImpl<String,Integer> symt15 = new SymbolTableImpl<>();
        HeapImpl<Integer,Integer> heap15 = new HeapImpl<>();
        OutputImpl<Integer> output15 = new OutputImpl<>();
        FileTable<Integer,FileData> fileT15 = new FileTableImpl<>();
        LockImpl<Integer,Integer> lock15 = new LockImpl<>();

        PrgState state15 = new PrgState(execStack15,symt15,output15,st15,fileT15,heap15,lock15);
        repo15.addPrg(state15);

        programs = new ArrayList<>();

        programs.add(new ProgramCapsule(1,controller1,state1));
        programs.add(new ProgramCapsule(2,controller2,state2));
        programs.add(new ProgramCapsule(3,controller3,state3));
        programs.add(new ProgramCapsule(4,controller4,state4));
        programs.add(new ProgramCapsule(5,controller5,state5));
        programs.add(new ProgramCapsule(6,controller6,state6));
        programs.add(new ProgramCapsule(7,controller7,state7));
        programs.add(new ProgramCapsule(10,controller10,state10));
        programs.add(new ProgramCapsule(11,controller11,state11));
        programs.add(new ProgramCapsule(12,controller12,state12));
        programs.add(new ProgramCapsule(13,controller13,state13));
        programs.add(new ProgramCapsule(14,controller14,state14));
        programs.add(new ProgramCapsule(15,controller15,state15));



    }

    public Controller getController() {
        return controller;
    }
}
