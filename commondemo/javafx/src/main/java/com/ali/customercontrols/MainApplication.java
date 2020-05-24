package com.ali.customercontrols;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MainApplication extends Application
{
    @Override
    public void start(Stage pPrimaryStage)
    {
        MyList lControl = new MyList();
        lControl.getItems().add(new MyListItem("Test 1"));
        lControl.getItems().add(new MyListItem("Test 2"));
        lControl.getItems().add(new MyListItem("Test 3"));
        lControl.getItems().add(new MyListItem("Test 4"));
        Pane lRoot = new Pane();
        lRoot.getChildren().add(lControl);

        pPrimaryStage.setScene(new Scene(lRoot, 800, 800));
        pPrimaryStage.show();
    }


    public static void main(String[] pArguments)
    {
        launch(pArguments);
    }
}
