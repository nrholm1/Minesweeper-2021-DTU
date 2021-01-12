package View.MenuScreen;

import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class DiffSlider extends VBox{

    static final String diffUrl = "Images/diff-icon.png";
    private Slider diffSlider;

    public DiffSlider(int width){
        super(12);
        //Difficulty
        diffSlider = new Slider();
        diffSlider.setMin(0);
        diffSlider.setMax(10);
        diffSlider.setMaxWidth(width/4);

        diffSlider.setValue(5);

        String initialText = "Difficulty: " + diffSlider.getValue();
        Text diffValueText = new Text(initialText.substring(0,initialText.length() -2));
        Font pixelfont = Font.loadFont(this.getClass().getResource("../PressStart2P-Regular.ttf").toExternalForm(), 16);
        diffValueText.setFont(pixelfont);
        diffValueText.setFill(Color.WHITE);

        diffSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            diffValueText.setText("Difficulty: " + newValue.intValue());
        });

        super.setAlignment(Pos.CENTER);
        super.getChildren().addAll(diffValueText, diffSlider);
    }

    public int getDifficulty() {
        return (int)diffSlider.getValue();
    }

}
