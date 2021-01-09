package View.MenuScreen;

import com.sun.glass.ui.Size;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Menu extends VBox {
  private final String titleURL = "Images/premenu-title.png";
  private SizeSlider sizeSlider;
  private DiffSlider diffSlider;
  private StartButton startButton;

  public Menu(int width, int height) {
    super((double) height/20);
    super.setAlignment(Pos.CENTER);
    super.setId("menu");

    //Title
    ImageView title = new ImageView(new Image(titleURL));
    title.setFitWidth(600);
    title.setFitHeight(80);

    sizeSlider = new SizeSlider(width);
    diffSlider = new DiffSlider(width);
    startButton = new StartButton();

    super.getChildren().addAll(title, sizeSlider, diffSlider, startButton);
  }
}
