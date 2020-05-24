package com;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class City  extends VBox {
    @FXML
    private Button buttdsad;
    public City()
    {
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/city.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
            buttdsad.setOnAction(evet->{System.out.println("dsa");});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
