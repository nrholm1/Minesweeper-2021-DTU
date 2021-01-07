package View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
//import sample.Main;

public class MainMenu {

  static Scene scene;
  static VBox menu;

  static final String titleURL = "Images/title.png";
  static final String startUrl = "Images/startgame-button.png";
  static final String startHoverUrl = "Images/startgame-button-hover.png";
  static final String sizeUrl = "Images/size-icon.png";
  static final String diffUrl = "Images/diff-icon.png";

  public MainMenu(int[] stagedims){
    menu = new VBox(stagedims[1]/12);
    menu.setAlignment(Pos.CENTER);

    //Title
    ImageView title = new ImageView(new Image(titleURL));
    title.setFitWidth(400);
    title.setFitHeight(100);



    //Size
    Slider sizeSlider = new Slider();
    sizeSlider.setMin(10);
    sizeSlider.setMax(50);
    sizeSlider.setShowTickLabels(true);
    sizeSlider.setMaxWidth(stagedims[0]/4);

    sizeSlider.setMajorTickUnit(20);
    sizeSlider.setValue(30);
    //Main.setGamesize(30);

    Label sizeValueText = new Label("" + sizeSlider.getValue());
    sizeValueText.setStyle("-fx-font-size: 16px; -fx-text-fill: green;");

    sizeSlider.valueProperty().addListener(new ChangeListener<Number>() {

      @Override
      public void changed(
              ObservableValue<? extends Number> observableValue,
              Number oldValue,
              Number newValue) {
        sizeValueText.setText("" + newValue.intValue());
        //Main.setGamesize(newValue.intValue());
        System.out.println(newValue.intValue());
      }
    });

    ImageView sizePic = new ImageView(new Image(sizeUrl));
    sizePic.setFitWidth(50);
    sizePic.setFitHeight(25);

    HBox sizeTitle = new HBox(0);
    sizeTitle.setAlignment(Pos.CENTER);
    sizeTitle.getChildren().addAll(sizePic,sizeValueText);

    VBox sizeElement = new VBox(12);
    sizeElement.setAlignment(Pos.CENTER);
    sizeElement.getChildren().addAll(sizeTitle, sizeSlider);



    //Difficulty
    Slider diffSlider = new Slider();
    diffSlider.setMin(0);
    diffSlider.setMax(10);
    diffSlider.setShowTickLabels(true);
    diffSlider.setMaxWidth(stagedims[0]/4);

    diffSlider.setMajorTickUnit(5);
    diffSlider.setValue(5);
    //Main.setDifficulty(5);

    Label diffValueText = new Label("" + diffSlider.getValue());
    diffValueText.setStyle("-fx-font-size: 16px; -fx-text-fill: green;");

    diffSlider.valueProperty().addListener(new ChangeListener<Number>() {

      @Override
      public void changed(
              ObservableValue<? extends Number> observableValue,
              Number oldValue,
              Number newValue) {
        diffValueText.setText("" + newValue.intValue());
        //Main.setDifficulty(newValue.intValue());
        System.out.println(newValue.intValue());
      }
    });

    ImageView diffPic = new ImageView(new Image(diffUrl));
    diffPic.setFitWidth(100);
    diffPic.setFitHeight(25);

    HBox diffTitle = new HBox(0);
    diffTitle.setAlignment(Pos.CENTER);
    diffTitle.getChildren().addAll(diffPic,diffValueText);

    VBox diffElement = new VBox(12);
    diffElement.setAlignment(Pos.CENTER);
    diffElement.getChildren().addAll(diffTitle, diffSlider);



    //Start
    Rectangle startbutton = new Rectangle(200,50);
    startbutton.setFill(new ImagePattern(new Image(startUrl)));
    startbutton.setOnMouseEntered(e -> startbutton.setFill(new ImagePattern(new Image(startHoverUrl))));
    startbutton.setOnMouseExited(e -> startbutton.setFill(new ImagePattern(new Image(startUrl))));
    //startbutton.setOnMouseClicked(e -> {Main.collectGameScene(); Main.goToScene("game");});

    menu.getChildren().addAll(title, sizeElement, diffElement, startbutton);
    scene = new Scene(menu, stagedims[0], stagedims[1]);
  }

  public Scene getScene() {
    return scene;
  }
}
