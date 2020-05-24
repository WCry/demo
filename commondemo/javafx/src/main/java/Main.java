import com.City;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Locale;

public class Main extends Application {
    private double xOffSet = 0;
    private double yOffSet = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        BorderPane root=new BorderPane();
        Parent rootcenter = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("DMT工具");
        Scene scene=new Scene(root, 1000, 653);
        scene.getStylesheets().add(getClass().getResource("sample.css").toExternalForm());
        primaryStage.setScene(scene);
        PopupControl popupControl=new PopupControl();
        popupControl.setAutoHide(true);

        ContextMenu contextMenu=new ContextMenu();
        Menu menu2=new Menu("dasdasd");
        Menu menu=new Menu("adsa");
        MenuItem menuItem=new MenuItem("dsa");

        menu.getItems().add(menuItem);
        CustomMenuItem customMenuItem=new CustomMenuItem();
        customMenuItem.setHideOnClick(false);
        customMenuItem.setMnemonicParsing(false);
        HBox pane=new HBox();
        Button button=new Button("advsfs");
        Button dsadnew=new Button("dfdsfsa");
        dsadnew.setOnAction(event -> {System.out.println("dasd");});
        pane.getChildren().addAll(button,dsadnew);
        City city=new City();
        //customMenuItem.setGraphic(city);
        customMenuItem.setContent(city);
        contextMenu.getItems().addAll(customMenuItem);
        contextMenu.setAutoHide(true);

        Button button2=new Button("dsad");
        button2.setOnAction(event -> {contextMenu.show(button2, Side.BOTTOM,0,0);});
       // root.setTop(button2);
        root.setCenter(button2);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}