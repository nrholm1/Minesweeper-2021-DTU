package Networking;

// methods for connecting to other person and sending / receiving requests

import Controller.GameController;
import Controller.NavigationController;
import Model.Field;
import Services.ThreadManager;
import com.sun.net.httpserver.HttpServer;
import javafx.application.Platform;

import java.io.IOException;
import java.net.InetSocketAddress;

public abstract class MultiplayerService {
    private static final int port = 5050;
    private static String targetIp;
    private static HttpServer server;
    private static GameController oppGameController;

    public static void initiateService(GameController oppGameController_, String ip) throws IOException {
        oppGameController = oppGameController_;
        targetIp = ip;

        startHttpListener();
    }

    // startHttpListener
    private static void startHttpListener() throws IOException {
        if(server != null) return;

        HttpListener listener = new HttpListener();

        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/swoop", listener);
        server.setExecutor(null);
        server.start();

        ThreadManager.setServer(server);
    }

    // sendRequestAsync
    public static void sendHttpRequest(FieldDTO fieldDTO) {
        ClientDriver.simpleAsyncRequestPrintFieldDTO(
                targetIp,
                port,
                fieldDTO
        );
    }

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

    public static void receiveIncomingRequest(FieldDTO dto) {
        String mpGameState = dto.getGameState();

        if (mpGameState.equals("W")) // opponent won
            Platform.runLater(NavigationController::goToDefeatScreen);
        if (mpGameState.equals("L")) // opponent lost
            Platform.runLater(() -> NavigationController
                    .goToVictoryScreen(oppGameController.getWinTime()));

        oppGameController.updateTile(
                dto.getX(),
                dto.getY(),
                dto.getAction(),
                dto.getTileText()
        );
    }
}
