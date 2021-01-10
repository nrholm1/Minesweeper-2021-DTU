package View.GameScreen;

import Controller.GameController;
import Model.Field;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class HexagonalBoard extends HBox{
    private double sideLength;
    private HexTile[][] hexTiles;

    public HexagonalBoard(int stagewidth, int inset, int size){
        super();
        super.setPadding(new Insets(inset,inset,inset,inset));
        super.setAlignment(Pos.CENTER);

        sideLength = (stagewidth/1.5 - 2*inset)/(4*size + 2);
        createFieldArray(size + 1);

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
                hexTiles[col+size][row] = currTile;
                int m = col+size;
                int n = row;
                hexTiles[col+size][row].setOnMouseClicked(e -> {
                    System.out.println(m + ": " + n );
                });
                currCol.getChildren().add(currTile);
            }

            //Hver gang en kolonne er skabt blir den tilfoejet til raekkerne
            verticalAxis.getChildren().add(currCol);
        }

        super.getChildren().add(verticalAxis);
    }

    private void createFieldArray(int height) {
        int width = 2*height -1;
        hexTiles = new HexTile[width][];
        hexTiles[height-1] = new HexTile[width]; // Middle column is created
        for(int x = 0; x <= height-2; x++) { // Other columns are created
            hexTiles[x] = new HexTile[height+x];
            hexTiles[width - 1 - x] = new HexTile[height+x];
        }

        System.out.println(hexTiles.length);
        System.out.println("---");
        for(int i = 0; i < hexTiles.length; i++) {
            for(int o = 0; o < width-hexTiles[i].length; o++) {
                System.out.print(" ");
            }
            for(int o = 0; o < hexTiles[i].length; o++) {
                System.out.print("X ");
            }
            System.out.println(hexTiles[i].length);
        }
    }

    public void setEvents(GameController controller) {
        for(int i = 0; i < hexTiles.length; i++) {
            for(int o = 0; o < hexTiles[i].length; o++) {
                int x = i;
                int y = o;
                hexTiles[i][o].setOnMouseClicked(e -> {
                    controller.clickField(hexTiles[x][y].getX(), hexTiles[x][y].getY());
                });
            }
        }
    }
}
