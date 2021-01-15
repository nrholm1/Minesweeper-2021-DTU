package Networking;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HttpListener implements HttpHandler {
    MultiplayerService mpService;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "response from server | ";

        long start = System.nanoTime();
        FieldDTO dto = Parser.readRequestInput(exchange
                .getRequestBody()
                .readAllBytes());
        response += dto.toString();
        mpService.receiveIncomingRequest(dto);
        long end = System.nanoTime();
        response += " | time elapsed: " + (end - start) + " ns";

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public void setMpService(MultiplayerService mpService) {
        this.mpService = mpService;
    }
}