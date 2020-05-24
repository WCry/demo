import com.cdt.CDTTool;
import com.controller.FirstPage;
import com.event.Event;
import com.event.EventBus;
import com.hik.jfx.controls.CustomerMenuBar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import com.hik.jfx.controls.CustomerMenu;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class MyController implements Initializable {
    @FXML
    private Button menueSwitch;
    @FXML
    private GridPane gridPane;
    private boolean isOPen=true;
    @FXML
    private Button test;
    @FXML
    private Pane logo;
    @FXML
    private Label logotext;
    @FXML
    private Label toolTile;
    @FXML
    private BorderPane toolPane;
    //记录当前操作的节点
    private Node currentNode;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CustomerMenu customerMenuecdt=new CustomerMenu();
        customerMenuecdt.setMenuImage("/images/LeftMenu/cdtico.png");
        customerMenuecdt.setMenuText("数据抽取工具");
        customerMenuecdt.setOnAction(event -> {  EventBus.getInstance().postSave(new Event(Event.EventType.CDTMenuEvent));});
        CustomerMenu customerMenueDbimport=new CustomerMenu();
        customerMenueDbimport.setMenuImage("/images/LeftMenu/dbimporticon.png");
        customerMenueDbimport.setMenuText("数据导入工具");
        customerMenueDbimport.setOnAction(event -> {  EventBus.getInstance().postSave(new Event(Event.EventType.DBMenuEvent));});
        CustomerMenu customerMenuefloormap=new CustomerMenu();
        customerMenuefloormap.setMenuImage("/images/LeftMenu/floormapicon.png");
        customerMenuefloormap.setMenuText("切图工具");
        customerMenuefloormap.setOnAction(event -> {  EventBus.getInstance().postSave(new Event(Event.EventType.FloorMapMenuEvent));});
        CustomerMenu customerMenuemdl=new CustomerMenu();
        customerMenuemdl.setMenuImage("/images/LeftMenu/mdlicon.png");
        customerMenuemdl.setMenuText("地图下载工具");
        customerMenuemdl.setOnAction(event -> {  EventBus.getInstance().postSave(new Event(Event.EventType.MDlMenuEvent));});
        CustomerMenu customerMenuecrs=new CustomerMenu();
        customerMenuecrs.setMenuImage("/images/LeftMenu/crsicon.png");
        customerMenuecrs.setMenuText("坐标转换工具");
        customerMenuecrs.setOnAction(event -> { EventBus.getInstance().postSave(new Event(Event.EventType.CRSMenuEvent));});

        List<CustomerMenu> list = new LinkedList<CustomerMenu>();
        list.add(customerMenuecdt);
        list.add(customerMenueDbimport);
        list.add(customerMenuefloormap);
        list.add(customerMenuemdl);
        list.add(customerMenuecrs);
        CustomerMenuBar customerMenuBar=new CustomerMenuBar(list);
        customerMenuBar.setPrefWidth(172);
        customerMenuBar.setPrefHeight(1000);
        customerMenuBar.setId("menu");
        customerMenuBar.SetMenuoffImage("/images/LeftMenu/menuoff.png");
        gridPane.add(customerMenuBar,0,1);
        customerMenuBar.prefWidthProperty().addListener(listener->{
            gridPane.getColumnConstraints().get(0).setPrefWidth(customerMenuBar.getPrefWidth());
            logotext.setVisible(!logotext.isVisible());
            logo.setPrefWidth(customerMenuBar.getPrefWidth());
        });
        EventBus.getInstance().register(event -> {menuEventHandle(event);});
        firstPageEventHandle();
    }

   private  void menuEventHandle(Event event)
   {
       switch (event.getType())
       {
           case DBMenuEvent:
               dbimportActionEvent();
               break;
           case CRSMenuEvent:
               crsMenuEventHandle();
               break;
           case FloorMapMenuEvent:
               floormapMenuEventHandle();
               break;
           case CDTMenuEvent:
               cdtMenuEventHandle();
               break;
           case MDlMenuEvent:
               mdlMenuEventHandle();
               break;
       }
   }

    private void firstPageEventHandle(){
        showCenterContent("全部工具",FirstPage.class);
    }

    private void cdtMenuEventHandle() {
        showCenterContent("全部工具",CDTTool.class);

    }
    private void dbimportActionEvent() {
        showCenterContent("数据导入工具",FirstPage.class);
    }
    private void floormapMenuEventHandle() {
        showCenterContent("切图工具",FirstPage.class);
    }
    private void mdlMenuEventHandle() {
        showCenterContent("地图下载工具",FirstPage.class);
    }
    private void crsMenuEventHandle() {
        showCenterContent("坐标转换工具",FirstPage.class);
    }

    private void showCenterContent(String text, Class nodeClass){
        Node node=null;
        if(currentNode!=null&&currentNode.getClass().equals(nodeClass))
        {
           return;
        }
        try {
            node=(Node) nodeClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        currentNode=node;
        toolTile.setText(text);
        toolPane.setCenter(node);
    }
}
