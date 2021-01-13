package sample.GameScreen;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class TimeCounter {

    private HBox counter;

    private Timer timer;
    private int timeElapsed = 0;

    public TimeCounter(){
        //Setup timer for top
        counter = new HBox();
        counter.setAlignment(Pos.CENTER);

        Text counterText = new Text("Time: 00:00");
        Font pixelfont = Font.loadFont(this.getClass().getResource("../PressStart2P-Regular.ttf").toExternalForm(), 16);
        counterText.setFont(pixelfont);
        counterText.setFill(Color.WHITE);

        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timeElapsed++;
                counterText.setText(calcTimeString(timeElapsed));
            }
        };
        timer.scheduleAtFixedRate(task, 1000l, 1000l);

        counter.getChildren().add(counterText);
    }
    public String calcTimeString(int time) {
        String stringTime = "Time: ";
        int secs = time;
        int mins = 0;

        if(secs > 3600) return "TOO LONG!";

        while(secs >= 60){
            secs -= 60;
            mins++;
        }

        stringTime += mins >= 10 ? mins : "0" + mins;
        stringTime += ":";
        stringTime += secs >= 10 ? secs : "0" + secs;

        return stringTime;
    }

    public HBox visual(){
        return counter;
    }
}
