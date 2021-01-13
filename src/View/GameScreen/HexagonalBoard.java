package sample.GameScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HexagonalBoard extends StackPane {

    static int size;
    static int diameter;
    static double sidelength;
    static int inset;
    static int amountMines;

    static private HBox board;
    static private VBox boat;

    static private HexTile[][] tileField;

    public HexagonalBoard(int[] stagedims, int inset, int size, int difficulty){
        super();

        this.size = size;
        this.diameter = 2*size + 1;
        this.inset = inset;
        this.sidelength = (stagedims[1]-2*inset)/(2*size*(2*Math.sqrt(3)) + 2*Math.sqrt(3));
        this.amountMines = difficulty;

        createNewTileField();
        createTileFieldVisual();
        //createBoatDecoration();

        getChildren().addAll(board);

    }

    public void createNewTileField(){

        tileField = new HexTile[diameter][];

        for(int col = 0; col < diameter; col++){
            tileField[col] = new HexTile[diameter - Math.abs(col-size)];

            for(int row =0; row<tileField[col].length; row++){
                tileField[col][row] = new HexTile(sidelength, new int[]{col,row});
            }
        }
    }

    public void createTileFieldVisual(){

        board = new HBox();
        board.setPadding(new Insets(inset,inset,inset,inset));
        board.setAlignment(Pos.CENTER);

        for(int col = 0; col< tileField.length; col++){

            VBox currCol = new VBox(sidelength/2);

            //Skaber pladsen der forskyder kolonnerne fra hinanden
            for(int row = 0; row < diameter - tileField[col].length; row++){
                currCol.getChildren().add(new Rectangle(0,Math.sqrt(3)*sidelength/2 - sidelength/4));
            }

            for(int row = 0; row < tileField[col].length; row++ ){
                currCol.getChildren().add(tileField[col][row]);
            }

            board.getChildren().add(currCol);
        }
    }

    public void createBoatDecoration(){
        boat = new VBox(10);
        boat.setPadding(new Insets(0,0,0,50));
        boat.setAlignment(Pos.CENTER_LEFT);

        Text boattext = new Text("Help me get through!\nFind all the mines!");
        Font pixelfont = Font.loadFont(this.getClass().getResource("../PressStart2P-Regular.ttf").toExternalForm(), 10);
        boattext.setLineSpacing(5);
        boattext.setFont(pixelfont);
        boattext.setFill(Color.WHITE);

        ImageView boatimage = new ImageView(new Image("images/image_submarine.gif"));
        boatimage.setFitWidth(160);
        boatimage.setFitHeight(75);

        boat.getChildren().addAll(boattext, boatimage);
    }

    private void setMines() {
        int curMines = 0;
        while(curMines < this.amountMines) {
            int x = (int)(Math.random() * diameter);
            int y = (int)(Math.random() * tileField[x].length);

            if (!tileField[x][y].isMine()) {
                tileField[x][y].toggleIsMine();
                curMines++;
            }
        }
    }

    private void setAdjacentMineCounters() {
        for(int x = 0; x < tileField.length; x++) {
            for (int y = 0; y < tileField[x].length; y++) {
                tileField[x][y].setAdjacentMines(getSideMines(x, y));
            }
        }
    }

    public int getSideMines(int col, int row){
        int mines = 0;
        int[][] fields = {
                {col, row-1},
                {col+1, row},
                {col, row+1},
                {col-1, row},
                {col-1, col > 5 ? row+1 : row-1},
                {col+1, col < 5 ? row-1 : row+1}
        };
        for(int i = 0; i < fields.length; i++) {
            int tempx = fields[i][0];
            int tempy = fields[i][1];
            if(tempx >= 0 && tempx < diameter && tempy >= 0 && tempy < tileField[tempx].length && tileField[tempx][tempy].isMine()) {
                mines++;
            }
        }
        return mines;
    }
}
