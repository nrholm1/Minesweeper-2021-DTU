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

        GridPane gameWindow = GameWindow.createGameWindow();
        gameWindow.setAlignment(Pos.CENTER);

        StackPane root = new StackPane();

        root.getChildren().add(gameWindow);
        primaryStage.setScene(new Scene(root, 1000, 1000));
        primaryStage.show();
    }
}