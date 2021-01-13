package sample.GameScreen;

import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

public class HexTile extends Polygon {

    private static boolean ismine;
    private static int adjacentMines;

    private double sidelength;

    private int[] pos;

    private static String tileUrl = "images/temp-tile.png";

    public HexTile(double sideLength, int[] _pos) {
        super();
        sidelength = sideLength;
        pos = _pos;

        getPoints().addAll(
                0.0,0.0,
                sidelength,0.0,
                3*sidelength/2,Math.sqrt(3)*sidelength/2,
                sidelength,Math.sqrt(3)*sidelength,
                0.0,Math.sqrt(3)*sidelength,
                -1*sidelength/2,Math.sqrt(3)*sidelength/2
        );

        setFill(Color.DARKTURQUOISE);
        setId("hextile");

        setOnMouseClicked(e -> {
            if(e.getButton() == MouseButton.SECONDARY){
                setFill(new ImagePattern(new Image("images/hex-flag-tile.png")));
            }
        });
    }

    public boolean isMine(){
        return ismine;
    }

    public void toggleIsMine(){
        ismine = !ismine;
    }

    public void setAdjacentMines(int val) { adjacentMines = val; }
}
