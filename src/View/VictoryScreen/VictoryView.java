package View.VictoryScreen;

import Services.ExternalResources;
import View.Components.PixelButton;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class VictoryView extends Scene {

    StackPane whole;
    VBox content;

    Text congratulations, extra, time;
    PixelButton next;

    public VictoryView (int[] stagedims, int seconds) {
        super(new StackPane(), stagedims[0], stagedims[1]);

        congratulations = new Text("Congratulations!");
        setTextType(congratulations, stagedims[0]/30);

        extra = new Text("You've cleared all the mines,\n so now the submarine can pass...");
        setTextType(extra, stagedims[0]/60);

        time = new Text("You did it in: \n" + seconds + "seconds");
        setTextType(extra, stagedims[0]/60);

        next = new PixelButton(ExternalResources.topmenuNewGame, stagedims[0]/6, stagedims[0]/18);

        content = new VBox(stagedims[1]/12.0);
        content.getChildren().addAll(congratulations,extra,time,next);

        whole = new StackPane();
        whole.setStyle("-fx-background-color: #306082");
        whole.getChildren().addAll(content);
        super.setRoot(whole);
        super.getStylesheets().add(ExternalResources.victoryStyleSheet);
    }

    public void setTextType (Text text, int size) {
        Font pixelfont = Font.loadFont(ExternalResources.pixelFontResource,16);
        text.setFont(pixelfont);
        text.setStyle("-fx-font-size: " + size + "px;");
    }
}
