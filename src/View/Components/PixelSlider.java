package View.Components;

import Services.ExternalResources;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

//Skrevet af s204508 Massimo Hansen
public class PixelSlider extends VBox{
    private final Slider slider;

    public PixelSlider(int[] stageDims, int[] vals, String type){
        super(12);

        slider = new Slider();
        slider.setMin(vals[0]);
        slider.setValue(vals[1]);
        slider.setMax(vals[2]);
        slider.setMaxWidth(stageDims[0]/4.0);

        //Creates the text on top of slider
        String initialText = type + ": " + slider.getValue();
        Text sliderText = new Text(initialText.substring(0,initialText.length() -2));
        Font pixelFont = ExternalResources.pixelFont16;
        sliderText.setFont(pixelFont);
        sliderText.setFill(Color.WHITE);

        //Get value from slider
        slider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (type.equals("Size")) {
                sliderText.setText("Size: " + newValue.intValue());
            } else if(type.equals("Difficulty")){
                sliderText.setText("Difficulty: " + newValue.intValue());
            }
        });

        setAlignment(Pos.CENTER);
        getChildren().addAll(sliderText, slider);
    }

    public int getSize() { return (int)slider.getValue(); }
}
