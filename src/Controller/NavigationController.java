package Controller;

import Model.Board;
import Model.Util.BoardBuilder;
import Networking.MultiplayerService;
import View.DefeatScreen.DefeatView;
import View.GameScreen.SingleplayerView;
import View.GameScreen.Util.SingleplayerViewBuilder;
import View.MainMenuScreen.MainMenuView2;
import View.MultiPlayerGameScreen.MultiPlayerView;
import View.MultiPlayerMenu.MultiPlayerMenuView;
import View.SinglePlayerMenu.SingleplayerMenuView;
import View.VictoryScreen.VictoryView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class NavigationController {

    private static MainMenuView2 mainMenuView;
    private static SingleplayerMenuView spMenuView;
    private static MultiPlayerMenuView mpMenuView;
    private static Stage root;
    private static int[] stageDims;
    private static MultiplayerService mpService;

    public static void setRoot(Stage _root) {
        root = _root;
    }

    private static void changeView(Scene _scene) {
        root.setScene(_scene);
    }

    public static void gotoMainMenuView() {
        changeView(mainMenuView);
    }

    public static void gotoSingleplayerMenuView() {
        changeView(spMenuView);
    }

    public static void gotoMultiplayerMenuView() {
        changeView(mpMenuView);
    }

    public static void setMpMenuView(MultiPlayerMenuView mpMenuView_) {
        mpMenuView = mpMenuView_;
    }

    public static void setMainMenuView(MainMenuView2 mainMenuView_) {
        mainMenuView = mainMenuView_;
    }

    public static void setSpMenuView(SingleplayerMenuView singleplayerMenuView) {
        spMenuView = singleplayerMenuView;
    }

    public static void setStageDims(int[] dims) {
        stageDims = dims;
    }

    // On demand initialization
    public static void createSingleplayerGame() {
        SingleplayerView singleplayerView = new SingleplayerViewBuilder()
                .withStageDims(stageDims)
                .withInsetSize(30)
                .withSize(spMenuView.getSize())
                .build();

        Board b = new BoardBuilder()
                .withSize(spMenuView.getSize())
                .withAmountMines(spMenuView.getDifficulty() * 5)
                .build();

        GameController gameController = new GameController(b , singleplayerView.getBoardView());

        changeView(singleplayerView);
    }

    public static void createMultiplayerGame() throws IOException {
        MultiPlayerView multiPlayerView = new MultiPlayerView(stageDims);

        Board board = new BoardBuilder()
                .withSize(10)
                .withAmountMines(50)
                .build();

        GameController ownGameController = new GameController(board, multiPlayerView.getPlayer1View());
        GameController oppGameController = new GameController(multiPlayerView.getPlayer2View());
        MultiplayerService mpService = new MultiplayerService(ownGameController, oppGameController, mpMenuView.getIp());


        changeView(multiPlayerView);
    }


    public static void goToVictoryScreen(int seconds) {
        VictoryView victory = new VictoryView(stageDims, seconds);
        changeView(victory);
    }

    public static void goToDefeatScreen() {
        changeView(new DefeatView(stageDims));
    }
}
