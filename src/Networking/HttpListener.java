package Networking;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

// s204503 - Niels Raunkjær Holm
// This class is the 'server' part of the networking module.
// It implements the HttpHandler interface from the com.sun.net.httpserver package.
// HttpListener runs on its own thread and simply listens for incoming requests.
// When it receives a request, it reads the bytes and passes it to the parser for
// parsing the FieldDTO object contained in the payload.
// The DTO is then handled further in MultiplayerService.
// Lastly, a http response is built throughout this, containing the elapsed time
// and the 200 OK response code.

public class HttpListener implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "Response from server: ";

        long start = System.nanoTime();
        byte[] receivedBytes = exchange
                .getRequestBody()
                .readAllBytes();

        FieldDTO dto = Parser.readRequestInput(receivedBytes);

        response += dto.toString();

        MultiplayerService.receiveIncomingRequest(dto);
        long end = System.nanoTime();
        response += " | time elapsed: " + (end - start) + " ns";

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

}