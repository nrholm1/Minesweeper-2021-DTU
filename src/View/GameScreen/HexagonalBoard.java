package View.GameScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class HexagonalBoard extends HBox{
    private double sideLength;

    public HexagonalBoard(int stagewidth, int inset, int size){
        super();
        super.setPadding(new Insets(inset,inset,inset,inset));
        super.setAlignment(Pos.CENTER);

        sideLength = (stagewidth/1.5 - 2*inset)/(4*size + 2);

        HBox verticalAxis = new HBox(0);

        for(int col = -size; col<=size; col++){

            //Afstanden mellem alle hexagonerne er halvdelen af sidelaengden
            VBox currCol = new VBox(sideLength/2);

            //Dette for-loop skaber pladsen der forskyder kolonnerne fra hinanden
            for(int row = 0; row<Math.abs(col); row++){
                currCol.getChildren().add(new Rectangle(0,Math.sqrt(3)*sideLength/2 - sideLength/4));
            }

            //Dette for-loop skaber HexTilesne
            for(int row = 0; row<=2*size-Math.abs(col); row++ ){
                //Skaber ny hextile og tilfoejer dens visual til kolonnen
                HexTile currTile = new HexTile(col + size, row, sideLength);
                currCol.getChildren().add(currTile);
            }

            //Hver gang en kolonne er skabt blir den tilfoejet til raekkerne
            verticalAxis.getChildren().add(currCol);
        }

        super.getChildren().add(verticalAxis);
    }
}
