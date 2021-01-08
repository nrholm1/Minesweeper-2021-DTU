package View.MenuScreen;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PregameMenu {

    static Scene scene;
    static VBox menu;

    static final String titleURL = "Images/premenu-title.png";

    public PregameMenu(int[] stagedims){
        menu = new VBox((double) stagedims[1]/20);
        menu.setAlignment(Pos.CENTER);
        menu.setId("menu");

        //Title
        ImageView title = new ImageView(new Image(titleURL));
        title.setFitWidth(600);
        title.setFitHeight(80);

        menu.getChildren().addAll(title, new SizeSlider(stagedims).visual(), new DiffSlider(stagedims).visual(), new StartButton().visual());
        scene = new Scene(menu, stagedims[0], stagedims[1]);
        scene.getStylesheets().add(this.getClass().getResource("./MenuStyles.css").toExternalForm());
    }

    public Scene getScene() {
        return scene;
    }

}
