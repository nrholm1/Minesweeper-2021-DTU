package View;

import Controller.NavigationController;
import Services.ExternalResources;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import View.Components.PixelButton;

//Skrevet af s204508 Massimo Hansen
public class MainMenuView extends Scene {
    static private VBox menu;
    static private StackPane whole;

    public MainMenuView(int[] stageDims){
        super(new StackPane(), stageDims[0], stageDims[1]);

        menu = new VBox((double) stageDims[1]/20);
        menu.setAlignment(Pos.CENTER);

        ImageView title = new ImageView(ExternalResources.menuTitle);
        title.setFitWidth(stageDims[0]/2.0);
        title.setFitHeight(stageDims[0]/15.0);

        PixelButton spButton = new PixelButton(ExternalResources.singleplayerText,
                stageDims[0]/4,
                stageDims[0]/24);

        PixelButton mpButton = new PixelButton(ExternalResources.multiplayerText,
                stageDims[0]/4,
                stageDims[0]/24);

        spButton.setOnMouseClicked(e -> {
            NavigationController.gotoSingleplayerMenuView();
        });
        mpButton.setOnMouseClicked(e -> {
            NavigationController.gotoMultiplayerMenuView();
        });

        menu.getChildren()
                .addAll(title,
                        spButton,
                        mpButton);

        whole = new StackPane();
        whole.setId("menu");
        whole.getChildren()
                .add(menu);

        super.setRoot(whole);
        super.getStylesheets()
                .add(ExternalResources.mainStyleStyleSheet);
    }
}
