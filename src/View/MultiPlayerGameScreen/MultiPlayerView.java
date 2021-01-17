package View.MultiPlayerGameScreen;

import Model.Board;
import Services.ExternalResources;
import View.Components.TopMenuView;
import View.GameScreen.Util.BoardView;
import View.GameScreen.Util.BoardViewBuilder;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MultiPlayerView extends Scene {

    private BorderPane whole;

    private TopMenuView topMenu;
    private final Font pixelfont = Font.loadFont(ExternalResources.pixelFontResource,16);

    private HBox boards;
    private VBox player1Screen, player2Screen;

    private Text player1Text;
    private Text player2Text;
    private BoardView player1View, player2View;

    public MultiPlayerView(int[] stageDimension){
        super(new BorderPane(), stageDimension[0], stageDimension[1]);

        assembleView(stageDimension[0], stageDimension[1]);

        super.setRoot(whole);
        super.getStylesheets()
                .add(ExternalResources.gamestyleSheet);
    }

    public void assembleView(int stageWidth, int stageHeight){
        int[] stageDims = new int[] {stageWidth, stageHeight};
        double screenHeight = stageHeight / 10.0;
        double screenWidth = stageWidth / 10.0;

        topMenu = new TopMenuView(stageWidth, 30);

        player1Screen = createPlayerScreen(screenHeight);
        player1Text = createPlayerText("Player 1: You");
        player1View = new BoardViewBuilder()
                .withStageDims(stageDims)
                .withInsetSize(30)
                .withSize(10)
                .withTopview(topMenu)
                .build();

        player1Screen.getChildren()
                .addAll(player1Text, player1View);

        player2Screen = createPlayerScreen(screenHeight);
        player2Text = createPlayerText("Player 2: Opponent");
        player2View = new BoardViewBuilder()
                .withStageDims(stageDims)
                .withInsetSize(30)
                .withSize(10)
                .withTopview(topMenu)
                .disableTiles() // Disables tiles for oppenent, since the client isn't supposed to be able to press them
                .build();

        player2Screen.getChildren()
                .addAll(player2Text, player2View);

        boards = new HBox(screenWidth);
        boards.setAlignment(Pos.CENTER);
        boards.getChildren()
                .addAll(player1Screen, player2Screen);

        whole = new BorderPane();
        whole.setId("gameback");
        whole.setTop(topMenu);
        whole.setCenter(boards);
    }

    VBox createPlayerScreen(double screenHeight) {
        VBox playerScreen = new VBox(screenHeight);
        playerScreen.setAlignment(Pos.CENTER);

        return playerScreen;
    }

    Text createPlayerText(String text) {
        Text playerText = new Text(text);
        playerText.setFont(pixelfont);

        return playerText;
    }

    public BoardView getPlayer1View() {
        return player1View;
    }

    public BoardView getPlayer2View() {
        return player2View;
    }
}
