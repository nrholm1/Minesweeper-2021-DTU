import Controller.GameController;
import Controller.MainMenuController;
import Controller.NavigationController;
import Model.Board;
import Model.Util.BoardBuilder;
import View.GameScreen.SingleplayerView;
import View.GameScreen.Util.SingleplayerViewBuilder;
import View.MenuScreen.MainMenuView;
import javafx.application.Application;
import javafx.stage.Stage;


// Main class containing startup logic
// Goal is to initialize FX main thread and provide a base context for MVC and "plug in" the various
// components through dependency injection

public class Main extends Application {
    private final int stageWidth = 1000;
    private final int stageHeight = 600;

    // "Data storage"
    Board board;

    @Override
    public void start(Stage root) {
        root.setTitle("MineSwoop");

        MainMenuView mainMenuView = new MainMenuView(stageWidth, stageHeight);
        SingleplayerView singleplayerView = new SingleplayerViewBuilder()
                .withWidth((int)mainMenuView.getWidth())
                .withInsetSize(30)
                .withSize(mainMenuView.getSize())
                .build();
        // MultiplayerView multiplayerView

        NavigationController navigation = new NavigationController(root);
        navigation.setMainMenuView(mainMenuView);
        navigation.setSingleplayerView(singleplayerView);
        // navigation.setMultiplayerView(multiplayerView);

        // temp
        board = new BoardBuilder().withAmountMines(20)
                .withSideLength(mainMenuView.getSize() + 1)
                .build();
        // end temp

        GameController gameController = new GameController(navigation);
        gameController.setBoardModel(board);
        MainMenuController mainMenuController = new MainMenuController(navigation);

        singleplayerView.setController(gameController);
        mainMenuView.setController(mainMenuController);

        // temp
        mainMenuView.configureStartButton();
        // end temp

        // set initial scene to main menu screen
        root.setScene(mainMenuView);
        root.show();
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