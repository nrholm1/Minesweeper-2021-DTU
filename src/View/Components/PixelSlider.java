package sample.Components;

import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.Main;

public class PixelSlider extends VBox{


    public PixelSlider(int[] stagedims, int[] vals, String type){
        super(12);
        //Size
        Slider slider = new Slider();
        slider.setMin(vals[0]);
        slider.setMax(vals[2]);
        slider.setMaxWidth(stagedims[0]/4);

        slider.setValue(vals[1]);
        if (type == "Size") {
            Main.setGamesize(vals[1]);
        } else if(type == "Difficulty"){
            Main.setDifficulty(vals[1]);
        }

        String initialText = type + ": " + slider.getValue();
        Text sliderText = new Text(initialText.substring(0,initialText.length() -2));
        Font pixelfont = Font.loadFont(this.getClass().getResource("../PressStart2P-Regular.ttf").toExternalForm(), 16);
        sliderText.setFont(pixelfont);
        sliderText.setFill(Color.WHITE);

        slider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            sliderText.setText("Size: " + newValue.intValue());
            if (type == "Size") {
                Main.setGamesize(newValue.intValue());
            } else if(type == "Difficulty"){
                Main.setDifficulty(newValue.intValue());
            }
        });

        setAlignment(Pos.CENTER);
        getChildren().addAll(sliderText, slider);

    }
}
