package com.ali.customercontrols;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class MyList extends Control
{
    private ListProperty<MyListItem> mItems;

    public MyList()
    {
        mItems = new SimpleListProperty<>(FXCollections.observableArrayList());
    }

    @Override
    protected Skin<?> createDefaultSkin()
    {
        return new MyListSkin(this);
    }

    public ObservableList<MyListItem> getItems()
    {
        return mItems.get();
    }

    public void setItems(ObservableList<MyListItem> pItems)
    {
        mItems.set(pItems);
    }

    public ListProperty<MyListItem> itemsProperty()
    {
        return mItems;
    }
}
