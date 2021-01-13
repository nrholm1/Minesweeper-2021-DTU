package Controller;

import View.GameScreen.SingleplayerView;
import View.MenuScreen.MainMenuView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationController {
    SingleplayerView spView;
//    MultiplayerView mpView;
    MainMenuView mainMenuView;

    Stage root;

    public NavigationController(Stage _root) {
        root = _root;
    }

    public void changeView(Scene _scene) {
        root.setScene(_scene);
    }


    public void gotoSingleplayerView() {
        changeView(spView);
    }

    public void gotoMainMenuView() {
        changeView(mainMenuView);
    }

    public SingleplayerView getSingleplayerView() {
        return spView;
    }

//    public MultiplayerView getMultiplayerView() {
//        return mpView;
//    }

    public MainMenuView getMainMenuView() {
        return mainMenuView;
    }

    public void setSingleplayerView(SingleplayerView spView) {
        this.spView = spView;
    }

//    public void setMultiplayerView(MultiplayerView mpView) {
//        this.mpView = mpView;
//    }

    public void setMainMenuView(MainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
    }
}
