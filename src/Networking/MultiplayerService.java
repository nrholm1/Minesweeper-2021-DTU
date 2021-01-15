package Networking;

// methods for connecting to other person and sending / receiving requests

import Controller.GameController;
import Controller.MultiplayerController;
import Model.Field;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class MultiplayerService {
    MultiplayerController mpController;

    String targetIp;
    int port = 5050;

    // startHttpListener
    public void startHttpListener() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(5050), 0);
        HttpListener listener = new HttpListener();
        listener.setMpService(this);
        server.createContext("/swoop", listener);
        server.setExecutor(null);
        server.start();
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
        this.mpController.receiveEvent(dto);
    }

    // setTargetIp
    public void setTargetIpAdress(String ipAdress) {
        targetIp = ipAdress;
    }

    public void setMpController(MultiplayerController mpController) {
        this.mpController = mpController;
    }

    // teardown methods?




    // ---Methods for testing---

    public static void main(String[] args) throws IOException, InterruptedException {
        MultiplayerService service = new MultiplayerService();
        service.startHttpListener();
        service.targetLocalIp();
        int counter = 0;
        while(counter < 100) {
            service.sendRandomHttpRequest();
            Thread.sleep(1000);
            counter++;
        }
    }

    void sendRandomHttpRequest() {
        FieldDTO dto = createRandomDTO();
        System.out.println(dto);
        sendHttpRequest(createRandomDTO());
    }

    public void targetLocalIp() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        targetIp = addr.getHostAddress();
    }

    FieldDTO createRandomDTO() {
        return new FieldDTO((int)(Math.random() * 70),
                (int)(Math.random() * 70),
                Math.random() >= 0.5 ?
                        Field.State.FLAGGED :
                        Field.State.PRESSED,
                "" + (int)(Math.random() * 9));
    }
}
