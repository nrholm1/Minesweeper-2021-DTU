package Networking;

// methods for connecting to other person and sending / receiving requests

import Controller.GameController;
import Services.ThreadManager;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public abstract class MultiplayerService {
    private static int port = 5050;
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

        server = HttpServer.create(new InetSocketAddress(5050), 0);
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

    public static void receiveIncomingRequest(FieldDTO dto) {
        oppGameController.updateTile(
                dto.getX(),
                dto.getY(),
                dto.getAction(),
                dto.getTileText()
        );
    }
}
