package Services;

import Controller.GameController;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class Bot{
  private GameController controller;
  private Thread t;
  private boolean runThread = true;

  public Bot(GameController controller_) {
    this.controller = controller_;

    Task<Void> task = new Task<Void>() {
      @Override
      public Void call() throws Exception {

        System.out.println("Bgeun");
        Thread.sleep(2000);
        for(int i = 0; i < 10; i++) {
          int finalI = i;
          Platform.runLater(new Runnable() {
            @Override
            public void run() {
              controller.pressField(finalI, finalI);
              controller.updateTile(finalI, finalI);
            }
          });
          Thread.sleep((int)(Math.random()*1000+500));
        }
        return null ;
      }
    };

    task.setOnSucceeded(event -> {
      System.out.println("SOK");
    });

    task.setOnFailed(event -> {
      System.out.println(event);
    });

    t = new Thread(task);
    t.start();
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

  public void stop() {
    runThread = false;
  }
}
