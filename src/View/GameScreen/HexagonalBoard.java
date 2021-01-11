package View.GameScreen;

import Controller.GameController;
import Model.Field;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HexagonalBoard extends StackPane {
    private double sideLength;
    private int inset;
    private int size;
    private int diameter;

    private HexTile[][] tileField;
    static private HBox board;
    static private VBox boat;

    private final String tileUrl = "Images/hex-tile.png";

    public HexagonalBoard(int stagewidth, int inset, int size){
        super();

        this.inset = inset;
        this.size = size;
        this.diameter = 2*size + 1;
        this.sideLength = (stagewidth/2 - 2*inset)/(4*size + 2);

        createNewTileField();
        createTileFieldVisual();
        createBoatDecoration();

        super.getChildren().addAll(boat, board);
    }

    public void createNewTileField(){

        tileField = new HexTile[diameter][];
        ImagePattern img = new ImagePattern(new Image(tileUrl));

        for(int col = 0; col < diameter; col++){
            tileField[col] = new HexTile[diameter - Math.abs(col-size)];

            for(int row =0; row<tileField[col].length; row++){
                tileField[col][row] = new HexTile(col, row, sideLength, img);
            }
        }
    }

    public void createTileFieldVisual(){

        board = new HBox();
        board.setPadding(new Insets(inset,inset,inset,inset));
        board.setAlignment(Pos.CENTER);

        for(int col = 0; col< tileField.length; col++){

            VBox currCol = new VBox(sideLength/2);

            //Skaber pladsen der forskyder kolonnerne fra hinanden
            for(int row = 0; row < diameter - tileField[col].length; row++){
                currCol.getChildren().add(new Rectangle(0,Math.sqrt(3)*sideLength/2 - sideLength/4));
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
        Font pixelfont = Font.loadFont(this.getClass().getResource("../MenuScreen/PressStart2P-Regular.ttf").toExternalForm(), 10);
        boattext.setLineSpacing(5);
        boattext.setFont(pixelfont);
        boattext.setFill(Color.WHITE);

        ImageView boatimage = new ImageView(new Image("Images/image_submarine.gif"));
        boatimage.setFitWidth(160);
        boatimage.setFitHeight(75);

        boat.getChildren().addAll(boattext, boatimage);
    }

    public void setEvents(GameController controller) {
        for(int i = 0; i < tileField.length; i++) {
            for(int o = 0; o < tileField[i].length; o++) {
                int x = i;
                int y = o;
                tileField[i][o].setOnMouseClicked(e -> {
                    controller.clickField(tileField[x][y].getX(), tileField[x][y].getY());
                });
            }
        }
    }
}
