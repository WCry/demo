package com;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main4 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Simple interface
        VBox root = new VBox(5);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);

        // Create the TitlePane
        TitledPane titledPane = new TitledPane();
        titledPane.setAlignment(Pos.CENTER);

        // Create HBox to hold our Title and button
        HBox contentPane = new HBox();
        contentPane.setAlignment(Pos.CENTER);

        // Set padding on the left side to avoid overlapping the TitlePane's expand arrow
        // We will also pad the right side
        contentPane.setPadding(new Insets(0, 10, 0, 10));

        // Now, since the TitlePane's graphic node generally has a fixed size, we need to bind our
        // content pane's width to match the width of the TitledPane. This will account for resizing as well
        contentPane.minWidthProperty().bind(titledPane.widthProperty());

        // Create a Region to act as a separator for the title and button
        HBox region = new HBox();
        region.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(region, Priority.ALWAYS);

        // Add our nodes to the contentPane
        contentPane.getChildren().addAll(new Label("数据库设置"),
                region,
                new Button("Click me!")
        );

        contentPane.setPrefHeight(100);
        // Add the contentPane as the graphic for the TitledPane
        titledPane.setGraphic(contentPane);
        titledPane.setContent(new Button("dsad"));
        titledPane.setCollapsible(false);
        titledPane.setAccessibleText("dsad");

        // Add the pane to our root layout
        root.getChildren().add(titledPane);

        // Show the Stage
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
