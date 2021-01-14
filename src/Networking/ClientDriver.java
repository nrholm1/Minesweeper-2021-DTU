package Networking;

import Model.Field;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ClientDriver {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Field[] fields = new Field[] { new Field(5,2),
                new Field(3,7),
                new Field(32,26),
                new Field(32,26),
                new Field(32,26),
                new Field(17,9)};

        System.out.println("--print--");
        testPrintField(fields);
        System.out.println("--get--");
        testGetField(fields);
    }

    static void testGetField(Field[] fields) throws InterruptedException, ExecutionException {
        ArrayList<String> responses = new ArrayList<>();

        // executes synchronously I think?
        for (Field field : fields)
            responses.add(simpleAsyncRequestGetField("localhost", 5050, field).get());

        for (String s : responses)
            System.out.println(s);

        Thread.sleep(1000);
    }

    static void testPrintField(Field[] fields) throws InterruptedException {
        // executes asynchronously I think?
        for (Field field : fields)
            simpleAsyncRequestPrintField("localhost", 5050, field);

        Thread.sleep(1000);
    }

    // SYNC
    // "await" makes it work sync
    static CompletableFuture<String> simpleAsyncRequestGetField(String IP, int PORT, Field field) {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + IP + ":" + PORT + "/test"))
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofByteArray(field.toBytes()))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
    }

    // ASYNC
    // chained methods after sendAsync makes it work async
    static void simpleAsyncRequestPrintField(String IP, int PORT, Field field) {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://" + IP + ":" + PORT + "/test"))
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofByteArray(field.toBytes()))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept((System.out::println));
    }
}