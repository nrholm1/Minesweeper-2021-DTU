package View.Components;

import Services.ExternResources;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PixelSlider extends VBox{
    private Slider slider;

    public PixelSlider(int[] stageDims, int[] vals, String type){
        super(12);
        //Size
        slider = new Slider();
        slider.setMin(vals[0]);
        slider.setMax(vals[2]);
        slider.setMaxWidth(stageDims[0]/4.0);

        slider.setValue(vals[1]);
        if (type.equals("Size")) {
            setGameSize(vals[1]);
        } else if(type.equals("Difficulty")){
            setDifficulty(vals[1]);
        }

        String initialText = type + ": " + slider.getValue();
        Text sliderText = new Text(initialText.substring(0,initialText.length() -2));
        Font pixelfont = Font.loadFont(ExternResources.pixelFontResource, 16);
        sliderText.setFont(pixelfont);
        sliderText.setFill(Color.WHITE);

        slider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            if (type.equals("Size")) {
                sliderText.setText("Size: " + newValue.intValue());
                setGameSize(newValue.intValue());
            } else if(type.equals("Difficulty")){
                sliderText.setText("Difficulty: " + newValue.intValue());
                setDifficulty(newValue.intValue());
            }
        });

        setAlignment(Pos.CENTER);
        getChildren().addAll(sliderText, slider);
    }


    private void setDifficulty(int val) {
        System.out.println("Difficulty set to " + val);
    }

    private void setGameSize(int val) {
        System.out.println("Game size set to " + val);
    }

    public int getSize() { return (int)slider.getValue(); }
}
