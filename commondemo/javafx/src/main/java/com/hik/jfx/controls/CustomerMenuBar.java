package com.hik.jfx.controls;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class CustomerMenuBar extends VBox {
    private CustomerMenu controlButton;
    private boolean isExpand=true;
    //设置默认样式类的名称
    private static final String DEFAULT_STYLE_CLASS = "customerMenuBar";
    private ObservableList<CustomerMenu> menus = FXCollections.<CustomerMenu>observableArrayList();
    public CustomerMenuBar() {
        //应用样式类
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/controls/CustomerMeueBar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controlButton=new CustomerMenu();
        controlButton.retract();
        this.getChildren().add(controlButton);
        controlButton.setOnAction(action->{
            if(isExpand)
            {
                this.retract();
            }
            else
            {
                this.expand();
            }
        });
    }
    public CustomerMenuBar(CustomerMenu... customerMenu) {
        this();
        if (menus != null) {
            getMenus().addAll(customerMenu);
        }
        this.getChildren().addAll(customerMenu);
        menus.addListener(new ListChangeListener<CustomerMenu>() {
            @Override
            public void onChanged(Change<? extends CustomerMenu> c) {

            }
        });
    }
    public CustomerMenuBar(List<CustomerMenu> customerMenu) {
        this();
        if (menus != null) {
            getMenus().addAll(customerMenu);
        }
        this.getChildren().addAll(customerMenu);
    }

    public final ObservableList<CustomerMenu> getMenus() {
        return menus;
    }

    public void SetMenuoffImage(String url)
    {
        controlButton.setMenuImage(url);
    }



    public void retract()
    {
        for (CustomerMenu customerMenu: menus) {
            customerMenu.retract();
        }
        this.setPrefWidth(menus.get(0).getPrefWidth());
        isExpand=false;
    }
    public void expand()
    {
        for (CustomerMenu customerMenu: menus) {
            customerMenu.expand();
        }
        this.setPrefWidth(menus.get(0).getPrefWidth());
        isExpand=true;
    }
}
