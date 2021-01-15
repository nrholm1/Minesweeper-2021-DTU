package Services;

import Controller.GameController;

public class Bot implements Runnable{
  private GameController controller;
  private Thread t;
  private boolean runThread = true;

  public Bot(GameController controller_) {
    this.controller = controller_;
    start();
  }

  public void run() {
    int x = 0;
    int y = 0;
    try {
      while(runThread) {
        System.out.println("Making Decision");
        controller.pressField(x, y);
        controller.updateTile(x++, y++);
        Thread.sleep(500);
      }

    } catch(Exception e) {
      System.out.println(e);
    }
  }

  public void start() {
    t = new Thread(this, "Name");
    GameTimer.addBot(this);
    t.start();
  }

  public void stop() {
    runThread = false;
  }
}
