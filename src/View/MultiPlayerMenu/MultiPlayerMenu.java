package sample.MultiPlayerMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.Components.PixelSlider;
import sample.Components.PixelButton;

public class MultiPlayerMenu extends Scene {

    static VBox menu;
    static VBox back;
    static StackPane whole;

    static final String titleURL = "images/premenu-title.png";
    static final String backUrl = "images/back-icon.png";
    static final String startUrl = "images/pre-start-game.png";

    public MultiPlayerMenu(int[] stagedims){
        super(new StackPane(), stagedims[0], stagedims[1]);

        menu = new VBox((double) stagedims[1]/20);
        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-background-color: null;");
        menu.setPickOnBounds(false);

        //Title
        ImageView title = new ImageView(new Image(titleURL));
        title.setFitWidth(stagedims[0]/2);
        title.setFitHeight(stagedims[0]/15);

        //Ip-address
        VBox wholeIp = new VBox(12);
        wholeIp.setAlignment(Pos.CENTER);

        Text ipText = new Text("Enter Game Code:");
        Font pixelfont = Font.loadFont(this.getClass().getResource("../PressStart2P-Regular.ttf").toExternalForm(), 16);
        ipText.setFont(pixelfont);
        ipText.setFill(Color.WHITE);

        TextField ipField = new TextField();
        ipField.setMaxWidth(stagedims[0]/4);
        ipField.setAlignment(Pos.CENTER);
        ipField.setFont(pixelfont);

        wholeIp.getChildren().addAll(ipText,ipField);


        menu.getChildren().addAll(title, new PixelSlider(stagedims, new int[]{4,8,12}, "Size"), new PixelSlider(stagedims, new int[]{1,5,10}, "Difficulty"), wholeIp, new PixelButton(startUrl, stagedims[0]/5, stagedims[0]/15, "game"));

        back = new VBox();
        int inset = stagedims[0]/40;
        back.setPadding(new Insets(inset,inset,inset,inset));

        PixelButton backbutton = new PixelButton(backUrl, stagedims[0]/10, stagedims[0]/30, "main-menu");
        back.getChildren().add(backbutton);

        whole = new StackPane();
        whole.getChildren().addAll(back,menu);
        whole.setId("menu");

        super.setRoot(whole);
        super.getStylesheets().add(this.getClass().getResource("./MultiStyles.css").toExternalForm());
    }
}
