package Networking;

// methods for connecting to other person and sending / receiving requests

import Controller.MultiplayerController;
import Model.Field;
import Services.ThreadManager;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class MultiplayerService {
    MultiplayerController mpController;

    String targetIp;
    int port = 5050;
    HttpServer server;

    // startHttpListener
    public void startHttpListener() throws IOException {
        System.out.println("Starting http listener");

        if (server != null)
            return;
        server = HttpServer.create(new InetSocketAddress(5050), 0);
        HttpListener listener = new HttpListener();
        listener.setMpService(this);
        server.createContext("/swoop", listener);
        server.setExecutor(null);
        server.start();

        ThreadManager.setServer(server);
    }

    public void stopHttpListener() {
        server.stop(1000);
    }

    // sendRequestAsync
    public void sendHttpRequest(FieldDTO fieldDTO) {
        ClientDriver.simpleAsyncRequestPrintFieldDTO(
                targetIp,
                port,
                fieldDTO
        );
    }

    public void receiveIncomingRequest(FieldDTO dto) {
        if (this.mpController != null)
            this.mpController.receiveEvent(dto);
    }

    // setTargetIp
    public void setTargetIpAdress(String ipAdress) {
        targetIp = ipAdress;
    }

    public void setMpController(MultiplayerController mpController) {
        this.mpController = mpController;
    }

}
