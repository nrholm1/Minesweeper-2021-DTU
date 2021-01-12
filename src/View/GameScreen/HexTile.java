package View.GameScreen;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

public class HexTile extends Polygon{
    private int xpos;
    private int ypos;

    public HexTile(int x, int y, double sideLength, ImagePattern img) {
        super();
        xpos = x;
        ypos = y;

        //Skaber sekskantens form
        super.getPoints().addAll(
                0.0,0.0,
                sideLength,0.0,
                3*sideLength/2,Math.sqrt(3)*sideLength/2,
                sideLength,Math.sqrt(3)*sideLength,
                0.0,Math.sqrt(3)*sideLength,
                -1*sideLength/2,Math.sqrt(3)*sideLength/2
        );
        super.setFill(img);
        super.setId("hextile");
    }

    public int getX() {
        return xpos;
    }

    public int getY() {
        return ypos;
    }
}
