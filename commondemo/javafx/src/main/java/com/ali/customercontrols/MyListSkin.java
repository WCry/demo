package com.ali.customercontrols;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyListSkin extends SkinBase<MyList>
{
    private VBox mVBox;
    private Map<MyListItem, Label> mNodes;

    protected MyListSkin(MyList pControl)
    {
        super(pControl);
        mVBox = new VBox();
        mNodes = new HashMap<>();
        getChildren().add(mVBox);
        getSkinnable().getItems().addListener(new ListChangeListener<MyListItem>()
        {
            @Override
            public void onChanged(Change<? extends MyListItem> pChange)
            {
                removeItems(pChange.getRemoved());
                addItems(pChange.getAddedSubList());
            }
        });

        getSkinnable().itemsProperty().addListener(new ChangeListener<ObservableList<MyListItem>>()
        {

            @Override
            public void changed(ObservableValue<? extends ObservableList<MyListItem>> pObservable,
                                ObservableList<MyListItem> pOldValue, ObservableList<MyListItem> pNewValue)
            {
                clearItems();
                addItems(pNewValue);
            }
        });

        addItems(getSkinnable().getItems());
    }

    private void addItems(List<? extends MyListItem> list)
    {
        for(MyListItem lItem : list)
        {
            Label lLabel = new Label();
            lLabel.setMinWidth(Control.USE_PREF_SIZE);
            lLabel.setPrefWidth(200);
            lLabel.textProperty().bind(lItem.nameProperty());
            mVBox.getChildren().add(lLabel);
            mNodes.put(lItem, lLabel);
        }
    }

    private void removeItems(List<? extends MyListItem> list)
    {
        for(MyListItem lItem : list)
        {
            mVBox.getChildren().remove(mNodes.get(lItem));
            mNodes.remove(lItem);
        }
    }

    private void clearItems()
    {
        for(Map.Entry<MyListItem, Label> lEntry : mNodes.entrySet())
        {
            mVBox.getChildren().remove(lEntry.getValue());
            mNodes.remove(lEntry.getKey());
        }
    }
}
