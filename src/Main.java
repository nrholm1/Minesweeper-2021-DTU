import Controller.GameController;
import Controller.NavigationController;
import Services.ExternResources;
import Services.GameTimer;
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

    // Views
    MainMenuView2 mainMenuView;
    SingleplayerMenuView singleplayerMenuView;
    MultiPlayerMenuView multiplayerMenuView;

    // Controllers
    NavigationController navigation;
    GameController gameController;

    @Override
    public void start(Stage root) throws UnknownHostException {
        root.setTitle("MineSwoop");

        ExternResources.createResources();

        mainMenuView = new MainMenuView2(getStageDims());
        singleplayerMenuView = new SingleplayerMenuView(getStageDims());
        multiplayerMenuView = new MultiPlayerMenuView(getStageDims());

        initializeNavigation(root);

        // set initial scene to main menu screen
        root.setScene(mainMenuView);
        root.show();
    }


    void initializeNavigation(Stage root) {
        navigation.setRoot(root);
        navigation.setStageDims(getStageDims());
        navigation.setMainMenuView(mainMenuView);
        navigation.setSpMenuView(singleplayerMenuView);
        navigation.setMpMenuView(multiplayerMenuView);
    }



    @Override
    public void stop() {
        // TODO kill all spawned threads
        GameTimer.stopTimers();
        System.out.println("Stopping Application");

    }

    // TODO refactor to not use int[]
    public int[] getStageDims() {
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