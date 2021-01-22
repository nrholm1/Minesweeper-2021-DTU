package View;

import Controller.NavigationController;
import Services.ExternalResources;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import View.Components.PixelButton;
import View.Components.PixelSlider;

//Written by s204508 Massimo Hansen
public class SingleplayerMenuView extends Scene{
    static VBox menu;
    static VBox back;
    static StackPane whole;

    ImageView title;
    PixelSlider size;
    PixelSlider difficulty;
    PixelButton startButton;

    public SingleplayerMenuView(int[] stageDims){
        super(new VBox(), stageDims[0], stageDims[1]);

        createMenu(stageDims);
        createBack(stageDims);

        //Add everything together
        whole = new StackPane();
        whole.getChildren().addAll(back,menu);
        whole.setId("menu");

        super.setRoot(whole);
        super.getStylesheets()
                .add(ExternalResources.singePlayerMenuStyleSheet);
    }

    public void createMenu(int[] stageDims){
        //Create title
        title = new ImageView(ExternalResources.menuTitle);
        title.setFitWidth(stageDims[0]/2.0);
        title.setFitHeight(stageDims[0]/15.0);

        //Create rest of content
        size = new PixelSlider(stageDims, new int[]{4,8,12}, "Size");
        difficulty = new PixelSlider(stageDims, new int[]{1,5,10}, "Difficulty");
        startButton = new PixelButton(ExternalResources.startGameText, stageDims[0]/4, stageDims[0]/16);

        startButton.setOnMouseClicked(e -> {
            NavigationController.createSingleplayerGame();
        });

        //Create the layout for the menu
        menu = new VBox((double) stageDims[1]/20);
        menu.setAlignment(Pos.CENTER);

        //Making sure you can click through it, to reach back-button
        menu.setStyle("-fx-background-color: null;");
        menu.setPickOnBounds(false);

        menu.getChildren().addAll(title, size, difficulty, startButton);
    }

    public void createBack(int[] stageDims){
        //Create the space for the back-button
        back = new VBox();
        int inset = stageDims[0]/40;
        back.setPadding(new Insets(inset,inset,inset,inset));

        PixelButton backButton = new PixelButton(ExternalResources.backButton, stageDims[0]/10, stageDims[0]/30);

        backButton.setOnMouseClicked(e -> {
            NavigationController.gotoMainMenuView();
        });

        back.getChildren().add(backButton);
    }

    public int getSize() {
        return size.getSize();
    }

    public int getDifficulty() {
        return difficulty.getSize();
    }
}
