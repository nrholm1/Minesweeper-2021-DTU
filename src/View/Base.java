package View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Base  extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");

        GridPane gameWindow = GameWindow.createGameWindow(40,40);
        gameWindow.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();

        root.getChildren().add(gameWindow);
        Scene scene = new Scene(root, 1000, 1000);
        scene.getStylesheets().add("GameWindow.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}