package View;

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

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

//Written by s204508 Massimo Hansen, s204503 Niels Raunkjær
public class MultiPlayerMenuView extends Scene {

    private VBox back;
    private StackPane whole;

    private VBox createGameMenu;

    private ImageView title;
    private PixelButton startButton;
    TextField ipField;

    public MultiPlayerMenuView(int[] stageDims) throws UnknownHostException{
        super(new StackPane(), stageDims[0], stageDims[1]);

        assembleSharedComponents(stageDims);
        assembleMenu(stageDims);
        assembleBack(stageDims);

        whole = new StackPane();
        whole.getChildren().addAll(back, createGameMenu);
        whole.setId("menu");

        super.setRoot(whole);
        super.getStylesheets().add(ExternalResources.multiplayerMenuStyleSheet);
    }

    public String getTargetIp() {
        return ipField.getText();
    }

    public String getLocalIp() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        return addr.getHostAddress();
    }

    public void assembleSharedComponents(int[] stageDims){
        //Create title
        title = new ImageView(ExternalResources.menuTitle);
        title.setFitWidth(stageDims[0]/2.0);
        title.setFitHeight(stageDims[0]/15.0);

        //Create startButton
        startButton = new PixelButton(ExternalResources.startGameText, stageDims[0]/4, stageDims[0]/16);
        startButton.setOnMouseClicked(e -> {
            try {
                NavigationController.createMultiplayerGame();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }

    public void assembleMenu(int[] stageDims) throws UnknownHostException {
        //Initiate the menu
        createGameMenu = new VBox((double) stageDims[1]/20);
        createGameMenu.setAlignment(Pos.CENTER);

        //Ensure that you can click through
        createGameMenu.setStyle("-fx-background-color: null;");
        createGameMenu.setPickOnBounds(false);

        //Create the ip-address field
        VBox wholeIp = new VBox(12);
        wholeIp.setAlignment(Pos.CENTER);

        Text ipText = new Text("Enter partner IP:");
        Font pixelfont = ExternalResources.pixelFont16;
        ipText.setFont(pixelfont);
        ipText.setFill(Color.WHITE);

        ipField = new TextField(getLocalIp());
        ipField.setMaxWidth(stageDims[0]/4.0);
        ipField.setAlignment(Pos.CENTER);
        ipField.setFont(pixelfont);

        wholeIp.getChildren().addAll(ipText,ipField);

        createGameMenu.getChildren().addAll(title,
                wholeIp,
                startButton);

    }

    public void assembleBack(int[] stageDims){
        //Create the space for the back-button
        back = new VBox();
        int inset = stageDims[0]/40;
        back.setPadding(new Insets(inset,inset,inset,inset));

        PixelButton backbutton = new PixelButton(ExternalResources.backButton, stageDims[0]/10, stageDims[0]/30);
        backbutton.setOnMouseClicked(e -> {
            NavigationController.gotoMainMenuView();
        });
        back.getChildren().add(backbutton);
    }
}
