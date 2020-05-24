import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main3 extends Application {
    private double xOffSet = 0;
    private double yOffSet = 0;

    // Node图标
    private final Node rootIcon = new ImageView(
            new Image(getClass().getResourceAsStream("/images/LeftMenu/logo.png"))
    );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tree View Sample");

        // TreeItem名字和图标
        TreeItem<String> rootItem = new TreeItem<> ("Inbox", rootIcon);
        rootItem.setExpanded(false);
        // 每个Item下又可以添加新的Item
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<> ("Message" + i);
            item.getChildren().add(new TreeItem<String>("第三级"));
            rootItem.getChildren().add(item);
        }
        // 创建TreeView
        TreeView<String> tree = new TreeView<> (rootItem);
        tree.setShowRoot(false);
        ListView<String> list = new ListView<>();

        ObservableList<String> items = FXCollections.observableArrayList (
                "Ac", "B", "C", "D");
        list.setItems(items);
        StackPane root = new StackPane();
       // root.getChildren().add(tree);
        root.getChildren().add(tree);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}