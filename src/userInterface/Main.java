package userInterface;

import controller.Controller;
import domain.PrgState;
import domain.Statement;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import utils.EntryIdentifier;
import utils.Pair;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by glinut on 1/20/2017.
 */
public class Main extends Application{

    HBox root;
    ListView<String> execStackView;
    ListView<String> outputView;
    TableView<Pair<String,Integer>> symbolTableView;
    TableView<Pair<Integer,Integer>> heapView;
    TableView<Pair<Integer,Pair<String,BufferedReader>>> fileTableView;
    ListView<EntryIdentifier> programStatesView;
    Button runOneStepBtn;
    Button backButton;
    Controller controller;
    TextField programsNr;


    @Override
    public void start(Stage primaryStage) throws Exception {
        initGUI(primaryStage);
        listenOnSelection();
        readyButton();

        Stage subordonate = new Stage();
        SubordinateController subordinateController = new SubordinateController(subordonate);
        readyOnHiddingSubordinate(primaryStage,subordonate,subordinateController);
        subordinateController.startSubordinateStage();

    }

    private void initGUI(Stage mainStage){
        root = new HBox(30);
        root.setPadding(new Insets(30));
        mainStage.setTitle("Main Stage");
        mainStage.setScene(new Scene(root,900,700));

        VBox leftPane;
        VBox rightPane;
        VBox midllePane;

        leftPane = new VBox(10);
        midllePane = new VBox(10);
        rightPane = new VBox(10);

        createLeftPane(leftPane);
        createMiddlePane(midllePane);
        createRightPane(rightPane);

        root.getChildren().add(leftPane);
        root.getChildren().add(midllePane);
        root.getChildren().add(rightPane);

    }

    public void createLeftPane(VBox leftPane){
        execStackView = new ListView<>(FXCollections.observableArrayList());
        symbolTableView = new TableView<>();

        TableColumn<Pair<String,Integer>,String> varColumn = new TableColumn<>("Variable");
        TableColumn<Pair<String,Integer>,String> valColumn = new TableColumn<>("Value");

        symbolTableView.getColumns().add(varColumn);
        symbolTableView.getColumns().add(valColumn);

        setColumnSymbolTable(varColumn,valColumn);

        leftPane.getChildren().add(new Label("Execution Stack"));
        leftPane.getChildren().add(execStackView);
        leftPane.getChildren().add(new Label("Symbol Table"));
        leftPane.getChildren().add(symbolTableView);
    }

