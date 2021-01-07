package View;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import sample.Tile;

public class BoardGenerator {

  GridPane tileGrid;

  public BoardGenerator(int[] dims, int[] info){
    tileGrid = new GridPane();
    tileGrid.setHgap(info[0]);
    tileGrid.setVgap(info[0]);
    tileGrid.setPadding(new Insets(0,info[1],info[1],info[1]));

    for(int col=0; col<dims[0]; col++) {
      for (int row = 0; row < dims[1]; row++) {
        double tilesize = calculateTilesize(dims, info);
        Tile tile = new Tile(new double[]{tilesize, tilesize}, new int[]{col, row}, "1");
        tile.visual().setOnMouseClicked(e -> System.out.println("yessir"));
        tileGrid.getChildren().add(tile.visual());
      }
    }
  }

  public static double calculateTilesize(int[] dims, int[] info){
    return (dims[2] - 2*info[1] - (dims[0]-1)*info[0])/dims[0];
  }

  public GridPane visual(){
    return tileGrid;
  }
}
