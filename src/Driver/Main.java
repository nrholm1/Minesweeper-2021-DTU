package Driver;

import Controller.GameController;
import Controller.NavigationController;
import Services.ExternalResources;
import Services.ThreadManager;
import View.MainMenuView;
import View.MultiPlayerMenuView;
import View.SingleplayerMenuView;
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.net.UnknownHostException;

// Driver.Main class containing startup logic
// Goal is to initialize FX main thread and provide a base context for MVC and "plug in" the various
// components through dependency injection

public class Main extends Application {

    // Views
    MainMenuView mainMenuView;
    SingleplayerMenuView singleplayerMenuView;
    MultiPlayerMenuView multiplayerMenuView;

    @Override
    public void start(Stage root) throws UnknownHostException {
        root.setTitle("MineSweeper");

        ExternalResources.createResources();

        mainMenuView = new MainMenuView(getStageDims());
        singleplayerMenuView = new SingleplayerMenuView(getStageDims());
        multiplayerMenuView = new MultiPlayerMenuView(getStageDims());

        initializeNavigation(root);

        // set initial scene to main menu screen
        root.setScene(mainMenuView);
        root.show();
    }

    void initializeNavigation(Stage root) {
        NavigationController.setRoot(root);
        NavigationController.setStageDims(getStageDims());
        NavigationController.setMainMenuView(mainMenuView);
        NavigationController.setSpMenuView(singleplayerMenuView);
        NavigationController.setMpMenuView(multiplayerMenuView);
    }

    @Override
    public void stop() {
        ThreadManager.stopTimers();
        ThreadManager.stopServer();

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