import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class ListViewDemo extends Application {

    public  class HBoxCell extends VBox {
        Label label = new Label();
        Button button = new Button();
        HBoxCell(String labelText, String buttonText) {
            super();
            label.setText(labelText);
            label.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(label, Priority.ALWAYS);
            button.setText(buttonText);
            this.getChildren().addAll(label, button);
        }
    }

    public Parent createContent() throws IOException {
        BorderPane layout = new BorderPane();
      /*  List list = new ArrayList();
        for (int i = 0; i<5;i++)
        {
            list.add(new com.hik.jfx.controls.CustomerMenu());
        }
        ListView listView = new ListView();
        ObservableList myObservableList = FXCollections.observableList(list);
        listView.setItems(myObservableList);*/
      //  listView.setCellFactory((ListView<String> l) -> new ColorCell());
        ObservableList<String> strList = FXCollections.observableArrayList("red","blue","gold");
        ListView<String> listView = new ListView<>(strList);
        listView.setPrefSize(400, 200);
        listView.setCellFactory((ListView<String> l) -> new ColorCell());
        listView.setEditable(true);
        layout.setCenter(listView);
        return layout;
    }
    class ColorCell extends ListCell<String> {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(100, 20);
            rect.setX(0);
            rect.setLayoutY(0);
            if (item != null) {
                rect.setFill(Color.web(item));
                setGraphic(rect);
            } else {
                setGraphic(null);
            }
        }
    }
        @Override
    public void start(Stage stage) throws Exception {
        Scene scene=new Scene(createContent());
        scene.getStylesheets().add(getClass().getResource("sample.css").toExternalForm());
        stage.setScene(scene);
        stage.setWidth(300);
        stage.setHeight(200);
        stage.show();
    }
    public static void main(String args[]) {
        launch(args);
    }
}
