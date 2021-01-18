package Networking;

// methods for connecting to other person and sending / receiving requests

import Controller.GameController;
import Services.ThreadManager;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class MultiplayerService {
    private String targetIp;
    private int port = 5050;
    private HttpServer server;
    private GameController oppGameController;

    public MultiplayerService(GameController ownGameController, GameController oppGameController, String ip) throws IOException {
        this.oppGameController = oppGameController;
        this.targetIp = ip;
        ownGameController.setMpService(this);

        startHttpListener();
    }

    // startHttpListener
    private void startHttpListener() throws IOException {
        System.out.println("Starting http listener");
        ThreadManager.stopServer();

        server = HttpServer.create(new InetSocketAddress(5050), 0);
        HttpListener listener = new HttpListener(this);

        server.createContext("/swoop", listener);
        server.setExecutor(null);
        server.start();

        ThreadManager.setServer(server);
    }

    // sendRequestAsync
    public void sendHttpRequest(FieldDTO fieldDTO) {
        System.out.println("adasd");
        ClientDriver.simpleAsyncRequestPrintFieldDTO(
                targetIp,
                port,
                fieldDTO
        );
    }

    public void receiveIncomingRequest(FieldDTO dto) {
        oppGameController.updateTile(
                dto.getX(),
                dto.getY(),
                dto.getAction(),
                dto.getTileText()
        );
    }
}
