package View.GameScreen;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import sample.Main;

public class TopMenu<calcTimeString> {

    private HBox top;

    static final String titleURL = "images/title.png";
    static final String newGameURL = "images/new-game.png";
    static final String newGameHoverURL = "images/new-game-hover.png";

    public TopMenu(int stagewidth, Stage stage, Scene scene) {
        top = new HBox(stagewidth/8);
        ImageView title = new ImageView();
        title.setImage(new Image(titleURL));
        title.setFitWidth(256);
        title.setFitHeight(64);

        Rectangle newgame = new Rectangle(120,40);
        newgame.setFill(new ImagePattern(new Image(newGameURL)));
        newgame.setOnMouseEntered(e -> {newgame.setFill(new ImagePattern(new Image(newGameHoverURL)));});
        newgame.setOnMouseExited(e -> newgame.setFill(new ImagePattern(new Image(newGameURL))));
        newgame.setOnMouseClicked(e -> Main.goToScene("pre"));

        StackPane filler1 = new StackPane();
        filler1.getChildren().add(newgame);

        StackPane filler2 = new StackPane();
        filler2.getChildren().add(title);

        top.setAlignment(Pos.CENTER);
        top.getChildren().addAll(filler1, filler2, new sample.GameScreen.TimeCounter().visual());
    }

    public HBox visual(){
        return top;
    }
}
