package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * Created by glinut on 1/19/2017.
 */
public class MainViewController {

    @FXML
    ListView options;


    ObservableList<String> model;
    public MainViewController() {
    }

    @FXML
    public void initialize(){
        model = FXCollections.observableArrayList();
        options.setItems(model);

        model.add("ceva");
    }


}
