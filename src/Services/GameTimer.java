package Services;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class GameTimer {
  private static ArrayList<Timer> timers = new ArrayList<Timer>();
  private static ArrayList<Bot> bots = new ArrayList<Bot>();

  public static Timer getTimer() {
    Timer t = new Timer();
    timers.add(t);
    return t;
  }

  public static void stopTimers() {
    for(int i = 0; i < timers.size(); i++) {
      timers.get(i).cancel();
    }
    stopThreads();
  }

  public static void addBot(Bot bot) {
    bots.add(bot);
  }

  public static void stopThreads() {
    for(int i = 0; i < bots.size(); i++) {
      bots.get(i).stop();
    }
  }

}
