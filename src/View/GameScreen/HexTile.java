package View.GameScreen;

import Model.Field;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HexTile extends StackPane {

    private final Polygon hexagon;
    private Text adjacentMinesText;
    private int xpos;
    private int ypos;

    public HexTile(int x, int y, double sideLength) {
        super();
        setupText();

        xpos = x;
        ypos = y;

        double[][] corners = {
                {0.0, 0.0}, //Bottom left corner
                {sideLength, 0.0}, //Bottom right corner
                {3*sideLength/2, Math.sqrt(3)*sideLength/2}, //Far right corner
                {sideLength,Math.sqrt(3)*sideLength}, //Top right corner
                {0.0,Math.sqrt(3)*sideLength}, //Top left corner
                {-1*sideLength/2,Math.sqrt(3)*sideLength/2} //Far left corner
        };

        hexagon = new Polygon();
        hexagon.getPoints()
             .addAll(
                    corners[0][0], corners[0][1],
                    corners[1][0], corners[1][1],
                    corners[2][0], corners[2][1],
                    corners[3][0], corners[3][1],
                    corners[4][0], corners[4][1],
                    corners[5][0], corners[5][1]
             );

        setAlignment(Pos.CENTER);
        getChildren().addAll(hexagon);
    }

    // TODO make setAdjacentMinesText part of render method
    public void render(Field.State state) {
        switch (state) {
            case UNFLAGGED -> renderUnflagged();
            case FLAGGED -> renderFlagged();
            case PRESSED -> renderPressed();
        }
    }

    public void renderUnflagged(){
        getChildren().remove(adjacentMinesText);

        hexagon.setFill(Color.DARKTURQUOISE);
        hexagon.setId("hextile");
    }

    public void renderFlagged(){
        getChildren().remove(adjacentMinesText);

        hexagon.setFill(Color.RED);
        hexagon.setId("hextile");
    }

    public void renderPressed(){
        if(!getChildren().contains(adjacentMinesText))
            getChildren().add(adjacentMinesText);

        hexagon.setFill(Color.AQUAMARINE.darker());
        hexagon.setId("");
    }

    public ImagePattern getTileImage(String tileUrl) {
        return new ImagePattern(new Image("Images/" + tileUrl + ".png"));
    }

    public void setupText(){
        adjacentMinesText = new Text();

        Font pixelfont = Font.loadFont(this.getClass().getResource("../PressStart2P-Regular.ttf").toExternalForm(), 16);
        adjacentMinesText.setFont(pixelfont);
        adjacentMinesText.setFill(Color.WHITE);
        adjacentMinesText.setStyle("-fx-background-color: null;");
        adjacentMinesText.setPickOnBounds(false);
    }

    public void setTileText(String _adjacentMines) {
        adjacentMinesText.setText(_adjacentMines);
    }
}
