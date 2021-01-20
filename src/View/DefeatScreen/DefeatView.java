package View.DefeatScreen;

import Controller.NavigationController;
import Services.ExternalResources;
import View.Components.PixelButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class DefeatView extends Scene{
    StackPane whole;
    VBox content;

    Text toobad, extra;
    PixelButton next;

    public DefeatView (int[] stagedims) {
        super(new StackPane(), stagedims[0], stagedims[1]);

        toobad = new Text("Thats too bad!");
        setTextType(toobad, stagedims[0]/30);

        extra = new Text("You almost made it... Try again!");
        setTextType(extra, stagedims[0]/60);

        next = new PixelButton(ExternalResources.topMenuNewGame, stagedims[0]/4, stagedims[0]/18);
        next.setId("pixelbutton");
        next.setOnMouseClicked(e -> NavigationController.gotoMainMenuView());

        content = new VBox(stagedims[1]/12.0);
        content.setAlignment(Pos.CENTER);
        content.getChildren().addAll(toobad,extra,next);

        whole = new StackPane();
        whole.setId("whole");
        whole.getChildren().addAll(content);
        super.setRoot(whole);
        super.getStylesheets().add(ExternalResources.victoryStyleSheet);
    }

    public void setTextType (Text text, int size) {
        Font pixelfont = ExternalResources.pixelFont16;
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(pixelfont);
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font-size: " + size + "px;");
    }
}

