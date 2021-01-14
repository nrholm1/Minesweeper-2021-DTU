package View.SinglePlayerMenu;

import Controller.NavigationController;
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

    PixelSlider size;
    PixelSlider difficulty;
    PixelButton startButton;

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

        size = new PixelSlider(stageDims, new int[]{4,8,12}, "Size");
        difficulty = new PixelSlider(stageDims, new int[]{1,5,10}, "Difficulty");
        startButton = new PixelButton(startUrl, stageDims[0]/4, stageDims[0]/16);

        startButton.setOnMouseClicked(e -> {
            NavigationController.createSingleplayerGame();
        });

        menu.getChildren()
                .addAll(title,
                        size,
                        difficulty,
                        startButton);


        back = new VBox();
        int inset = stageDims[0]/40;
        back.setPadding(new Insets(inset,inset,inset,inset));

        PixelButton backButton = new PixelButton(backUrl,
                stageDims[0]/10,
                stageDims[0]/30);

        backButton.setOnMouseClicked(e -> {
            NavigationController.gotoMainMenuView();
        });

        back.getChildren().add(backButton);

        whole = new StackPane();
        whole.getChildren().addAll(back,menu);
        whole.setId("menu");

        super.setRoot(whole);
        super.getStylesheets()
                .add(this.getClass()
                      .getResource("./SingleStyles.css")
                      .toExternalForm());
    }

    public int getSize() {
        return size.getSize();
    }

    public int getDifficulty() {
        return difficulty.getSize();
    }
}
