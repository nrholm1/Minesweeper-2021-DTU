package View.MenuScreen;

import Controller.GameController;
import View.GameScreen.Game;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PregameMenu extends Scene{

    private VBox menu;

    private final String titleURL = "Images/premenu-title.png";
    private SizeSlider sizeSlider;
    private DiffSlider diffSlider;
    private StartButton startButton;


    public PregameMenu(int width, int height){
        super(new VBox(), width, height);

        menu = new VBox((double) height/20);
        menu.setAlignment(Pos.CENTER);
        menu.setId("menu");

        //Title
        ImageView title = new ImageView(new Image(titleURL));
        title.setFitWidth(600);
        title.setFitHeight(80);

        sizeSlider = new SizeSlider(width);
        diffSlider = new DiffSlider(width);
        startButton = new StartButton();

        menu.getChildren().addAll(title, sizeSlider, diffSlider, startButton);

        super.setRoot(menu);
        super.getStylesheets().add(this.getClass().getResource("./MenuStyles.css").toExternalForm());
    }

    public int getSize() {
        return sizeSlider.getSize();
    }

    public int getDifficulty() {
        return  diffSlider.getDifficulty();
    }

    public void setController(GameController controller) {
        startButton.setController(controller);
    }

}
