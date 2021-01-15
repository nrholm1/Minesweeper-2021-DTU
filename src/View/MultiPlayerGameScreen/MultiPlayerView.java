package View.MultiPlayerGameScreen;

import Model.Board;
import Services.ExternalResources;
import View.Components.TopMenuView;
import View.GameScreen.Util.BoardView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MultiPlayerView extends Scene {

    BorderPane whole;

    TopMenuView topMenu;

    HBox boards;
    VBox player1Screen, player2Screen;

    Text player1Text;
    Text player2Text;
    Board player1Board, player2Board;
    BoardView player1View, player2View;

    public MultiPlayerView(int[] stageDimension){
        super(new BorderPane(), stageDimension[0], stageDimension[1]);

        assembleView(stageDimension[0], stageDimension[1]);

        super.setRoot(whole);
        super.getStylesheets().add(ExternalResources.multiplayerMenuStyleSheet);
    }

    public void assembleView(int stageWidth, int stageHeight){
        Font pixelfont = Font.loadFont(ExternalResources.pixelFontResource,16);

        int[] stageDims = new int[] {stageWidth, stageHeight};
        double screenHeight = stageHeight / 10.0;
        double screenWidth = stageWidth / 10.0;

        player1Screen = new VBox(screenHeight);

        player1Text = new Text("Player 1: You");
        player1Text.setFont(pixelfont);

        player1Board = new Board(stageWidth, stageHeight);
        player1View = new BoardView(stageDims, 30, 10);

        player1Screen.getChildren()
                .addAll(player1Text, player1View);

        player2Screen = new VBox(screenHeight);
        player2Screen.setAlignment(Pos.CENTER);

        player2Text = new Text("Player 2: Opponent");
        player2Text.setFont(pixelfont);

        player2Board = new Board(stageWidth, stageHeight);
        player2View = new BoardView(stageDims, 30, 10);

        player2Screen.getChildren()
                .addAll(player2Text, player2View);

        boards = new HBox(screenWidth);
        boards.setAlignment(Pos.CENTER);
        boards.getChildren()
                .addAll(player1Screen, player2Screen);

        whole = new BorderPane();
        whole.setId("whole");
        whole.setTop(topMenu);
        whole.setCenter(boards);
    }
}
