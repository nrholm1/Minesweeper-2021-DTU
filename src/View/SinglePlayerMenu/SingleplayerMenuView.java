package View.SinglePlayerMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import View.Components.PixelButton;
import View.Components.PixelSlider;

public class SingleplayerMenuView extends Scene{
    static VBox menu;
    static VBox back;
    static StackPane whole;

    static final String titleURL = "images/premenu-title.png";
    static final String backUrl = "images/back-icon.png";
    static final String startUrl = "images/pre-start-game.png";

    public SingleplayerMenuView(int[] stageDims){
        super(new VBox(), stageDims[0], stageDims[1]);

        menu = new VBox((double) stageDims[1]/20);
        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-background-color: null;");
        menu.setPickOnBounds(false);

        //Title
        ImageView title = new ImageView(new Image(titleURL));
        title.setFitWidth(stageDims[0]/2.0);
        title.setFitHeight(stageDims[0]/15.0);

        menu.getChildren()
                .addAll(title,
                        new PixelSlider(stageDims, new int[]{4,8,12}, "Size"),
                        new PixelSlider(stageDims, new int[]{1,5,10}, "Difficulty"),
                        new PixelButton(startUrl, stageDims[0]/4, stageDims[0]/16));


        back = new VBox();
        int inset = stageDims[0]/40;
        back.setPadding(new Insets(inset,inset,inset,inset));

        PixelButton backbutton = new PixelButton(backUrl,
                stageDims[0]/10,
                stageDims[0]/30);

        back.getChildren().add(backbutton);

        whole = new StackPane();
        whole.getChildren().addAll(back,menu);
        whole.setId("menu");

        super.setRoot(whole);
        super.getStylesheets()
                .add(this.getClass()
                      .getResource("./SingleStyles.css")
                      .toExternalForm());
    }
}
