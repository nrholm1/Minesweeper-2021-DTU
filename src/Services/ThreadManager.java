package Services;

import com.sun.net.httpserver.HttpServer;

import java.util.ArrayList;
import java.util.Timer;

public abstract class ThreadManager {
    private static HttpServer server;
    private static final ArrayList<Timer> timers = new ArrayList<>();

    public static Timer getTimer() {
        Timer t = new Timer();
        timers.add(t);
        return t;
    }

    public static void stopTimers() {
        for (Timer timer : timers)
            timer.cancel();
    }

    public static void stopServer() {
        if (server != null)
            server.stop(1);
    }

    public static void setServer(HttpServer _server) {
        server = _server;
    }
}
