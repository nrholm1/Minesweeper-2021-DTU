package View;

import MinesweeperTests.Board;
import javafx.geometry.HPos;
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

    // might come in handy to just be able to pass in a board
    public static GridPane createGameWindow(Board board) {
        return createGameWindow(board.getRowLength(), board.getColLength());
    }

    public static GridPane createGameWindow(int rows, int cols) {
        GridPane root = new GridPane();

        // dynamic button sizing depending on dimensions
        double width = rows < cols ? 500.0 / rows : 500.0 / cols;
        double height = rows > cols ? 500.0 / rows : 500.0 / cols;

//        System.out.println("w: " + width + " | h: " + height);

        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++) {
                Button btn = new Button();
                btn.setMinSize(width, height);

                setButtonStyle(btn);

                GridPane.setRowIndex(btn, r);
                GridPane.setColumnIndex(btn, c);
                GridPane.setHalignment(btn, HPos.CENTER);
                root.getChildren().add(btn);
            }

        return root;
    }

    public static void setButtonStyle(Button btn) {
        btn.setStyle("-fx-background-color: -fx-outer-border, -fx-inner-border, -fx-body-color; \n" +
                "    -fx-background-insets: 0, 1, 2;\n" +
                "    -fx-background-radius: 5, 4, 3;");
    }

}
