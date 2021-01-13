package sample.SinglePlayerMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import sample.Components.PixelSlider;
import sample.Components.PixelButton;

public class SinglePlayerMenu extends Scene{

    static VBox menu;
    static VBox back;
    static StackPane whole;

    static final String titleURL = "images/premenu-title.png";
    static final String backUrl = "images/back-icon.png";
    static final String startUrl = "images/pre-start-game.png";

    public SinglePlayerMenu(int[] stagedims){
        super(new VBox(), stagedims[0], stagedims[1]);

        menu = new VBox((double) stagedims[1]/20);
        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-background-color: null;");
        menu.setPickOnBounds(false);

        //Title
        ImageView title = new ImageView(new Image(titleURL));
        title.setFitWidth(stagedims[0]/2);
        title.setFitHeight(stagedims[0]/15);

        menu.getChildren().addAll(title, new PixelSlider(stagedims, new int[]{4,8,12}, "Size"), new PixelSlider(stagedims, new int[]{1,5,10}, "Difficulty"), new PixelButton(startUrl, stagedims[0]/4, stagedims[0]/16, "game"));


        back = new VBox();
        int inset = stagedims[0]/40;
        back.setPadding(new Insets(inset,inset,inset,inset));

        PixelButton backbutton = new PixelButton(backUrl, stagedims[0]/10, stagedims[0]/30, "main-menu");
        back.getChildren().add(backbutton);

        whole = new StackPane();
        whole.getChildren().addAll(back,menu);
        whole.setId("menu");

        super.setRoot(whole);
        super.getStylesheets().add(this.getClass().getResource("./SingleStyles.css").toExternalForm());
    }
}
