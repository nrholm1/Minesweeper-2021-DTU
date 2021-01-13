package Controller;

import View.GameScreen.SingleplayerView;
import View.MainMenuScreen.MainMenuView2;
import View.MenuScreen.MainMenuView;
import View.MultiPlayerMenu.MultiPlayerMenuView;
import View.SinglePlayerMenu.SingleplayerMenuView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationController {
    SingleplayerView spView;
//    MultiplayerView mpView;
    MainMenuView2 mainMenuView;
    SingleplayerMenuView spMenuView;
    MultiPlayerMenuView mpMenuView;

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

    public void gotoMultiplayerView() {
        // TODO change to mpView
        changeView(spView);
    }

    public void gotoMainMenuView() {
        changeView(mainMenuView);
    }


    public void gotoSingleplayerMenuView() {
        changeView(spMenuView);
    }

    public void gotoMultiplayerMenuView() {
        changeView(mpMenuView);
    }

    public void setSingleplayerView(SingleplayerView spView) {
        this.spView = spView;
    }

//    public void setMultiplayerView(MultiplayerView mpView) {
//        this.mpView = mpView;
//    }

    public void setSpView(SingleplayerView spView) {
        this.spView = spView;
    }

    public void setMpMenuView(MultiPlayerMenuView mpMenuView) {
        this.mpMenuView = mpMenuView;
    }

    public void setMainMenuView(MainMenuView2 mainMenuView) {
        this.mainMenuView = mainMenuView;
    }

    public void setSpMenuView(SingleplayerMenuView singleplayerMenuView) {
        this.spMenuView = singleplayerMenuView;
    }
}
