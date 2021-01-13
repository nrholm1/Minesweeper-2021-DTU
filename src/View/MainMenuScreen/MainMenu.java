package sample.MainMenuScreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import sample.Components.PixelButton;
import sample.Main;

import java.util.Stack;

public class MainMenu extends Scene {

    static private VBox menu;
    static private VBox back;
    static private StackPane whole;

    private final String titleURL = "Images/premenu-title.png";
    private final String singleURL = "Images/pre-single-game.png";
    private final String multiURL = "Images/pre-multi-game.png";

    public MainMenu ( int[] stagedims ){
        super(new StackPane(), stagedims[0], stagedims[1]);

        menu = new VBox((double) stagedims[1]/20);
        menu.setAlignment(Pos.CENTER);

        ImageView title = new ImageView(new Image(titleURL));
        title.setFitWidth(stagedims[0]/2);
        title.setFitHeight(stagedims[0]/15);

        PixelButton singlebutton = new PixelButton(singleURL, stagedims[0]/4, stagedims[0]/24, "single-pregame");
        PixelButton multibutton = new PixelButton(multiURL, stagedims[0]/4, stagedims[0]/24, "multi-pregame");

        menu.getChildren().addAll(title, singlebutton, multibutton);

        whole = new StackPane();
        whole.setId("menu");
        whole.getChildren().add(menu);

        super.setRoot(whole);
        super.getStylesheets().add(this.getClass().getResource("./MainStyles.css").toExternalForm());
    }
}
