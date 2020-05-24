package com.cdt;

import com.cdt.pojo.District;
import com.cdt.until.FxmlPathConfig;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CDTTool extends Pane {
    @FXML
    private ComboBox<District> city;
    @FXML
    private ComboBox<District> county;
    @FXML
    private ComboBox<District> province;
    private List<District> provinceList = null;
    private List<District> cityList = null;
    private List<District> countyList = null;
    private ObservableList<District> provinceDp = null;
    private ObservableList<District> cityDp = null;
    private ObservableList<District> countyDp = null;
    /** 行政区划Map */
    private static Map<String, District> districtMap = new HashMap<String, District>();
    public CDTTool() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/dmt/CDTTool.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
