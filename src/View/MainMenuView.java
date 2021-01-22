package View;

import Controller.NavigationController;
import Services.ExternalResources;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import View.Components.PixelButton;

//Written by s204508 Massimo Hansen
public class MainMenuView extends Scene {
    static private VBox menu;
    static private StackPane whole;

    public MainMenuView(int[] stageDims){
        super(new StackPane(), stageDims[0], stageDims[1]);

        assembleContent(stageDims);

        whole = new StackPane();
        whole.setId("menu");
        whole.getChildren()
                .add(menu);

        super.setRoot(whole);
        super.getStylesheets()
                .add(ExternalResources.mainStyleStyleSheet);
    }

    public void assembleContent(int[] stageDims){
        //initiate menu
        menu = new VBox((double) stageDims[1]/20);
        menu.setAlignment(Pos.CENTER);

        //Create title
        ImageView title = new ImageView(ExternalResources.menuTitle);
        title.setFitWidth(stageDims[0]/2.0);
        title.setFitHeight(stageDims[0]/15.0);

        //Create singleplayer button
        PixelButton spButton = new PixelButton(ExternalResources.singleplayerText,
                stageDims[0]/4,
                stageDims[0]/24);

        //Create multiplayer button
        PixelButton mpButton = new PixelButton(ExternalResources.multiplayerText,
                stageDims[0]/4,
                stageDims[0]/24);

        //Give buttons functionality
        spButton.setOnMouseClicked(e -> {
            NavigationController.gotoSingleplayerMenuView();
        });
        mpButton.setOnMouseClicked(e -> {
            NavigationController.gotoMultiplayerMenuView();
        });

        //Assemble menu
        menu.getChildren().addAll(title, spButton, mpButton);

    }
}
