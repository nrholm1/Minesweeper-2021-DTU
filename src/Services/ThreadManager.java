package Services;

import com.sun.net.httpserver.HttpServer;

//TODO maybe only http listener, so maybe call something else
public abstract class ThreadManager {
    static HttpServer server;

    public static void stopServer() {
        server.stop(1);
    }

    public static void setServer(HttpServer _server) {
        server = _server;
    }
}
