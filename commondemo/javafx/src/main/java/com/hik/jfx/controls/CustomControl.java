package com.hik.jfx.controls;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class CustomControl extends Pane {
    @FXML
    private TextField textField;

    public CustomControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/controls/custom_control.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {
        textProperty().set(value);
    }

    public StringProperty textProperty() {
        return textField.textProperty();
    }

    public double getTextLayoutX()
    {
        return textLayoutX().get();
    }
    public void setTextLayoutX(double x)
    {
        textLayoutX().setValue(x);
    }



    public DoubleProperty textLayoutX()
    {
        return textField.layoutXProperty();
    }


    @FXML
    protected void doSomething() {
        textField.setText("dianji");
    }
}
