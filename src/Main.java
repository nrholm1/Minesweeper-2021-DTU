import Controller.GameController;
import View.GameScreen.Game;
import View.MenuScreen.PregameMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private final int stageWidth = 1000;
    private final int stageHeight = 600;

    private final int inset = 20;


    @Override
    public void start(Stage primaryStage) {
        PregameMenu mainMenuScreen = new PregameMenu(stageWidth, stageHeight);
        Game gameView = new Game(stageWidth, inset, 7);

        Stage window = primaryStage;
        window.setTitle("MineSwoop");

        GameController controller = new GameController(primaryStage, mainMenuScreen, gameView);
    }

    @Override
    public void stop() {
        System.out.println("Stopping Application");
    }

    public static void main(String[] args) {
        launch(args);
    }
}