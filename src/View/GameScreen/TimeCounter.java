package View.GameScreen;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

public class TimeCounter {

    private HBox counter;

    private Timer timer;
    private int timeElapsed = 0;

    private static String timeUrl = "Images/counter-icon.png";

    public TimeCounter(){
        //Setup timer for top
        counter = new HBox();
        counter.setAlignment(Pos.CENTER);

        ImageView timePic = new ImageView(new Image(timeUrl));
        timePic.setFitWidth(60);
        timePic.setFitHeight(30);

        Text counterText = new Text("00:00");
        counterText.setStyle("-fx-font-size: 16px;");
        counterText.setFill(Color.GREEN);

        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timeElapsed++;
                counterText.setText(calcTimeString(timeElapsed));
            }
        };
        timer.scheduleAtFixedRate(task, 1000l, 1000l);

        counter.getChildren().addAll(timePic, counterText);
    }
    public String calcTimeString(int time) {
        String stringTime = "";
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
