package Networking;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

class ServerDriver {
    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(5050), 0);
        server.createContext("/test", new Handler());
        server.setExecutor(null);
        server.start();
    }

    static class Handler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "response from server | ";

            long start = System.nanoTime();
            response += Parser.readRequestInput(exchange
                                .getRequestBody()
                                .readAllBytes())
                                .toString();
            long end = System.nanoTime();
            response += " | time elapsed: " + (end - start) + " ns";

            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}