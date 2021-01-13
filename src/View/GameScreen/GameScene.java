package sample.GameScreen;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class GameScene extends Scene{

    public GameScene(int[] stagedims, int size, int difficulty){
        super(new BorderPane(), stagedims[0], stagedims[1]);
        BorderPane gameView = new BorderPane();
        gameView.setTop(new TopMenu(stagedims[0], stagedims[0]/30).visual());
        gameView.setCenter(new HexagonalBoard(stagedims, stagedims[0]/30, size, difficulty));
        gameView.setId("gameback");

        super.setRoot(gameView);
        super.getStylesheets().add(this.getClass().getResource("./GameStyles.css").toExternalForm());
    }
}
