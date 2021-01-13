package View.MainMenuScreen;

import Controller.MainMenuController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import View.Components.PixelButton;

// new -> Massimo's class
public class MainMenuView2 extends Scene {
    MainMenuController controller;

    static private VBox menu;
    static private VBox back;
    static private StackPane whole;

    private final String titleURL = "Images/premenu-title.png";
    private final String singleURL = "Images/pre-single-game.png";
    private final String multiURL = "Images/pre-multi-game.png";

    public MainMenuView2(int[] stagedims ){
        super(new StackPane(), stagedims[0], stagedims[1]);

        menu = new VBox((double) stagedims[1]/20);
        menu.setAlignment(Pos.CENTER);

        ImageView title = new ImageView(new Image(titleURL));
        title.setFitWidth(stagedims[0]/2.0);
        title.setFitHeight(stagedims[0]/15.0);

        PixelButton singleButton = new PixelButton(singleURL,
                stagedims[0]/4,
                stagedims[0]/24);

        PixelButton multiButton = new PixelButton(multiURL,
                stagedims[0]/4,
                stagedims[0]/24);

        singleButton.setOnMouseClicked(e -> {
            controller.gotoSingleplayerMenu();
        });
        multiButton.setOnMouseClicked(e -> {
            controller.gotoMultiplayerMenu();
        });

        menu.getChildren()
                .addAll(title,
                        singleButton,
                        multiButton);

        whole = new StackPane();
        whole.setId("menu");
        whole.getChildren()
                .add(menu);

        super.setRoot(whole);
        super.getStylesheets()
                .add(this.getClass()
                .getResource("./MainStyles.css")
                .toExternalForm());
    }

    public void setController(MainMenuController _controller) {
        controller = _controller;
    }
}
