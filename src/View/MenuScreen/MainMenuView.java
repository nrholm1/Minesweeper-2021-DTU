package View.MenuScreen;

import Controller.GameController;
import Controller.MainMenuController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MainMenuView extends Scene{
    private VBox menu;

    MainMenuController controller;

    private final String titleURL = "Images/premenu-title.png";
    private SizeSlider sizeSlider;
    private DiffSlider diffSlider;
    private StartButton startButton;
    private int width;
    private int height;

    public MainMenuView(int width, int height){
        super(new VBox(), width, height);
        this.width = width;
        this.height = height;

        menu = new VBox((double) height/20);
        menu.setAlignment(Pos.CENTER);
        menu.setId("menu");

        ImageView title = new ImageView(new Image(titleURL));
        title.setFitWidth(600);
        title.setFitHeight(80);

        sizeSlider = new SizeSlider(width);
        diffSlider = new DiffSlider(width);
        startButton = new StartButton();

        menu.getChildren().addAll(title, sizeSlider, diffSlider, startButton);

        super.setRoot(menu);
        super.getStylesheets()
             .add(this.getClass()
                      .getResource("./MenuStyles.css")
                      .toExternalForm());
    }

    // TODO magic numbers?
    public int getSize() {
        return (int)((sizeSlider.getSize() - 10)*2.5/100*12 + 3);
    }

    public int getDifficulty() {
        return diffSlider.getDifficulty();
    }

    public void setController(MainMenuController _controller) {
        controller = _controller;
    }

    // TODO refactor?
    public void configureStartButton() {
        startButton.setController(controller);
    }
}
