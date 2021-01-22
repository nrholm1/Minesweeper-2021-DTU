package View.Components;

import Model.Field;
import Services.ExternalResources;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//Skrevet af s204508 Massimo Hansen
public class HexTile extends StackPane {

    private final Polygon hexagon;
    private Text adjacentMinesText;

    public HexTile(double sideLength) {
        super();
        setupText();

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
        getChildren().addAll(hexagon, adjacentMinesText);
    }

    public void render(Field.State state) {
        switch (state) {
            case UNFLAGGED -> renderUnflagged();
            case FLAGGED -> renderFlagged();
            case PRESSED -> renderPressed();
        }
    }

    public void renderUnflagged(){
        adjacentMinesText.setText("");

        hexagon.setFill(Color.DARKTURQUOISE);
        hexagon.setId("hextile");
    }

    public void renderFlagged(){
        adjacentMinesText.setText("");

        hexagon.setFill(Color.RED);
        hexagon.setId("hextile");
    }

    public void renderPressed(){
        hexagon.setFill(Color.AQUAMARINE.darker());
        hexagon.setId("");
    }

    public void setupText(){
        adjacentMinesText = new Text();

        Font pixelfont = ExternalResources.pixelFont16;
        adjacentMinesText.setFont(pixelfont);
        adjacentMinesText.setFill(Color.WHITE);
        adjacentMinesText.setStyle("-fx-background-color: null;");
        adjacentMinesText.setPickOnBounds(false);
    }

    public void setTileText(String _adjacentMines) {
        adjacentMinesText.setText(_adjacentMines);
    }
}
