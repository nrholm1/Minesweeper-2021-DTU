import View.MenuScreen.PregameMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private final int stageWidth = 1000;
    private final int stageHeight = 600;


    @Override
    public void start(Stage primaryStage) {
        Scene mainMenuScreen = new PregameMenu(stageWidth, stageHeight);

        Stage window = primaryStage;
        window.setTitle("MineSwoop");
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}