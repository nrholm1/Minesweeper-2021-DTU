package View.GameScreen;

import Model.Field;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

public class HexTile extends Polygon{
    public HexTile(int x, int y, double sideLength) {
        super();

        // TODO make readable pls
        //Skaber sekskantens form
        super.getPoints()
             .addAll(
                0.0,0.0,
                sideLength,0.0,
                3*sideLength/2,Math.sqrt(3)*sideLength/2,
                sideLength,Math.sqrt(3)*sideLength,
                0.0,Math.sqrt(3)*sideLength,
                -1*sideLength/2,Math.sqrt(3)*sideLength/2);
        setFill(Color.DARKTURQUOISE);
        super.setId("hextile");
    }

    public void render(Field.State state) {
        switch (state) {
            case UNFLAGGED -> { super.setFill(getTileImage("hex-tile"));}
            case FLAGGED -> { super.setFill(getTileImage("hex-flag-tile"));}
            case PRESSED -> { super.setFill(getTileImage("temp-tile"));}
        }
    }

    public ImagePattern getTileImage(String tileUrl) {
        return new ImagePattern(new Image("Images/" + tileUrl + ".png"));
    }
}
