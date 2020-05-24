package com.hik.jfx.controls;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CustomerMenu extends Pane {
    @FXML
    private Label menueText;
    @FXML
    private ImageView menueImageView;
    //设置默认样式类的名称
    private static final String DEFAULT_STYLE_CLASS = "customerMenu";
    private double expandWidth;

    public CustomerMenu() {
        //应用样式类
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controls/CustomerMenueItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        expandWidth=this.getPrefWidth();
        this.setOnMouseClicked(event -> {fire();});
    }
    public void setMenuText(String text)
    {
        menueText.setText(text);
    }
    public String getMenuText()
    {
        return menueText.getText();
    }
    public void setMenuImage(String url)
    {
        menueImageView.setImage(new Image(url));
        Button button=new Button();
    }
    public void retract()
    {
        menueText.setVisible(false);
        this.setPrefWidth(64);
    }
    public void expand()
    {
        menueText.setVisible(true);
        this.setPrefWidth(172);
    }


    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() { return onAction; }
    public final void setOnAction(EventHandler<ActionEvent> value) { onActionProperty().set(value); }
    public final EventHandler<ActionEvent> getOnAction() { return onActionProperty().get(); }
    private ObjectProperty<EventHandler<ActionEvent>> onAction = new ObjectPropertyBase<EventHandler<ActionEvent>>() {
        @Override
        public Object getBean() {
            return this;
        }

        @Override
        public String getName() {
            return "onAction";
        }
    };

    public void fire() {
        if (!isDisabled()) {
            if(getOnAction()!=null)
              getOnAction().handle(new ActionEvent());
        }
    }

}
