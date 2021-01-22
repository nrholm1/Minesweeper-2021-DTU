package View.Components;

import Controller.GameController;
import Controller.NavigationController;
import Services.ExternalResources;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

//Skrevet af s204508 Massimo Hansen
public class TopMenuView extends HBox{
    private GameController controller;

    private ImageView title;
    private Rectangle newGame;
    private TimeCounter timeCounter;

    public TopMenuView(int stagewidth, int inset) {
        super(stagewidth/8.0);
        super.setPadding(new Insets(inset,inset,inset+10,inset));
        super.setAlignment(Pos.CENTER);
        super.setId("top-panel");

        createContent(stagewidth);

        super.setAlignment(Pos.CENTER);
        super.getChildren().addAll(newGame, title, timeCounter);
    }

    public void createContent(int stagewidth){
        title = new ImageView();
        title.setImage(ExternalResources.topMenuTitle);
        title.setFitWidth(stagewidth/3.0);
        title.setFitHeight(stagewidth/22.0);

        newGame = new Rectangle(stagewidth/6.0,stagewidth/27.0);
        newGame.setFill(new ImagePattern(ExternalResources.topMenuNewGame));
        newGame.setId("newgame-button"); //SetId is a css related method
        newGame.setOnMouseClicked(e -> {
            NavigationController.gotoMainMenuView();
        });

        timeCounter = new TimeCounter();
    }

    public void setController(GameController _controller) {
        controller = _controller;
    }

    public int getSeconds() { return timeCounter.getTime(); }
}
