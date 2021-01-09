package View.GameScreen;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class HexTile extends Polygon{
    private int xpos;
    private int ypos;

    public HexTile(int x, int y, double sideLength) {
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
        super.setFill(Color.RED);
    }
}
