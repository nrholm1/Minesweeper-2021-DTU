package sample.GameScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Main;

import java.util.Timer;
import java.util.TimerTask;

public class TopMenu {

    private HBox top;

    static final String titleURL = "images/premenu-title.png";
    static final String newGameURL = "images/pre-new-game.png";

    public TopMenu(int stagewidth, int inset) {
        top = new HBox(stagewidth/8);
        top.setPadding(new Insets(inset,inset,inset+10,inset));
        top.setAlignment(Pos.CENTER);
        top.setId("top-panel");

        ImageView title = new ImageView();
        title.setImage(new Image(titleURL));
        title.setFitWidth(stagewidth/3);
        title.setFitHeight(stagewidth/22);

        Rectangle newgame = new Rectangle(stagewidth/6,stagewidth/27);
        newgame.setFill(new ImagePattern(new Image(newGameURL)));
        newgame.setId("newgame-button");
        newgame.setOnMouseClicked(e -> Main.goToScene("main-menu"));

        StackPane filler1 = new StackPane();
        filler1.getChildren().add(newgame);

        StackPane filler2 = new StackPane();
        filler2.getChildren().add(title);

        StackPane filler3 = new StackPane();
        filler3.getChildren().add(new TimeCounter().visual());

        top.setAlignment(Pos.CENTER);
        top.getChildren().addAll(filler1, filler2, filler3);
    }

    public HBox visual(){
        return top;
    }
}
