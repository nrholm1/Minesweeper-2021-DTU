package Controller;


import Model.Util.BoardBuilder;

public class MainMenuController {
    NavigationController navigation;

    int difficulty;
    int size;

    public MainMenuController(NavigationController _navigation) {
        navigation = _navigation;
    }

    public void beginGame() {
        navigation.gotoSingleplayerView();
    }
}
