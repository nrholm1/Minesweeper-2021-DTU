package View.MenuScreen;

import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.Main;

public class DiffSlider {

    static VBox diffElement;

    static final String diffUrl = "Images/diff-icon.png";

    public DiffSlider(int[] stagedims){
        //Difficulty
        Slider diffSlider = new Slider();
        diffSlider.setMin(0);
        diffSlider.setMax(10);
        diffSlider.setMaxWidth(stagedims[0]/4);

        diffSlider.setValue(5);
        Main.setDifficulty(5);

        String initialText = "Difficulty: " + diffSlider.getValue();
        Text diffValueText = new Text(initialText.substring(0,initialText.length() -2));
        Font pixelfont = Font.loadFont(this.getClass().getResource("../PressStart2P-Regular.ttf").toExternalForm(), 16);
        diffValueText.setFont(pixelfont);
        diffValueText.setFill(Color.WHITE);

        diffSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            diffValueText.setText("Difficulty: " + newValue.intValue());
            Main.setDifficulty(newValue.intValue());
        });

        diffElement = new VBox(12);
        diffElement.setAlignment(Pos.CENTER);
        diffElement.getChildren().addAll(diffValueText, diffSlider);
    }

    public VBox visual(){
        return diffElement;
    }
}
