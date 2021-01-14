package View.Components;

import Controller.GameController;
import View.Components.TimeCounter;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class TopMenuView<calcTimeString> extends HBox{
    static final String titleURL = "Images/premenu-title.png";
    static final String newGameURL = "Images/pre-new-game.png";
    //static final String newGameHoverURL = "Images/new-game-hover.png";

    GameController controller;

    Rectangle newGame;

    public TopMenuView(int stagewidth, int inset) {
        super(stagewidth/8.0);
        super.setPadding(new Insets(inset,inset,inset+10,inset));
        super.setAlignment(Pos.CENTER);
        super.setId("top-panel");

        ImageView title = new ImageView();
        title.setImage(new Image(titleURL));
        title.setFitWidth(stagewidth/3.0);
        title.setFitHeight(stagewidth/22.0);

        newGame = new Rectangle(stagewidth/6.0,stagewidth/27.0);
        newGame.setFill(new ImagePattern(new Image(newGameURL)));
        newGame.setId("newgame-button");
        newGame.setOnMouseClicked(e -> {
            controller
                    .getNavigation()
                    .gotoMainMenuView();
        });

        StackPane filler1 = new StackPane();
        filler1.getChildren().add(newGame);

        StackPane filler2 = new StackPane();
        filler2.getChildren().add(title);

        StackPane filler3 = new StackPane();
        filler3.getChildren().add(new TimeCounter());

        super.setAlignment(Pos.CENTER);
        super.getChildren().addAll(filler1, filler2, filler3);
    }

    public void setController(GameController _controller) {
        controller = _controller;
    }
}
