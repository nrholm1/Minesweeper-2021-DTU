package Services;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class GameTimer {
  private static ArrayList<Timer> timers = new ArrayList<Timer>();

  public static Timer getTimer() {
    Timer t = new Timer();
    timers.add(t);
    return t;
  }

  public static void stopTimers() {
    for (Timer timer : timers) {
      timer.cancel();
    }
  }

}
