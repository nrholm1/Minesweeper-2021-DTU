package View.Components;

import Controller.GameController;
import Controller.NavigationController;
import Services.ExternalResources;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

//Skrevet af s204508 Massimo Hansen
public class TopMenuView<calcTimeString> extends HBox{
    private GameController controller;

    private final Rectangle newGame;
    private final TimeCounter timeCounter;

    public TopMenuView(int stagewidth, int inset) {
        super(stagewidth/8.0);
        super.setPadding(new Insets(inset,inset,inset+10,inset));
        super.setAlignment(Pos.CENTER);
        super.setId("top-panel");

        ImageView title = new ImageView();
        title.setImage(ExternalResources.topMenuTitle);
        title.setFitWidth(stagewidth/3.0);
        title.setFitHeight(stagewidth/22.0);

        newGame = new Rectangle(stagewidth/6.0,stagewidth/27.0);
        newGame.setFill(new ImagePattern(ExternalResources.topMenuNewGame));
        newGame.setId("newgame-button");
        newGame.setOnMouseClicked(e -> {
            NavigationController.gotoMainMenuView();
        });

        StackPane filler1 = new StackPane();
        filler1.getChildren().add(newGame);

        StackPane filler2 = new StackPane();
        filler2.getChildren().add(title);

        timeCounter = new TimeCounter();
        StackPane filler3 = new StackPane();
        filler3.getChildren().add(timeCounter);

        super.setAlignment(Pos.CENTER);
        super.getChildren().addAll(filler1, filler2, filler3);
    }

    public void setController(GameController _controller) {
        controller = _controller;
    }

    public int getSeconds() { return timeCounter.getTime(); }
}