    public void setColumnSymbolTable(TableColumn<Pair<String,Integer>,String> columnVariable,
                                    TableColumn<Pair<String,Integer>,String> columnValue) {

        columnVariable.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<String, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Pair<String, Integer>, String> param) {
                Pair<String,Integer> pair = param.getValue();
                return new SimpleStringProperty(pair.getKey());
            }
        });

        columnValue.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<String, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Pair<String, Integer>, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().toString());
            }
        });
    }

    public void createRightPane(VBox rightPane){
        backButton = new Button("Back");
        runOneStepBtn = new Button("Run one step");
        programsNr = new TextField(Integer.toString(1));
        programStatesView = new ListView<>(FXCollections.observableArrayList());

        rightPane.getChildren().add(new Label("Program States"));
        rightPane.getChildren().add(programStatesView);
        rightPane.getChildren().add(runOneStepBtn);
        rightPane.getChildren().add(backButton);
        rightPane.getChildren().add(programsNr);
    }
    public void createMiddlePane(VBox middlePane){
        outputView = new ListView<>(FXCollections.observableArrayList());
        heapView = new TableView<>();
        TableColumn<Pair<Integer,Integer>,String> leftHeapColumn=
                new TableColumn<Pair<Integer,Integer>, String>("Address");
        TableColumn<Pair<Integer,Integer>,String> rightHeapColumn=
                new TableColumn<Pair<Integer,Integer>,String>("Value");
        heapView.getColumns().add(leftHeapColumn);
        heapView.getColumns().add(rightHeapColumn);
        setColumnPropertyHeap(leftHeapColumn,rightHeapColumn);

        fileTableView = new TableView<>();
        TableColumn<Pair<Integer,Pair<String,BufferedReader>>,String> columnLeft=
                new TableColumn<Pair<Integer,Pair<String,BufferedReader>>,String>("File Descriptor");
        TableColumn<Pair<Integer,Pair<String,BufferedReader>>,String> columnRight=
                new TableColumn<Pair<Integer,Pair<String,BufferedReader>>,String>("Name");

        fileTableView.getColumns().add(columnLeft);
        fileTableView.getColumns().add(columnRight);
        setColumnsPropertyFileTable(columnLeft,columnRight);

        middlePane.getChildren().add(new Label("Output List"));
        middlePane.getChildren().add(outputView);
        middlePane.getChildren().add(new Label("Heap Table"));
        middlePane.getChildren().add(heapView);
        middlePane.getChildren().add(new Label("File Table"));
        middlePane.getChildren().add(fileTableView);

    }

    public void setColumnPropertyHeap(TableColumn<Pair<Integer,Integer>,String> leftHeapColumn,
                                      TableColumn<Pair<Integer,Integer>,String> rightHeapColumn){
        leftHeapColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<Integer, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Pair<Integer, Integer>, String> param) {
                return  new SimpleStringProperty(param.getValue().getKey().toString());
            }
        });

        rightHeapColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<Integer, Integer>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Pair<Integer, Integer>, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().toString());
            }
        });
    }

    public void setColumnsPropertyFileTable(TableColumn<Pair<Integer,Pair<String,BufferedReader>>,String> columnLeft,
                                            TableColumn<Pair<Integer,Pair<String,BufferedReader>>,String> columnRight) {
        columnLeft.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<Integer, Pair<String, BufferedReader>>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Pair<Integer, Pair<String, BufferedReader>>, String> param) {
                return new SimpleStringProperty(param.getValue().getKey().toString());
            }

        });

        columnRight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Pair<Integer, Pair<String, BufferedReader>>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Pair<Integer, Pair<String, BufferedReader>>, String> param) {
                return new SimpleStringProperty(param.getValue().getValue().toString());
            }
        });
    }

    private void listenOnSelection(){
        readyBackButton();
        programStatesView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<EntryIdentifier>() {
                    @Override
                    public void changed(ObservableValue<? extends EntryIdentifier> observable, EntryIdentifier oldValue, EntryIdentifier newValue) {
                        if (newValue == null){
                            return;
                        }
                        System.out.println(controller.getProgramStates().size());
                        execStackView.setItems(getExeStackOList(newValue));
                        symbolTableView.setItems(getSymbolTableOList(newValue));
                        outputView.setItems(getOutOList(newValue));
                        heapView.setItems(getHeapOList(newValue));
                        fileTableView.setItems(getFileTableOList(newValue));
                    }
                }
        );
    }

    public ObservableList<String> getExeStackOList(EntryIdentifier entryIdentifier){

        if(entryIdentifier == null){
            System.out.println("ID");
        }
        PrgState state=entryIdentifier.getPrgState();
        List<String> ret=new ArrayList<>();
        Iterator<Statement> iter = state.getExecStack().iterator();
        while(iter.hasNext()){
            Statement stmt=iter.next();
            ret.add(stmt.toString());
        }
        return FXCollections.observableArrayList(ret);
    }


    public ObservableList<Pair<Integer,Integer>> getHeapOList(EntryIdentifier entryIdentifier){

        PrgState state=entryIdentifier.getPrgState();
        List<Pair<Integer,Integer>> ret=new ArrayList<>();
        Iterator<Map.Entry<Integer,Integer>> iter=state.getHeap().iterator();
        while (iter.hasNext()){
            Map.Entry<Integer,Integer> entry=iter.next();
            ret.add(new Pair<>(entry.getKey(),entry.getValue()));
        }
        return FXCollections.observableArrayList(ret);
    }

    public ObservableList<Pair<Integer,Pair<String,BufferedReader>>> getFileTableOList(EntryIdentifier entryIdentifier){

        PrgState state=entryIdentifier.getPrgState();
        List<Pair<Integer,Pair<String,BufferedReader>>> ret=new ArrayList<>();
        Iterator<Map.Entry<Integer,Pair<String,BufferedReader>>> iter=state.getFt().iterator();
        while(iter.hasNext()){
            Map.Entry<Integer,Pair<String,BufferedReader>> entry=iter.next();
            ret.add(new Pair<>(entry.getKey(),entry.getValue()));
        }
        return FXCollections.observableArrayList(ret);

    }

    public ObservableList<String> getOutOList(EntryIdentifier entryIdentifier){
        PrgState state=entryIdentifier.getPrgState();
        List<String> ret=new ArrayList<>();
        Iterator<Integer> iter=state.getOut().iterator();
        while (iter.hasNext()){
            ret.add(iter.next().toString());
        }
        return FXCollections.observableArrayList(ret);
    }

    public ObservableList<Pair<String,Integer>> getSymbolTableOList(EntryIdentifier entryIdentifier){
        PrgState state=entryIdentifier.getPrgState();
        List<Pair<String,Integer>> ret=new ArrayList<>();
        Iterator<Map.Entry<String,Integer>> iter = state.getSt().iterator();
        while(iter.hasNext()){
            Map.Entry<String,Integer> mapEntry=iter.next();
            Pair<String,Integer> pr=new Pair<>(mapEntry.getKey(),mapEntry.getValue());
            ret.add(pr);
        }
        return FXCollections.observableArrayList(ret);
    }

    public void readyBackButton(){
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                clearDataInWindow();
                Stage subordonate = new Stage();
                Stage main = new Stage();
                initGUI(main);
                listenOnSelection();
                readyButton();
                SubordinateController subordinateController = new SubordinateController(subordonate);
                readyOnHiddingSubordinate(main,subordonate,subordinateController);
                subordinateController.startSubordinateStage();
            }
        });
    }

    public void readyButton(){
        runOneStepBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                programStatesView.getSelectionModel().clearSelection();
                clearDataInWindow();
                controller.allStepGUI();
                populateIdentifierList();
                programStatesView.getSelectionModel().select(0);
                programsNr.setText(Integer.toString(controller.getProgramStates().size()));
            }
        });
    }

    public void clearDataInWindow(){
        execStackView.setItems(FXCollections.observableArrayList());
        symbolTableView.setItems(FXCollections.observableArrayList());
        outputView.setItems(FXCollections.observableArrayList());
        heapView.setItems(FXCollections.observableArrayList());
        fileTableView.setItems(FXCollections.observableArrayList());
    }

    public void populateIdentifierList(){
        List<PrgState> prgStates = controller.getProgramStates();
        List<EntryIdentifier> entryIdentifiers = new ArrayList<>();
        for (PrgState prg : prgStates){
            entryIdentifiers.add(new EntryIdentifier(prg));
        }
        programStatesView.setItems(FXCollections.observableArrayList(entryIdentifiers));
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void readyOnHiddingSubordinate(Stage main,Stage sub,SubordinateController subordinateController){
        sub.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                controller=subordinateController.getController();
                populateIdentifierList();
                main.show();
            }
        });
    }

}
