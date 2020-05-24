package com.hik.jfx.controls;

import com.sun.javafx.scene.paint.GradientUtils;
import javafx.beans.property.DoubleProperty;
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

public class CustomerButton extends Pane {
    @FXML
    private Label buttonText;
    @FXML
    private ImageView buttonImage;

    //设置默认样式类的名称
    private static final String DEFAULT_STYLE_CLASS = "customerbutton";
    private double expandWidth;

    public CustomerButton() {
        //应用样式类
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controls/CustomerButton.fxml"));
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
        buttonText.setText(text);
    }
    public String getMenuText()
    {
        return buttonText.getText();
    }
    public void setMenuImage(String url)
    {
        buttonImage.setImage(new Image(url));
    }


    public void retract()
    {
        this.setPrefWidth(64);
        buttonText.setVisible(false);
    }
    public void expand()
    {
        buttonText.setVisible(true);
        this.setPrefWidth(172);
    }



    public void setButtonImageX(double x)
    {
        buttonImage.setLayoutX(x);
    }
    public double getButtonImageX()
    {
        return buttonImage.getLayoutY();
    }
    public DoubleProperty ButtonImageXProperty()
    {
         return buttonImage.layoutXProperty();
    }



    public void setButtonImageY(double y)
    {
        buttonImage.setLayoutY(y);
    }
    public void getButtonImageY()
    {
        buttonImage.getLayoutY();
    }
    public DoubleProperty ButtonImageLayoutYProperty()
    {
        return buttonImage.layoutYProperty();
    }


    //public void





    public void setButtonImageLocation(double x,double y)
    {
        buttonImage.setLayoutX(x);
        buttonImage.setLayoutY(y);
    }
    public void setButtonImageSize(double width,double height)
    {
        buttonImage.setFitWidth(width);
        buttonImage.setFitHeight(height);
    }






    public final ObjectProperty<EventHandler<ActionEvent>> onActionProperty() { return onAction; }
    public final void setOnAction(EventHandler<ActionEvent> value) { onActionProperty().set(value); }
    public final EventHandler<ActionEvent> getOnAction() { return onActionProperty().get(); }
    private ObjectProperty<EventHandler<ActionEvent>> onAction = new ObjectPropertyBase<EventHandler<ActionEvent>>() {
        @Override protected void invalidated() {
            setEventHandler(ActionEvent.ACTION, get());
        }

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
