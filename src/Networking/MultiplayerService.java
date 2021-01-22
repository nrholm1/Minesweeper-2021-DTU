package Networking;

import Controller.GameController;
import Controller.NavigationController;
import Model.Field;
import Services.ThreadManager;
import com.sun.net.httpserver.HttpServer;
import javafx.application.Platform;

import java.io.IOException;
import java.net.InetSocketAddress;

// s204503 - Niels Raunkjær Holm, s204509 - Magnus Meyer
// Service class wrapping all networking methods and networking-to-game interfacing methods.

public abstract class MultiplayerService {
    private static final int port = 5050;
    private static String targetIp;
    private static HttpServer server;
    private static GameController oppGameController;

    // Replacement for constructor, since class is abstract
    public static void initiateService(GameController oppGameController_, String ip) throws IOException {
        oppGameController = oppGameController_;
        targetIp = ip;

        startHttpListener();
    }

    // Start thread for HttpListener and server
    private static void startHttpListener() throws IOException {
        if(server != null) return;

        HttpListener listener = new HttpListener();

        // Create server and context for serving the responses
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/swoop", listener);
        server.setExecutor(null);
        server.start();

        // Set reference to server process in the ThreadManager for clean teardown on application close
        ThreadManager.setServer(server);
    }

    // Wrapper method for using ClientDrivers request method with correct target ip and port
    public static void sendHttpRequest(FieldDTO fieldDTO) {
        ClientDriver.simpleAsyncRequestPrintFieldDTO(
                targetIp,
                port,
                fieldDTO
        );
    }

    // Create a FieldDTO from game event. Called from GameController when playing in multiplayer contexts
    public static FieldDTO createPayload(boolean gameWon, Field field) {
        String gameState = gameWon ? "W" :
                field.isMine() &&
                field.getState() == Field.State.PRESSED ? "L" :
                        " ";

        return new FieldDTO(
                field.getX(),
                field.getY(),
                field.getState(),
                field.getTileText(),
                gameState);
    }

    // Interfaces with GameController to handle the contents of the FieldDTO
    public static void receiveIncomingRequest(FieldDTO dto) {
        String mpGameState = dto.getGameState();

        // if game state has changed, go to a different screen
        // Platform.runLater schedules an action on the UI/FX thread.
        // This is necessary since the receiveIncomingRequest is called from the server thread
        if (mpGameState.equals("W")) // opponent won
            Platform.runLater(NavigationController::goToDefeatScreen);
        if (mpGameState.equals("L")) // opponent lost
            Platform.runLater(() -> NavigationController
                    .goToVictoryScreen(oppGameController.getWinTime(), false));

        oppGameController.updateTile(
                dto.getX(),
                dto.getY(),
                dto.getAction(),
                dto.getTileText()
        );
    }
}
