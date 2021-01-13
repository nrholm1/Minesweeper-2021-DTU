package View.GameScreen;

import Controller.GameController;
import Controller.NavigationController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class TopMenu<calcTimeString> extends HBox{
    static final String titleURL = "Images/premenu-title.png";
    static final String newGameURL = "Images/pre-new-game.png";
    //static final String newGameHoverURL = "Images/new-game-hover.png";

    Rectangle newGame;

    public TopMenu(int stagewidth, int inset) {
        super(stagewidth/8.0);
        super.setPadding(new Insets(inset,inset,inset+10,inset));
        super.setAlignment(Pos.CENTER);
        super.setId("top-panel");

        ImageView title = new ImageView();
        title.setImage(new Image(titleURL));
        title.setFitWidth(360);
        title.setFitHeight(45);

        newGame = new Rectangle(180,40);
        newGame.setFill(new ImagePattern(new Image(newGameURL)));
//        newGame.setOnMouseEntered(e -> {newGame.setFill(new ImagePattern(new Image(newGameHoverURL)));});
//        newGame.setOnMouseExited(e -> newGame.setFill(new ImagePattern(new Image(newGameURL))));


        StackPane filler1 = new StackPane();
        filler1.getChildren().add(newGame);

        StackPane filler2 = new StackPane();
        filler2.getChildren().add(title);

        StackPane filler3 = new StackPane();
        filler3.getChildren().add(new TimeCounter());

        super.setAlignment(Pos.CENTER);
        super.getChildren().addAll(filler1, filler2, filler3);
    }

    public void setEvents(GameController controller) {
        newGame.setOnMouseClicked(e -> { controller.getNavigation()
                                                   .gotoMainMenuView();
        });
    }
}
