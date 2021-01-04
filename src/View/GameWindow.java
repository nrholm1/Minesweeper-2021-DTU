package View;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class GameWindow {
    public static GridPane createGameWindow() {
        int rows = 10;
        int cols = 10;

        GridPane root = new GridPane();

        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++) {
                Button btn = new Button();
                btn.setPrefSize(50, 50);

                GridPane.setRowIndex(btn, r);
                GridPane.setColumnIndex(btn, c);
                GridPane.setHalignment(btn, HPos.CENTER);
                root.getChildren().add(btn);
            }

        return root;
    }
}
