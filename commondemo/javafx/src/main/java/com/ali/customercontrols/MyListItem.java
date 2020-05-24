package com.ali.customercontrols;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MyListItem
{
    private StringProperty mName;


    public MyListItem(String pName)
    {
        mName = new SimpleStringProperty(pName);
    }


    public String getName()
    {
        return mName.get();
    }

    public void setName(String pName)
    {
        mName.set(pName);
    }

    public StringProperty nameProperty()
    {
        return mName;
    }
}