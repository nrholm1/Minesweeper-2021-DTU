package View;

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

//Written by s204508 Massimo Hansen
public class VictoryView extends Scene {
    StackPane whole;
    VBox content;

    Text congratulations, extra, time;
    PixelButton next;

    public VictoryView (int[] stagedims, int seconds, String victoryText) {
        super(new StackPane(), stagedims[0], stagedims[1]);

        //The next 10 lines are just creating the content of page, including 3 textfields and 1 button
        congratulations = new Text("Congratulations!");
        setTextType(congratulations, stagedims[0]/30);

        //The victory text can be altered
        extra = new Text(victoryText);
        setTextType(extra, stagedims[0]/60);

        //The time it took to finished is passed in
        time = new Text("You won in only " + seconds + " seconds!");
        setTextType(time, stagedims[0]/60);

        next = new PixelButton(ExternalResources.topMenuNewGame, stagedims[0]/4, stagedims[0]/18);
        next.setId("pixelbutton");
        next.setOnMouseClicked(e -> NavigationController.gotoMainMenuView());

        content = new VBox(stagedims[1]/12.0);
        content.setAlignment(Pos.CENTER);
        content.getChildren().addAll(congratulations,extra,time,next);

        whole = new StackPane();
        whole.setId("whole");
        whole.getChildren().addAll(content);
        super.setRoot(whole);
        super.getStylesheets().add(ExternalResources.victoryStyleSheet);
    }

    public void setTextType (Text text, int size) {
        Font pixelFont = ExternalResources.pixelFont16;
        text.setTextAlignment(TextAlignment.CENTER);
        text.setFont(pixelFont);
        text.setFill(Color.WHITE);
        text.setStyle("-fx-font-size: " + size + "px;");
    }
}
