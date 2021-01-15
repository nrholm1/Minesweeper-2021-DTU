package View.MultiPlayerGameScreen;

import Model.Board;
import View.SinglePlayerGameScreen.BoardView;
import View.SinglePlayerGameScreen.TopMenuView;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MultiPlayerView extends Scene {

    BorderPane whole;

    TopMenuView topMenu;

    HBox boards;
    VBox player1Screen, player2Screen;

    Board player1Board, player2Board;
    BoardView player1View, player2View;

    public MultiPlayerView(int[] stageDimension){
        super(new BorderPane(), stageDimension[0], stageDimension[1]);

        assembleView(stageDimension[0], stageDimension[1]);

        super.setRoot(whole);
        super.getStylesheets().add(this.getClass().getResource("./MultiStyles.css").toExternalForm());
    }

    public void assembleView(int stagewidth, int stageheight){
        player1Screen = new VBox(stageheight/10);
        player1Screen.setAlignment(Pos.CENTER);

        boards = new HBox(stagewidth/10);
        boards.setAlignment(Pos.CENTER);
        boards.getChildren().addAll(player1Screen, player2Screen);

        whole = new BorderPane();
        whole.setId("whole");
        whole.setTop(topMenu);
        whole.setCenter(boards);
    }
}
