package View;

import MinesweeperTests.Board;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.GridPane;


public class GameWindow {
    public static GridPane createGameWindow() {
        int rows = 10;
        int cols = 10;

        GridPane root = new GridPane();

        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++) {
                Rectangle rect = new Rectangle();

                GridPane.setRowIndex(rect, r);
                GridPane.setColumnIndex(rect, c);
                GridPane.setHalignment(rect, HPos.CENTER);
                root.getChildren().add(rect);
            }

        return root;
    }

    // might come in handy to just be able to pass in a board
    public static GridPane createGameWindow(Board board) {
        return createGameWindow(board.getRowLength(), board.getColLength());
    }

    public static GridPane createGameWindow(int rows, int cols) {
        GridPane root = new GridPane();

        // dynamic Rectangle sizing depending on dimensions
        double size = cols > rows ? 500.0 / cols : 500.0 / rows;

        System.out.println("w: " + size + " | h: " + size);

        for(int r = 0; r < rows; r++)
            for(int c = 0; c < cols; c++) {
                Rectangle rect = new Rectangle(size, size);
                setRectangleStyle(rect);
                setRectangleGridPaneSettings(rect, r, c);

                root.getChildren().add(rect);
            }

        return root;
    }

    // might be needed, or replaced by css
    public static void setRectangleStyle(Rectangle rect) {
        rect.setFill(Color.LIGHTGRAY);
        rect.setStyle("");
    }

    public static void setRectangleGridPaneSettings(Rectangle rect, int r, int c) {
        GridPane.setMargin(rect, new Insets(1));
        GridPane.setRowIndex(rect, r);
        GridPane.setColumnIndex(rect, c);
        GridPane.setHalignment(rect, HPos.CENTER);
    }

}
