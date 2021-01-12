package View.MenuScreen;

import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SizeSlider extends VBox{

    private final String sizeUrl = "Images/size-icon.png";
    private Slider sizeSlider;

    public SizeSlider(int width){
        super(12);

        //Size
        sizeSlider = new Slider();
        sizeSlider.setMin(10);
        sizeSlider.setMax(50);
        sizeSlider.setMaxWidth(width/4);

        sizeSlider.setValue(30);

        String initialText = "Size: " + sizeSlider.getValue();
        Text sizeValueText = new Text(initialText.substring(0,initialText.length() -2));
        Font pixelfont = Font.loadFont(this.getClass().getResource("../PressStart2P-Regular.ttf").toExternalForm(), 16);
        sizeValueText.setFont(pixelfont);
        sizeValueText.setFill(Color.WHITE);

        sizeSlider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            sizeValueText.setText("Size: " + newValue.intValue());
        });

        super.setAlignment(Pos.CENTER);
        super.getChildren().addAll(sizeValueText, sizeSlider);

    }

    public int getSize() {
        return (int)sizeSlider.getValue();
    }
}
