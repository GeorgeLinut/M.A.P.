package com.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by glinut on 1/19/2017.
 */
public class guiMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader(); // Loads an object hierarchy from an XML
//document.
        loader.setLocation(Main.class.getResource("mainView.fxml")); //Finds a resource
// with a given name -> URL.
        BorderPane rootLayout= (BorderPane) loader.load();
        MainViewController ctrl = loader.getController();

        Scene scene = new Scene(rootLayout, 550, 500);
        primaryStage.setTitle("Welcome to JavaFX!!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
