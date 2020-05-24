package com.controller;

import com.event.Event;
import com.event.EventBus;
import com.hik.jfx.controls.CustomerMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.awt.event.MouseEvent;
import java.io.IOException;

public class FirstPage extends Pane {

    @FXML
    private Pane cdtMenu;
    @FXML
    private Pane crsMenu;
    @FXML
    private Pane mdlMenu;
    @FXML
    private Pane floorMapMenu;
    @FXML
    private Pane dbImportMenu;

    private ContextMenu contextMenu;
    @FXML
    private Button dd;

    private ContextMenu ddcontextMenu;
    public  FirstPage()
    {
        contextMenu=new ContextMenu();
        Menu settingMenuItem = new Menu("设置");

        MenuItem updateMenuItem = new MenuItem("检查更新");

        settingMenuItem.getItems().add(updateMenuItem);
        MenuItem feedbackMenuItem = new MenuItem("官方论坛");
        MenuItem aboutMenuItem = new MenuItem("问题与建议");
        Menu companyMenuItem = new Menu("关于");
        companyMenuItem.getItems().add(updateMenuItem);

        contextMenu.getItems().add(settingMenuItem);
        contextMenu.getItems().add(companyMenuItem);
        contextMenu.getItems().add(feedbackMenuItem);
        contextMenu.getItems().add(aboutMenuItem);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/FirsPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        cdtMenu.setOnMouseClicked(event -> { EventBus.getInstance().postSave(new Event(Event.EventType.CDTMenuEvent)); });
        crsMenu.setOnMouseClicked(event -> {EventBus.getInstance().postSave(new Event(Event.EventType.CRSMenuEvent));});
        mdlMenu.setOnMouseClicked(event -> {EventBus.getInstance().postSave(new Event(Event.EventType.MDlMenuEvent));});
        floorMapMenu.setOnMouseClicked(event -> {EventBus.getInstance().postSave(new Event(Event.EventType.FloorMapMenuEvent));});

        dbImportMenu.setOnMouseClicked(event -> {
            contextMenu.show(this.getScene().getWindow());
            contextMenu.show(dbImportMenu, Side.RIGHT,20,0);
        });
        ddcontextMenu=new ContextMenu();
        CustomMenuItem customerMenu=new CustomMenuItem();
        HBox hBox=new HBox();
        for(int i=0;i<5;i++)
        {
            Button label=new Button("ds");
            label.setPrefWidth(70);
            label.setLayoutX(i*label.getWidth()+(i+1)*20);
            label.setOnAction(event -> {System.out.println("dsa");});
            hBox.getChildren().addAll(label);
        }
        customerMenu.setContent(hBox);
        ddcontextMenu.getItems().addAll(customerMenu);
        dd.setOnAction(event -> {
            ddcontextMenu.show(dd,Side.BOTTOM,0,5);
        });

        //dbImportMenu.setOnMouseClicked(event -> {EventBus.getInstance().postSave(new Event(Event.EventType.DBMenuEvent));});
    }
}
