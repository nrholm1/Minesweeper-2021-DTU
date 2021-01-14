package Networking;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HttpListener implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "response from server | ";

        long start = System.nanoTime();
        FieldDTO dto = Parser.readRequestInput(exchange
                .getRequestBody()
                .readAllBytes());
        response += dto.toString();
        // MultiplayerService.parseIncomingRequest(dto);
        long end = System.nanoTime();
        response += " | time elapsed: " + (end - start) + " ns";

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}