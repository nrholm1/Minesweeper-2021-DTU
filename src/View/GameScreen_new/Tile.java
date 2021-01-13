package View.GameScreen_new;

import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class Tile {
    private double width;
    private double height;

    private int xpos;
    private int ypos;

    private Rectangle tile;

    private String value;
    private boolean clicked;

    public Tile(double[] dims, int[] pos, String value){
        this.width = dims[0];
        this.height = dims[1];

        this.xpos = pos[0];
        this.ypos = pos[1];

        this.value = value;

        tile = new Rectangle(width, height);
        GridPane.setConstraints(tile, pos[0], pos[1]);

        initialConfig();
    }

    public void initialConfig(){
        tile.setStyle("-fx-cursor: hand;");
    }

    public Rectangle visual(){
        return tile;
    }
}
