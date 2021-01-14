import Controller.GameController;
import Controller.MainMenuController;
import Controller.NavigationController;
import Model.Board;
import Model.Util.BoardBuilder;
import View.GameScreen.SingleplayerView;
import View.GameScreen.Util.SingleplayerViewBuilder;
import View.MainMenuScreen.MainMenuView2;
import View.MultiPlayerMenu.MultiPlayerMenuView;
import View.SinglePlayerMenu.SingleplayerMenuView;
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.UnknownHostException;


// Main class containing startup logic
// Goal is to initialize FX main thread and provide a base context for MVC and "plug in" the various
// components through dependency injection

public class Main extends Application {
    // "Data storage" of Models
    Board ownBoard;
    Board opponentBoard;

    // Views
    MainMenuView2 mainMenuView;
    SingleplayerView singleplayerView;
//    MultiplayerView multiplayerView;
    SingleplayerMenuView singleplayerMenuView;
    MultiPlayerMenuView multiplayerMenuView;

    // Controllers
    NavigationController navigation;
    MainMenuController mainMenuController;
    GameController gameController;

    @Override
    public void start(Stage root) throws UnknownHostException {
        root.setTitle("MineSwoop");

        initializeMainMenuView();
        initializeSingleplayerMenu();
        initializeMultiplayerMenu();
        initializeSingleplayerView();
        initializeMultiplayerView();

        initializeNavigation(root);

        initializeBoard();

        initializeGameController();
        initializeMainMenuController();

        linkControllersToViews();

        // set initial scene to main menu screen
        root.setScene(mainMenuView);
        root.show();
    }

    void initializeMainMenuView() {
        mainMenuView = new MainMenuView2(getStageDims());
    }

    void initializeSingleplayerMenu() {
        singleplayerMenuView = new SingleplayerMenuView(getStageDims());
    }

    void initializeMultiplayerMenu() throws UnknownHostException {
        multiplayerMenuView = new MultiPlayerMenuView(getStageDims());
    }

    void initializeSingleplayerView() {
        // hardcoded right now, but should be set by the sliders in spMenuView
        singleplayerView = new SingleplayerViewBuilder()
                .withStageDims(getStageDims())
                .withInsetSize(30)
                .withSize(5)
                .build();
    }

    void initializeMultiplayerView() {}

    void initializeNavigation(Stage root) {
        navigation = new NavigationController(root);
        navigation.setMainMenuView(mainMenuView);
        navigation.setSpMenuView(singleplayerMenuView);
        navigation.setMpMenuView(multiplayerMenuView);
        navigation.setSingleplayerView(singleplayerView);
        // navigation.setMultiplayerView(multiplayerView);
    }

    void initializeBoard() {
        // temp
        ownBoard = new BoardBuilder()
                .withSize(5)
                .withAmountMines(26) // Bruh
                .build();
        // end temp
    }

    void initializeGameController() {
        gameController = new GameController(navigation);
        gameController.setBoardModel(ownBoard);
        gameController.setBoardView(
                // TODO this is temp, and should be different if multiplayer
                singleplayerView.getBoardView()
        );
    }

    void initializeMainMenuController() {
        mainMenuController = new MainMenuController(navigation);
        mainMenuController.setGameController(gameController);
    }

    void linkControllersToViews() {
        singleplayerView.setController(gameController);
        mainMenuView.setController(mainMenuController);
        singleplayerMenuView.setController(mainMenuController);
        multiplayerMenuView.setController(mainMenuController);
    }

    @Override
    public void stop() {
        // TODO kill all spawned threads
        System.out.println("Stopping Application");

    }

    // TODO refactor to not use int[]
    int[] getStageDims() {
        return new int[] {
            (int) Screen
                    .getPrimary()
                    .getVisualBounds()
                    .getWidth(),
            (int) Screen
                    .getPrimary()
                    .getVisualBounds()
                    .getHeight() - 35
        };
    }

    public static void main(String[] args) {
        launch(args);
    }
}