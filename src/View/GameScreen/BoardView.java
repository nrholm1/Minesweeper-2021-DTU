package View.GameScreen;

import Controller.GameController;
import Model.Board;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BoardView extends StackPane {
    private final double sideLength;
    private final int inset;
    private final int boardRadius;
    private final int boardDiameter;

    private HexTile[][] tileField;
    static private HBox board;
    static private VBox boat;

    GameController controller;

    public BoardView(int[] stageDimensions, int inset, int size) {
        super();

        this.inset = inset;
        this.boardRadius = size;
        this.boardDiameter = 2*size + 1;
        this.sideLength = calculateSideLength(stageDimensions);

        createNewTileField();
        createTileFieldVisual();
        createBoatDecoration();

        super.getChildren().addAll(boat, board);
    }

    int calculateSideLength(int[] stageDimensions) {
        return (int)
                ((stageDimensions[1]-2*inset)
                /
                (2*boardRadius*(2*Math.sqrt(3)) + 2*Math.sqrt(3)));
    }

    public void createNewTileField(){

        tileField = new HexTile[boardDiameter][];

        for(int col = 0; col < boardDiameter; col++){
            tileField[col] = new HexTile[boardDiameter - Math.abs(col - boardRadius)];

            for(int row = 0; row < tileField[col].length; row++){
                HexTile currentTile = new HexTile(col, row, sideLength);
                int x = col;
                int y = row;

                currentTile.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.PRIMARY)
                        handleLeftClick(x, y);
                    if (e.getButton() == MouseButton.SECONDARY)
                        handleRightClick(x, y);
                });

                tileField[col][row] = currentTile;
            }
        }
    }

    public void createTileFieldVisual(){
        board = new HBox();
        board.setPadding(new Insets(inset, inset, inset, inset));
        board.setAlignment(Pos.CENTER);

        for (HexTile[] hexTiles : tileField) {

            VBox currCol = new VBox(sideLength / 2);

            //Skaber pladsen der forskyder kolonnerne fra hinanden
            for (int row = 0; row < boardDiameter - hexTiles.length; row++)
                currCol.getChildren()
                       .add(new Rectangle(0, Math.sqrt(3) * sideLength / 2 - sideLength / 4));

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

    public void setController(GameController _controller) {
        controller = _controller;
    }

    void handleLeftClick(int x, int y) {
        controller.pressField(x,y);
        controller.updateTile(x,y);
    }

    void handleRightClick(int x, int y) {
        controller.flagField(x,y);
        controller.updateTile(x,y);
    }

    public void renderEntireTileField() {
        for(int x = 0; x < tileField.length; x++)
            for(int y = 0; y < tileField[x].length; y++)
                controller.updateTile(x,y);
    }

    public HexTile getTile(int x, int y) {
        return tileField[x][y];
    }
}
