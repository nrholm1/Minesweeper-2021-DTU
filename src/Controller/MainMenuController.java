package Controller;

public class MainMenuController {
    NavigationController navigation;
    GameController gameController;

    public MainMenuController(NavigationController _navigation) {
        navigation = _navigation;
    }

    public void gotoSingleplayerMenu() {navigation.gotoSingleplayerMenuView(); }

    public void gotoMultiplayerMenu() {
        navigation.gotoMultiplayerMenuView();
    }

    public void beginSingleplayerGame() {
        gameController.initializeMinefield();
        gameController.initializeBoardView();
        navigation.gotoSingleplayerView();
    }

    public void beginMultiplayerGame() {
        navigation.gotoMultiplayerView();
    }

    public void gotoMainMenu() {
        navigation.gotoMainMenuView();
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }
}
