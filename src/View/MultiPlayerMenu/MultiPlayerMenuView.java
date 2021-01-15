package View.MultiPlayerMenu;

import Controller.NavigationController;
import Services.ExternalResources;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import View.Components.PixelButton;
import View.Components.PixelSlider;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MultiPlayerMenuView extends Scene {
    static VBox menu;
    static VBox back;
    static StackPane whole;

    public MultiPlayerMenuView(int[] stagedims) throws UnknownHostException {
        super(new StackPane(), stagedims[0], stagedims[1]);

        menu = new VBox((double) stagedims[1]/20);
        menu.setAlignment(Pos.CENTER);
        menu.setStyle("-fx-background-color: null;");
        menu.setPickOnBounds(false);

        //Title
        ImageView title = new ImageView(ExternalResources.menuTitle);
        title.setFitWidth(stagedims[0]/2.0);
        title.setFitHeight(stagedims[0]/15.0);

        //Ip-address
        VBox wholeIp = new VBox(12);
        wholeIp.setAlignment(Pos.CENTER);

        Text ipText = new Text("Enter partner IP:");
        Font pixelfont = Font.loadFont(ExternalResources.pixelFontResource, 16);
        ipText.setFont(pixelfont);
        ipText.setFill(Color.WHITE);

        TextField ipField = new TextField(getLocalIp());
        ipField.setMaxWidth(stagedims[0]/4.0);
        ipField.setAlignment(Pos.CENTER);
        ipField.setFont(pixelfont);

        wholeIp.getChildren().addAll(ipText,ipField);


        menu.getChildren().addAll(title,
                new PixelSlider(stagedims, new int[]{4,8,12}, "Size"),
                new PixelSlider(stagedims, new int[]{1,5,10}, "Difficulty"), wholeIp,
                new PixelButton(ExternalResources.startgameText, stagedims[0]/5, stagedims[0]/15));

        back = new VBox();
        int inset = stagedims[0]/40;
        back.setPadding(new Insets(inset,inset,inset,inset));

        PixelButton backbutton = new PixelButton(ExternalResources.backButton, stagedims[0]/10, stagedims[0]/30);
        backbutton.setOnMouseClicked(e -> {
            NavigationController.gotoMainMenuView();
        });
        back.getChildren().add(backbutton);

        whole = new StackPane();
        whole.getChildren().addAll(back,menu);
        whole.setId("menu");

        super.setRoot(whole);
        super.getStylesheets().add(this.getClass().getResource("./MultiStyles.css").toExternalForm());
    }

    // TODO works as intended, but should be
    public String getLocalIp() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        return addr.getHostAddress();
    }
}
