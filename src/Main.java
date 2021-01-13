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
    // "Data storage" of Models
    Board board;

    // Views
    MainMenuView mainMenuView;
    SingleplayerView singleplayerView;
//    MultiplayerView multiplayerView;

    // Controllers
    NavigationController navigation;
    MainMenuController mainMenuController;
    GameController gameController;

    @Override
    public void start(Stage root) {
        root.setTitle("MineSwoop");

        initializeMainMenuView();
        initializeSingleplayerView();
        // initializeMultiplayerView();

        initializeNavigation(root);

        initializeBoard();

        initializeGameController();
        initializeMainMenuController();

        linkComponents();

        // set initial scene to main menu screen
        root.setScene(mainMenuView);
        root.show();
    }

    void initializeMainMenuView() {
        int stageWidth = 1000;
        int stageHeight = 600;
        mainMenuView = new MainMenuView(stageWidth, stageHeight);
    }

    void initializeSingleplayerView() {
        singleplayerView = new SingleplayerViewBuilder()
                .withWidth((int)mainMenuView.getWidth())
                .withInsetSize(30)
                .withSize(mainMenuView.getSize())
                .build();
    }

    void initializeNavigation(Stage root) {
        navigation = new NavigationController(root);
        navigation.setMainMenuView(mainMenuView);
        navigation.setSingleplayerView(singleplayerView);
        // navigation.setMultiplayerView(multiplayerView);
    }

    void initializeBoard() {
        // temp
        board = new BoardBuilder().withAmountMines(20)
                .withSideLength(mainMenuView.getSize() + 1)
                .build();
        // end temp
    }

    void initializeGameController() {
        gameController = new GameController(navigation);
        gameController.setBoardModel(board);
    }

    void initializeMainMenuController() {
        mainMenuController = new MainMenuController(navigation);
    }

    private void linkComponents() {
        singleplayerView.setController(gameController);
        mainMenuView.setController(mainMenuController);

        // temp
        mainMenuView.configureStartButton();
        // end temp
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