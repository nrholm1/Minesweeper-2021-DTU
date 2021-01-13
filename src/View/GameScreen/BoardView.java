package View.GameScreen;

import Controller.GameController;
import Model.Field;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BoardView extends StackPane {
    private final double sideLength;
    private final int inset;
    private final int size;
    private final int diameter;

    private HexTile[][] tileField;
    static private HBox board;
    static private VBox boat;

    private final String tileUrl = "Images/hex-tile.png";

    public BoardView(int stageWidth, int inset, int size) {
        super();

        this.inset = inset;
        this.size = size;
        this.diameter = 2*size + 1;
        this.sideLength = (stageWidth/2.0 - 2*inset)/(4*size + 2);

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

        for (HexTile[] hexTiles : tileField) {

            VBox currCol = new VBox(sideLength / 2);

            //Skaber pladsen der forskyder kolonnerne fra hinanden
            for (int row = 0; row < diameter - hexTiles.length; row++) {
                currCol.getChildren().add(new Rectangle(0, Math.sqrt(3) * sideLength / 2 - sideLength / 4));
            }

            for (HexTile hexTile : hexTiles) {
                currCol.getChildren().add(hexTile);
            }

            board.getChildren().add(currCol);
        }
    }

    public void createBoatDecoration(){
        boat = new VBox(10);
        boat.setPadding(new Insets(0,0,0,50));
        boat.setAlignment(Pos.CENTER_LEFT);

        Text boatText = new Text("Help me get through!\nFind all the mines!");
        Font pixelFont = Font.loadFont(this.getClass()
                                           .getResource("../PressStart2P-Regular.ttf")
                                           .toExternalForm(), 10);
        boatText.setLineSpacing(5);
        boatText.setFont(pixelFont);
        boatText.setFill(Color.WHITE);

        ImageView boatImage = new ImageView(new Image("Images/image_submarine.gif"));
        boatImage.setFitWidth(160);
        boatImage.setFitHeight(75);

        boat.getChildren().addAll(boatText, boatImage);
    }

    // TODO refactor and clear up exactly what the architecture is?
    // set directly when creating tiles instead?
    public void setEvents(GameController controller) {
        // temp set here:
        linkTileFieldToController(controller);

        for(int i = 0; i < tileField.length; i++) {
            for(int o = 0; o < tileField[i].length; o++) {
                int x = i;
                int y = o;
                tileField[i][o].setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.PRIMARY)
                        handleLeftClick(controller, x, y);
                    if (e.getButton() == MouseButton.SECONDARY)
                        handleRightClick(controller, x, y);
                });
            }
        }
    }

    // static/final
    void linkTileFieldToController(GameController controller) {
        controller.setGuiBoard(this);
    }

    void handleLeftClick(GameController controller, int x, int y) {
        // bundle all methods to be called when a tile is leftclicked
        controller.pressField(x,y);
        controller.updateTile(x,y);
    }

    void handleRightClick(GameController controller, int x, int y) {
        // bundle all methods to be called when a tile is rightclicked
        controller.flagField(x,y);
        controller.updateTile(x,y);
    }

    public HexTile getTile(int x, int y) {
        return tileField[x][y];
    }
}
