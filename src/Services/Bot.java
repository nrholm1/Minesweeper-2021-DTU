package Services;

import Controller.GameController;

public class Bot implements Runnable{
  GameController controller;
  Thread t;

  public Bot(GameController controller_) {
    this.controller = controller_;
    start();
  }

  public void run() {
    try {
      while(true) {
        System.out.println("Making Decision");
        Thread.sleep(500);

      }

    } catch(Exception e) {
      System.out.println("Something went wrong");
    }
  }

  public void start() {
    t = new Thread(this, "Bot");
    t.start();
  }
}
