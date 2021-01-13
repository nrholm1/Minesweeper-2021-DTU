package Controller;

public class MainMenuController {
    NavigationController navigation;

    public MainMenuController(NavigationController _navigation) {
        navigation = _navigation;
    }

    public void gotoSingleplayerMenu() {
        navigation.gotoSingleplayerMenuView();
    }

    public void gotoMultiplayerMenu() {
        navigation.gotoMultiplayerMenuView();
    }

    public void beginSingleplayerGame() {
        navigation.gotoSingleplayerView();
    }

    public void beginMultiplayerGame() {
        navigation.gotoMultiplayerView();
    }

    public void gotoMainMenu() {
        navigation.gotoMainMenuView();
    }
}
