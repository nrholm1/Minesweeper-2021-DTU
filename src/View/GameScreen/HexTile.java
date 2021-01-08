package View.GameScreen;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class HexTile {

    private double sidelength;

    private int xpos;
    private int ypos;

    private Polygon tile;

    public HexTile(double sideLength, int[] pos) {
        sidelength = sideLength;

        xpos = pos[0];
        ypos = pos[1];

        tile = new Polygon();
        //Skaber sekskantens form
        tile.getPoints().addAll(
                0.0,0.0,
                sidelength,0.0,
                3*sidelength/2,Math.sqrt(3)*sidelength/2,
                sidelength,Math.sqrt(3)*sidelength,
                0.0,Math.sqrt(3)*sidelength,
                -1*sidelength/2,Math.sqrt(3)*sidelength/2
        );
        tile.setFill(Color.RED);

    }

    public Polygon visual(){return tile;}
}
