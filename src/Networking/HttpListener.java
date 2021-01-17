package Networking;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;

public class HttpListener implements HttpHandler {
    MultiplayerService mpService;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "Response from server | ";

        long start = System.nanoTime();
        byte[] receivedBytes = exchange
                    .getRequestBody()
                    .readAllBytes();

        FieldDTO dto = Parser.readRequestInput(receivedBytes);

        response += dto.toString();

        if (mpService != null)
            mpService.receiveIncomingRequest(dto);
        long end = System.nanoTime();
        response += " | time elapsed: " + (end - start) + " ns";

        exchange.sendResponseHeaders(200, response.length());
//        OutputStream os = exchange.getResponseBody();
////        os.write(response.getBytes());
//        os.close();
    }

    public void setMpService(MultiplayerService mpService) {
        this.mpService = mpService;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Starting http listener");

        HttpServer server = HttpServer.create(new InetSocketAddress(5050), 0);
        HttpListener listener = new HttpListener();
        server.createContext("/swoop", listener);
        server.setExecutor(null);
        server.start();
    }
}