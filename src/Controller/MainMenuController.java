package Controller;

public class MainMenuController {
    NavigationController navigation;

    // get from mainMenuView
    int difficulty;
    int size;
    // ---

    public MainMenuController(NavigationController _navigation) {
        navigation = _navigation;
    }

    public void beginGame() {
        navigation.gotoSingleplayerView();
    }
}
