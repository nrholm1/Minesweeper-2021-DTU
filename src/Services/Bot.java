package Services;

import Controller.GameController;
import Model.Board;
import Model.Field;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class Bot{
  private GameController controller;
  private Board b;
  private Thread t;
  private boolean runThread = true;

  public Bot(GameController controller_, Board b) {
    this.controller = controller_;
    this.b = b;

    Task<Void> task = new Task<Void>() {
      @Override
      public Void call() throws Exception {

        System.out.println("Bgeun");
        Thread.sleep(2000);
        for(int i = 0; i < 100; i++) {
          Platform.runLater(new Runnable() {
            @Override
            public void run() {
              makeNewChoice();
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

  public void stop() {
    runThread = false;
  }

  public void makeNewChoice() {
    Field f;
    do {
      f = b.getRandomField();
    } while(!madeChoide(f));
    controller.pressField(f.getX(), f.getY());
    controller.updateTile(f.getX(), f.getY());
  }

  public boolean madeChoide(Field f) {
    if(f.isMine() || f.getState() == Field.State.PRESSED) return false;
    return true;
  }


}
