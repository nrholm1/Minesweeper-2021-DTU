package View.Components;

import Services.ExternalResources;
import Services.ThreadManager;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class TimeCounter extends HBox{

    private Timer timer;
    private int timeElapsed = 0;

    public TimeCounter(){
        //Setup timer for top
        super();
        super.setAlignment(Pos.CENTER);

        Text counterText = new Text("Time: 00:00");
        Font pixelFont = ExternalResources.pixelFont16;
        counterText.setFont(pixelFont);
        counterText.setFill(Color.WHITE);

        timer = ThreadManager.getTimer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timeElapsed++;
                counterText.setText(calcTimeString(timeElapsed));
            }
        };
        timer.scheduleAtFixedRate(task, 1000L, 1000L);

        super.getChildren().addAll(counterText);
    }

    private String calcTimeString(int time) {
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

    public int getTime() { return timeElapsed; }
}
