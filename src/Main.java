import Controller.GameController;
import Model.Board;
import View.GameScreen.Game;
import View.MenuScreen.PregameMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


// Main class containing startup logic
// Goal is to initialize FX main thread and provide a base context for MVC and "plug in" the various
// components through dependency injection

public class Main extends Application {
    private final int stageWidth = 1000;
    private final int stageHeight = 600;

    private final int inset = 20;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MineSwoop");

        PregameMenu mainMenuScreen = new PregameMenu(stageWidth, stageHeight);

        GameController gameController = new GameController(primaryStage, mainMenuScreen);
    }

    @Override
    public void stop() {
        // TODO kill all spawned threads
        System.out.println("Stopping Application");
    }

    public static void main(String[] args) {
        launch(args);
    }
}